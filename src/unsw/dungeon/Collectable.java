package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

public interface Collectable {
	
	/**
	 * marks entities that can be collected, as collectables
	 * @return
	 */
	public boolean inInventory();
	public BooleanProperty inInventoryProp();
	public void setInInvenProp(boolean b);
}
