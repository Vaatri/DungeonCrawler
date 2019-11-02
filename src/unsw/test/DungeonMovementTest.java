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
		
		// test portal1 works
		dungeon.getPlayer().setXandY(1, 0);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 1));
		dungeon.getPlayer().setXandY(1, 2);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 1));
		dungeon.getPlayer().setXandY(2, 1);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 1));
		dungeon.getPlayer().setXandY(0, 1);
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 1));
		
		// walk through open door
		dungeon.getPlayer().setXandY(3, 2);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 3));
		dungeon.getPlayer().setXandY(3, 4);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 3));
		dungeon.getPlayer().setXandY(4, 3);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 3));
		dungeon.getPlayer().setXandY(2, 3);
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 3));
		
		// walk through potion
		dungeon.getPlayer().setXandY(2, 3);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 2) &&(dungeon.getPlayer().getY() == 4));
		dungeon.getPlayer().setXandY(2, 5);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 2) &&(dungeon.getPlayer().getY() == 4));
		dungeon.getPlayer().setXandY(3, 4);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 2) &&(dungeon.getPlayer().getY() == 4));
		dungeon.getPlayer().setXandY(1, 4);
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 2) &&(dungeon.getPlayer().getY() == 4));
		
		// walk through floor switch
		dungeon.getPlayer().setXandY(4, 3);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 4) &&(dungeon.getPlayer().getY() == 4));
		dungeon.getPlayer().setXandY(4, 5);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 4) &&(dungeon.getPlayer().getY() == 4));
		dungeon.getPlayer().setXandY(5, 4);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 4) &&(dungeon.getPlayer().getY() == 4));		
		dungeon.getPlayer().setXandY(3, 4);
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 4) &&(dungeon.getPlayer().getY() == 4));
		
		// walk through exit
		dungeon.getPlayer().setXandY(1, 5);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 1) &&(dungeon.getPlayer().getY() == 6));
		dungeon.getPlayer().setXandY(1, 7);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 1) &&(dungeon.getPlayer().getY() == 6));
		dungeon.getPlayer().setXandY(2, 6);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 1) &&(dungeon.getPlayer().getY() == 6));		
		dungeon.getPlayer().setXandY(0, 6);
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 1) &&(dungeon.getPlayer().getY() == 6));
		
		// walk through treasure
		dungeon.getPlayer().setXandY(3, 5);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 6));
		dungeon.getPlayer().setXandY(3, 7);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 6));
		dungeon.getPlayer().setXandY(4, 6);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 6));		
		dungeon.getPlayer().setXandY(2, 6);
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 6));

		// walk through key
		dungeon.getPlayer().setXandY(5, 5);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 6));
		dungeon.getPlayer().setXandY(5, 7);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 6));
		dungeon.getPlayer().setXandY(6, 6);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 6));		
		dungeon.getPlayer().setXandY(4, 6);
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 6));

		
	}
	@Test
	void invalidMovement() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("invalidMovement.json");
		Dungeon dungeon = dungeonLoader.load();
		
		// cannot walk outside the map
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 0));
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 0));
		dungeon.getPlayer().setXandY(0, 4);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 0) &&(dungeon.getPlayer().getY() == 4));
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 0) &&(dungeon.getPlayer().getY() == 4));
		
		// cannot walk through doors with locked state
		ArrayList<Entity> d = dungeon.getEntityAtLocation(3, 1);
		((Door)d.get(0)).setState(((Door)d.get(0)).getLockedState());
		dungeon.getPlayer().setXandY(3, 0);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 0));
		dungeon.getPlayer().setXandY(3, 2);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 3) &&(dungeon.getPlayer().getY() == 2));
		dungeon.getPlayer().setXandY(4, 1);
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 4) &&(dungeon.getPlayer().getY() == 1));
		dungeon.getPlayer().setXandY(2, 1);
		dungeon.getPlayer().moveRight();
		assertTrue((dungeon.getPlayer().getX() == 2) &&(dungeon.getPlayer().getY() == 1));
		
		// cannot push a boulder blocked by another boulder
		dungeon.getPlayer().setXandY(2, 2);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 2) &&(dungeon.getPlayer().getY() == 2));
		
		// cannot push a boulder blocked by an exit
		dungeon.getPlayer().setXandY(3, 3);
		dungeon.getPlayer().moveLeft();
		dungeon.getPlayer().moveLeft();
		assertTrue((dungeon.getPlayer().getX() == 2) &&(dungeon.getPlayer().getY() == 3));
		
		// cannot push a boulder blocked by a wall
		dungeon.getPlayer().setXandY(4, 5);
		dungeon.getPlayer().moveUp();
		assertTrue((dungeon.getPlayer().getX() == 4) &&(dungeon.getPlayer().getY() == 5));
		
		// cannot move a boulder blocked by a locked door
		d = dungeon.getEntityAtLocation(5, 3);
		((Door)d.get(0)).setState(((Door)d.get(0)).getLockedState());
		dungeon.getPlayer().setXandY(5, 1);
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 1));
		
		// cannot move a boulder blocked by an unlocked door
		((Door)d.get(0)).setState(((Door)d.get(0)).getUnlockedState());
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 1));
		
		// cannot move a boulder blocked by an open door
		((Door)d.get(0)).setState(((Door)d.get(0)).getOpenState());
		dungeon.getPlayer().moveDown();
		assertTrue((dungeon.getPlayer().getX() == 5) &&(dungeon.getPlayer().getY() == 1));
		
		
		
		
		
		
		
		
		
		
	}
}
