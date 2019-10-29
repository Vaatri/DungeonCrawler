package unsw.dungeon;

public class Door extends Entity{
	
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
	public boolean isLocked() {
		if(this.state == lockedState ) {
			return true;
		}
		return false;
	}
	public void move(Player player, int x, int y, String direction) {
    	state.move(player, x, y, direction);
    }
	public boolean isUnlocked() {
		if(this.state == unlockedState ) {
			return true;
		}
		return false;
	}
	public DoorState getState() {
		return state;
	}
	void setState(DoorState state) {
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
}
