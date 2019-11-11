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
	
	
	/**
	 * in the event of collision with a Sword. If player doesn't have a sword in its inventory, it will pick it up,
	 * place it in players inventory, and change player state. Then move the player.
	 */
	@Override
	public void collide() {
		Player p = getDungeon().getPlayer();
		if(checkPos(p.getX(), p.getY(), getX(), getY())) {
			p.pickUpSword(this);
		}
	}
	
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		return true; 
	}
}
