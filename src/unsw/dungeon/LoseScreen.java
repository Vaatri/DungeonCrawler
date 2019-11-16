package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoseScreen {
	private Stage stage;
	private LoseController controller;
	private Scene scene;
	
	public LoseScreen(Stage stage) throws IOException{
		this.stage = stage;
		controller = new LoseController();
		controller.setStage(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("loseScreen.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);
	}
	
	public void start() {
		stage.setScene(scene);
		stage.show();
	}
	
	public LoseController getController() {
		return controller;
	}
}
