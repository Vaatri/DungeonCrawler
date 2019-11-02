package unsw.dungeon;

public class MoveTowards implements MoveOption{
	public void moveDirection(Player player, Enemy enemy) {
		System.out.println("move towards");
		int smallest = 100; //fix
		String direction = "";
		System.out.println(enemy.distanceUp(player) +" "+ enemy.distanceDown(player)+" " + enemy.distanceLeft(player) +" "+ enemy.distanceRight(player));
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
		System.out.println(direction);
		enemy.move(direction, player);
	}
}
