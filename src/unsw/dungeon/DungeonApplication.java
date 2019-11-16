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
    	String levelOne = "test2.json";
    	String levelTwo = "level2.json";
    	
    	StartScreen startScreen = new StartScreen(primaryStage);
    	
    	DungeonScreen dungeonScreen = new DungeonScreen(primaryStage,levelOne);
    	DungeonScreen dungeon2Screen = new DungeonScreen(primaryStage, levelTwo);
    	
    	WinScreen winScreen = new WinScreen(primaryStage);
    	WinScreen winScreen2 = new WinScreen(primaryStage);
    	winScreen2.getController().convertEndScreen();
    	
    	LoseScreen loseScreen = new LoseScreen(primaryStage);
    	LoseScreen loseScreen2 = new LoseScreen(primaryStage);
    	
    	winScreen.getController().setScreen(dungeon2Screen);
    	
    	dungeonScreen.getLoader().setWinScreen(winScreen);
    	dungeonScreen.getLoader().setLoseScreen(loseScreen);
    	
    	dungeon2Screen.getLoader().setWinScreen(winScreen2);
    	dungeon2Screen.getLoader().setLoseScreen(loseScreen2);
    	
    	startScreen.getController().setScreen(dungeonScreen);
    	startScreen.getController().setScreen(dungeonScreen);
    	
    	loseScreen.getController().setLevelFileName(levelOne);
    	
    	loseScreen2.getController().setLevelFileName(levelTwo);
    	
    	startScreen.start();
    
    } 

    public static void main(String[] args) {
        launch(args);
    }

}
