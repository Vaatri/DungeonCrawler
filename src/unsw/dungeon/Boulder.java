package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity{
	
	
	public Boulder(int x, int y) {
        super(x, y);
    }
	
	/**
	 * in the case of a player colliding with a Boulder, the boulder must in the same direction it was pushed
	 * from. This will check if the boulder has collided with a trigger.
	 * @param x
	 * @param y
	 * @param direction
	 * @param dungeon
	 */
	public void moveBoulder(int x, int y, String direction, Dungeon dungeon) {
		switch(direction) {
		case("up"):
			y--;
			break;
		case("down"):
			y++;
			break;
		case("left"):
			x--;
			break;
		case("right"):
			x++;
			break;
		}
		
		//we have to check if boulder is on a trigger or not
		//if boulder was previously on trigger, then we need to untrigger the switch if moving of
		this.setXandY(x, y);
		checkTrigger(dungeon,x,y);
	}
	
	/**
	 * If the boulder collides with a trigger, then it must trigger the switch
	 * @param dungeon
	 * @param x
	 * @param y
	 */
	public void checkTrigger(Dungeon dungeon, int x, int y) {
		for (Entity e: dungeon.getEntitiesList()) {
			if(e instanceof FloorSwitch) {
				if(x == e.getX() && y == e.getY()) {
					((FloorSwitch)e).setTriggerStatus();
					break;
				}	
			}
		}
		
	}
	
	/**
	 * move the boulder, then move the player.
	 */
	@Override
	public void collide() {
		
	}
	
	/**
	 * if the boulder is not colliding with another boulder, i.e. boulder is not stacked, we can move the boulder
	 */
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		int checkX = x;
		int checkY = y;
		switch(dir){
		case"up":
			checkY -= 1;
			break;
		case"down":
			checkY += 1;
			break;
		case"left":
			checkX -= 1;
			break;
		case "right":
			checkX += 1;
			break;
		}
		ArrayList<Entity> el = getDungeon().getEntityAtLocation(checkX, checkY);
		for(Entity e: el) {
			if (e instanceof Boulder || !e.checkCollision(checkX, checkY, dir))
				return false;
		}
		moveBoulder(x,y,dir,getDungeon());
		return true;
	}
	
	
	
}
