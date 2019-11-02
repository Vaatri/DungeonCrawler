package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonTestLoader;
import unsw.dungeon.*;

class DungeonInventoryTest {

	@Test
	void inInventory() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("inInventory.json");
		Dungeon dungeon = dungeonLoader.load();
		Inventory i = dungeon.getPlayer().getInventory();
		Player p = dungeon.getPlayer();
		
		// picked up treasure is in the inventory
		assertTrue(!i.hasTreasure());
		p.setXandY(1, 0);
		p.moveDown();
		assertTrue(i.hasTreasure() == true);
		
		// picked up key is in the inventory
		assertTrue(!i.hasKey());
		p.setXandY(3, 0);
		p.moveDown();
		assertTrue(i.hasKey());
		// key used to open the door is removed from inventory
		p.moveDown();
		assertTrue(!i.hasKey());
		
		// picked up potion is in the inventory
		assertTrue(!i.hasPotion());
		p.setXandY(5, 0);
		p.moveDown();
		assertTrue(i.hasPotion());
		// potion used after moving 10 steps is removed from inventory
		p.moveDown(); // 2
		p.moveUp(); // 3
		p.moveDown(); // 4
		p.moveUp(); // 5
		p.moveDown(); //6
		p.moveUp(); //7
		p.moveDown(); //8
		p.moveUp(); //9
		assertTrue(i.hasPotion());
		p.moveDown(); //10
		assertTrue(!i.hasPotion());
		
		
		// picked up sword is in the inventory
		assertTrue(!i.hasSword());
		p.setXandY(7, 0);
		p.moveDown();
		assertTrue(i.hasSword());
		
	}

}
