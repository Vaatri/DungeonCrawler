package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoseController {

	
	@FXML
	private Button restartButton;
	
	@FXML
	private Button exitButton;
	
	
	private Stage stage;
	
	private String levelFileName;
	
	
	
	/**
	 * Event handler of user pressing restart Button within lose screen.
	 * @param restartButtonPress
	 * @throws IOException
	 */
	@FXML
	public void handleRestartButton(ActionEvent restartButtonPress) throws IOException {
		DungeonScreen resetScreen = new DungeonScreen(stage, levelFileName);
		resetScreen.start();
	}
	
	/**
	 * Event handler of user pressing exit button within lose screen. 
	 * @param exitButtonPress
	 */
	@FXML
	public void handleExitButton(ActionEvent exitButtonPress) {
		stage.close();
	}
	
	public void setStage(Stage s) {
		stage = s;
	}
	
	
	public void setLevelFileName(String filename) {
		levelFileName = filename;
	}
}
