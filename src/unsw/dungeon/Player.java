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
    	System.out.println(entityType);
    	//if theres a wall then cant move
    	if(entityType == null) {
    		switch(direction) {
    		case("up"):
    			if (getY() > 0)
    	            y().set(getY() + 1);
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
    	if(entityType instanceof Wall) {
    		System.out.println("you have walked into a wall");
    	} else if (entityType instanceof Treasure) {
    		inven.addToInv(entityType);
    		//remove from map
    		dungeon.removeEntityFromMap(entityType);
    		System.out.println(inven);
    	}
    	
    }
    
}
