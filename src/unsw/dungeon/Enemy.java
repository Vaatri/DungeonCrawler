package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class Enemy extends Entity implements Immovable,Observer, Subject{
	
	MoveOption moveOption;
	List<Observer> observerList = new ArrayList<Observer>();
	private String type;
	
	public Enemy(int x, int y) {
        super(x, y);
        moveOption = new MoveTowards();
        this.type = "enemy";
        
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
	
	@Override
	public void collide(Player player, int x, int y, String direction) {
		if(player.getState().equals(player.getEmptyHandState()))
			killPlayer(player);
		else 
			dieByPlayer(player);
		player.move(x, y, direction);
	}
	
	public void killPlayer(Player player) {
			player.setState(player.getPlayerDeadState());
	}
	
	public void dieByPlayer(Player player) {
		Dungeon d = player.getDungeon();
		d.removeEntity(this);
		this.setMoveOption(new MoveDead());
		notifyObservers();
		SwordState ss = (SwordState)player.getSwordState();
		ss.useSword();
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
	
	@Override
	public void update(Subject obj) {
		// do something when a player notifies that it has picked up/dropped a potion/ sword
		if(!(getMoveOption() instanceof MoveDead)) {
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
				this.setMoveOption(new MoveTowards());
				moveEnemy(((Player)obj));
			}
		}
	}
	
	@Override
	public void registerObserver(Observer o) {
		observerList.add(o);
	}
	
	@Override
	public void removeObserver(Observer o) {
		observerList.remove(o);
	}
	
	@Override
	public void notifyObservers() {
		for(Observer o : observerList) {
			o.update(this);
		}
	}
	
	@Override
	public String getType() {
		return type;
	}
}
