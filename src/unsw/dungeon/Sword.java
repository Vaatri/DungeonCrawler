package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword extends Entity implements Collectable{
	
	private IntegerProperty attacksLeft;
	private BooleanProperty inInventory;
	
	public Sword(int x, int y) {
        super(x, y);
        this.attacksLeft = new SimpleIntegerProperty(5);
        this.inInventory = new SimpleBooleanProperty(false);
    }
	
	/**
	 * Use the sword. Sword usage will be decremented.
	 * If sword is exhausted, sword will be removed from inventory.
	 * @param i
	 */
	public void useSword(Inventory i) {
		attacksLeft.setValue(attacksLeft.get() - 1);
		if (emptySword()) {
			i.removeSword();
			setInInvenProp(false);
		}
	}
	public IntegerProperty getAttacksLeftProp() {
		return attacksLeft;
	}
	
	/**
	 * Check if sword has attacks left or not.
	 * @return
	 */
	public boolean emptySword() {
		if (attacksLeft.get() == 0) {
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

	/**
	 * check if item is in players inventory.
	 */
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
	
	public IntegerProperty getUsageProperty() {
		return attacksLeft;
	}
}
