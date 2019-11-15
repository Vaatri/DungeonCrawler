package unsw.dungeon;

public class MoveTowards implements MoveOption{
	/**
	 * When enemy is using MoveTowards strategy pattern, it will find the shortest distance to player
	 * and move towards that direction when player moves.
	 */
	public void moveDirection(Player player, Enemy enemy) {
		int smallest = Integer.MAX_VALUE;
		String direction = "";
		if (enemy.distanceOnSpot(player) == 0) {
			return;
		}
		if ((enemy.distanceUp(player) <= smallest) && enemy.distanceUp(player) >= 0) {
			smallest = enemy.distanceUp(player);
			direction = "up";
		}
		if ((enemy.distanceDown(player) <= smallest) && enemy.distanceDown(player) >= 0) {
			smallest = enemy.distanceDown(player);
			direction = "down";
		}
		if ((enemy.distanceLeft(player) <= smallest) && enemy.distanceLeft(player) >=0) {
			smallest = enemy.distanceLeft(player);
			direction = "left";
		}
		if ((enemy.distanceRight(player) <= smallest)&& enemy.distanceRight(player) >= 0) {
			smallest = enemy.distanceRight(player);
			direction = "right";
		}
		enemy.move(direction, player);
	}
}
