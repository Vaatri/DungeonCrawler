package unsw.dungeon;

public interface DoorState {
	// door has a state: locked, unlocked and open
	public void react();
	public boolean checkCollision();
}
