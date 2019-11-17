package unsw.dungeon;

public class LockedState implements DoorState{
	Door door;
	 
    public LockedState(Door door) {
        this.door = door;
    }
    public void react() {
    	System.out.println("Door is locked, you can't move!");
    }
    
    /**
     * Determine behaviour set with collision with another entity.
     */
	@Override
	public boolean checkCollision() {
		return false;
	}
}
