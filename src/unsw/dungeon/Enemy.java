package unsw.dungeon;

public class Enemy extends Entity implements Immovable,Observer{
	
	MoveOption moveOption;
	public Enemy(int x, int y) {
        super(x, y);
        moveOption = new MoveTowards();
        
	}
	public void moveEnemy(Player player) {
		moveOption.moveDirection(player, this);
	}
	public MoveOption getMoveOption() {
		return moveOption;
	}
	public void setMoveOption(MoveOption moveOption) {
		this.moveOption = moveOption;
	}
	public int distanceUp(Player player) {
		if (player.getDungeon().checkAdjacent(this.getX(), this.getY(), "up", null)) {
			return -1;
		}
		return(Math.abs(player.getX() - this.getX()) + Math.abs(player.getY() - (this.getY()-1)));
	}
	public int distanceDown(Player player) { 
		if (player.getDungeon().checkAdjacent(this.getX(), this.getY(), "down", null)) {
			return -1;
		}
		return(Math.abs(player.getX() - this.getX()) + Math.abs(player.getY() - (this.getY()+1)));
	}
	public int distanceLeft(Player player) { 
		if (player.getDungeon().checkAdjacent(this.getX(), this.getY(), "left", null)) {
			return -1;
		}
		return(Math.abs(player.getX() - (this.getX()-1)) + Math.abs(player.getY() - this.getY()));
	}
	public int distanceRight(Player player) {
		if (player.getDungeon().checkAdjacent(this.getX(), this.getY(), "right", null)) {
			return -1;
		}
		return(Math.abs(player.getX() - (this.getX()+1)) + Math.abs(player.getY() - this.getY()));
	}
	public void collide(Player player) {
		if (player.getState().equals(player.getEmptyHandState())) {
			//to do
			System.out.println("u died");
		}
		else {
			Dungeon d = player.getDungeon();
			d.removeEntity(this);
		}
	}
	
	public void move(String direction, Player player) {
		int dungeonHeight = player.getDungeon().getHeight();
		int dungeonWidth = player.getDungeon().getWidth();
		switch(direction) {
		case("up"):
			if (getY() > 0)
	            y().set(getY() - 1);
			return;
		case("down"):
	        if (getY() < dungeonHeight - 1)
	            y().set(getY() + 1);
			return;
		case("left"):
			if (getX() > 0)
	            x().set(getX() - 1);
			return;
		case("right"):
			if (getX() < dungeonWidth - 1)
	            x().set(getX() + 1);
			return;
		}
    }
	public void update(Subject obj) {
		// do something when a player notifies that it has picked up/dropped a potion/ sword
		System.out.println("inside update function in enemy with state " + ((Player)obj).getState());
		if (((Player)obj).getState().equals(((Player)obj).getEmptyHandState())) {
			//setMoveOption(new MoveTowards);
			this.setMoveOption(new MoveTowards());
			moveEnemy(((Player)obj));
		}
		else if (((Player)obj).getState().equals(((Player)obj).getPotionState())){
			this.setMoveOption(new MoveAway());
			moveEnemy(((Player)obj));
		}
		else if (((Player)obj).getState().equals(((Player)obj).getSwordState())){
			this.setMoveOption(new MoveAway());
			moveEnemy(((Player)obj));
		}
	}
}
