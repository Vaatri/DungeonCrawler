package unsw.dungeon;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
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
     * Player will contain the dungeon its in.
     * Player has an inventory
     * Player has a total amount of lives
     * Player has various states depending on its equipment
     * Player has a List of Goals it wants to complete.
     * @param dungeon
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
    
    /**
     * Add all enemies within the dungeon to players observer List.
     */
    public void addObserverList() {
    	// add all the enemies on the dungeon in observer list;
    	for (Entity e: dungeon.getEntitiesList()) {
    		
			if (e instanceof Enemy) {
				registerObserver(((Observer)e));
			}
    		
    	}
    	
    }
    
    /**
     * helper function for addObserverList();
     */
	@Override
	public void registerObserver(Observer o) {
		if(! listObservers.contains(o)) { 
			listObservers.add(o); 
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
        movementHandler(this.getX(), this.getY() + 1, "down"); 
    }

    public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void moveLeft() {
        movementHandler(this.getX() - 1, this.getY(), "left"); 
    }

    public void moveRight() {
        movementHandler(this.getX() + 1, this.getY(), "right"); 
    }
    
    public Inventory getInventory() {
    	return inven;
    }
    
    public ComplexGoal getPlayerGoals() {
    	return playerGoals;
    }
    
    /**
     * This method will handle all movement from player.
     * The player will respond depending on what entity it will interact with given the 
     * direction the player wants to go too, 
     * @param x
     * @param y
     * @param direction
     */
    public void movementHandler(int x, int y, String direction) {
    	
    	//refactor
    	//check if there will be a collision
    	//if there is and we can move, then move
    	//otherwise do nothing.
    	if (dungeon.checkCollision(x,y,direction)) {
    		move(x,y,direction);
    	}
    	dungeon.handleCollision();
    	
		playerGoals.checkCompleted();
		
    }
    
    /**
     * This function is used to check enemies location.
     * A player and an enemy can meet at the same coordinate
     * Rather than the player checking if the enemy is within an adjacent block.
     */
	public void checkCurrentLocation(){
		ArrayList<Entity> el = dungeon.getEntityAtLocation(getX(), getY());
		for(Entity e: el) {
			if(!(e instanceof Enemy)) continue;
			if(getState().equals(getEmptyHandState())) {
				((Enemy)e).killPlayer(this);
			} else {
				((Enemy)e).dieByPlayer(this);
			}
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
    
    /**
     * Will handle potential items that are picked up by the player.
     * @param e
     */
    public boolean inventoryHandler(Collectable c) {
    	
//    	if(inven.addToInv(c, dungeon)) {
//    		dungeon.removeEntity(c);
//    		return true;
//    	} else 
//    		return false;
    	return true;
    	
    }
    
    /**
     * This will handle the change in coordinates when the player moves
     * It will also handle change and observers.
     * @param x
     * @param y
     * @param direction
     */
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
    
    /**
     * Depending on the players state, this function will dictate the states
     * active time.
     */
    public void stateHandler() {
    	//if player is currently within a potion state, it will diminish each time
    	//player takes a step.
    	if (getState().equals(getPotionState())) {
			Potion p = state.getPotion();
			p.decrementDuration();
			//Depending if the player has a sword or not, it will
			//return to the appropriate previous state after potion diminished.
			if (p.emptyPotion()) {
				if (inven.hasSword()) {
					setState(getSwordState());
				}
				else {
					setState(getEmptyHandState());
				}
//				inven.removeItem(p);
				notifyObservers();
			}
		//A SwordState should never fall back into a PotionState.
		} else if (getState().equals(getSwordState())) {
			Sword s = swordState.getSword();
			if(s.checkAttacksLeft() == 0) {
				setState(getEmptyHandState());
//				inven.removeItem(s);
			}
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
	
	public void pickup(Entity e) {
		
	}

  
    
}
