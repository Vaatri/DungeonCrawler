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

    } 

    public static void main(String[] args) {
        launch(args);
    }

}
