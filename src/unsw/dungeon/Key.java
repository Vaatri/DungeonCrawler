package unsw.dungeon;

public class Key extends Entity{
	
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
}
