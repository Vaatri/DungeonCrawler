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
    PlayerState playerDeadState;
    PlayerState state;
    private ComplexGoal playerGoals;
    
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
        swordState = new SwordState(this, null);
        potionState = new PotionState(this , null);
        playerDeadState = new PlayerDeadState(this);
        emptyHandState = new EmptyHandState(this);
        state = emptyHandState;
        addObserverList();
        this.playerGoals = null;
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
		for( Observer obs : listObservers) {
			obs.update(this);
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
    	
    	Boulder b = null;
    	if (entityList.size() == 0) {
    		// nothing is there
    		move(x, y, direction);
    		return;
    	}
    	
		//check if theres a boulder we need to move
		for(Entity e: entityList) {
			if(e instanceof Boulder) {
				b = (Boulder)e;
			}
		}
		//if theres a boulder that cant be moved we cant move
		if (b != null) {
			if(dungeon.checkAdjacent(x, y, direction, b))
				return;
		}
		for(Entity e: entityList) {
			e.collide(this, x, y, direction);
		}
		
    }
    
    
    public PlayerState getPlayerDeadState() {
    	return playerDeadState;
    }
    public void setPlayerDeadState(PlayerState playerDeadState) {
    	this.playerDeadState = playerDeadState;
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
    public boolean inventoryHandler(Entity e) {
    	
    	if(inven.addToInv(e, dungeon)) {
    		dungeon.removeEntity(e);
    		return true;
    	} else 
    		return false;
    	
    	
    }
    
    
    public void move(int x, int y, String direction) {
		notifyObservers();
		stateHandler();
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
    
    public void stateHandler() {
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
		} else if (getState().equals(getSwordState())) {
			Sword s = swordState.getSword();
		}
    }
    
    public void setPlayerGoals(ComplexGoal g) {
    	playerGoals = g;
    }
    
    
    public void removeLife() {
    	lives--;
    }
    
    public void resetLives() {
    	lives = 4;
    }
    
    @Override
    public String getType() {
    	return "";
    }
    
}
