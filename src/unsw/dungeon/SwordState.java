package unsw.dungeon;

public class SwordState implements PlayerState{
	Player player;
	Sword swordUsed;
	public SwordState(Player player, Sword swordUsed) {
		this.player = player;
		this.swordUsed = swordUsed;
	}
	@Override
	public void setPotion(Potion p) {
	}
	
	@Override
	public Potion getPotion() {
		return null;
	}
	
	@Override
	public void setSword(Sword p) {
		swordUsed = p;
	}
	@Override
	public Sword getSword() {
		return swordUsed;
	}
	
	
	/**
	 * If player has a sword in inventory, is in swordState, and collides with an enemy
	 * it will call this function to use the sword within the function.
	 */
	public void useSword() {
		if(swordUsed != null)
			swordUsed.useSword();
	}
}
