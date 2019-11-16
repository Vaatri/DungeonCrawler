package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {
	private Stage stage;

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
    
    @FXML
    private Label durationCount;
    
    @FXML
    private Label attacksCount;
    
    @FXML
    private GridPane livesLabel;
    
    @FXML
    private Button resetButton;
    
    @FXML
    private Button dropButton;

    private List<ImageView> initialEntities;
    
    private List<Label> dungeonGoals;

    private Player player;

    private Dungeon dungeon;
    
    private String levelFile;
    
    

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, List<Label> dungeonGoals, List<Collectable> playerInventory, Stage stage, String levelFile) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.dungeonGoals = new ArrayList<>(dungeonGoals);
        this.stage = stage;
        this.levelFile = levelFile;
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
        createLivesLabel();
        player.addObserverList();
        dropButton.setFocusTraversable(false);
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
    private void createLivesLabel() {
    	Label label = new Label();
		label.textProperty().bind(player.getLives().asString());
		livesLabel.add(label, 0, 0);
		
	}
    private void createInventory() {
    	Inventory playerInven = player.getInventory();
    	createSwordSlot(playerInven.hasSwordProp(), playerInven);
    	createKeySlot(playerInven.hasKeyProp());
    	createPotionSlot(playerInven.hasPotionProp(), playerInven);
    	createTreasureSlot(playerInven.hasTreasureProp(), playerInven);
    }
    
    private void createSwordSlot(BooleanProperty bp, Inventory playerInven) {
    	ImageView swordImage = new ImageView(new Image("/greatsword_1_new.png"));
    	Label swordUsageLabel = new Label("Attacks left: ");
    	Label countUsageLabel = new Label();
    	inventory.add(swordImage, 0, 0);
    	inventory.add(swordUsageLabel, 1, 0);
    	inventory.add(countUsageLabel, 2, 0);
    	swordImage.setVisible(false);
    	swordUsageLabel.setVisible(false);
    	countUsageLabel.setVisible(false);
    	bp.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					swordImage.setVisible(true);
					swordUsageLabel.setVisible(true);
					setUsageCountLabel(playerInven, countUsageLabel);
					
				} else {
					swordImage.setVisible(false);
					swordUsageLabel.setVisible(false);
					countUsageLabel.setVisible(false);
				}
			}
		});
    }
    
    private void setUsageCountLabel(Inventory playerInven, Label usageCountLabel) {
    	Sword s = playerInven.getSword();
    	if(s != null) {
    		usageCountLabel.textProperty().bind(s.getUsageProperty().asString());
    		usageCountLabel.setVisible(true);
    	}
    }
    public ImageView keyIm() {
    	
    	if (!dungeon.hasKey()) return new ImageView(new Image ("/key1.png"));
    	
    	if(dungeon.getkeyID() == 1)return new ImageView(new Image("/key1.png"));
    	if(dungeon.getkeyID()==2)return new ImageView(new Image("/key2.png"));
    	if(dungeon.getkeyID()==3)return new ImageView(new Image("/key3.png"));
    	return new ImageView(new Image("/key4.png"));
    }
    private void createKeySlot(BooleanProperty bp) {
    	//ImageView keyImage = new ImageView(new Image("/key1.png"));
    	dropButton.setVisible(false);
    	
		bp.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					//keyImage = new ImageView(keyIm(dungeon.getPlayer().getKeyID()));
					System.out.println("set true " + keyIm());
			    	
			    	keyIm().setVisible(true);
			    	inventory.add(keyIm(), 0, 2);
					
				} else {
					keyIm().setVisible(false);
					//keyImage.setVisible(true);
					dropButton.setVisible(true);
				}
			}
			
		});
		System.out.println("set false " + keyIm());
		keyIm().setVisible(false);
		inventory.add(keyIm(), 0, 2);
    	
    }
    
    private void createPotionSlot(BooleanProperty bp, Inventory playerInven) {
		ImageView potionImage = new ImageView (new Image("/bubbly.png"));
		Label durationLabel = new Label("Duration: ");
		Label durationCountLabel = new Label();
		inventory.add(durationLabel, 1, 1);
		inventory.add(durationCountLabel, 2, 1);
		bp.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					potionImage.setVisible(true);
					durationLabel.setVisible(true);
					setPotionUsageCountLabel(playerInven,durationCountLabel);
				} else {
					potionImage.setVisible(false);
					durationLabel.setVisible(false);
					durationCountLabel.setVisible(false);
				}
			}
		});
		potionImage.setVisible(false);
		durationLabel.setVisible(false);
		inventory.add(potionImage, 0, 1);	
    }
    
    private void setPotionUsageCountLabel(Inventory playerInven, Label countLabel) {
    	Potion p = playerInven.getPotion();
    	if (p != null) {
    		countLabel.textProperty().bind(p.getDurationProp().asString());
    		countLabel.setVisible(true);
    	}
    }
    
    private void createTreasureSlot(BooleanProperty bp, Inventory playerInven) {
    	ImageView treasureImage = new ImageView(new Image("/gold_pile.png"));
    	Label descriptionLabel = new Label("How many: ");
    	Label treasureCount = new Label();
    	descriptionLabel.setVisible(false);
    	treasureCount.setVisible(false);
		bp.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					treasureImage.setVisible(true);
					descriptionLabel.setVisible(true);
					createTreasureCountLabel(playerInven, treasureCount);
				} else {
					treasureImage.setVisible(false);
					descriptionLabel.setVisible(false);
					treasureCount.setVisible(false);
				}
			}
		});
    	treasureImage.setVisible(false);
    	inventory.add(treasureImage, 0, 3);
    	inventory.add(descriptionLabel, 1, 3);
    	inventory.add(treasureCount, 2, 3);
    }
    
    private void createTreasureCountLabel(Inventory playerInven, Label treasureCount) {
    	treasureCount.textProperty().bind(playerInven.getTreasureProp().asString());
    	treasureCount.setVisible(true);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	System.out.println("hello");
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
    
    @FXML
    public void handleResetButton(ActionEvent resetButtonPress) throws IOException {
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(levelFile);
		dungeonLoader.setStage(stage);
        DungeonController controller = dungeonLoader.loadController(levelFile);
        dungeonLoader.setDungeonController(controller);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void handleDropButton(ActionEvent dropButtonPress) {
    	Key k = player.dropKey();
    	if (k != null) {
    		//Image keyImage = keyIm();
    		ImageView keyView = keyIm();
    		squares.add(keyView, k.getX(), k.getY());
    		k.inInventoryProp().addListener(new ChangeListener<Boolean>() {
    			@Override
    			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    				if(newValue) {
    					keyView.setVisible(false);
    				}
    			}
    		});
    	}
    	
    	
    	
    }

}

