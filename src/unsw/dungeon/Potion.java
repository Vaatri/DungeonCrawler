package unsw.dungeon;

import java.io.FileNotFoundException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Potion extends Entity implements Collectable {
	
	private int duration;
	private BooleanProperty inInventory;
	
	public Potion(int x, int y) {
        super(x, y);
        this.duration = 10;
        this.inInventory = new SimpleBooleanProperty(false);
    }
	
	/**
	 * Every Time player moves within Potion State
	 * potion duration will decrement.
	 */
	public void decrementDuration(Player player) {
		duration--;
		if (emptyPotion()) {
			player.setState(player.getEmptyHandState());
			player.notifyObservers();
			player.getInventory().removePotion();
			setInInvenProp(false);
		}
	}
	
	/**
	 * if potion has no more duration return true
	 * @return
	 */
	public boolean emptyPotion() {
		if(duration == 0)	
			return true;
		return false;
	}
	
	/**
	 * return duration of potion
	 * @return
	 */
	public int getDuration() {
		return duration;
	}
	
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		return true; 
	}
	
	//refactoring
	@Override
	public void collide() {
		Player p = getDungeon().getPlayer();
		if(checkPos(p.getX(), p.getY(), getX(), getY())) {
			try {
				p.pickUpPotion(this);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean inInventory() {
		// TODO Auto-generated method stub
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
		this.inInventory.set(b);
		
	}
	
	
	
}
