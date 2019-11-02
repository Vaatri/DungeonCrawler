package unsw.dungeon;

public class Treasure extends Entity implements Collectable{

	
	public Treasure(int x, int y) {
		super(x,y);
		
	}
	
	@Override
	public void collide(Player player, int x, int y, String direction) {
		player.inventoryHandler(this);
		player.move(x, y, direction);
	}

}
