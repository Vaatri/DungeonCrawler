package unsw.dungeon;

public class EmptyHandState implements PlayerState{
	Player player;
	public EmptyHandState(Player player) {
		this.player = player;
	}
	@Override
	public void setPotion(Potion p) {
	}
	@Override
	public Potion getPotion() {
		return null;
	}
	@Override
	public Sword getSword() {
		return null;
	}
	
	@Override
	public void setSword(Sword s) {
		
	}
}
