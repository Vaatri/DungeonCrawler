package unsw.dungeon;

public class Key extends Entity implements Collectable{
	
	private int doorID;
	
	public Key(int x, int y, int doorID) {
        super(x, y);
        this.doorID = doorID;
    }
	
	public void unlockDoor(Dungeon dungeon) {
		Door d = getLinkedDoor(dungeon);
		if(d != null) {
			d.setState(d.getUnlockedState());
		} else {
			System.out.println("DOOR IS FUCKED BRO");
		}
	}
	
	public void lockDoor(Dungeon dungeon) {
		Door d = getLinkedDoor(dungeon);
		if (d != null) {
			d.setState(d.getLockedState());
		} else {
			System.out.println("DOOR IS FUCKED BRO");
		}
		
	}
	

	public Door getLinkedDoor(Dungeon dungeon) {
		for(Entity e: dungeon.getEntitiesList()) {
			if(e instanceof Door) {
				if(((Door)e).getID() == doorID) {
					return ((Door)e);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * In event of collision with player, if player already has a key in its inventory, key will remain on the floor
	 * otherwise it will be picked up, placed in Players inventory, and linked door will be set to unlocked state.
	 */
	@Override
	public void collide(Player player, int x, int y, String direction) {
		if(player.inventoryHandler(this)) {
			unlockDoor(player.getDungeon());
		}	
		player.move(x, y, direction);
	}
}
