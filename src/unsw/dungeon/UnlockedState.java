package unsw.dungeon;

public class UnlockedState implements DoorState{
	Door door;
	 
    public UnlockedState(Door door) {
        this.door = door;
    }
    
    /**
     * A door will change to an unlocked state when the player picks up a Key
     * that is linked with the door.
     * Door will be set to an unlocked state, but will only be open when
     * the Player collides the door.
     */
    public void react() {
    	door.setState(door.getOpenState());
    	door.setOpen(true);
    	
    }

	@Override
	public boolean checkCollision() {
		// TODO Auto-generated method stub
		return true;
	}
}
