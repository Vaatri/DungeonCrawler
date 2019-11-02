package unsw.dungeon;

import java.util.ArrayList;
import java.util.Optional;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Immovable,Subject{

    private Dungeon dungeon;
    private Inventory inven;
    private int lives;
    PlayerState swordState;
    PlayerState potionState;
    PlayerState emptyHandState;
    PlayerState state;

    ArrayList<Observer> listObservers = new ArrayList<Observer>();
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inven = new Inventory();
        this.lives = 4;
        swordState = new SwordState(this);
        potionState = new PotionState(this , null);
        emptyHandState = new EmptyHandState(this);
        state = emptyHandState;
        addObserverList();
    }
    public void addObserverList() {
    	// add all the enemies on the dungeon in observer list;
    	for (Entity e: dungeon.getEntitiesList()) {
    		
			if (e instanceof Enemy) {
				System.out.println("added enemy" + e);
				registerObserver(((Observer)e));
			}
    		
    	}
    }
	@Override
	public void registerObserver(Observer o) {
		if(! listObservers.contains(o)) { 
			listObservers.add(o); 
			System.out.println("added enemy " + o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		listObservers.remove(o);
	}

	@Override
	public void notifyObservers() {
		System.out.println("inside notify function");
		for( Observer obs : listObservers) {
			obs.update(this);
			System.out.println("notified " + obs);
		}
	}

    public void moveUp() {
        movementHandler(this.getX(), this.getY() - 1, "up"); 
    }

    public void moveDown() {
//        if (getY() < dungeon.getHeight() - 1)
//            y().set(getY() + 1);
        movementHandler(this.getX(), this.getY() + 1, "down"); 
    }

    public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void moveLeft() {
//        if (getX() > 0)
//            x().set(getX() - 1);
        movementHandler(this.getX() - 1, this.getY(), "left"); 
    }

    public void moveRight() {
//        if (getX() < dungeon.getWidth() - 1)
//            x().set(getX() + 1);
        movementHandler(this.getX() + 1, this.getY(), "right"); 
    }
    
    public Inventory getInventory() {
    	return inven;
    }
    
    public void movementHandler(int x, int y, String direction) {
    	
    	ArrayList<Entity> entityList = dungeon.getEntityAtLocation(x, y);
    	// entities that a player cannot move to: wall, blocked (on the other side) portal, 
    	// locked door, boulder in a unmoveable position
    	
    	
    	if (entityList.size() == 0) {
    		// nothing is there
    		move(x, y, direction);
    		return;
    	}
    	
		Boulder b = null;
		Entity entityType = null;
		//check if theres a boulder or not
		for(Entity e: entityList) {
			if (e instanceof Boulder) {
				b = (Boulder)e;
			} else {
				entityType = e;
			}
		}
		
		//if its a wall then you can't move anyways.
		if (entityType instanceof Wall) {
			return;
		}
		//in the case of a boulder check if there is something blocking
		if (b != null) {
			if (dungeon.checkAdjacent(x, y, direction, b))
				return;
			b.moveBoulder(x, y, direction, dungeon);
		}
		//if its a collectable
		if(entityType != null) {
			pickupHandler(x,y,direction,entityType);
		}
		
		if(entityType instanceof Door) {
			Door d = (Door)entityType;
			d.move(this, x, y, direction);
		} else if (entityType instanceof Portal) {
			Portal p = (Portal)entityType;
			p.teleport(this, dungeon, x, y, direction);
		} else {
    		move(x,y,direction);
    		notifyObservers();
    	}
    	
    }
    
    
    public PlayerState getSwordState() {
		return swordState;
	}
	public void setSwordState(PlayerState swordState) {
		this.swordState = swordState;
	}
	public PlayerState getPotionState() {
		return potionState;
	}
	public void setPotionState(PlayerState potionState) {
		this.potionState = potionState;
	}
	public PlayerState getEmptyHandState() {
		return emptyHandState;
	}
	public void setEmptyHandState(PlayerState emptyHandState) {
		this.emptyHandState = emptyHandState;
	}
	public PlayerState getState() {
		return state;
	}
	public void setState(PlayerState state) {
		this.state = state;
	}
	public void pickupHandler(int x, int y, String direction, Entity e) {
    	if ((e instanceof Key) || (e instanceof Treasure) || (e instanceof Sword) || (e instanceof Potion)){ 
	    	inventoryHandler(e);
	    	dungeon.removeEntity(e);
    	}
    }
    
    /**
     * Will handle potential items that are picked up by the player.
     * @param e
     */
    public void inventoryHandler(Entity e) {
    	if(e instanceof Treasure) {
    		inven.addToInv(e, dungeon);
    		// trigger goal
    		return;
    	} else {
    		// for key, sword and potion, add to inventory iff not in current inventory
    		if (!inven.inInventory(e)) {
    			if (e instanceof Potion){
    				setState(this.getPotionState());
    				state.setPotion((Potion)e);
    			}
    			inven.addToInv(e, dungeon);
    		}
    		// picking up potion (add extra 10 seconds), and key (should drop then pick up)
    		else {
    			
    		}
    	} //remove the object from the map.
    }
    
    
    public void move(int x, int y, String direction) {
		notifyObservers();
		if (getState().equals(getPotionState())) {
			Potion p = state.getPotion();
			p.decrementDuration();
			System.out.println("decremented");
			if (p.emptyPotion()) {
				if (inven.hasSword()) {
					setState(getSwordState());
				}
				else {
					setState(getEmptyHandState());
				}
				System.out.println("empty");
				inven.removePotion();
				notifyObservers();
			}
		}
    	switch(direction) {
		case("up"):
			if (getY() > 0)
	            y().set(getY() - 1);
			return;
		case("down"):
	        if (getY() < dungeon.getHeight() - 1)
	            y().set(getY() + 1);
			return;
		case("left"):
			if (getX() > 0)
	            x().set(getX() - 1);
			return;
		case("right"):
			if (getX() < dungeon.getWidth() - 1)
	            x().set(getX() + 1);
			return;
		}
    }
    
    public void removeLife() {
    	lives--;
    }
    
    public void resetLives() {
    	lives = 4;
    }
    
}
