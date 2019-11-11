/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }
    
    public int getPlayerX() {
    	return player.getX();
    }
    
    public int getPlayerY() {
    	return player.getY();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    
    /**
     * EveryTime play moves, dungeon should check for collisions.
     */
    public void handleCollision() {
    	for(int col = 0; col < width; col++) {
    		for(int row = 0; row < height; row++) {
    			ArrayList<Entity> entityAtLoc = getEntityAtLocation(col, row);
    			if(entityAtLoc.size()>1) {
    				System.out.println(entityAtLoc.get(0));
    				System.out.println(entityAtLoc.get(1));
    				entityAtLoc.get(0).collide();
    				entityAtLoc.get(1).collide();
    			}
    		}	
    	}
    }
    
    public Portal findLinkedPortal(Portal p) {
    	for(Entity e: entities) {
    		if(e instanceof Portal && !e.equals(p)) {
    			if(((Portal)e).getPortalID() == p.getPortalID()) {
    				return ((Portal)e);
    			}
    		}
    	}
    	
    	return null;
    }
    
    /**
     * Check if there will be a collision at the coordinates player wants
     * to move towards. If there is a collision, player will move depending 
     * on the interaction with the entity at the location.
     * else player will move.
     * @param x
     * @param y
     * @param direction
     * @return
     */
    public boolean checkCollision(int x, int y, String direction) {
    	ArrayList<Entity> entityAtLoc = getEntityAtLocation(x,y);
    	for(Entity e: entityAtLoc) {
    		if(!e.checkCollision(x,y,direction)) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public boolean checkIfBoulder(int x, int y, String dir) {
		int checkX = x;
		int checkY = y;
		switch(dir){
		case"up":
			checkY += 1;
			break;
		case"down":
			checkY -= 1;
			break;
		case"left":
			checkX += 1;
			break;
		case "right":
			checkX -= 1;
		}
		//get the enttity that is colliding
		ArrayList<Entity> el = getEntityAtLocation(checkX, checkY);
		for(Entity e: el) {
			if(e instanceof Boulder) {
				return false;
			}
		}
		return true;	
    }
    
    
    /**
     * This will return a List of entities at a coordinate.
     * @param x
     * @param y
     * @return
     */
    public ArrayList<Entity> getEntityAtLocation(int x, int y) {
    	ArrayList<Entity> entityList = new ArrayList<Entity>();
    	for(Entity e: entities) {
    		if (e != null) {
	    		if(e.getX() == x && e.getY() == y) {
	    			entityList.add(e);
	    		}
    		}	
    	}
    	return entityList;
    }
    
    public void removeEntityFromMap(Entity e) {
    	//todo 
    }
    
    /**
     * return a list of entities from dungeon
     * @return
     */
    public List<Entity> getEntitiesList(){
    	return entities;
    }
    
    /**
     * This will check a given direction's adjacent block for an immovable entity.
     * @param x
     * @param y
     * @param direction
     * @param b
     * @return
     */
    public boolean checkAdjacent(int x, int y, String direction, Boulder b) {
    	//System.out.println("checkAdjacent: "+ x+" "+y);
    	//check for anything adjacent to the boulder that we want to push
		for(Entity e: entities) {	
    		if ((e instanceof Boulder && !e.equals(b)) || (e instanceof Enemy) || 
    			(e instanceof Door) || (e instanceof Player)|| (e instanceof Exit) || (e instanceof Wall)) {
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
     * helper function for testing
     * @return
     */
    public int countEnemies() {
    	int count = 0;
    	for(Entity e: entities) {
    		if (e instanceof Enemy){
    			count++;
    		}
    	}
    	return count;
    }
    
    public void removeEntity(Entity e) {
    	entities.remove(e);
    }
    
}
