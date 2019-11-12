package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
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
    private List<ImageView> playerInventory;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, List<Label> dungeonGoals, List<ImageView> playerInventory) {
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
//        createInventory();
    }
	public void removeObject(Entity entity, Player player) {
    	Image ground = new Image("/dirt_0_new.png");
    	Image playerImage = new Image("/human_new.png");
    	squares.getChildren().remove(entity);
    	//squares.getChildren().add(null);
    	squares.add(new ImageView(ground), entity.getX(), entity.getY());
    	
    	//squares.add(new ImageView(player), entity.getX(), entity.getY());
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
    	ImageView treasureImage = new ImageView(new Image("/gold_pile.png"));
    	ImageView potionImage = new ImageView (new Image("/bubbly.png"));
        ImageView keyImage = new ImageView(new Image("/key.png"));
        ImageView swordImage = new ImageView(new Image("/greatsword_1_new.png"));
        int treasureCount = 0;
        int potionCount = 0;
        int keyCount = 0;
        int swordCount = 0;
    	for(ImageView items: playerInventory) {
    		if(items == treasureImage) 
    			inventory.add(treasureImage, treasureCount++, 3);
    		if(items == potionImage)
    			inventory.add(potionImage, potionCount++, 1);
    		if(items == swordImage)
    			inventory.add(swordImage, swordCount++, 0);
    		if(items == keyImage)
    			inventory.add(keyImage, keyCount, 2);
    		inventory.getChildren().add(items);
    	}
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

}

