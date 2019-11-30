package application;

import java.net.URL;

import application.actor.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.input.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;

public class levelController implements Initializable{
	
	@FXML
	Pane background;
	
	@FXML
	Button level1_but;
	@FXML
	Button level2_but;
	@FXML
	Button level3_but;
	@FXML
	Button level4_but;
	@FXML
	Button level5_but;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		performAction(level1_but);
		performAction(level2_but);
		performAction(level3_but);
		performAction(level4_but);
		performAction(level5_but);
	}

	
	public void performAction(Button b) {
		b.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				if(Main.getCurrentBase().getIsGamePaused()) {
					Main.getCurrentBase().setIsGamePaused(false);
					Main.getCurrentBase().resetGame();
				}
				
				if(b.getId().equals("level1_but")) {
					System.out.println("going to level 1");

					Stage stage = Main.getStage();
					stage.setScene(Main.getBackyarScene());
				
				}
				
				else if(b.getId().equals("level2_but")) {
					System.out.println("going to level 2");
				
				}
				
				else if(b.getId().equals("level3_but")) {
					System.out.println("going to level 3");
				
				}
				
				else if(b.getId().equals("level4_but")) {
					System.out.println("going to level 4");
				
				}
				
				else if(b.getId().equals("level5_but")) {
					System.out.println("going to level 5");
				
				}
				Main.playGameSound();
				
			}
		});
	}
	
	
	
	

}