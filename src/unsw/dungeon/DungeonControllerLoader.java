package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {
	
    private List<ImageView> entities;
    private List<Label> goals;
    private List<ImageView> playerInventory;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image treasureImage;
    private Image portalImage;
    private Image floorSwitchImage;
    private Image doorImage;
    private Image boulderImage;
    private Image enemyImage;
    private Image exitImage;
    private Image potionImage;
    private Image keyImage;
    private Image swordImage;
    private Image openDoorImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        dungeon = null;
        this.dungeonController = null;
        entities = new ArrayList<>();
        goals = new ArrayList<>();
        playerInventory = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        treasureImage = new Image("/gold_pile.png");
        portalImage = new Image("/portal.png");
        floorSwitchImage = new Image("/pressure_plate.png");
        // need an open door
        doorImage = new Image("/closed_door.png");
        boulderImage = new Image("/boulder.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        exitImage = new Image("/exit.png");
        // brilliant blue potion not added
        potionImage = new Image("/bubbly.png");
        keyImage = new Image("/key.png");
        swordImage = new Image("/greatsword_1_new.png");
        openDoorImage = new Image("/open_door.png");
        
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	addEntity(treasure, view);
    	addToInventory(view);
    }
    @Override
    public void onLoad(Portal portal) {
    	ImageView view = new ImageView(portalImage);
    	addEntity(portal, view);
    }
    @Override
    public void onLoad(FloorSwitch floorSwitch) {
    	ImageView view = new ImageView(floorSwitchImage);
    	addEntity(floorSwitch, view);
    }
    @Override
    public void onLoad(Door door) {
    	ImageView view;
    	if(door.getState().equals(door.getOpenState())) {
    		view = new ImageView(openDoorImage);
    	}
    	else {
    		view = new ImageView(doorImage);
    	}
    	addEntity(door, view);
    }
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	addEntity(enemy, view);
    }
    @Override
    public void onLoad(Exit exit) {
    	ImageView view = new ImageView(exitImage);
    	addEntity(exit, view);
    }
    @Override
    public void onLoad(Potion potion) {
    	ImageView view = new ImageView(potionImage);
    	addEntity(potion, view);
    }
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	addEntity(key, view);
    }
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }
    
    private void addToInventory(ImageView view) {
    	playerInventory.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController(String filename) throws FileNotFoundException {
    	this.dungeonController = new DungeonController(load(filename), entities, goals, playerInventory);
        return dungeonController;
    }
    

	public DungeonController getDungeonController() {
		return dungeonController;
	}

	public void setDungeonController(DungeonController dungeonController) {
		this.dungeonController = dungeonController;
		System.out.println("dungeon loader's controller set as " + dungeonController);
		this.dungeon.setDungeonController(dungeonController);
		System.out.println("setting dungeon's controller now...");
	}

	@Override
	public void onLoad(Goal goal) {

		String goalType = "Optional: ";
		if(goal.getMandatory()) {
			goalType = "Mandatory: ";
		}
		Label l = new Label(goalType+goal.getType()+" 0/"+goal.getNeededToSatisfy());
		addGoal(goal, l);
		
		
	}

	private void addGoal(Goal goal, Label label) {
		goals.add(label);
		trackGoals(goal, label);
	}
	
	private void trackGoals(Goal goal, Label label) {
		goal.propertyGS().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				String goalType = "Optional: ";
				if(goal.getMandatory()) {
					goalType = "Mandatory: ";
				}
				if (newValue.intValue() <= goal.getNeededToSatisfy())
					label.setText(goalType+goal.getType()+" "+newValue.intValue()+"/"+goal.getNeededToSatisfy());
			}
		});
	}

	
	

}
