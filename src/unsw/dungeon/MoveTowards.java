package unsw.dungeon;

public class MoveTowards implements MoveOption{
	public void moveDirection(Player player, Enemy enemy) {
		int smallest = 100; //fix
		String direction = "";
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
