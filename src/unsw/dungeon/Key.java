package unsw.dungeon;

public class Key extends Entity implements Collectable{
	
	private int doorID;
	private Dungeon dungeon;
	public Key(int x, int y, int doorID, Dungeon dung) {
        super(x, y);
        this.doorID = doorID;
        this.dungeon = dung;
    }
	
	public void unlockDoor() {
		Door d = getLinkedDoor();
		if(d != null) {
			d.setState(d.getUnlockedState());
		} else {
			System.out.println("DOOR IS FUCKED BRO");
		}
	}
	
	public void lockDoor() {
		Door d = getLinkedDoor();
		if (d != null) {
			d.setState(d.getLockedState());
		} else {
			System.out.println("DOOR IS FUCKED BRO");
		}
		
	}
	

	public Door getLinkedDoor() {
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
	public void collide() {
		Player p = dungeon.getPlayer();
		if(checkPos(p.getX(), p.getY(), getX(), getY())) {
			p.pickUpKey(this);
			unlockDoor();
		}
	}
	
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		return true; 
	}
}
