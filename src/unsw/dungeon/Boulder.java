package unsw.dungeon;

public class Boulder extends Entity{
	public Boulder(int x, int y) {
        super(x, y);
    }
	
	public void moveBoulder(int x, int y, String direction) {
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
		this.setXandY(x, y);
	}
}
