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
    		//door.setOpen(true);
    		System.out.println("open go through");
    }

	@Override
	public boolean checkCollision() {
		// TODO Auto-generated method stub
		return true;
	}
}
