package unsw.dungeon;

import java.util.List;

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

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
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
    
    public void setXandY(int xVal, int yVal) {
    	x().set(xVal);
    	y().set(yVal);
    }
    
	public boolean checkIfBoulder(List<Entity> el) {
		for(Entity e: el) {
			if (e instanceof Boulder) {
				return true;
			}
		}
		return false;
	}
	
	public void collide(Player player, int x, int y, String direction) {
		
	}
}
