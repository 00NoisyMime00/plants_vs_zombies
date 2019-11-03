package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainPageController implements Initializable{
	@FXML
	Pane base;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
//		Start Game Button
		Button startGameButton = new Button("startGame");
		startGameButton.setMinSize(200, 100);
		startGameButton.setTranslateX(185);
		startGameButton.setTranslateY(265);
		startGameButton.setRotate(-10);
		startGameButton.setOpacity(0);
		performAction(startGameButton);
		base.getChildren().add(startGameButton);
		
//		Load Game Button
		Button loadGameButton = new Button("loadGame");
		loadGameButton.setMinSize(200, 100);
		loadGameButton.setTranslateX(195);
		loadGameButton.setTranslateY(370);
		loadGameButton.setRotate(-10);
		loadGameButton.setOpacity(0);
		performAction(loadGameButton);
		base.getChildren().add(loadGameButton);
		
//		Exit Button
		Button exitButton = new Button("exitButton");
		exitButton.setMinSize(130, 50);
		exitButton.setTranslateX(780);
		exitButton.setTranslateY(365);
		exitButton.setRotate(5);
		exitButton.setOpacity(0);
		performAction(exitButton);
		base.getChildren().add(exitButton);
		
	}
	
	public void performAction(Button b) {
		b.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(b.getText().equals("startGame")) {
					System.out.println("startgame clicked..");
					Stage stage = Main.getStage();
					stage.setScene(Main.getBackyarScene());
				}
				else if(b.getText().equals("loadGame")) {
					System.out.println("clicked load game..");
				}
				else if(b.getText().equals("exitButton")) {
					System.out.println("clicking exit..");
					Main.getStage().close();
				}
				
			}
		});
	}

}
