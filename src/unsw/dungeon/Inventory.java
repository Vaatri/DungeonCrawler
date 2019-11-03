package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {

		private ArrayList<Entity> inven;
		
		public Inventory() {
			this.inven = new ArrayList<>();
		}
		
		/**
		 * Add entity into player inventory
		 * @param e
		 * @param dungeon
		 * @return
		 */
		public boolean addToInv(Entity e, Dungeon dungeon) {
			
			if (!inInventory(e)) {
				inven.add(e);
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
		
		
		/**
		 * Remove the item passed in as an arguement
		 * @param e
		 */
		public void removeItem(Entity e) {
			for(Entity in: inven) {
				if(in.equals(e)) {
					inven.remove(e);
					break;
				}
			}
		}
		/**
		 * return true if player has sword in inventory
		 * @return
		 */
		public boolean hasSword() {
			for(Entity inInventory: inven) {
				if (inInventory instanceof Sword) {
					return true;
				}
			}return false;
		}
		
		/**
		 * return Key within player's inventory.
		 * @return
		 */
		public Key getKey() {
			for(Entity e: inven) {
				if(e instanceof Key) {
					return (Key)e;
				}
			}
			return null;
		}
		/**
		 * return true/false depending on if player has a key in its inventory.
		 * @return
		 */
		public boolean hasKey() {
			for (Entity inInventory: inven) {
				if (inInventory instanceof Key) {
					return true;
				}
			}return false;
		}
		/**
		 * return true/false depending on if player has a potion in its inventory.
		 * @return
		 */
		public boolean hasPotion() {
			for (Entity inInventory: inven) {
				if(inInventory instanceof Potion) {
					return true;
				}
			}return false;
		}
		/**
		 * return true/false depending on if player has a treasure in its inventory.
		 * @return
		 */
		public boolean hasTreasure() {
			for(Entity inInventory: inven) {
				if(inInventory instanceof Treasure) {
					return true;
				}
			}return false;
		}
		
		/**
		 * return how much Treasure is in palyer inventory
		 * @return
		 */
		public int howManyTreasure() {
			int count = 0;
			for (Entity inInventory: inven) {
				if (inInventory instanceof Treasure) {
					count++;
				}
			}
			return count;
		}
}
