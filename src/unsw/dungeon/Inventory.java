package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {

		private Sword sword;
		private Key key;
		private int treasure;
		private Potion potion;
		
		public Inventory() {
			this.sword = null;
			this.key = null;
			this.treasure = 0;
			this.potion = null;
		}
		
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
			if (treasure == 0)
				return false;
			else 
				return true;
		}
		
		/**
		 * return how many Treasure is in player inventory
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

		public void setTreasure(Treasure t) {
			t.setInInvenProp();
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
