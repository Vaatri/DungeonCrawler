package unsw.dungeon;

import java.io.FileNotFoundException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Key extends Entity implements Collectable{
	
	private int doorID;
	private Dungeon dungeon;
	private BooleanProperty inInventory;
	
	public Key(int x, int y, int doorID, Dungeon dung) {
        super(x, y);
        this.doorID = doorID;
        this.dungeon = dung;
        this.inInventory = new SimpleBooleanProperty(false);
    }
	
	public void unlockDoor() {
		Door d = getLinkedDoor();
		if(d != null) {
			System.out.println("unlocked door with id " + d.getID());
			d.setState(d.getUnlockedState());
			//d.setOpen(true);
		} else {
			System.out.println("DOOR IS FUCKED BRO");
		}
	}
	public int getID() {
		return doorID;
	}
	public void lockDoor() {
		Door d = getLinkedDoor();
		if (d != null) {
			d.setState(d.getLockedState());
			d.setOpen(false);
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
			try {
				if(p.pickUpKey(this)) {
					unlockDoor();
					setInInvenProp(true);
				}	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//unlockDoor();
		}
	}
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		return true; 
	}

	@Override
	public boolean inInventory() {
		// TODO Auto-generated method stub
		return inInventory.get();
	}

	@Override
	public BooleanProperty inInventoryProp() {
		// TODO Auto-generated method stub
		return inInventory;
	}

	@Override
	public void setInInvenProp(boolean b) {
		// TODO Auto-generated method stub
		this.inInventory.set(b);
		
	}

}
