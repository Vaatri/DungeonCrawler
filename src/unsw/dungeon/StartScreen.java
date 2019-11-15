package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {

	private Stage stage;
	private Scene scene;
	private TutorialController controller;
	
	public StartScreen(Stage stage) throws IOException{
		this.stage = stage;
		this.controller = new TutorialController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startScreen.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		this.scene = scene;
	}
	
	public void start() {
		stage.setScene(this.scene);
		stage.show();
	};
	
	public TutorialController getController() {
		return controller;
	}
}
