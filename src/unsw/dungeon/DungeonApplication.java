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
    	String levelTwo = "level2.json";
    	String levelOne = "test2.json";
    	StartScreen startScreen = new StartScreen(primaryStage);
    	DungeonScreen dungeonScreen = new DungeonScreen(primaryStage,levelOne);
    	DungeonScreen dungeon2Screen = new DungeonScreen(primaryStage, levelTwo);
    	WinScreen winScreen = new WinScreen(primaryStage);
    	LoseScreen loseScreen = new LoseScreen(primaryStage);
    	startScreen.getController().setScreen(dungeonScreen);
    	winScreen.getController().setScreen(dungeon2Screen);
    	startScreen.getController().setScreen(dungeonScreen);
    	dungeonScreen.getLoader().setWinScreen(winScreen);
    	dungeonScreen.getLoader().setLoseScreen(loseScreen);
    	startScreen.start();
    } 

    public static void main(String[] args) {
        launch(args);
    }

}
