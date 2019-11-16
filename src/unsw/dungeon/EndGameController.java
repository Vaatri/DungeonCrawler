package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EndGameController {

	private Stage stage;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private void handleExitButton() {
		stage.close();
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
