package unsw.dungeon;

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
    	
    	Entity entityType = dungeon.getEntityAtLocation(x, y);
    	
    	boolean canMove = true;
    	
    	if(entityType instanceof Wall) {
    		canMove = false;
    	} else if (entityType instanceof Door) {
    		//check if open else
    		canMove = false;
    	} else if (entityType instanceof Boulder) {
    		//check if moveable
    		if (checkAdjacent(x,y,direction, ((Boulder)entityType)))
    			canMove = false;
    		else 
    			((Boulder) entityType).moveBoulder(x, y, direction);
    	}
    	//if cant move then do nothing
    	if(!canMove) return;
    	
    	
    	//if entity is able to be picked up then handle the inventory.
    	if ((entityType instanceof Key) || (entityType instanceof Sword)
    			|| (entityType instanceof Potion) || (entityType instanceof Treasure)){
    		
    		inventoryHandler(entityType);
    	}
    	
    	//if entity isnt a portal then we can move freely.
    	if(entityType instanceof Portal) {
    		Portal p = (Portal)entityType;
    		p.teleport(this);
    	} else {
    		//todo teleport the player to different position
    		move(x, y, direction);
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
    		return;
    	} else {
    		if (!inven.inInventory(e)) {
    			inven.addToInv(e);
    			//remove the object from the map.
    		}
    	}

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
