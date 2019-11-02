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
	
	public void triggerSwitch() {
		triggered = true;
	}
	
	public void untriggerSwitch() {
		triggered = false;
	}
	
	public boolean getTriggerStatus() {
		return triggered;
	}
	
	@Override
	public void registerObserver(Observer o) {
		System.out.println("adding observer: "+o);
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
