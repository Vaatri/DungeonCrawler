package unsw.dungeon;

import java.io.FileNotFoundException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class HealthPotion extends Entity implements Collectable{
	private BooleanProperty inInventory;
	public HealthPotion(int x, int y) {
		super(x, y);
		this.inInventory = new SimpleBooleanProperty(false);
	}
	public BooleanProperty getInInventory() {
		return inInventory;
	}
	/**
	 * Returns true because a player can collide onto it
	 */
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		return true; 
	}
	/**
	 * Once collision with healthPotion, player picks it up
	 */
	@Override
	public void collide() {
		Player p = getDungeon().getPlayer();
		if(checkPos(p.getX(), p.getY(), getX(), getY())) {
			try {
				p.pickUpHealthPotion(this);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setInInventory(BooleanProperty inInventory) {
		this.inInventory = inInventory;
	}
	@Override
	public boolean inInventory() {
		
		return inInventory.getValue();
	}

	@Override
	public BooleanProperty inInventoryProp() {
		// TODO Auto-generated method stub
		return inInventory;
	}

	@Override
	public void setInInvenProp(boolean b) {
		// TODO Auto-generated method stub
		inInventory.set(b);
		
	}
	

}
