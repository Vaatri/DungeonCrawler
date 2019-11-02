package unsw.dungeon;

public class UnlockedState implements DoorState{
	Door door;
	 
    public UnlockedState(Door door) {
        this.door = door;
    }
    public void react(Player player, int x, int y, String direction) {
    	System.out.println("Door open, you may go through");
    	player.move(x, y, direction);
    	// once a player goes through unlocked door, change its state to open
    	door.setState(door.getOpenState());
    	
    	//after used the key remove it from the inventory
    	Inventory i = player.getInventory();
    	i.removeKey();
    	// set ui of door to open door
    }
}
