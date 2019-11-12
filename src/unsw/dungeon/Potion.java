package unsw.dungeon;

import java.io.FileNotFoundException;

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
	public void decrementDuration(Player player) {
		duration--;
		if (emptyPotion()) {
			player.setState(player.getEmptyHandState());
			player.notifyObservers();
		}
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
	
	
	
}
