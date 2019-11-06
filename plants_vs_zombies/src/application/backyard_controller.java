package application;

import java.net.URL;
import application.actor.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.input.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class backyard_controller implements Initializable{
	
	
	
	@FXML
	public StackPane base;
	
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
//	change this list to list of all bullet shooters
	public ArrayList<plants> peashooterList = new ArrayList<plants>();
	public ArrayList<plants> cardPlantList = new ArrayList<plants>();
	private ArrayList<Pane> sunTokenList = new ArrayList<Pane>();
	
//	The drag and drop Images, have to be intialised, add chomper etc...
	private Pane peashooter;
	private Pane sunflower;
	
	
//	Shovel for each backyard
	private static shovel sh;
	
//	2D matrix for each backyard
	private int[][] backyardMatrix = new int[9][9];
	
	private static ingameMenu m;
	
	private boolean gamePaused = false;
	
	
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
		
		
		
//		Ingame menu button
		m = new ingameMenu();
		pause(m.getSprite());
		pause(m.getSprite());
		base.getChildren().add(m.getSprite());
		
		
//		Lawn Mowers
		lawnMower.createlawnMowers(this.getBase());
		
		
//		Shovel
		sh = new shovel();
		base.getChildren().add(sh.getSprite());
		
		
//		Sun Token
//		TODO: remove suns after some time
		generateSunTokens(this);
		
//		base.setOnMouseMoved(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent arg0) {
//				System.out.println("x "+arg0.getSceneX()+" y "+arg0.getSceneY());
//				
//			}
//		});
		
	}
	
	public void generateSunTokens(backyard_controller base) {
		long[] startTime = new long[] {System.nanoTime()};
		Pane pane[] = new Pane[] {null};
		double[] y = new double[] {-1};
		
		new AnimationTimer() {
			
			@Override
			public void handle(long currentTime) {
				
				if((currentTime - startTime[0])/1000000000 >= 10) {
					pane[0] = sunToken.generateSun();
					y[0] = pane[0].getTranslateY();
					pane[0].setTranslateY(0);
					base.getBase().getChildren().add(pane[0]);
					base.addToSunTokenList(pane[0]);
					startTime[0] = currentTime;
					backyard_controller.bringComponentsOnTop();
				}
				if(pane[0] != null) {
					if(y[0] >= pane[0].getTranslateY()) {
						pane[0].setTranslateY(2*((currentTime - startTime[0])/10000000));
					}
					else {
						
						pane[0] = null;
						y[0] = -1;
					}
				}
			}
		}.start();
		
	}
	
	public void addToSunTokenList(Pane p) {
		this.sunTokenList.add(p);
	}
	
	public Pane getBase() {
		return this.base;
	}
	
	public boolean getIsGamePaused() {
		return this.gamePaused;
	}
	
	public void setIsGamePaused(boolean b) {
		this.gamePaused = b;
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
					peashooter.setTranslateX(event.getSceneX() - 25);
					peashooter.setTranslateY(event.getSceneY());
				}
			}
		});
		
		o.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				int xIndex = (int)(event.getSceneX() - 255 + 20)/100;
				int yIndex = (int)(event.getSceneY() - 80)/100;
				
//				just to adjust
				if(event.getSceneX() > 420)
					xIndex+=1;
				if(event.getSceneX() > 815)
					xIndex++;
				
//				checking if inside backyard or not
				if(xIndex >= 0 && xIndex <= 8 && yIndex >=0 && yIndex <= 4) {
					
					if(plantChoice.equals("sunflower")) {
						placePlants(255+xIndex*80 - 20, 80 + yIndex*100, "sunflower", xIndex, yIndex);
					}
					else if(plantChoice.equals("peashooter")) {
						
						placePlants(255+xIndex*80 - 20, 80 + yIndex*100, "peashooter", xIndex, yIndex);
					}
				}
				sunflower.setVisible(false);
				peashooter.setVisible(false);
			}
		});
	}
	
	
	public void placePlants(double positionX, double positionY, String plantChoice, int matrixX, int matrixY) {
		plants o = null;
//		System.out.println("x "+matrixX+" y "+matrixY);
		if(this.backyardMatrix[matrixX][matrixY] != 1) {
			this.backyardMatrix[matrixX][matrixY] = 1;
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
		bringComponentsOnTop();
	}
	
	public void pause(Pane p) {
		p.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				System.out.println("clicking menu");
				Stage s = Main.getStage();
				s.setScene(Main.getInGameScene());
				
				Main.playMainPageSound();
				
			}
		});
	}
	
	public static void bringComponentsOnTop() {

		m.getSprite().toFront();
		sh.getSprite().toFront();
//		System.out.println("calling");
	}
	
	public ArrayList<Pane> getSunTokenList() {
		return this.sunTokenList;
	}

	
	
}
