package unsw.dungeon;

public class Door extends Entity {
	
	private int keyID;
	DoorState lockedState;
	DoorState unlockedState;
	DoorState openState;
	
	DoorState state;
	public Door(int x, int y, int keyID) {
        super(x, y);
        this.keyID = keyID;
        lockedState = new LockedState(this);
        unlockedState = new UnlockedState(this);
        openState = new OpenState(this);
        state = lockedState;
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
	public void react(Player player, int x, int y, String direction) {
    	state.react(player,x,y,direction);
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
	public void collide(Player player, int x, int y, String direction) {
		react(player,x,y,direction);
	}
}
