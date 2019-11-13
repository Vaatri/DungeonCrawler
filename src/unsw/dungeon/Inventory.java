package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Inventory {

		private Sword sword;
		private Key key;
		private int treasure;
		private Potion potion;
		private BooleanProperty hasSword;
		private BooleanProperty hasKey;
		private BooleanProperty hasTreasure;
		private BooleanProperty hasPotion;
		
		public Inventory() {
			this.sword = null;
			this.key = null;
			this.treasure = 0;
			this.potion = null;
			this.hasKey = new SimpleBooleanProperty(false);
			this.hasSword = new SimpleBooleanProperty(false);
			this.hasPotion = new SimpleBooleanProperty(false);
			this.hasTreasure = new SimpleBooleanProperty(false);
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
		
		public BooleanProperty hasSwordProp() {
			return hasSword;
		}
		
		public void setSwordProp(boolean b) {
			hasSword.set(b);
		}
		public BooleanProperty hasPotionProp() {
			return hasPotion;
		}
		
		public void setPotionProp(boolean b) {
			hasPotion.set(b);
		}
		public BooleanProperty hasKeyProp() {
			return hasKey;
		}
		
		public void setKeyProp(boolean b) {
			hasKey.set(b);
		}
		public BooleanProperty hasTreasureProp() {
			return hasTreasure;
		}
		
		public void setTreasureProp(boolean b) {
			hasTreasure.set(b);
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

		public void setSword(Sword s) {
			s.setInInvenProp(true);
			setSwordProp(true);
			this.sword = s;
		}

		public int getTreasure() {
			return treasure;
		}

		public void setTreasure(Treasure t) {
			t.setInInvenProp(true);
			setTreasureProp(true);
			this.treasure++;
		}

		public Potion getPotion() {
			return potion;
		}

		public void setPotion(Potion p) {
			p.setInInvenProp(true);
			setPotionProp(true);
			this.potion = p;
		}


		public void setKey(Key k) {
			k.setInInvenProp(true);
			setKeyProp(true);
			this.key = k;
		}
		
		public void removeKey() {
			setKeyProp(false);
			this.key = null;
		}
		
		public void removeSword() {
			setSwordProp(false);
			this.sword = null;
		}
		
		public void removePotion() {
			setPotionProp(false);
			this.potion = null;
		}
}
