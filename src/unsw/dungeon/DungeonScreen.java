package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {

	
	private Stage stage;
	private DungeonController controller;
	private Scene scene;
	
	public DungeonScreen(Stage stage) throws IOException{
		this.stage = stage;
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("test.json");
        dungeonLoader.setStage(stage);
        DungeonController controller = dungeonLoader.loadController("test.json");
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
	
}
