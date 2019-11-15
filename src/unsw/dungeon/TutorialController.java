package unsw.dungeon;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class TutorialController {

    private ImageView playerImage;
    private ImageView wallImage;
    private ImageView treasureImage;
    private ImageView portalImage;
    private ImageView floorSwitchImage;
    private ImageView doorImage;
    private ImageView boulderImage;
    private ImageView enemyImage;
    private ImageView exitImage;
    private ImageView potionImage;
    private ImageView keyImage;
    private ImageView swordImage;
    private ImageView openDoorImage;
	
	@FXML
	private GridPane tutorialPane;
	
	@FXML
	private Button startButton;
	
	private DungeonScreen dungeonScreen;
	
	public TutorialController() throws FileNotFoundException {
        playerImage = new ImageView(new Image("/human_new.png"));
        wallImage = new ImageView(new Image("/brick_brown_0.png"));
        treasureImage = new ImageView(new Image("/gold_pile.png"));
        portalImage = new ImageView(new Image("/portal.png"));
        floorSwitchImage = new ImageView(new Image("/pressure_plate.png"));
        doorImage = new ImageView(new Image("/closed_door1.png"));
        boulderImage = new ImageView(new Image("/boulder.png"));
        enemyImage = new ImageView(new Image("/deep_elf_master_archer.png"));
        exitImage = new ImageView(new Image("/exit.png"));
        potionImage = new ImageView(new Image("/bubbly.png"));
        keyImage = new ImageView(new Image("/key1.png"));
        swordImage = new ImageView(new Image("/greatsword_1_new.png"));
        openDoorImage = new ImageView(new Image("/open_door1.png"));
	}
	
	
	@FXML
	private void handleStartButton(ActionEvent startButtonPress) {
		dungeonScreen.start();
	}
	
	public void setScreen(DungeonScreen ds) {
		dungeonScreen = ds;
	}
	
	@FXML
	private void initialize() {
		tutorialPane.add(playerImage,0, 0);
		tutorialPane.add(treasureImage, 0, 1);
		tutorialPane.add(swordImage, 0, 2);
		tutorialPane.add(potionImage, 0, 3);
		tutorialPane.add(keyImage, 0, 4);
		tutorialPane.add(doorImage, 0, 5);
		tutorialPane.add(enemyImage, 0, 6);
		tutorialPane.add(boulderImage, 0, 7);
		tutorialPane.add(floorSwitchImage, 0, 8);
		tutorialPane.add(portalImage, 0, 9);
		tutorialPane.add(exitImage, 0, 10);
	}
}
