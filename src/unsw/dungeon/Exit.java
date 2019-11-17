package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class Exit extends Entity implements Subject{
	
	List<Observer> observerList = new ArrayList<Observer>();
	private String type;
	private Dungeon dungeon;
	public Exit(int x, int y, Dungeon d) {
        super(x, y);
        this.type = "exit";
        this.dungeon = d;
    }
	
	/**
	 * when the player collides with an exit, exit goal is triggered.
	 */
	@Override
	public void collide() {
		notifyObservers();
	}
	
	/**
	 * check that a player is not colliding with a boulder
	 */
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		//check the entity that is colliding
		if(!dungeon.checkIfBoulder(x, y, dir)) {
			return false;
		}
		return true;
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
}	
