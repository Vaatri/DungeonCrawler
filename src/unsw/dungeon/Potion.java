package unsw.dungeon;

import java.io.FileNotFoundException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Potion extends Entity implements Collectable {
	
	private IntegerProperty duration;
	private BooleanProperty inInventory;
	
	public Potion(int x, int y) {
        super(x, y);
        this.duration = new SimpleIntegerProperty(10);
        this.inInventory = new SimpleBooleanProperty(false);
    }
	
	/**
	 * Every Time player moves within Potion State
	 * potion duration will decrement.
	 */
	public void decrementDuration(Player player) {
		duration.set(duration.get() - 1);;
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
		if(duration.get() == 0)	
			return true;
		return false;
	}
	
	/**
	 * return duration of potion
	 * @return
	 */
	public int getDuration() {
		return duration.get();
	}
	
	public IntegerProperty getDurationProp() {
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
