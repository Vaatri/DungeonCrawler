package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WinScreen {

	
	private Stage stage;
	private WinController controller;
	private Scene scene;
	
	public WinScreen(Stage stage) throws IOException{
		this.stage = stage;
		controller = new WinController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("winPage.fxml"));
		controller.setStage(stage);
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);
	}
	
	public void start() {
		stage.setScene(scene);
		stage.show();
	}
	
	public WinController getController() {
		return controller;
	}
}
