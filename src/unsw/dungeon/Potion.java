package unsw.dungeon;

public class Potion extends Entity implements Collectable {
	
	private int duration;
	public Potion(int x, int y) {
        super(x, y);
        this.duration = 10;
    }
	
	/**
	 * Every Time player moves within Potion State
	 * potion duration will decrement.
	 */
	public void decrementDuration() {
		duration--;
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
	
	/**
	 * if player collides with potion, player state will be set to
	 * PotionState. State will include the item used that induced the state.
	 */
	@Override
	public void collide(Player player, int x, int y, String direction) {
		if(player.inventoryHandler(this)) {
			player.setState(player.getPotionState());
			player.getState().setPotion(this);
		}	
		player.move(x, y, direction);
	}
}
