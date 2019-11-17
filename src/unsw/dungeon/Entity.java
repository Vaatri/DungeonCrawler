package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private Dungeon dungeon;
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeon = null;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    /**
     * set entity's x and y value
     * @param xVal
     * @param yVal
     */
    public void setXandY(int xVal, int yVal) {
    	x().set(xVal);
    	y().set(yVal);
    }
    
    /**
     * check if the entity is a boulder
     * @param el
     * @return
     */
	public boolean checkIfBoulder(List<Entity> el) {
		for(Entity e: el) {
			if (e instanceof Boulder) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Handler function when player collides with an entity
	 * @param player
	 * @param x
	 * @param y
	 * @param direction
	 */
	public void collide(){
		
	}
	
	public boolean checkCollision(int x, int y, String dir) {
		return false;
	}
	
	public String getType() {
		return "";
	}
	
	/**
	 * check if arg1 is equal to arg3 and arg 2 is equal to arg4
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean checkPos(int x1, int y1, int x2, int y2) {
		if(x1 == x2 && y1 == y2) {
			return true;
		}
		return false;
	}
	
	public void setDungeon(Dungeon d) {
		this.dungeon = d;
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}
	
}
