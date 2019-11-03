package unsw.test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

class DungeonGoalTest {

	@Test
	void testValidSingleExitGoal() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testSingleGoalExit.json");
		Dungeon dungeon = dungeonLoader.load();	
		Player p = dungeon.getPlayer();
		ComplexGoal cG = p.getPlayerGoals();
		
		//check that there is indeed only 1 goal
		Goal g = cG.getGoalList().get(0);
		assertEquals(g.getType(), "exit");
		assertFalse(g.checkCompleted());
		assertTrue(g.getGoalPoints() == 3);
		
		//check if there is any extra goals
		try {
			Goal gNull = cG.getGoalList().get(1);
			fail("Expected an exception to be thrown");
		} catch (Exception e){
			assertEquals(e.getMessage(), "Index 1 out of bounds for length 1");
		}
		
		p.moveUp();
		assertTrue(g.checkCompleted());
		assertTrue(cG.checkCompleted());
		System.out.println("PASSED Single Exit Goal!");
		
	}

	@Test
	void testValidSingleTreasureGoal() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testSingleGoalTreasure.json");
		Dungeon dungeon = dungeonLoader.load();	
		Player p = dungeon.getPlayer();
		ComplexGoal cG = p.getPlayerGoals();
		
		//check that there is indeed only 1 goal
		Goal g = cG.getGoalList().get(0);
		assertEquals(g.getType(), "treasure");
		assertFalse(g.checkCompleted());
		assertTrue(g.getGoalPoints() == 3);
		
		p.moveDown();
		assertTrue(g.checkCompleted());
		assertTrue(cG.checkCompleted());
		System.out.println("PASSED Single Treasure Goal! tests");
	}
	
	@Test
	void testValidSingleSwitchGoal() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testSinglegoalSwitch.json");
		Dungeon dungeon = dungeonLoader.load();	
		Player p = dungeon.getPlayer();
		ComplexGoal cG = p.getPlayerGoals();
		
		FloorSwitch fs = (FloorSwitch)dungeon.getEntitiesList().get(2);
		assertFalse(fs.getTriggerStatus());
		
		Goal g = cG.getGoalList().get(0);
		assertEquals(g.getType(), "switch");
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		assertTrue(g.getGoalPoints() == 3);
		//trigger the switch to satisfy goal
		p.moveRight();
		assertTrue(fs.getTriggerStatus());
		assertTrue(g.checkCompleted());
		assertTrue(cG.checkCompleted());
		
		//untrigger the switch and check if goal is incompleted.
		p.moveRight();
		assertFalse(fs.getTriggerStatus());
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		
		System.out.println("PASSED Single Switch Goal tests!");
	}
	
	@Test
	void testValidSingleEnemyGoal() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testSingleGoalEenemy.json");
		Dungeon dungeon = dungeonLoader.load();	
		Player p = dungeon.getPlayer();
		ComplexGoal cG = p.getPlayerGoals();
		
		//Test complete enemy Goal with Sword
		Sword s = new Sword(0,2);
		p.getInventory().addToInv(s, dungeon);
		p.setState(p.getSwordState());
		p.getState().setSword(s);
		
		Goal g = cG.getGoalList().get(0);
		assertEquals(g.getType(), "enemies");
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		assertTrue(g.getGoalPoints() == 3);
		
		p.moveLeft();
		assertTrue(g.checkCompleted());
		assertTrue(cG.checkCompleted());
		
		
		dungeonLoader = new DungeonTestLoader("testSingleGoalEenemy.json");
		dungeon = dungeonLoader.load();	
		p = dungeon.getPlayer();
		cG = p.getPlayerGoals();
		
		//Test complete enemy goal with Potion
		Potion pot = new Potion(0 ,2);
		p.getInventory().addToInv(pot, dungeon);
		p.setState(p.getPotionState());
		p.getState().setPotion(pot);
		p.moveLeft();
		assertTrue(g.checkCompleted());
		assertTrue(cG.checkCompleted());
		
		System.out.println("PASSED Single Enemy Goal tests!");
		
		
	}
	
	@Test
	void testValidSingleGoalWithMultipleEntities() throws FileNotFoundException {
		
		//Load in Treasure Map.
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testMultiTreasureGoal.json");
		Dungeon dungeon = dungeonLoader.load();	
		Player p = dungeon.getPlayer();
		ComplexGoal cG = p.getPlayerGoals();
		
		SingleGoal g = (SingleGoal)cG.getGoalList().get(0);
		assertEquals(g.getType(), "treasure");
		assertEquals(g.getGoalsSatisfied(), 0);
		assertEquals(g.getNeededToSatisfy(), 4);
		
		//Test multiple treasure for a Single goal.
		p.moveDown();
		assertEquals(g.getGoalsSatisfied(),1);
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveRight();
		assertEquals(g.getGoalsSatisfied(),2);
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveUp();
		assertEquals(g.getGoalsSatisfied(),3);
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveRight();
		assertEquals(g.getGoalsSatisfied(),4);
		assertTrue(g.checkCompleted());
		assertTrue(cG.checkCompleted());
		System.out.println("Passed Multiple Treasure Single Goal test!");
		
		
		//Load in multiple Enemies test
		dungeonLoader = new DungeonTestLoader("testMultiEnemiesGoal.json");
		dungeon = dungeonLoader.load();
		p = dungeon.getPlayer();
		cG = p.getPlayerGoals();
		g = (SingleGoal)cG.getGoalList().get(0);
		
		Sword s = new Sword(0,2);
		p.getInventory().addToInv(s, dungeon);
		p.setState(p.getSwordState());
		p.getState().setSword(s);
		
		//Test multiple Enemies for a Single Goal
		p.moveDown();
		assertEquals(g.getGoalsSatisfied(),1);
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveRight();
		assertEquals(g.getGoalsSatisfied(),2);
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveUp();
		assertEquals(g.getGoalsSatisfied(),3);
		assertTrue(g.checkCompleted());
		assertTrue(cG.checkCompleted());
		System.out.println("Passed Multiple Enemies Single Goal Test!");
		
		
		//Load in multiple switch goal test
		dungeonLoader = new DungeonTestLoader("testMultiSwitchGoal.json");
		dungeon = dungeonLoader.load();
		p = dungeon.getPlayer();
		cG = p.getPlayerGoals();
		g = (SingleGoal)cG.getGoalList().get(0);
		assertEquals(g.getType(), "switch");
		assertEquals(g.getNeededToSatisfy(), 3);
		assertEquals(g.getGoalsSatisfied(), 0);
		
		//Test multiple switches for a Single goal
		p.moveDown();
		assertEquals(g.getGoalsSatisfied(),1);
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveUp();
		p.moveRight();
		p.moveDown();
		assertEquals(g.getGoalsSatisfied(),2);
		assertFalse(g.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveUp();
		p.moveRight();
		p.moveDown();
		assertEquals(g.getGoalsSatisfied(),3);
		assertTrue(g.checkCompleted());
		assertTrue(cG.checkCompleted());
		System.out.println("Passed Multiple Switch Single Goal Test!");
		
		System.out.println("Passed Multiple Entries Goal Tests!");
		
	}
	
	@Test
	void testValidORGoalType() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testORGoalTreasureExit.json");
		Dungeon dungeon = dungeonLoader.load();	
		Player p = dungeon.getPlayer();
		ComplexGoal cG = p.getPlayerGoals();
		
		//Test OR goals from treasure->exit
		SingleGoal exitGoal = (SingleGoal)cG.getGoalList().get(0);
		SingleGoal treasureGoal = (SingleGoal)cG.getGoalList().get(1);
		assertFalse(exitGoal.checkCompleted());
		assertFalse(treasureGoal.checkCompleted());
		assertFalse(cG.checkCompleted());
		
		p.moveDown();
		assertTrue(treasureGoal.checkCompleted());
		assertFalse(exitGoal.checkCompleted());
		
		p.moveUp();
		p.moveRight();
		assertTrue(treasureGoal.checkCompleted());
		assertTrue(exitGoal.checkCompleted());
		assertTrue(cG.checkCompleted());
		
		//Test from the other direction exit->Treasure
		dungeonLoader = new DungeonTestLoader("testORGoalTreasureExit.json");
		dungeon = dungeonLoader.load();	
		p = dungeon.getPlayer();
		cG = p.getPlayerGoals();
		
		exitGoal = (SingleGoal)cG.getGoalList().get(0);
		treasureGoal = (SingleGoal)cG.getGoalList().get(1);
		
		assertFalse(exitGoal.checkCompleted());
		assertFalse(treasureGoal.checkCompleted());
		assertFalse(cG.checkCompleted());
		
		p.moveRight();
		assertFalse(treasureGoal.checkCompleted());
		assertTrue(exitGoal.checkCompleted());
		
		p.moveLeft();
		p.moveDown();
		assertTrue(treasureGoal.checkCompleted());
		assertTrue(exitGoal.checkCompleted());
		assertTrue(cG.checkCompleted());
		
		//Test Or Goal Enemy->Switch
		DungeonTestLoader dungeonLoader2 = new DungeonTestLoader("testORGoalEnemySwitch.json");
		Dungeon dungeon2 = dungeonLoader2.load();	
		Player p2 = dungeon2.getPlayer();
		ComplexGoal cG2 = p2.getPlayerGoals();
		
		SingleGoal enemyGoal = (SingleGoal)cG2.getGoalList().get(0);
		SingleGoal switchGoal = (SingleGoal)cG2.getGoalList().get(1);
		System.out.println(switchGoal.getType());
		assertEquals(switchGoal.getType(), "switch");
		assertFalse(enemyGoal.checkCompleted());
		assertFalse(switchGoal.checkCompleted());
		assertFalse(cG2.checkCompleted());
		
		//give player sword to kill enemy
		Sword s = new Sword(0,0);
		dungeon2.addEntity(s);
		p2.getInventory().addToInv(s, dungeon2);
		p2.setState(p2.getSwordState());
		p2.getState().setSword(s);
		
		p2.moveDown();
		assertTrue(enemyGoal.checkCompleted());
		assertFalse(switchGoal.checkCompleted());
		
		p2.moveUp();
		p2.moveRight();
		assertTrue(enemyGoal.checkCompleted());
		assertTrue(switchGoal.checkCompleted());
		assertTrue(cG2.checkCompleted());
		
		//test 4 differnt OR Goals
		dungeonLoader = new DungeonTestLoader("testORAll.json");
		dungeon = dungeonLoader.load();	
		p = dungeon.getPlayer();
		cG = p.getPlayerGoals();
		
		
		enemyGoal = (SingleGoal)cG.getGoalList().get(0);
		switchGoal = (SingleGoal)cG.getGoalList().get(1);
		treasureGoal = (SingleGoal)cG.getGoalList().get(2);
		exitGoal = (SingleGoal)cG.getGoalList().get(3);
		assertEquals(switchGoal.getType(), "switch");
		assertEquals(enemyGoal.getType(), "enemies");
		assertEquals(treasureGoal.getType(), "treasure");
		assertEquals(exitGoal.getType(), "exit");
		
		assertFalse(enemyGoal.checkCompleted());
		assertFalse(switchGoal.checkCompleted());
		assertFalse(treasureGoal.checkCompleted());
		assertFalse(exitGoal.checkCompleted());
		assertFalse(cG.checkCompleted());
		
		s = new Sword(1,2);
		dungeon.addEntity(s);
		p.getInventory().addToInv(s, dungeon);
		p.setState(p.getSwordState());
		p.getState().setSword(s);
		
		p.moveDown();
		assertTrue(enemyGoal.checkCompleted());
		assertFalse(switchGoal.checkCompleted());
		assertFalse(treasureGoal.checkCompleted());
		assertFalse(exitGoal.checkCompleted());
		
	}
	
	@Test
	void testValidANDGoalType() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testANDTreasureExit.json");
		Dungeon dungeon = dungeonLoader.load();	
		Player p = dungeon.getPlayer();
		ComplexGoal cG = p.getPlayerGoals();
		
		SingleGoal treasureGoal = (SingleGoal)cG.getGoalList().get(0);
		assertEquals(treasureGoal.getType(), "treasure");
		SingleGoal exitGoal = (SingleGoal)cG.getGoalList().get(1);
		
		assertFalse(treasureGoal.checkCompleted());
		assertFalse(exitGoal.checkCompleted());
		assertFalse(cG.checkCompleted());
		
		p.moveDown();
		assertTrue(treasureGoal.getGoalsSatisfied() == 1);
		assertFalse(treasureGoal.checkCompleted());
		assertFalse(exitGoal.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveRight();
		assertTrue(treasureGoal.getGoalsSatisfied() == 2);
		assertFalse(treasureGoal.checkCompleted());
		assertFalse(exitGoal.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveRight();
		assertTrue(treasureGoal.getGoalsSatisfied() == 3);
		assertTrue(treasureGoal.checkCompleted());
		assertFalse(exitGoal.checkCompleted());
		assertFalse(cG.checkCompleted());
		p.moveDown();
		p.moveRight();
		assertTrue(treasureGoal.getGoalsSatisfied() == 3);
		assertTrue(treasureGoal.checkCompleted());
		assertTrue(exitGoal.checkCompleted());
		assertTrue(cG.checkCompleted());
		
	}
	
	@Test
	void testValidANDORGoalType() throws FileNotFoundException {
		DungeonTestLoader dungeonLoader = new DungeonTestLoader("testAndOr.json");
		Dungeon dungeon = dungeonLoader.load();	
		Player p = dungeon.getPlayer();
		ComplexGoal cG = p.getPlayerGoals();
		
		SingleGoal treasureGoal = (SingleGoal)cG.getGoalList().get(1);
		SingleGoal exitGoal = (SingleGoal)cG.getGoalList().get(0);
		SingleGoal switchGoal = (SingleGoal)cG.getGoalList().get(2);
		assertEquals(switchGoal.getType(), "switch");
		assertEquals(treasureGoal.getType(), "treasure");
		assertEquals(exitGoal.getType(), "exit");
		
		
		p.moveRight();
		assertTrue(treasureGoal.checkCompleted());
		assertFalse(exitGoal.checkCompleted());
		assertFalse(switchGoal.checkCompleted());
		assertFalse(cG.checkCompleted());
		
		p.moveRight();
		p.moveUp();
		p.moveUp();
		assertTrue(treasureGoal.checkCompleted());
		assertTrue(exitGoal.checkCompleted());
		assertFalse(switchGoal.checkCompleted());
		assertTrue(cG.checkCompleted());
		
		
	}
	

	
}
