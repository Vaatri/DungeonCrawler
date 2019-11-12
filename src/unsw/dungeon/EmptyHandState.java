package unsw.dungeon;

public class EmptyHandState implements PlayerState{
	Player player;
	public EmptyHandState(Player player) {
		this.player = player;
	}
	public void handle() {
	}
	/**
	 * if the player has a sword, enemy dies
	 * otherwise player dies
	 */
	public void metEnemy(Enemy enemy) {
		// player has collided with an enemy
		// if the player has a sword, it will kill the enemy and decrement sword usage
		Inventory i = player.getInventory();
		if (i.hasSword()) {
			Sword sword = i.getSword();
			sword.useSword(i);
			enemy.die(player);
			return;
		}
		// if the player does not have a sword, it dies
		System.out.println("YOU DIED");
		
	}
	@Override
	public void setPotion(Potion p) {
	}
	@Override
	public Potion getPotion() {
		return null;
	}
	
}
