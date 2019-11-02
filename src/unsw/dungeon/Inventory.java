package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {

		private ArrayList<Entity> inven;
		
		public Inventory() {
			this.inven = new ArrayList<>();
		}
		
		public boolean addToInv(Entity e, Dungeon dungeon) {
			
			if (!inInventory(e)) {
				inven.add(e);
				System.out.println("Adding: "+e+" to inventory");
				return true;
			}
			return false;
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
					break;
				}
			}
		}
		public boolean hasSword() {
			for(Entity inInventory: inven) {
				if (inInventory instanceof Sword) {
					return true;
				}
			}return false;
		}
		public void removePotion() {
			for(Entity inInventory: inven) {
				if (inInventory instanceof Potion) {
					inven.remove(inInventory);
					break;
				}
			}
		}
}
