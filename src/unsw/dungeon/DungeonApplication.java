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
    	String levelOne = "level1.json";
    	String levelTwo = "level2.json";
    	String levelThree = "level3.json";
    	StartScreen startScreen = new StartScreen(primaryStage);
    	
    	DungeonScreen dungeonScreen = new DungeonScreen(primaryStage,levelOne);
    	DungeonScreen dungeon2Screen = new DungeonScreen(primaryStage, levelTwo);
    	DungeonScreen dungeon3Screen = new DungeonScreen(primaryStage, levelThree);
    	
    	WinScreen winScreen = new WinScreen(primaryStage);
    	WinScreen winScreen2 = new WinScreen(primaryStage);
    	WinScreen winScreen3 = new WinScreen(primaryStage);
    	winScreen3.getController().convertEndScreen();
    	
    	LoseScreen loseScreen = new LoseScreen(primaryStage);
    	LoseScreen loseScreen2 = new LoseScreen(primaryStage);
    	LoseScreen loseScreen3 = new LoseScreen(primaryStage);
    	
    	winScreen.getController().setScreen(dungeon2Screen);
    	winScreen2.getController().setScreen(dungeon3Screen);    	
    	
    	dungeonScreen.getLoader().setWinScreen(winScreen);
    	dungeonScreen.getLoader().setLoseScreen(loseScreen);
    	
    	dungeon2Screen.getLoader().setWinScreen(winScreen2);
    	dungeon2Screen.getLoader().setLoseScreen(loseScreen2);
    	
    	dungeon3Screen.getLoader().setWinScreen(winScreen3);
    	dungeon3Screen.getLoader().setLoseScreen(loseScreen3);
    	
    	startScreen.getController().setScreen(dungeonScreen);
    	startScreen.getController().setScreen(dungeonScreen);
    	
    	loseScreen.getController().setLevelFileName(levelOne);
    	
    	loseScreen2.getController().setLevelFileName(levelTwo);
    	loseScreen3.getController().setLevelFileName(levelThree);
    	startScreen.start();
    
    } 

    public static void main(String[] args) {
        launch(args);
    }

}
