package application;

import java.net.URL;

import java.util.Random;
import java.util.ResourceBundle;

//import com.sun.glass.events.MouseEvent;
import javafx.scene.input.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	private final int IMG_WIDTH = 80;
	private final int IMG_HEIGHT = 71;
	
	public static actor peaActor;
	public static actor ab;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		base.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String msg =
				          "(x: "       + event.getX()      + ", y: "       + event.getY()       + ") -- " +
				          "(sceneX: "  + event.getSceneX() + ", sceneY: "  + event.getSceneY()  + ") -- " +
				          "(screenX: " + event.getScreenX()+ ", screenY: " + event.getScreenY() + ")";
				
//				System.out.println(msg);
			}
		});
//		base.addEventHandler(MouseEvent.MOUSE_PRESSED, arg1);
		
		
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
		
		
		Image i = new Image("sunflower.png", IMG_WIDTH, IMG_HEIGHT, false, true);
		ImageView j = new ImageView(i);
		Pane p = new Pane(j);
//		j.setLayoutX(272);
//		j.setLayoutY(181);
		actor a = new actor(-260, 0, 40, 0, j);
//		a.getSprite().setTranslateX(260);
//		a.getSprite().setTranslateY(270);
		
		Image nImage = new Image("chomper_moving.gif", IMG_WIDTH, IMG_HEIGHT, false, true);
		ImageView jImageView = new ImageView(nImage);
		Pane pane = new Pane(jImageView);
		ab = new actor(-260, 100, 1, 0, jImageView);
		
		Image peashooterImage = new Image("peashooter.png", 120, 71, false, true);
		ImageView peashooterView = new ImageView(peashooterImage);
		actor peashooter = new actor(-260, 200, 0, 0, peashooterView);
		
		Image pea = new Image("pea.png", IMG_WIDTH - 30, IMG_HEIGHT - 30, false, true);
		ImageView peaView = new ImageView(pea);
		peaActor = new actor(-230, 190, 1, 0, peaView);
		
		PeaShooter aPeaShooter = new PeaShooter(0, 0);
		
		base.getChildren().add(a.getSprite());
		base.getChildren().add(ab.getSprite());
		base.getChildren().add(peashooter.getSprite());
		base.getChildren().add(peaActor.getSprite());
		base.getChildren().add(aPeaShooter.getSprite());
		
//		while(true) {
//			p.setLayoutX(100);
//			p.
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
