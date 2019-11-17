package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class FloorSwitch extends Entity implements Subject{
	
	private boolean triggered;
	private String type;
	List<Observer> observerList = new ArrayList<Observer>();
	
	public FloorSwitch(int x, int y) {
        super(x, y);
        this.triggered = false;
        this.type = "switch";
    }
	
	/**
	 * Trigger switch to be on.
	 */
	public void triggerSwitch() {
		triggered = true;
	}
	
	/**
	 * Trigger switch to be off.
	 */
	public void untriggerSwitch() {
		if(triggered == true) {
			triggered = false;
			notifyObservers();
		}
	}
	
	/**
	 * return Trigger Status
	 * @return
	 */
	public boolean getTriggerStatus() {
		return triggered;
	}
	
	/**
	 * If a player walks onto a switch, that can only mean that a switch
	 * is untriggered.
	 */
	@Override
	public void collide() {
		untriggerSwitch();
	}
	
	/**
	 * trigger floor switch if boulder is moved on top
	 */
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		//check the entity that is colliding
		if(!getDungeon().checkIfBoulder(x, y, dir)) {
			setTriggerStatus();
		}
		return true;
	}
	
	/**
	 * Set the switches trigger status to triggered and notify observers.
	 * @param dungeon
	 */
	public void setTriggerStatus() {
		triggerSwitch();
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
			o.update(this);
		}
	}
	
	@Override
	public String getType() {
		return type;
	}
}
