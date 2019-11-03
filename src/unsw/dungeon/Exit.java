package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class Exit extends Entity implements Subject{
	
	List<Observer> observerList = new ArrayList<Observer>();
	private String type;
	
	public Exit(int x, int y) {
        super(x, y);
        this.type = "exit";
    }
	
	/**
	 * when the player collides with an exit, exit goal is triggered.
	 */
	@Override
	public void collide(Player player, int x, int y, String direction) {
		player.move(x, y, direction);
		System.out.println(observerList);
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
}	
