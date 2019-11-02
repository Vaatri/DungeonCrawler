package unsw.dungeon;

public class Sword extends Entity implements Collectable{
	
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
	
	@Override
	public void collide(Player player, int x, int y, String direction) {
		player.inventoryHandler(this);
		player.move(x, y, direction);
	}
}
