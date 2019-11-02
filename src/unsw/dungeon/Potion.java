package unsw.dungeon;

public class Potion extends Entity {
	
	private int duration;
	public Potion(int x, int y) {
        super(x, y);
        this.duration = 10;
    }
	public void decrementDuration() {
		duration--;
	}
	public boolean emptyPotion() {
		if(duration == 0)	
			return true;
		return false;
	}
}
