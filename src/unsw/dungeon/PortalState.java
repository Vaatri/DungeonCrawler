package unsw.dungeon;

public interface PortalState {
	public void teleport(Player p, Dungeon dungeon, int x, int y, String direction);
}
