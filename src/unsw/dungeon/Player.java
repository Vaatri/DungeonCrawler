package unsw.dungeon;

import java.util.ArrayList;
import java.util.Optional;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inven;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inven = new Inventory();
    }

    public void moveUp() {
        movementHandler(this.getX(), this.getY() - 1, "up"); 
    }

    public void moveDown() {
//        if (getY() < dungeon.getHeight() - 1)
//            y().set(getY() + 1);
        movementHandler(this.getX(), this.getY() + 1, "down"); 
    }

    public void moveLeft() {
//        if (getX() > 0)
//            x().set(getX() - 1);
        movementHandler(this.getX() - 1, this.getY(), "left"); 
    }

    public void moveRight() {
//        if (getX() < dungeon.getWidth() - 1)
//            x().set(getX() + 1);
        movementHandler(this.getX() + 1, this.getY(), "right"); 
    }
    
    public void movementHandler(int x, int y, String direction) {
    	
    	ArrayList<Entity> entityList = dungeon.getEntityAtLocation(x, y);
    	// entities that a player cannot move to: wall, blocked (on the other side) portal, 
    	// locked door, boulder in a unmoveable position
    	boolean canMove = true;
    	if (entityList.size() == 0) {
    		// nothing is there
    		move(x, y, direction);
    		return;
    	}
    	if (entityList.size() > 1) {
    		// do something about it
    		// e.g. if boulder and floor switch, then 
    		return;
    	}
    	
    	// if only one in entityList, continue
    	Entity entityType = entityList.get(0);
    	
    	// wall
    	if(entityType instanceof Wall) {
    		return;
    	} 
    	// door
    	else if (entityType instanceof Door) {
    		Door door = (Door)entityType;
			door.move(this,x,y,direction);
			return;
    		
    	} 
    	// key
    	else if(entityType instanceof Key) {
    		inventoryHandler(entityType);
    		move(x, y, direction);
    	}
    	// boulder
    	else if (entityType instanceof Boulder) {
    		//check if moveable
    		if (checkAdjacent(x,y,direction, ((Boulder)entityType)))
    			return;
    		else { 
    			((Boulder) entityType).moveBoulder(x, y, direction);
    			move(x, y, direction);
    		}
    		
    	}
    	
    	//if entity is able to be picked up then handle the inventory.
    	if ((entityType instanceof Sword)|| (entityType instanceof Potion) || (entityType instanceof Treasure)){
    		
    		inventoryHandler(entityType);
    		move(x, y, direction);
    	}
    	
    	// portal
    	if(entityType instanceof Portal) {
    		Portal p = (Portal)entityType;
    		p.teleport(this, dungeon, x, y, direction);
    	} 
    	
    }
    
    
    public boolean checkAdjacent(int x, int y, String direction, Boulder b) {
    	//check for anything adjacent to the boulder that we want to push
		for(Entity e: dungeon.getEntitiesList()) {	
    		if ((e instanceof Boulder && !b.equals(b)) || (e instanceof Enemy) || 
    			(e instanceof Door) || (e instanceof Exit) || (e instanceof Wall)) {
				switch(direction) {
				case("up"):
					if (e.getX() == x && e.getY() == (y-1))
						return true;
					break;
				case("down"):
					if (e.getX() == x && e.getY() == (y+1))
						return true;
					break;
				case("left"):
					if (e.getX() == (x-1) && e.getY() == y)
						return true;
					break;
				case("right"):
					if (e.getX() == (x+1) && e.getY() == y) 
						return true;
					break;
				}
    		}
		}	
    	
    	return false;
    }
    
    /**
     * Will handle potential items that are picked up by the player.
     * @param e
     */
    public void inventoryHandler(Entity e) {
    	if(e instanceof Treasure) {
    		inven.addToInv(e);
    		// trigger goal
    		return;
    	} else {
    		// for key, sword and potion, add to inventory iff not in current inventory
    		if (!inven.inInventory(e)) {
    			inven.addToInv(e);
    		}
    		// picking up potion (add extra 10 seconds), and key (should drop then pick up)
    		else {
    			
    		}
    	} //remove the object from the map.
    }
    
    
    public void move(int x, int y, String direction) {
		switch(direction) {
		case("up"):
			if (getY() > 0)
	            y().set(getY() - 1);
			return;
		case("down"):
	        if (getY() < dungeon.getHeight() - 1)
	            y().set(getY() + 1);
			return;
		case("left"):
			if (getX() > 0)
	            x().set(getX() - 1);
			return;
		case("right"):
			if (getX() < dungeon.getWidth() - 1)
	            x().set(getX() + 1);
			return;
		}
    }
    
}
