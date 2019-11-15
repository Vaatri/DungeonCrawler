package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {
	DungeonController dungeonController;
	Dungeon dungeon;
    public DungeonController getDungeonController() {
		return dungeonController;
	}

	public void setDungeonController(DungeonController dungeonController) {
		
	}

	private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     * @throws FileNotFoundException 
     */
    public Dungeon load(String filename) throws FileNotFoundException {
        int width = json.getInt("width");
        int height = json.getInt("height");
        
        Dungeon dungeon = new Dungeon(width, height, filename);
        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoal = json.getJSONObject("goal-condition");
        loadGoals(dungeon, jsonGoal, jsonEntities);
        trackPlayerDead(dungeon.getPlayer());
        this.dungeon = dungeon;
        return dungeon;
    }
    
   protected abstract void trackPlayerDead(Player player);

/*
    public DungeonController loadController(String filename) throws FileNotFoundException {
    	System.out.println("shouldnt be here");
        return null;
    }*/
    
    /**
     * A handler function that will load all the goals indicated by the JSON file
     * @param dungeon
     * @param goal
     * @param jsonEntities
     */
    private void loadGoals(Dungeon dungeon, JSONObject goal, JSONArray jsonEntities) {
    	String goalType = goal.getString("goal");
    	int goalCount = 0;
    	Goal cG = new ComplexGoal("complex");
    	
    	
    	//load in single goal type
    	if(goalType.equals("AND") || goalType.equals("OR")){
    		loadComplexGoal(goalType, dungeon, goal, jsonEntities, cG);
    	} else {
    		loadSingleGoal(dungeon, goalType, jsonEntities, cG);
    		cG.setNeededToSatisfy(++goalCount);
    	}
    	
    	dungeon.getPlayer().setPlayerGoals((ComplexGoal)cG);
    	trackLevelGoal((ComplexGoal)cG);
    	
    }

    /**
     * This function will handle single goals
     * @param dungeon
     * @param goalType
     * @param entities
     * @param g
     */
    private void loadSingleGoal(Dungeon dungeon, String goalType, JSONArray entities, Goal g) {
		Goal sg = new SingleGoal(goalType,countEntities(goalType, entities),new AndGoal(), true);
		onLoad(sg);
		attachObserver(dungeon, (SingleGoal)sg);
		g.addGoal(sg);
		
    }
    
    /**
     * This function will handle complex goals such as "AND" and "OR
     * @param goalType
     * @param dungeon
     * @param goal
     * @param entities
     * @param g
     */
    private void loadComplexGoal(String goalType, Dungeon dungeon, JSONObject goal, JSONArray entities, Goal g) {
    	JSONArray array = goal.getJSONArray("subgoals");	
    	JSONObject jsonObject;
    	String type;
    	int andGoalCount = 0;
    	
    	for(int i = 0; i < array.length(); i++) {
    		jsonObject = array.getJSONObject(i);
    		type = jsonObject.getString("goal");
    		if (type.equals("OR")) {
    			array = jsonObject.getJSONArray("subgoals");
    			for(int j = 0; j < array.length(); j++) {
    				jsonObject = array.getJSONObject(j);
					type = jsonObject.getString("goal");
					Goal sg = new SingleGoal(type,countEntities(type, entities), new OrGoal(), false);
					onLoad(sg);
					g.addGoal(sg);
					attachObserver(dungeon, (SingleGoal)sg);
    			}
    			break;
    		} else {
	    		Goal sg = new SingleGoal(type ,countEntities(type, entities), new AndGoal(), true);
	    		onLoad(sg);
	    		g.addGoal(sg);
	    		attachObserver(dungeon, (SingleGoal)sg);
	    		andGoalCount++;
    		}
    	}
    	g.setNeededToSatisfy(andGoalCount);
    }

    
    /**
     * This function will attach goal Observers to the entities
     * that are required for completing a goal.
     * @param d
     * @param g
     */
    public void attachObserver(Dungeon d, SingleGoal g) {
    	for(Entity e: d.getEntitiesList()) {
    		if(e == null) continue;
    		String entityType = e.getType();
    		if(entityType.equals("enemy"))
    			entityType = "enemies";
    		if(entityType.equals(g.getType())) {
    			((Subject)e).registerObserver((Observer)g);
    		}
    	}
    }
   
    /**
     * This will return the amount of Entities specified by "type
     * @param type
     * @param jsonEntities
     * @return
     */
    private int countEntities(String type, JSONArray jsonEntities) {
    	int count = 0;
    	if(type.equals("boulders")) 
    		type = "switch";
    	else if (type.equals("enemies"))
			type = "enemy";
    	
    	for(int i = 0; i < jsonEntities.length(); i++) {
    		JSONObject jo = jsonEntities.getJSONObject(i);
    		String entityType = jo.getString("type");
    		if (type.equals(entityType)) {
    			count++;
    		}	
    		
    	}
    	return count;
    }
    
    /**
     * Load entities from given json object.
     * @param dungeon
     * @param json
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "treasure":
        	Treasure treasure = new Treasure(x,y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "portal":
        	int portalID = json.getInt("id");
        	Portal portal = new Portal(x,y, portalID);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(x,y);
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	break;
        case "door":
        	int keyID = json.getInt("id");
        	Door door = new Door(x,y,keyID);
        	onLoad(door);
        	entity = door;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x,y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "openDoor":
        	int keyID2 = json.getInt("id");
        	Door openDoor = new Door(x,y, keyID2);
        	openDoor.setState(openDoor.getOpenState());
        	onLoad(openDoor);
        	entity = openDoor;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x,y);
        	if (dungeon.getPlayer() != null) {
        		dungeon.getPlayer().registerObserver(((Observer)enemy));
        	}
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "exit":
        	Exit exit = new Exit(x,y, dungeon);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "potion":
        	Potion potion = new Potion(x,y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "key":
        	int doorID = json.getInt("id");
        	Key key = new Key(x,y, doorID, dungeon);
        	onLoad(key);
        	entity = key;
        	break;
        case "sword":
        	Sword sword = new Sword(x,y);
        	onLoad(sword);
        	entity = sword;
        	break;
        }
        entity.setDungeon(dungeon);
        dungeon.addEntity(entity);
       
    }
    

    
    
    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
    
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(FloorSwitch floorSwitch);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Potion potion);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Goal goal);
    public abstract void trackLevelGoal(ComplexGoal g);

	
 

}
