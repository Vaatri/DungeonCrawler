package unsw.dungeon;

public interface PlayerState {
	public void setPotion(Potion p);
	public Potion getPotion();
	public void handle();
	public void metEnemy(Enemy e);
}
