package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class WinController {
	
	private DungeonScreen dungeonScreen;
	private Stage stage;
	

	@FXML
	private Pane canvas;
	
	@FXML
	private Button nextLevelButton;
	
	@FXML
	private Label winLabel;
		
	
	public void setScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private void handleNextLevelButton(ActionEvent buttonPress) {
		System.out.println("yo");
	}
	
	@FXML
	private void initialize() {
		nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
			     dungeonScreen.start();
			}
		});
		
	}
	
	/**
	 * If level is last level of the game, convert the win screen into an end game screen.
	 */
	public void convertEndScreen() {
		winLabel.setText("You have finished the game!!");
		nextLevelButton.setText("exit");
		nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				stage.close();
			}
		});
	}
}
