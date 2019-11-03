package unsw.dungeon;

public class MoveDead implements MoveOption {

	
	
	/**
	 * class is used to signify a dead enemy.
	 */
	@Override
	public void moveDirection(Player player, Enemy enemy) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MoveDead) {
			return true;
		} else {
			return false;
		}
	}

}
