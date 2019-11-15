package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TutorialController {

	@FXML
	private Button startButton;
	
	private DungeonScreen dungeonScreen;
	
	@FXML
	private void handleStartButton(ActionEvent startButtonPress) {
		dungeonScreen.start();
	}
	
	public void setScreen(DungeonScreen ds) {
		dungeonScreen = ds;
	}
	
	
}
