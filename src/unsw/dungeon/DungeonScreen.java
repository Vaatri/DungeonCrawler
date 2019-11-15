package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {

	
	private Stage stage;
	private DungeonController controller;
	private DungeonControllerLoader dungeonLoader;
	private Scene scene;
	
	public DungeonScreen(Stage stage) throws IOException{
		this.stage = stage;
		String levelFile = "test.json";
        dungeonLoader = new DungeonControllerLoader(levelFile);
        dungeonLoader.setStage(stage);
        DungeonController controller = dungeonLoader.loadController(levelFile);
        dungeonLoader.setDungeonController(controller);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        this.scene = new Scene(root);
       
        root.requestFocus();

	}
	
	public void start() {
        stage.setScene(scene);
        stage.setTitle("Dungeon Crawlers");
        stage.show();
	}
	
	public DungeonController getController() {
		return controller;
	}
	
	public DungeonControllerLoader getLoader() {
		return dungeonLoader;
	}

	
}
