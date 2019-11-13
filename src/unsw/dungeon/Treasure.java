package unsw.dungeon;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

public class Treasure extends Entity implements Collectable, Subject{

	private String type;
	private BooleanProperty inInven;
	List<Observer> observerList = new ArrayList<Observer>();
	
	public Treasure(int x, int y) {
		super(x,y);
		this.type = "treasure";
		this.inInven = new SimpleBooleanProperty(false);
	}
	
	/**
	 * In event of collision with player, the Treasure will be added to players
	 * inventory, and goal observers will be notified.
	 */
	@Override
	public void collide() {
		Player p = getDungeon().getPlayer();
		if(checkPos(p.getX(), p.getY(), getX(), getY())) {
			p.pickUpTreasure(this);
		}
		notifyObservers();
	}
	@Override
	public void registerObserver(Observer o) {
		observerList.add(o);
	}
	
	@Override
	public void removeObserver(Observer o) {
		observerList.remove(o);
	}
	
	@Override
	public void notifyObservers() {
		for(Observer o : observerList) {
			System.out.println(o);
			o.update(this);
		}
	}
	
	@Override 
	public String getType() {
		return type;
	}
	
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		return true; 
	}
	
	@Override
	public boolean inInventory() {
		return inInven.get();
	}
	@Override
	public BooleanProperty inInventoryProp() {
		return inInven;
	}
	@Override
	public void setInInvenProp(boolean b) {
		inInven.set(b);
	}


}
