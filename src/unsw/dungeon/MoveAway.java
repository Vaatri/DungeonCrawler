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
		if (enemy.distanceUp(player) == -1) {
			biggest = -1;
			direction = "";
		}
		if ((enemy.distanceDown(player) >= biggest) && (enemy.distanceDown(player) >= 0)) {
			biggest = enemy.distanceDown(player);
			direction = "down";
		}
		if ((enemy.distanceLeft(player) >= biggest) && (enemy.distanceLeft(player) >= 0)) {
			biggest = enemy.distanceLeft(player);
			direction = "left";
		}
		if ((enemy.distanceRight(player) >= biggest) && (enemy.distanceRight(player) >= 0)) {
			biggest = enemy.distanceRight(player);
			direction = "right";
		}
		if(biggest != -1) {
			enemy.move(direction, player);
		}

	}
}
