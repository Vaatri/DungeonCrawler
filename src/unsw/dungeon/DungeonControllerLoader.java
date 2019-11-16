package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {
	
    private List<ImageView> entities;
    private List<Label> goals;
    private List<Collectable> playerInventory;
    private Stage stage;
    private WinScreen winScreen;
    private LoseScreen loseScreen;
    
    
    //Images
    private Image playerImage;
    private Image wallImage;
    private Image treasureImage;
    private Image portalImage;
    private Image floorSwitchImage;
    private Image doorImage1;
    private Image doorImage2;
    private Image doorImage3;
    private Image doorImage4;
    private Image boulderImage;
    private Image enemyImage;
    private Image exitImage;
    private Image potionImage;
    private Image keyImage1;
    private Image keyImage2;
    private Image keyImage3;
    private Image keyImage4;
    private Image swordImage;
    private Image openDoorImage1;
    private Image openDoorImage2;
    private Image openDoorImage3;
    private Image openDoorImage4;
    private Image healthPotionImage;

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
        doorImage1 = new Image("/closed_door1.png");
        doorImage2 = new Image("/closed_door2.png");
        doorImage3 = new Image("/closed_door3.png");
        doorImage4 = new Image("/closed_door4.png");
        boulderImage = new Image("/boulder.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        exitImage = new Image("/exit.png");
        healthPotionImage = new Image("/brilliant_blue_new.png");
        potionImage = new Image("/bubbly.png");
        keyImage1 = new Image("/key1.png");
        keyImage2 = new Image("/key2.png");
        keyImage3 = new Image("/key3.png");
        keyImage4 = new Image("/key4.png");
        swordImage = new Image("/greatsword_1_new.png");
        openDoorImage1 = new Image("/open_door1.png");
        openDoorImage2 = new Image("/open_door2.png");
        openDoorImage3 = new Image("/open_door3.png");
        openDoorImage4 = new Image("/open_door4.png");
    }
    
    public void setStage(Stage stage) {
    	this.stage = stage;
    }
    
    public Stage getStage() {
    	return this.stage;
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
    	ImageView inventoryView = new ImageView(potionImage);
    	trackCollectables(treasure, view, inventoryView);
    	inventoryView.setVisible(false);
    	addToInventory(treasure);
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
    		view = new ImageView(openIm(door.getID()));
    	}
    	else {
    		view = new ImageView(closedIm(door.getID()));
    	}
    	addEntity(door, view);
    	door.getOpenProp().addListener(new ChangeListener<Boolean>() {
    		@Override
    		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    			if(newValue) {
    				view.setImage(openIm(door.getID()));
    			}
    		}
    	});
    }
    public Image openIm(int i) {
	    if(i == 1)return openDoorImage1;
		if(i==2) return openDoorImage2;
		if(i==3)return openDoorImage3;
		return openDoorImage4;
    }
    public Image closedIm(int i) {
    	if(i == 1)return doorImage1;
 		if(i==2) return doorImage2;
 		if(i==3)return doorImage3;
 		return doorImage4;
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
    	trackEnemyDead(enemy, view);
    }
    @Override
    public void onLoad(Exit exit) {
    	ImageView view = new ImageView(exitImage);
    	addEntity(exit, view);
    }
    @Override
    public void onLoad(HealthPotion healthPotion) {
    	ImageView view = new ImageView(healthPotionImage);
    	addEntity(healthPotion, view);
    	addToInventory(healthPotion);
    	trackCollectablesNonInventory(healthPotion, view);
    }
    @Override
    public void onLoad(Potion potion) {
    	ImageView view = new ImageView(potionImage);
    	ImageView inventoryView = new ImageView(potionImage);
    	inventoryView.setVisible(false);
    	addToInventory(potion);
    	addEntity(potion, view);
    	trackCollectables(potion, view, inventoryView);
    }
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyIm(key.getID()));
    	ImageView inventoryView = new ImageView(keyIm(key.getID()));
    	inventoryView.setVisible(false);
    	addToInventory(key);
    	addEntity(key, view);
    	trackCollectables(key, view, inventoryView);
    }
    public Image keyIm(int i) {
    	if(i == 1)return keyImage1;
    	if(i==2) return keyImage2;
    	if(i==3)return keyImage3;
    	return keyImage4;
    }
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	ImageView inventoryView = new ImageView(potionImage);
    	inventoryView.setVisible(false);
    	addToInventory(sword);
    	addEntity(sword, view);
    	trackCollectables(sword, view, inventoryView);
    }
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }
    
    private void addToInventory(Collectable c) {
    	playerInventory.add(c);
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
    	this.dungeonController = new DungeonController(load(filename), entities, goals, playerInventory, this.stage,filename);
        return dungeonController;
    }
    

	@Override
	public DungeonController getDungeonController() {
		return dungeonController;
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

	private void trackCollectablesNonInventory(Collectable c, Node dungeonNode) {
		c.inInventoryProp().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					dungeonNode.setVisible(false);
				}
			}
		});
		
	}
	
	private void trackCollectables(Collectable c, Node dungeonNode, Node inventoryNode) {
		c.inInventoryProp().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					dungeonNode.setVisible(false);
					inventoryNode.setVisible(true);
				}
			}
		});
	}
	
	private void trackEnemyDead(Enemy enemy, Node node) {
		enemy.getDeadProp().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					node.setVisible(false);
				}
			}
		});
	}
	
	@Override
	public void trackLevelGoal(ComplexGoal g) {
		g.getCompletedProp().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					System.out.println("hello");
					winScreen.start();
				}
			}
		});
	}
	@Override
	public void trackPlayerDead(Player p) {
		p.getDead().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					System.out.println("CHANGED " + oldValue + newValue);
					loseScreen.start();
				}
			}
		});
	}
    public void setWinScreen(WinScreen ws) {
    	winScreen = ws;
    }

    public void setLoseScreen(LoseScreen ls) {
    	loseScreen = ls;
    }
}
