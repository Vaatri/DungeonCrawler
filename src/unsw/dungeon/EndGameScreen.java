package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EndGameScreen {

	private Stage stage;
	private EndGameController controller;
	private Scene scene;
	
	
	public EndGameScreen(Stage stage) throws IOException {
		this.stage = stage;
		controller = new EndGameController();
		controller.setStage(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("endGame.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		this.scene = scene;
	}
	
	public void start() {
		stage.setScene(this.scene);
		stage.show();
	}
	
	public EndGameController getController() {
		return controller;
	}
	
	
}
