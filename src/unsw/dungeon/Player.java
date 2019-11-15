package unsw.dungeon;

import java.io.FileNotFoundException;
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
    PlayerState potionState;
    PlayerState emptyHandState;
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
        potionState = new PotionState(this , null);
        emptyHandState = new EmptyHandState(this);
        state = emptyHandState;
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
			if (e instanceof Door) {
				((Door)e).registerObserver(inven);
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
    	
		if(playerGoals.checkCompleted()) {
			System.out.println("You win!");
		}
		
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
     * This will handle the change in coordinates when the player moves
     * It will also handle change and observers.
     * @param x
     * @param y
     * @param direction
     */
    public void move(int x, int y, String direction) {

		stateHandler();
    	switch(direction) {
		case("up"):
			System.out.println("get y "+ getY());
			if (getY() > 0) {
	        	y().set(getY() - 1);
				notifyObservers();
			}
			return;
		case("down"):
	        if (getY() < dungeon.getHeight() - 1) {
	            y().set(getY() + 1);
				notifyObservers();
	        }
			return;
		case("left"):
			if (getX() > 0) {
	            x().set(getX() - 1);
				notifyObservers();
			}
			return;
		case("right"):
			if (getX() < dungeon.getWidth() - 1) {
	            x().set(getX() + 1);
				notifyObservers();
			}
			return;
		}
    	
		return;
    }
    
    /**
     * Depending on the players state, this function will dictate the states
     * active time.
     */
    public void stateHandler() {
    	// handle player's state
    	state.handle();
    }
    
    /**
     * Depending on the player's state, it will take action upon collision with the enemy
     * @param enemy
     */
    public void metEnemy(Enemy enemy) {
    	state.metEnemy(enemy);
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
	
	public void pickUpSword(Sword s) {
		if(inven.getSword() == null) {
			inven.setSword(s);
			dungeon.removeEntity(s);
		}
	}
	public void pickUpKey(Key k) throws FileNotFoundException {
		if(inven.getKey() == null) {
			inven.setKey(k);
			dungeon.removeEntity(k);
		}
	}
	/**
	 * When potion is picked up, it will store potion in the inventory, 
	 * remove from the map and set its state to potionState
	 * @param p
	 * @throws FileNotFoundException 
	 */
	public void pickUpPotion(Potion p) throws FileNotFoundException {
		if(inven.getPotion() == null) {
			inven.setPotion(p);
			dungeon.removeEntity(p);
//			dungeon.removeEntity(p, this);
			potionState = new PotionState(this, p);
			setState(this.getPotionState());
		}
	}
	public void pickUpTreasure(Treasure t) {
		//if(inven.getSword() == null) {
			inven.setTreasure(t);
			dungeon.removeEntity(t);
//			try {
//				dungeon.removeEntity(t, this);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		//}
	}
  
    
}
