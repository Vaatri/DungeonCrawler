package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class DungeonMovementTest {

	@Test
	void validMovement() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("validMovement.json");
		Dungeon dungeon = dungeonLoader.load();
		Player p = dungeon.getPlayer();
		Door d = new Door(3,3, 1);
		dungeon.addEntity(d);
		d.setState(d.getOpenState());
		// test portal1 works
		p.setXandY(1, 0);
		p.moveDown();
		assertTrue((p.getX() == 5) &&(p.getY() == 1));
		p.setXandY(1, 2);
		p.moveUp();
		assertTrue((p.getX() == 5) &&(p.getY() == 1));
		p.setXandY(2, 1);
		p.moveLeft();
		assertTrue((p.getX() == 5) &&(p.getY() == 1));
		p.setXandY(0, 1);
		p.moveRight();
		assertTrue((p.getX() == 5) &&(p.getY() == 1));
		
		// walk through open door
		p.setXandY(3, 2);
		p.moveDown();
		assertTrue((p.getX() == 3) &&(p.getY() == 3));
		p.setXandY(3, 4);
		p.moveUp();
		assertTrue((p.getX() == 3) &&(p.getY() == 3));
		p.setXandY(4, 3);
		p.moveLeft();
		assertTrue((p.getX() == 3) &&(p.getY() == 3));
		p.setXandY(2, 3);
		p.moveRight();
		assertTrue((p.getX() == 3) &&(p.getY() == 3));
		
		// walk through potion
		p.setXandY(2, 3);
		p.moveDown();
		assertTrue((p.getX() == 2) &&(p.getY() == 4));
		p.setXandY(2, 5);
		p.moveUp();
		assertTrue((p.getX() == 2) &&(p.getY() == 4));
		p.setXandY(3, 4);
		p.moveLeft();
		assertTrue((p.getX() == 2) &&(p.getY() == 4));
		p.setXandY(1, 4);
		p.moveRight();
		assertTrue((p.getX() == 2) &&(p.getY() == 4));
		
		// walk through floor switch
		p.setXandY(4, 3);
		p.moveDown();
		assertTrue((p.getX() == 4) &&(p.getY() == 4));
		p.setXandY(4, 5);
		p.moveUp();
		assertTrue((p.getX() == 4) &&(p.getY() == 4));
		p.setXandY(5, 4);
		p.moveLeft();
		assertTrue((p.getX() == 4) &&(p.getY() == 4));		
		p.setXandY(3, 4);
		p.moveRight();
		assertTrue((p.getX() == 4) &&(p.getY() == 4));
		
		// walk through exit
		p.setXandY(1, 5);
		p.moveDown();
		assertTrue((p.getX() == 1) &&(p.getY() == 6));
		p.setXandY(1, 7);
		p.moveUp();
		assertTrue((p.getX() == 1) &&(p.getY() == 6));
		p.setXandY(2, 6);
		p.moveLeft();
		assertTrue((p.getX() == 1) &&(p.getY() == 6));		
		p.setXandY(0, 6);
		p.moveRight();
		assertTrue((p.getX() == 1) &&(p.getY() == 6));
		
		// walk through treasure
		p.setXandY(3, 5);
		p.moveDown();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(p.getY() == 6));
		p.setXandY(3, 7);
		p.moveUp();
		assertTrue((p.getX() == 3) &&(p.getY() == 6));
		p.setXandY(4, 6);
		p.moveLeft();
		assertTrue((p.getX() == 3) &&(p.getY() == 6));		
		p.setXandY(2, 6);
		p.moveRight();
		assertTrue((p.getX() == 3) &&(p.getY() == 6));

		// walk through key
		p.setXandY(5, 5);
		p.moveDown();
		assertTrue((p.getX() == 5) &&(p.getY() == 6));
		p.setXandY(5, 7);
		p.moveUp();
		assertTrue((p.getX() == 5) &&(p.getY() == 6));
		p.setXandY(6, 6);
		p.moveLeft();
		assertTrue((p.getX() == 5) &&(p.getY() == 6));		
		p.setXandY(4, 6);
		p.moveRight();
		assertTrue((p.getX() == 5) &&(p.getY() == 6));

		
	}
	@Test
	void invalidMovement() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("invalidMovement.json");
		Dungeon dungeon = dungeonLoader.load();
		Player p = dungeon.getPlayer();
		// cannot walk outside the map
		p.moveUp();
		assertTrue((p.getX() == 5) &&(p.getY() == 0));
		p.moveRight();
		assertTrue((p.getX() == 5) &&(p.getY() == 0));
		p.setXandY(0, 5);
		p.moveLeft();
		assertTrue((p.getX() == 0) &&(p.getY() == 5));
		p.moveDown();
		assertTrue((p.getX() == 0) &&(p.getY() == 5));
		
		// cannot walk through doors with locked state
		ArrayList<Entity> d = dungeon.getEntityAtLocation(3, 1);
		((Door)d.get(0)).setState(((Door)d.get(0)).getLockedState());
		p.setXandY(3, 0);
		p.moveDown();
		assertTrue((p.getX() == 3) &&(p.getY() == 0));
		p.setXandY(3, 2);
		p.moveUp();
		assertTrue((p.getX() == 3) &&(p.getY() == 2));
		p.setXandY(4, 1);
		p.moveLeft();
		assertTrue((p.getX() == 4) &&(p.getY() == 1));
		p.setXandY(2, 1);
		p.moveRight();
		assertTrue((p.getX() == 2) &&(p.getY() == 1));
		
		// cannot push a boulder blocked by another boulder
		p.setXandY(2, 2);
		p.moveDown();
		assertTrue((p.getX() == 2) &&(p.getY() == 2));
		
		// cannot push a boulder blocked by an exit
		p.setXandY(3, 3);
		p.moveLeft();
		p.moveLeft();
		assertTrue((p.getX() == 2) &&(p.getY() == 3));
		
		// cannot push a boulder blocked by a wall
		p.setXandY(4, 5);
		p.moveUp();
		assertTrue((p.getX() == 4) &&(p.getY() == 5));
		
		// cannot move a boulder blocked by a locked door
		d = dungeon.getEntityAtLocation(5, 3);
		((Door)d.get(0)).setState(((Door)d.get(0)).getLockedState());
		p.setXandY(5, 1);
		p.moveDown();
		assertTrue((p.getX() == 5) &&(p.getY() == 1));
		
		// cannot move a boulder blocked by an unlocked door
		((Door)d.get(0)).setState(((Door)d.get(0)).getUnlockedState());
		p.moveDown();
		assertTrue((p.getX() == 5) &&(p.getY() == 1));
		
		// cannot move a boulder blocked by an open door
		((Door)d.get(0)).setState(((Door)d.get(0)).getOpenState());
		p.moveDown();
		assertTrue((p.getX() == 5) &&(p.getY() == 1));
		
	}
	@Test
	void OpenDoor() throws FileNotFoundException{
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("openDoor.json");
		Dungeon dungeon = dungeonLoader.load();
		Player p = dungeon.getPlayer();
		Inventory i = p.getInventory();
		
		// player picks up a key, stays in inventory, key disappears
		assertTrue(!i.hasKey());
		p.moveUp();
		p.moveLeft();
		ArrayList<Entity>d2 = dungeon.getEntityAtLocation(1, 4);
		assertTrue(((Door)d2.get(0)).getState().equals(((Door)d2.get(0)).getUnlockedState()));
		assertTrue((p.getX() == 1) &&(p.getY() == 1));
		p.setXandY(3, 2);
		assertTrue(i.hasKey());
		ArrayList<Entity> e = dungeon.getEntityAtLocation(1, 1);
		assertTrue(e.size() == 0);
		
		// player cannot pick up two keys at the same time
		p.moveUp();
		p.moveLeft();
		ArrayList<Entity> k = dungeon.getEntityAtLocation(3, 1);
		assertTrue(k.size()!= 0);
		p.setXandY(3, 3);
		p.moveDown();
		// player cannot open a door with a different id key
		ArrayList<Entity> d = dungeon.getEntityAtLocation(3, 4);
		assertTrue(((Door)d.get(0)).getState().equals(((Door)d.get(0)).getLockedState()));
		assertTrue((p.getX() == 3) &&(p.getY() == 3));
		assertTrue(i.hasKey());
		
		// player can open a door with a same id as the key and once opened, key is removed from inventory
		p.setXandY(1, 3);
		p.moveDown();
		assertTrue(((Door)d2.get(0)).getState().equals(((Door)d2.get(0)).getOpenState()));
		assertTrue(!i.hasKey());
	}
	@Test
	void teleportPortal() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("teleportPortal.json");
		Dungeon dungeon = dungeonLoader.load();
		Player p = dungeon.getPlayer();
		ArrayList<Entity> e0 = dungeon.getEntityAtLocation(5, 3);
		Enemy enemy = ((Enemy)e0.get(0));
		
		// portal has state unblocked initially
		ArrayList<Entity> e1 = dungeon.getEntityAtLocation(1, 2);
		Portal p1 = ((Portal)e1.get(0));
		ArrayList<Entity> e2 = dungeon.getEntityAtLocation(3, 1);
		Portal p2 = ((Portal)e2.get(0));
		assertTrue(!p1.checkBlocked(p1.getX(),p1.getY(), dungeon));
		assertTrue(!p2.checkBlocked(p2.getX(),p2.getY(), dungeon));
		
		// when a boulder is moved on top, its state changes to blocked
		p.moveRight();
		assertTrue((p.getX() == 2)&& (p.getY() == 1));
		assertTrue(p2.checkBlocked(p2.getX(), p2.getY(), dungeon));
		
		// a portal can teleport a player into an unblocked portal 
		// and can be used more than once
		p.setXandY(1, 1);
		p.moveDown();
		assertTrue((p.getX() == 1)&& (p.getY() == 2));
		p.setXandY(3, 0);
		p.moveDown();
		assertTrue((p.getX() == 1)&& (p.getY() == 2));
		assertTrue(!p2.checkBlocked(p2.getX(), p2.getY(), dungeon));
		
		p.moveUp();
		p.moveDown();
		assertTrue((p.getX() == 3)&& (p.getY() == 1));
		
		// only a player can walk through portals
		enemy.setXandY(1, 2);
		assertTrue((enemy.getX() == 1)&&(enemy.getY() == 2));
		
	}
}
