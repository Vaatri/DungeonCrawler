package unsw.dungeon;


public class Boulder extends Entity{
	public Boulder(int x, int y) {
        super(x, y);
    }
	
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
	}
	
	
	public void checkTrigger(Dungeon dungeon, int x, int y) {
		for(Entity e: dungeon.getEntitiesList()) {
			if(e instanceof FloorSwitch) {
				if(e.getX() == x && e.getY() == y) {
					if(((FloorSwitch)e).getTriggerStatus()) {
						
					}
				}
			}
		}
		
	}
	
}
