package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {

		private ArrayList<Entity> inven;
		
		public Inventory() {
			this.inven = new ArrayList<>();
		}
		
		public void addToInv(Entity e, Dungeon dungeon) {
			if(e instanceof Key) {
				((Key)e).unlockDoor(dungeon);
			}
			inven.add(e);
		}
		
		
		/**
		 * Check if there are any keys, swords, or potions already existing in players inventory.
		 * It will return true if there are any instances of the object within it.
		 * @param e
		 * @return
		 */
		public boolean inInventory(Entity e) {
			
			for(Entity inInventory: inven) {
				if(inInventory instanceof Key && e instanceof Key)
					return true;
				if(inInventory instanceof Sword && e instanceof Sword) 
					return true;
				if(inInventory instanceof Potion && e instanceof Potion)
					return true;
			}
			return false;
		}
		
		public void removeKey() {
			for(Entity inInventory: inven) {
				if(inInventory instanceof Key) {
					inven.remove(inInventory);
				}
			}
		}
}
