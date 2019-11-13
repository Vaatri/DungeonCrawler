package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    
    @FXML
    private Pane statusContainer;
    
    @FXML
    private GridPane orObjectives;
    
    @FXML
    private GridPane andObjectives;
    
    @FXML
    private GridPane inventory;
    
    @FXML
    private BorderPane frame;

    private List<ImageView> initialEntities;
    
    private List<Label> dungeonGoals;
    private List<Collectable> playerInventory;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, List<Label> dungeonGoals, List<Collectable> playerInventory) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.dungeonGoals = new ArrayList<>(dungeonGoals);
        this.playerInventory = new ArrayList<>(playerInventory);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        createObjectives();
        createInventory();
    }
	public void removeObject(Entity entity, Player player) {
    	Image ground = new Image("/dirt_0_new.png");
    	Image playerImage = new Image("/human_new.png");
    	int first= 0 ;
    	for (Node child: squares.getChildren()) {
    		if ((squares.getColumnIndex(child) == entity.getX()) && (squares.getRowIndex(child) == entity.getY())) {
				
    			if (first == 0) {
    				first = 1;
    			}
    			else {
    				System.out.println("the right one " + child);
    				squares.getChildren().remove(child);
        			return;
    			}
    		}
    	}
    }
    private void createObjectives() {
  
    	int andRowCount = 0;
    	int orRowCount = 0;
    	for(Label goal: dungeonGoals) {
    		if(goal.getText().contains("Optional")){
    			orObjectives.add(goal, 0, orRowCount++);
    		} else {
    			andObjectives.add(goal, 0, andRowCount++);
    		}
    	}
    }
    
    private void createInventory() {
    	Inventory playerInven = player.getInventory();
    	createSwordSlot(playerInven.hasSwordProp());
    	createKeySlot(playerInven.hasKeyProp());
    	createPotionSlot(playerInven.hasPotionProp());
    	createTreasureSlot(playerInven.hasTreasureProp());
    }
    
    private void createSwordSlot(BooleanProperty bp) {
    	ImageView swordImage = new ImageView(new Image("/greatsword_1_new.png"));
    	inventory.add(swordImage, 0, 0);
    	swordImage.setVisible(false);
    	bp.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					swordImage.setVisible(true);
				} else {
					swordImage.setVisible(false);
				}
			}
		});
    }
    private void createKeySlot(BooleanProperty bp) {
    	ImageView keyImage = new ImageView(new Image("/key.png"));
		bp.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					keyImage.setVisible(true);
				} else {
					keyImage.setVisible(false);
				}
			}
		});
    	keyImage.setVisible(false);
    	inventory.add(keyImage, 0, 2);
    }
    
    private void createPotionSlot(BooleanProperty bp) {
		ImageView potionImage = new ImageView (new Image("/bubbly.png"));
		bp.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					potionImage.setVisible(true);
				} else {
					potionImage.setVisible(false);
				}
			}
		});
		potionImage.setVisible(false);
		inventory.add(potionImage, 0, 1);	
    }
    
    private void createTreasureSlot(BooleanProperty bp) {
    	ImageView treasureImage = new ImageView(new Image("/gold_pile.png"));
		bp.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					treasureImage.setVisible(true);
				} else {
					treasureImage.setVisible(false);
				}
			}
		});
    	treasureImage.setVisible(false);
    	inventory.add(treasureImage, 0, 3);
    }
    
    

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        default:
            break;
        }
    }

//    private void linkSwordImage() {
//    	ImageView swordImage = new ImageView(new Image("/greatsword_1_new.png"));
//    	for(Collectable c: playerInventory) {
//    		if (c instanceof Sword) {
//    			c.inInventoryProp().addListener(new ChangeListener<Boolean>() {
//    				@Override
//    				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//    					if(newValue) {
//    						swordImage.setVisible(true);
//    					} else {
//    						swordImage.setVisible(false);
//    					}
//    				}
//    			});
//    		}
//    	}
//    	inventory.add(swordImage, 0, 0);
//    	swordImage.setVisible(false);
//    }
//    
//    private void linkPotionImage() {
//    	ImageView potionImage = new ImageView (new Image("/bubbly.png"));
//    	for(Collectable c: playerInventory) {
//    		if (c instanceof Potion) {
//    			c.inInventoryProp().addListener(new ChangeListener<Boolean>() {
//    				@Override
//    				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//    					if(newValue) {
//    						potionImage.setVisible(true);
//    					} else {
//    						potionImage.setVisible(false);
//    					}
//    				}
//    			});
//    		}
//    	}
//    	potionImage.setVisible(false);
//    	inventory.add(potionImage, 0, 1);
//    }
//    
//    private void linkKeyImage() {
//    	ImageView keyImage = new ImageView(new Image("/key.png"));
//    	for(Collectable c: playerInventory) {
//    		if (c instanceof Key) {
//    			c.inInventoryProp().addListener(new ChangeListener<Boolean>() {
//    				@Override
//    				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//    					if(newValue) {
//    						keyImage.setVisible(true);
//    					} else {
//    						keyImage.setVisible(false);
//    					}
//    				}
//    			});
//    		}
//    	}
//    	keyImage.setVisible(false);
//    	inventory.add(keyImage, 0, 2);
//    }
//    
//    private void linkTreasureImage() {
//    	ImageView treasureImage = new ImageView(new Image("/gold_pile.png"));
//    	for(Collectable c: playerInventory) {
//    		if (c instanceof Treasure) {
//    			c.inInventoryProp().addListener(new ChangeListener<Boolean>() {
//    				@Override
//    				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//    					if(newValue) {
//    						treasureImage.setVisible(true);
//    					} else {
//    						treasureImage.setVisible(false);
//    					}
//    				}
//    			});
//    		}
//    	}
//    
//    	treasureImage.setVisible(false);
//    	inventory.add(treasureImage, 0, 3);
//    }
}

