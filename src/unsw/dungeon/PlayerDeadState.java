package unsw.dungeon;

public class PlayerDeadState implements PlayerState {

	Player player;
	
	public PlayerDeadState(Player player) {
		this.player = player;
	}
	@Override
	public void setPotion(Potion p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Potion getPotion() {
		// TODO Auto-generated method stub
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
