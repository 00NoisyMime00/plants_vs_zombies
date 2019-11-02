package application;

import java.net.URL;
import application.PeaShooter;

import java.util.ArrayList;
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
	private ImageView sunflowerDrag;
	
	@FXML
	public StackPane base;
	
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public ArrayList<plants> plantsList = new ArrayList<plants>();
	
	private final int IMG_WIDTH = 80;
	private final int IMG_HEIGHT = 71;
	
	public static actor peaActor;
	public static actor ab;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
//		sunflowerDrag.addEventHandler(MouseEvent.MOUSE_DRAGGED, arg1);
//		sunflowerDrag.setOnMouseDragEntered(
//				new EventHandler<MouseEvent>() {
//					public void handle(MouseEvent e) {
//						sunflowerDrag.setTranslateX(e.getX());
//						sunflowerDrag.setTranslateY(e.getY());
//					}
//				}
//				);
		
//		sunflowerDrag.setVisible(false);
		
//	sunflowerDrag.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//		public void handle(MouseEvent e) {
//			sunflowerDrag.setTranslateX(e.getX());
//			sunflowerDrag.setTranslateY(e.getY());
//			
//			System.out.println("clicking "+e.getX());
//		}
//	});
//		
//		sunflowerDrag.setOnMouseClicked(
//						new EventHandler<MouseEvent>() {
//							public void handle(MouseEvent e) {
//								sunflowerDrag.setTranslateX(e.getX());
//								sunflowerDrag.setTranslateY(e.getY());
//								
//								System.out.println("clicking "+e.getX());
//							}
//						}
//				);
//		
		
//		base.addEventHandler(MouseEvent.MOUSE_PRESSED, arg1);
		
		
//		TranslateTransition transition = new TranslateTransition();
//		transition.setDuration(Duration.seconds(4));
//		transition.setNode(test);
		
//		transition.setFromX(0);
//		transition.setFromY(0);
//		test.setLayoutX(316);
//		test.setLayoutY(93);
		
		PeaShooter o = new PeaShooter(260, 260);
		draga(o);
		
		plantsList.add(o);
		base.getChildren().add(o.getSprite());
		
//		PeaBullet peaBullet = new PeaBullet(new Double(280), new Double(260), new Double(1));
//		base.getChildren().add(peaBullet.getSprite());
		
//		transition.setToX(1008);
//		transition.setByX(200);
//		transition.setAutoReverse(true);
//		transition.setCycleCount(TranslateTransition.INDEFINITE);
		
		
//		Image i = new Image("sunflower.png", IMG_WIDTH, IMG_HEIGHT, false, true);
//		ImageView j = new ImageView(i);
//		Pane p = new Pane(j);
//		j.setLayoutX(272);
//		j.setLayoutY(181);
//		actor a = new actor(0, 0, 40, 0, p);
//		a.getSprite().setTranslateX(260);
//		a.getSprite().setTranslateY(270);
		
//		Image nImage = new Image("chomper_moving.gif", IMG_WIDTH, IMG_HEIGHT, false, true);
//		ImageView jImageView = new ImageView(nImage);
//		Pane pane = new Pane(jImageView);
//		ab = new actor(442, 236, 1, 0, pane);
//		
//		Image peashooterImage = new Image("moving_peashooter.gif", 120, 71, false, true);
//		ImageView peashooterView = new ImageView(peashooterImage);
//		Pane peashooterPane = new Pane(peashooterView);
//		actor peashooter = new actor(100, 100, 0, 0, peashooterPane);
//		
//		Image pea = new Image("pea.png", IMG_WIDTH - 30, IMG_HEIGHT - 30, false, true);
//		ImageView peaView = new ImageView(pea);
//		Pane fPane = new Pane(peaView);
//		peaActor = new actor(100, 190, 1, 0, fPane);
//		
//		PeaShooter aPeaShooter = new PeaShooter(250, 474);
//		
//		base.getChildren().add(a.getSprite());
//		base.getChildren().add(ab.getSprite());
//		base.getChildren().add(peashooter.getSprite());
//		base.getChildren().add(peaActor.getSprite());
//		base.getChildren().add(aPeaShooter.getSprite());
//		
//		base.setOnMouseMoved(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				String msg =
//				          "(x: "       + event.getX()      + ", y: "       + event.getY()       + ") -- " +
//				          "(sceneX: "  + event.getSceneX() + ", sceneY: "  + event.getSceneY()  + ") -- " +
//				          "(screenX: " + event.getScreenX()+ ", screenY: " + event.getScreenY() + ")";
//				
//				System.out.println(msg);
////				peaActor.setPosition(event.getX(), event.getY());
//				peashooter.setPosition(event.getX(), event.getY());
//			}
//		});
		
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
//		transition.play();
		

		
	}
	
	public Pane getBase() {
		return this.base;
	}
	
	public ArrayList<Bullet> getBulletsList(){
		return this.bullets;
	}
	
	public ArrayList<plants> getPlantsList(){
		return this.plantsList;
	}
	
	public void draga(plants o) {
		o.getSprite().setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
//				o.getSprite().setTranslateX(event.getX());
				o.setPosition(event.getSceneX(), event.getSceneY());
				System.out.println(o.getSprite().getTranslateX()+" "+o.getSprite().getTranslateY()+" "+event.getX()+" "+event.getY());
			}
		});
	}
	
	public void drag(ActionEvent event) {
		
	}
	
	
	
	
}
