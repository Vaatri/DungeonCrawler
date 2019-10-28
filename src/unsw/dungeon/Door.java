package unsw.dungeon;

public class Door extends Entity{
	
	private int keyID;
	
	public Door(int x, int y, int keyID) {
        super(x, y);
        this.keyID = keyID;
    }
}
