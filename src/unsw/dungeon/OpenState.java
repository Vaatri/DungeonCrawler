package unsw.dungeon;

public class OpenState implements DoorState{
	Door door;
	 
    public OpenState(Door door) {
        this.door = door;
    }
    public void react(Player player, int x, int y, String direction) {
    	System.out.println("Door open, you may go through");
    	player.move(x, y, direction);
    }
}
