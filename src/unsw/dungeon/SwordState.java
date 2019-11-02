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
}
