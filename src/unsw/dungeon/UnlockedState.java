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
    public void react(Player player, int x, int y, String direction) {
    	System.out.println("Door open, you may go through");
    	player.move(x, y, direction);
    	// once a player goes through unlocked door, change its state to open
    	door.setState(door.getOpenState());
    	
    	//after used the key remove it from the inventory
    	Inventory i = player.getInventory();
    	i.removeItem(i.getKey());
    	// set ui of door to open door
    }
}
