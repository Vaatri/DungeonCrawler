package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {

		private ArrayList<Entity> inven;
		
		public Inventory() {
			this.inven = new ArrayList<>();
		}
		
		public void addToInv(Entity e) {
			System.out.println("adding " +e+" into inventory!");
			inven.add(e);
		}
}
