package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;

public class backyard_controller implements Initializable{
	
	
	@FXML
	private ImageView test;
	
	@FXML
	private StackPane base;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(4));
		transition.setNode(test);
		
//		transition.setFromX(0);
//		transition.setFromY(0);
		test.setLayoutX(316);
		test.setLayoutY(93);
		
		transition.setToX(1008);
//		transition.setByX(200);
//		transition.setAutoReverse(true);
		transition.setCycleCount(TranslateTransition.INDEFINITE);
		
		
		Image i = new Image("sunflower.png", 200, 71, false, true);
		ImageView j = new ImageView(i);
		Pane p = new Pane(j);
//		j.setLayoutX(272);
//		j.setLayoutY(181);
		actor a = new actor(100, 100, 40, 0, p);
		base.getChildren().add(a.getSprite());
//		while(true) {
//			p.setLayoutX(100);
//			p.setLayoutY(100);
//		}
//		j.setLayoutX(278);
//		j.setLayoutY(300);
//		base.getChildren().add(j);

//		Rectangle r = new Rectangle();
//		r.setX(50);
//		r.setY(50);
//		r.setWidth(200);
//		r.setHeight(100);
//		r.setArcWidth(20);
//		r.setArcHeight(20);
//		r.setFill(Color.ALICEBLUE);
//		
//		base.getChildren().add(r);
		transition.play();
		

		
	}
	
	
}
