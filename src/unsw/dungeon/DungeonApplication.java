package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	
    	StartScreen startScreen = new StartScreen(primaryStage);
    	DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
    	WinScreen winScreen = new WinScreen(primaryStage);
    	
    	startScreen.getController().setScreen(dungeonScreen);
    	dungeonScreen.getLoader().setWinScreen(winScreen);
    	startScreen.start();
        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("test.json");
        //dungeonLoader.setStage(primaryStage);
        //DungeonController controller = dungeonLoader.loadController();
        //dungeonLoader.setDungeonController(controller);
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        //loader.setController(controller);
        //Parent root = loader.load();
        //Scene scene = new Scene(root);
        //root.requestFocus();
        //primaryStage.setScene(scene);
        //primaryStage.setTitle("Dungeon Crawlers");
        //primaryStage.show(); 

        

    } 

    public static void main(String[] args) {
        launch(args);
    }

}
