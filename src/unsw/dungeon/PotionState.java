package unsw.dungeon;

public class PotionState implements PlayerState{
	Player player;
	Potion potionUsed;
	public PotionState(Player player, Potion potionUsed) {
		this.player = player;
		this.potionUsed = potionUsed;
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
