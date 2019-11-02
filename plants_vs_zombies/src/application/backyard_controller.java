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
	public StackPane base;
	
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
//	change this list to list of all bullet shooters
	public ArrayList<plants> peashooterList = new ArrayList<plants>();
	public ArrayList<plants> cardPlantList = new ArrayList<plants>();
	
//	The drag and drop Images, have to be intialised, add chomper etc...
	private Pane peashooter;
	private Pane sunflower;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
//		BACKYARD SETUP!
		
		Pane pane = null;
		
//		for drag and drop of sunflower, completed
		pane = new Pane(new ImageView(new Image("sunflower_card.png", 70, 90, false, true)));
		pane.setTranslateX(20);
		pane.setTranslateY(20);
		dragPlantsToPlace(pane, "sunflower");
		sunflower = new Pane(new ImageView(new Image("sunflower.png", 100, 71, false, true)));
		sunflower.setVisible(false);
		base.getChildren().add(sunflower);
		base.getChildren().add(pane);
		
//		for drag and drop of peashooter, completed
		pane = new Pane(new ImageView(new Image("peashooter_card.png", 70, 90, false, true)));
		pane.setTranslateX(20);
		pane.setTranslateY(115);
		dragPlantsToPlace(pane, "peashooter");
		peashooter = new Pane(new ImageView(new Image("peashooter.png", 150, 90, false, true)));
		peashooter.setVisible(false);
		base.getChildren().add(peashooter);
		base.getChildren().add(pane);
		
//		for drag and drop of chomper, INCOMPLETE
		pane = new Pane(new ImageView(new Image("chomper_card.png", 70, 90, false, true)));
		pane.setTranslateX(20);
		pane.setTranslateY(210);
		base.getChildren().add(pane);
		
//		for drag and drop of wallnut, INCOMPLETE
		pane = new Pane(new ImageView(new Image("wallnut_card.png", 70, 90, false, true)));
		pane.setTranslateX(20);
		pane.setTranslateY(300);
		base.getChildren().add(pane);
		
//		for drag and drop of snowpea, INCOMPLETE
		pane = new Pane(new ImageView(new Image("snowpea_card.png", 70, 90, false, true)));
		pane.setTranslateX(20);
		pane.setTranslateY(395);
		base.getChildren().add(pane);
		
		
	}
	
	public Pane getBase() {
		return this.base;
	}
	
	public ArrayList<Bullet> getBulletsList(){
		return this.bullets;
	}
	
	public ArrayList<plants> getpeashooterList(){
		return this.peashooterList;
	}
	
	public ArrayList<plants> getCardPlantList(){
		return this.cardPlantList;
	}
	
	
//	Method that does the drag and drop! add for chomper etc..
	public void dragPlantsToPlace(Pane o, String plantChoice) {
		
		o.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				
				
				if(plantChoice.equals("sunflower")) {
					sunflower.setVisible(true);
					sunflower.setTranslateX(event.getSceneX());
					sunflower.setTranslateY(event.getSceneY());
				}
				else if(plantChoice.equals("peashooter")) {
					peashooter.setVisible(true);
					peashooter.setTranslateX(event.getSceneX());
					peashooter.setTranslateY(event.getSceneY());
				}
			}
		});
		
		o.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(plantChoice.equals("sunflower")) {
					sunflower.setVisible(false);
					placePlants(event.getSceneX() - 10, event.getSceneY() - 5, "sunflower");
				}
				else if(plantChoice.equals("peashooter")) {
					peashooter.setVisible(false);
					placePlants(event.getSceneX() + 10, event.getSceneY() + 10, "peashooter");
				}
			}
		});
	}
	
	
	
	public void placePlants(double positionX, double positionY, String plantChoice) {
		plants o = null;
		if(plantChoice.equals("peashooter")) {
			o = new PeaShooter(positionX, positionY);
//			CHANGE THIS!! ONLY ADDING PEASHOOTERS NOW!! THIS LIST IS FOR ALL BULLET PLANTS
			this.peashooterList.add(o);
			
		}
		else if(plantChoice.equals("sunflower")) {
			o = new Sunflower(positionX, positionY);
		}
		this.base.getChildren().add(o.getSprite());
	}
	
	
}
