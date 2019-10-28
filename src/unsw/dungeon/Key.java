package unsw.dungeon;

public class Key extends Entity{
	
	private int doorID;
	
	public Key(int x, int y, int doorID) {
        super(x, y);
        this.doorID = doorID;
    }
}
