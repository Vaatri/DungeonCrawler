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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
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
    	System.out.println("hello");
    	//remove the bitch
    }
    
    public List<Entity> getEntitiesList(){
    	return entities;
    }
}
