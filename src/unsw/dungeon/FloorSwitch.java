package unsw.dungeon;

public class FloorSwitch extends Entity {
	
	private boolean triggered;
	
	public FloorSwitch(int x, int y) {
        super(x, y);
        this.triggered = false;
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
}
