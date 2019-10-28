package unsw.dungeon;

public class Sword extends Entity{
	
	private int attacksLeft;
	
	public Sword(int x, int y) {
        super(x, y);
        this.attacksLeft = 5;
    }
	
	public void useSword() {
		attacksLeft--;
	}
	
	public int checkAttacksLeft() {
		return attacksLeft;
	}
}
