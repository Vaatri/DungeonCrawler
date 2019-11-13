package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Sword extends Entity implements Collectable{
	
//	private IntegerProperty attacksLeft;
	private int attacksLeft;
	private BooleanProperty inInventory;
	
	public Sword(int x, int y) {
        super(x, y);
        this.attacksLeft = 5;
//        this.attacksLeft = new SimpleIntegerProperty(true);
        this.inInventory = new SimpleBooleanProperty(false);
    }
	
	public void useSword(Inventory i) {
		attacksLeft--;
		if (emptySword()) {
			i.removeSword();
			setInInvenProp(false);
			
		}
	}
	
	public boolean emptySword() {
		if (attacksLeft == 0) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * in the event of collision with a Sword. If player doesn't have a sword in its inventory, it will pick it up,
	 * place it in players inventory, and change player state. Then move the player.
	 */
	@Override
	public void collide() {
		Player p = getDungeon().getPlayer();
		if(checkPos(p.getX(), p.getY(), getX(), getY())) {
			p.pickUpSword(this);
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
