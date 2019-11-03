package unsw.dungeon;

public class MoveAway implements MoveOption {
	/**
	 * When Player consumes a potion and state is set to PotionState, enemy will
	 * adopt MoveAway function, which will find the furthest distance from player
	 * and move towards that direction.
	 */
	public void moveDirection(Player player, Enemy enemy) {
		int biggest = enemy.distanceUp(player);
		String direction = "up";
		if (enemy.distanceDown(player) >= biggest) {
			biggest = enemy.distanceDown(player);
			direction = "down";
		}
		if (enemy.distanceLeft(player) >= biggest) {
			biggest = enemy.distanceLeft(player);
			direction = "left";
		}
		if (enemy.distanceRight(player) >= biggest) {
			biggest = enemy.distanceRight(player);
			direction = "right";
		}
		enemy.move(direction, player);

	}
}
