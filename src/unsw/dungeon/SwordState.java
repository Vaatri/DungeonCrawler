package unsw.dungeon;

public class SwordState implements PlayerState{
	Player player;
	public SwordState(Player player) {
		this.player = player;
	}
	@Override
	public void setPotion(Potion p) {
	}
	
	@Override
	public Potion getPotion() {
		return null;
	}
}
