package unsw.dungeon;

public class LockedState implements DoorState{
	Door door;
	 
    public LockedState(Door door) {
        this.door = door;
    }
    public void move(Player player, int x, int y, String direction) {
    	System.out.println("Door is locked, you can't move!");
    }
}
