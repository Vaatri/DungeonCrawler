package unsw.dungeon;

public interface DoorState {
	public void react(Player player, int x, int y, String direction);
}
