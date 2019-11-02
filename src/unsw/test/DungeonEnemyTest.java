package unsw.test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class DungeonEnemyTest {

	@Test
	void testPlayerEmptyHand() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testPlayerEmptyHanded.json");
		Dungeon dungeon = dungeonLoader.load();
		//test player can die from all movement directions
		Player p = dungeon.getPlayer();
		assertTrue(p.getState().equals(p.getEmptyHandState()));
		p.moveUp();
		assertTrue(p.getState().equals(p.getPlayerDeadState()));
		p.setEmptyHandState(p.getEmptyHandState());
		p.setXandY(1, 1);
		p.moveDown();
		assertTrue(p.getState().equals(p.getPlayerDeadState()));
		p.setEmptyHandState(p.getEmptyHandState());
		p.setXandY(1, 1);
		p.moveLeft();
		assertTrue(p.getState().equals(p.getPlayerDeadState()));
		p.setEmptyHandState(p.getEmptyHandState());
		p.setXandY(1, 1);
		p.moveRight();
		assertTrue(p.getState().equals(p.getPlayerDeadState()));
		p.setEmptyHandState(p.getEmptyHandState());
		p.setXandY(1, 1);
		//Do it twice to test if enemies were affected
		p.moveUp();
		assertTrue(p.getState().equals(p.getPlayerDeadState()));
		p.setEmptyHandState(p.getEmptyHandState());
		p.setXandY(1, 1);
		p.moveDown();
		assertTrue(p.getState().equals(p.getPlayerDeadState()));
		p.setEmptyHandState(p.getEmptyHandState());
		p.setXandY(1, 1);
		p.moveLeft();
		assertTrue(p.getState().equals(p.getPlayerDeadState()));
		p.setEmptyHandState(p.getEmptyHandState());
		p.setXandY(1, 1);
		p.moveRight();
		assertTrue(p.getState().equals(p.getPlayerDeadState()));
		System.out.println("Passed Player Empty Handed State!");
	}
	
	@Test
	void testPlayerWithSword() throws FileNotFoundException{
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testPlayerEmptyHanded.json");
		Dungeon dungeon = dungeonLoader.load();
		//test player can kill from all movement directions
		Player p = dungeon.getPlayer();
		//give player sword
		Sword s = new Sword(1,1);
		p.getInventory().addToInv(s, dungeon);
		p.setState(p.getSwordState());
		p.getState().setSword(s);
		assertTrue(p.getState().equals(p.getSwordState()));
		assertTrue(dungeon.countEnemies() == 4);
		
		//test player moving to kill enemy
		p.moveLeft();
		assertTrue(dungeon.countEnemies() == 3);
		assertTrue(s.checkAttacksLeft() == 4);
		p.setXandY(1, 1);
		p.moveRight();
		assertTrue(dungeon.countEnemies() == 2);
		assertTrue(s.checkAttacksLeft() == 3);
		p.setXandY(1, 1);
		p.moveUp();
		assertTrue(dungeon.countEnemies() == 1);
		assertTrue(s.checkAttacksLeft() == 2);
		p.setXandY(1, 1);
		p.moveDown();
		assertTrue(dungeon.countEnemies() == 0);
		assertTrue(s.checkAttacksLeft() == 1);
		
		//test player killing Enemy on same block
		Enemy e = new Enemy(2,2);
		dungeon.addEntity(e);
		assertTrue(dungeon.countEnemies() == 1);
		p.setXandY(2, 0);
		e.move("up", p);
		p.moveDown();
		//test enemy is in correct state after killed enemy
		MoveDead md = new MoveDead();
		assertTrue(e.getMoveOption().equals(md));
		assertTrue(dungeon.countEnemies() == 0);
		
		//test that when sword uses are depleted, player goes back to empty state
		//and sword is lost from inventory
		assertTrue(s.checkAttacksLeft() == 0);
		assertTrue(p.getState().equals(p.getEmptyHandState()));
		assertTrue(!p.getInventory().hasSword());
		System.out.println("Passed Player With Sword State");
	}
	
	@Test
	void testPlayerWithPotion() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testPlayerPotion.json");
		Dungeon dungeon = dungeonLoader.load();
		Player p = dungeon.getPlayer();
		//create an enemy that will die from potion contact
		Enemy e = new Enemy(0,0);
		p.registerObserver(e);
		dungeon.addEntity(e);
		//create an enemy that will run away from potion state
		Enemy e2 = new Enemy(3,3);
		p.registerObserver(e2);
		dungeon.addEntity(e2);
		assertTrue(dungeon.countEnemies() == 2);
		//both enemys are initially moving towards player
		assertTrue(e.getMoveOption() instanceof MoveTowards);
		assertTrue(e2.getMoveOption() instanceof MoveTowards);
		//create a potion for player to pick up and consume
		Potion potion = new Potion(1,1);
		potion.collide(p, 1, 1, "up");
		assertTrue(p.getState().equals(p.getPotionState()));
		assertTrue(potion.getDuration() == 9);
		p.moveUp();
		p.moveLeft();
		//enemy 2 is now running from player and enemy 1 is dead
		assertTrue(e2.getMoveOption() instanceof MoveAway);
		assertTrue(potion.getDuration() == 7);
		assertTrue(dungeon.countEnemies() == 1);
		assertTrue(e.getMoveOption() instanceof MoveDead);
		
		

	}

}
