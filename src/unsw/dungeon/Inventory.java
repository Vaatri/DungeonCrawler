package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {

		private Sword sword;
		private Key key;
		private int treasure;
		private Potion potion;
		//refactor
		//private ArrayList<Collectable> inven;
//		private boolean hasSword;
//		private boolean hasPotion;
//		private boolean hasKey;
		
		
		public Inventory() {
//			this.inven = new ArrayList<>();
			this.sword = null;
			this.key = null;
			this.treasure = 0;
			this.potion = null;
//			this.hasSword = false;
//			this.hasPotion = false;
		}
		
//		/**
//		 * Add entity into player inventory
//		 * @param e
//		 * @param dungeon
//		 * @return
//		 */
//		public boolean addToInv(Collectable c, Dungeon dungeon) {
//			
//			if (!inInventory(e)) {
//				inven.add(e);
//				return true;
//			}
//			return false;
//			
//			
//			//refactoring 
////			if(c.getInside() != null) {
////				inven.add(c);
////				
////			}
//		}
//		
//		
//		/**
//		 * Check if there are any keys, swords, or potions already existing in players inventory.
//		 * It will return true if there are any instances of the object within it.
//		 * @param e
//		 * @return
//		 */
//		public boolean inInventory(Entity e) {
//			
//			for(Entity inInventory: inven) {
//				if(inInventory instanceof Key && e instanceof Key)
//					return true;
//				if(inInventory instanceof Sword && e instanceof Sword) 
//					return true;
//				if(inInventory instanceof Potion && e instanceof Potion)
//					return true;
//			}
//			return false;
//		}
		
		
//		/**
//		 * Remove the item passed in as an arguement
//		 * @param e
//		 */
//		public void removeItem(Entity e) {
//			for(Entity in: inven) {
//				if(in.equals(e)) {
//					inven.remove(e);
//					break;
//				}
//			}
//		}
		/**
		 * return true if player has sword in inventory
		 * @return
		 */
		public boolean hasSword() {
			if(sword != null)
				return true;
			else 
				return false;
		}
		
		/**
		 * return Key within player's inventory.
		 * @return
		 */
		public Key getKey() {
			return key;
		}
		/**
		 * return true/false depending on if player has a key in its inventory.
		 * @return
		 */
		public boolean hasKey() {
			if (key != null) {
				return true;
			} else 
				return false;
		}
		/**
		 * return true/false depending on if player has a potion in its inventory.
		 * @return
		 */
		public boolean hasPotion() {
			if(potion != null)
				return true;
			else 
				return false;
		}	
		/**
		 * return true/false depending on if player has a treasure in its inventory.
		 * @return
		 */
		public boolean hasTreasure() {
//			for(Entity inInventory: inven) {
//				if(inInventory instanceof Treasure) {
//					return true;
//				}
//			}return false;
			
			
			//refactor
			if (treasure == 0)
				return false;
			else 
				return true;
		}
		
		/**
		 * return how much Treasure is in palyer inventory
		 * @return
		 */
		public int howManyTreasure() {
			return treasure;
		}

		public Sword getSword() {
			return sword;
		}

		public void setSword(Sword sword) {
			this.sword = sword;
		}

		public int getTreasure() {
			return treasure;
		}

		public void setTreasure() {
			this.treasure++;
		}

		public Potion getPotion() {
			return potion;
		}

		public void setPotion(Potion potion) {
			this.potion = potion;
		}


		public void setKey(Key key) {
			this.key = key;
		}
		
		public void removeKey() {
			this.key = null;
		}
		
		public void removeSword() {
			this.sword = null;
		}
		
		public void removePotion() {
			this.potion = null;
		}
}
