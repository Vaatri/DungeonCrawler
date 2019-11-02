package unsw.dungeon;

public class Potion extends Entity implements Collectable {
	
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
	
	public int getDuration() {
		return duration;
	}
	
	@Override
	public void collide(Player player, int x, int y, String direction) {
		if(player.inventoryHandler(this)) {
			player.setState(player.getPotionState());
			player.getState().setPotion(this);
		}	
		player.move(x, y, direction);
	}
}
