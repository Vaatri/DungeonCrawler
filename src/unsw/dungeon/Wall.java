package unsw.dungeon;

public class Wall extends Entity implements Immovable{

    public Wall(int x, int y) {
        super(x, y);
    }

    
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		return false;
	}
}
