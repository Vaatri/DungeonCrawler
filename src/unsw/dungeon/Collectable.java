package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

public interface Collectable {

	public boolean inInventory();
	public BooleanProperty inInventoryProp();
	public void setInInvenProp(boolean b);
}
