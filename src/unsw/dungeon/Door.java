package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
	
	private int keyID;
	DoorState lockedState;
	DoorState unlockedState;
	DoorState openState;
	DoorState state;
	private BooleanProperty open;
	
	public Door(int x, int y, int keyID) {
        super(x, y);
        this.keyID = keyID;
        lockedState = new LockedState(this);
        unlockedState = new UnlockedState(this);
        openState = new OpenState(this);
        state = lockedState;
        this.open = new SimpleBooleanProperty(false);
    }
	/**
	 * check if door is locked.
	 * @return
	 */
	public boolean isLocked() {
		if(this.state == lockedState ) {
			return true;
		}
		return false;
	}
	
	/**
	 * react to player colliding with door.
	 * @param player
	 * @param x
	 * @param y
	 * @param direction
	 */
	public void react() {
    	state.react();
    }
	/**
	 * check if door is unlocked.
	 * @return
	 */
	public boolean isUnlocked() {
		if(this.state == unlockedState ) {
			
			return true;
		}
		return false;
	}
	public DoorState getState() {
		return state;
	}
	public void setState(DoorState state) {
		this.state = state;
	}
	public DoorState getLockedState() {
		return lockedState;
	}
	public void setLockedState(DoorState lockedState) {
		this.lockedState = lockedState;
	}
	public DoorState getUnlockedState() {
		return unlockedState;
	}
	public void setUnlockedState(DoorState unlockedState) {
		this.unlockedState = unlockedState;
	}
	public DoorState getOpenState() {
		return openState;
	}
	public void setOpenState(DoorState openState) {
		this.openState = openState;
	}
	
	public int getID() {
		return keyID;
	}
	
	/**
	 * Player interaction with door will depend on door's current state. If door is locked, then player
	 * will not pass. If door is unlocked, Door state is changed to open, and player will pass through. 
	 * If Door is open, player will pass through.
	 */
	@Override
	public void collide() {
		react();
	}
	
	@Override
	public boolean checkCollision(int x, int y, String direction) {
		//check the entity that is colliding
		if(!getDungeon().checkIfBoulder(x, y, direction)) {
			return false;
		}
		return state.checkCollision();
	}
	
	public BooleanProperty getOpenProp() {
		return this.open;
	}
	
	public boolean getOpen() {
		return this.open.get();
	}
	
	public void setOpen(boolean b) {
		this.open.set(b);
	}
}
