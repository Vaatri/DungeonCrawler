package unsw.dungeon;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

public class Enemy extends Entity implements Immovable,Observer, Subject{
	
	MoveOption moveOption;
	List<Observer> observerList = new ArrayList<Observer>();
	private String type;
	private BooleanProperty dead;
	
	public Enemy(int x, int y) {
        super(x, y);
        this.moveOption = new MoveTowards();
        this.type = "enemy";
        this.dead = new SimpleBooleanProperty(false);
	}
	/**
	 * this will move the enemy depending on its movement stratergy
	 * @param player
	 */
	public void moveEnemy(Player player) {
		moveOption.moveDirection(player, this);
	}
	public MoveOption getMoveOption() {
		return moveOption;
	}
	
	public void setMoveOption(MoveOption moveOption) {
		this.moveOption = moveOption;
	}
	
	/**
	 * find the distance from enemy to player
	 * @param player
	 * @return
	 */
	public int distanceUp(Player player) {
		if (player.getDungeon().checkAdjacent(this.getX(), this.getY(), "up", null)) {
			return -1;
		}
		return(Math.abs(player.getX() - this.getX()) + Math.abs(player.getY() - (this.getY()-1)));
	}
	
	/**
	 * find the distance from enemy to player
	 * @param player
	 * @return
	 */
	public int distanceDown(Player player) { 
		if (player.getDungeon().checkAdjacent(this.getX(), this.getY(), "down", null)) {
			return -1;
		}
		return(Math.abs(player.getX() - this.getX()) + Math.abs(player.getY() - (this.getY()+1)));
	}
	
	/**
	 * find the distance from enemy to player
	 * @param player
	 * @return
	 */
	public int distanceLeft(Player player) { 
		if (player.getDungeon().checkAdjacent(this.getX(), this.getY(), "left", null)) {
			return -1;
		}
		return(Math.abs(player.getX() - (this.getX()-1)) + Math.abs(player.getY() - this.getY()));
	}
	
	/**
	 * find the distance from enemy to player
	 * @param player
	 * @return
	 */
	public int distanceRight(Player player) {
		if (player.getDungeon().checkAdjacent(this.getX(), this.getY(), "right", null)) {
			return -1;
		}
		return(Math.abs(player.getX() - (this.getX()+1)) + Math.abs(player.getY() - this.getY()));
	}
	
	/**
	 * depending on player state, enemy will either die from the enemy, or kill the enemy.
	 */
	@Override
	public void collide() {
		Player player = getDungeon().getPlayer();
		if(!checkPos(getX(),getY(),player.getX(), player.getY())) return;
		// when a player collides with an enemy,
		// enemy/player will die depending on player's state
		player.metEnemy(this);
	}
	
	/**
	 * if player has sword or potion and collides with enemy.  Enemy will die and its 
	 * movement pattern set to Dead.
	 * @param player
	 */
	public void die(Player player) {
		Dungeon d = player.getDungeon();
		d.removeEntity(this);
		setDead(true);
		notifyObservers();
	}
	
	
	/**
	 * Similar move function that player uses.
	 * @param direction
	 * @param player
	 */
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
	
	/**
	 * Update will handle change in movement Strategy when player consumes a potion, or if 
	 * that potion duration expires.
	 */
	@Override
	public void update(Subject obj) {
		// do something when a player notifies that it has picked up/dropped a potion/ sword
		
		if (((Player)obj).getState().equals(((Player)obj).getPotionState())){
			this.setMoveOption(new MoveAway());
			moveEnemy(((Player)obj));
		}
		else {
			this.setMoveOption(new MoveTowards());
			moveEnemy(((Player)obj));
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
	
	@Override
	public boolean checkCollision(int x, int y, String direction) {
		//check the entity that is colliding
		if(!getDungeon().checkIfBoulder(x, y, direction)) {
			return false;
		}
		return true;
	}
	
	public BooleanProperty getDeadProp() {
		return dead;
	}
	
	public boolean getDead() {
		return dead.get();
	}
	
	public void setDead(boolean b) {
		this.dead.set(b);
	}
}
