package unsw.dungeon;

public class MoveAway implements MoveOption {
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
