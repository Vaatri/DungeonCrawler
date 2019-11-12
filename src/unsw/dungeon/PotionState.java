package unsw.dungeon;

public class PotionState implements PlayerState{
	Player player;
	Potion potionUsed;
	public PotionState(Player player, Potion potionUsed) {
		this.player = player;
		this.potionUsed = potionUsed;
	}
	/**
	 * decrement potion usage
	 */
	public void handle() {
		potionUsed.decrementDuration(player);
	}
	/**
	 * decrements potion usage then kills enemy
	 */
	public void metEnemy(Enemy enemy) {
		potionUsed.decrementDuration(player);
		enemy.die(player);
		
	}
	@Override
	public void setPotion(Potion p) {
		potionUsed = p;
	}
	@Override
	public Potion getPotion() {
		return potionUsed;
	}
}
