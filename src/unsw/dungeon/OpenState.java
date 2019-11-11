package unsw.dungeon;

public class OpenState implements DoorState{
	Door door;
	 
    public OpenState(Door door) {
        this.door = door;
    }
    
    /**
     * if the door is open, then the player is able to pass through the door.
     */
    public void react() {
    }

	@Override
	public boolean checkCollision() {
		// TODO Auto-generated method stub
		return true;
	}
}
