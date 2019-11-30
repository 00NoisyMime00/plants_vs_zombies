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

public class backyard_controller implements Initializable{
	
	
	
	@FXML
	public StackPane base;
	
	@FXML
	public Label scoreLabel;
	
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
//	change this list to list of all bullet shooters
	public ArrayList<plants> peashooterList = new ArrayList<plants>();
	public ArrayList<plants> cardPlantList = new ArrayList<plants>();
	private ArrayList<sunToken> sunTokenList = new ArrayList<sunToken>();
	
	public ArrayList<plants> nonShooterPlantsList = new ArrayList<plants>();
	
	private int score = 50;
	
//	The drag and drop Images, have to be intialised, add chomper etc...
	private Pane peashooter;
	private Pane sunflower;
	private Pane snowpeashooter;
	private Pane walnut;
	private Pane chomper;
	
//	Side-card Images, have to be initialised
	private static Pane peashooterCard;
	private static Pane sunflowerCard;
	private static Pane walnutCard;
	private static Pane snowPeaShooterCard;
	private static Pane chomperCard;
	
	private static long pauseTime;
	
	
//	Shovel for each backyard
	private static shovel sh;
	
//	2D matrix for each backyard
	private int[][] backyardMatrix = new int[9][9];
	
//	2D matrix for marking Zombie positions
	private int[][] zombieMatrix = new int[9][9];
	
//	2D matrix that stores actual plants for attack purposes
	private plants[][] actualPlantMatrix = new plants[9][9];
	
//	2D matrix that stores actual zombies for attack purposes
	private Zombie[][][] actualZombieMatrix = new Zombie[9][9][20];
	
	private static ingameMenu m;
	
	private boolean gamePaused = false;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
//		BACKYARD SETUP!
		
		Pane pane = null;
		
//		Score board setup
		this.updateScoreBoard();
		
//		for drag and drop of sunflower, completed
		pane = new Pane(new ImageView(new Image("sunflower_card.png", 70, 90, false, true)));
		sunflowerCard = pane;
		pane.setTranslateX(20);
		pane.setTranslateY(20);
		dragPlantsToPlace(pane, "sunflower");
		sunflower = new Pane(new ImageView(new Image("sunflower.png", 100, 71, false, true)));
		sunflower.setVisible(false);
		base.getChildren().add(sunflower);
		base.getChildren().add(pane);
		
//		for drag and drop of peashooter, completed
		pane = new Pane(new ImageView(new Image("peashooter_card.png", 70, 90, false, true)));
		peashooterCard = pane;
		pane.setTranslateX(20);
		pane.setTranslateY(115);
		dragPlantsToPlace(pane, "peashooter");
		peashooter = new Pane(new ImageView(new Image("peashooter.png", 150, 90, false, true)));
		peashooter.setVisible(false);
		base.getChildren().add(peashooter);
		base.getChildren().add(pane);
		
//		for drag and drop of snowpea, INCOMPLETE
		pane = new Pane(new ImageView(new Image("snowpea_card.png", 70, 90, false, true)));
		snowPeaShooterCard = pane;
		pane.setTranslateX(20);
		pane.setTranslateY(210);
		dragPlantsToPlace(pane, "snowpeashooter");
		snowpeashooter = new Pane(new ImageView(new Image("icePeaShooter.png", 110, 90, false, true)));
		snowpeashooter.setVisible(false);
		base.getChildren().add(snowpeashooter);
		base.getChildren().add(pane);
		
//		for drag and drop of chomper, INCOMPLETE
		pane = new Pane(new ImageView(new Image("chomper_card.png", 70, 90, false, true)));
		chomperCard = pane;
		pane.setTranslateX(20);
		pane.setTranslateY(300);
		dragPlantsToPlace(pane, "chomper");
		chomper = new Pane(new ImageView(new Image("chomper.png", 70, 90, false, true)));
		chomper.setVisible(false);
		base.getChildren().add(chomper);
		base.getChildren().add(pane);
		
//		for drag and drop of wallnut, INCOMPLETE
		pane = new Pane(new ImageView(new Image("wallnut_card.png", 70, 90, false, true)));
		walnutCard = pane;
		pane.setTranslateX(20);
		pane.setTranslateY(395);
		dragPlantsToPlace(pane, "walnut");
		walnut = new Pane(new ImageView(new Image("walnut.png", 100, 90, false, true)));
		walnut.setVisible(false);
		base.getChildren().add(walnut);
		base.getChildren().add(pane);
		
		
		
//		Ingame menu button
		m = new ingameMenu();
		pause(m.getSprite(), this);
		base.getChildren().add(m.getSprite());
		
		
//		Lawn Mowers
		lawnMower.createlawnMowers(this.getBase());
		
		
//		Shovel
		sh = new shovel();
		base.getChildren().add(sh.getSprite());
	
		
//		Sun Token
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
	
	
//	generates new sun tokens every 10 seconds and places them(animates)
	public void generateSunTokens(backyard_controller base) {
		long[] startTime = new long[] {System.nanoTime()};
		Pane pane[] = new Pane[] {null};
		double[] y = new double[] {-1};
		
		new AnimationTimer() {
			
			@Override
			public void handle(long currentTime) {
				
				if(!base.getIsGamePaused()) {
					
					if((currentTime - startTime[0])/1000000000 >= 10) {
						sunToken newToken = sunToken.generateSun();
						pane[0] = newToken.getSprite();
						base.collectSun(newToken, base);
						y[0] = pane[0].getTranslateY();
						pane[0].setTranslateY(0);
						base.getBase().getChildren().add(pane[0]);
						base.addToSunTokenList(newToken);
						startTime[0] = currentTime;
						base.bringComponentsOnTop();
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
				else {
					startTime[0] = currentTime;
					for(sunToken s: base.getSunTokenList()) {
						s.setStartTime(System.nanoTime());
					}
				}
				
			}
		}.start();
		
	}
	
	public void addToSunTokenList(sunToken p) {
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
	
	public ArrayList<plants> getNonShooterPlantsList(){
		return this.nonShooterPlantsList;
	}
	
	public Label getScoreBoard() {
		return this.scoreLabel;
	}
	
	public void updateScoreBoard() {
		this.getScoreBoard().setText(Integer.toString(this.getScore()));
	}
	
	public boolean checkEnoughMoney(int cost) {
		if(this.score - cost >= 0)
			return true;
		return false;
	}
	
	public static long getPauseTime() {
		return pauseTime;
	}
	
	public static void setPauseTime(long time) {
		pauseTime = time;
	}
	
	public static double getYCoordinate(int y) {
		return y*100 + 80;
	}
	
	public int[][] getBackyardMatrix(){
		return backyardMatrix;
	}
	
	public void setBackyardMatrix(int x, int y, int value) {
		this.backyardMatrix[x][y] = value; 
	}
	
	public int[][] getZombieMatrix(){
		return this.zombieMatrix;
	}
	
	public void setZombieMatrix(int x, int y, int value) {
		this.zombieMatrix[x][y] = value; 
	}
	
	public Zombie[][][] getActualZombieMatrix(){
		return this.actualZombieMatrix;
	}
	
	public void setActualZombieMatrix(int x, int y, Zombie z) {
		this.actualZombieMatrix[x][y][this.getZombieMatrix()[x][y] - 1] = z; 
	}
	
	public void removeActualZombieMatrix(int x, int y) {
		for(int i = 1; i < 20; i++) {
			this.actualZombieMatrix[x][y][i - 1] = this.actualZombieMatrix[x][y][i]; 
			this.actualZombieMatrix[x][y][19] = null;
		}
	}
	
	public plants[][] getActualPlantMatrix(){
		return this.actualPlantMatrix;
	}
	
	public void setActualPlantMatrix(int x, int y, plants p) {
		this.actualPlantMatrix[x][y] = p; 
	}
	
	public static int[] getIndices(double x, double y) {
		int xIndex = (int)(x - 255 + 20)/100;
		int yIndex = (int)(y - 80)/100;
		
//		just to adjust
		if(x > 420)
			xIndex+=1;
		if(y > 815)
			xIndex++;
		
//		checking if inside backyard or not
//		if(xIndex >= 0 && xIndex <= 8 && yIndex >=0 && yIndex <= 4) {
//			
//		}
		int[] cor = new int[2];
		cor[0] = xIndex;
		cor[1] = yIndex;
		
		return cor;
	}
	
	public static Pane getCard(String name) {
		if(name.equals("sunflower")) {
			return sunflowerCard;
		}
		if(name.equals("peashooter")) {
			return peashooterCard;
		}
		if(name.equals("walnut")) {
			return walnutCard;
		}
		if(name.equals("snowpeashooter")) {
			return snowPeaShooterCard;
		}
		if(name.equals("chomper")) {
			return chomperCard;
		}
		System.out.println("error in card getting card, backyard class");
		return null;
	}
	
	
//	Method that does the drag and drop! add for chomper etc..
	public void dragPlantsToPlace(Pane o, String plantChoice) {
		
		o.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				
				if(plantChoice.equals("sunflower") && checkEnoughMoney(Sunflower.getPrice()) && Sunflower.getTimeLeftToPlant() <= 0) {
					sunflower.setVisible(true);
					sunflower.setTranslateX(event.getSceneX());
					sunflower.setTranslateY(event.getSceneY());
				}
				else if(plantChoice.equals("peashooter") && checkEnoughMoney(PeaShooter.getPrice()) && PeaShooter.getTimeLeftToPlant() <= 0) {
					peashooter.setVisible(true);
					peashooter.setTranslateX(event.getSceneX() - 25);
					peashooter.setTranslateY(event.getSceneY());
				}
				else if(plantChoice.equals("snowpeashooter") && checkEnoughMoney(snowPeaShooter.getPrice()) && snowPeaShooter.getTimeLeftToPlant() <= 0) {
					snowpeashooter.setVisible(true);
					snowpeashooter.setTranslateX(event.getSceneX() - 25);
					snowpeashooter.setTranslateY(event.getSceneY());
				}
				else if(plantChoice.equals("chomper") && checkEnoughMoney(Chomper.getPrice()) && Chomper.getTimeLeftToPlant() <= 0) {
					chomper.setVisible(true);
					chomper.setTranslateX(event.getSceneX() - 25);
					chomper.setTranslateY(event.getSceneY());
				}
				else if(plantChoice.equals("walnut") && checkEnoughMoney(Walnut.getPrice()) && Walnut.getTimeLeftToPlant() <= 0) {
					walnut.setVisible(true);
					walnut.setTranslateX(event.getSceneX() - 25);
					walnut.setTranslateY(event.getSceneY());
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
					
					if(plantChoice.equals("sunflower") && checkEnoughMoney(Sunflower.getPrice()) && Sunflower.getTimeLeftToPlant() <= 0) {
						placePlants(255+xIndex*80 - 20, 80 + yIndex*100, "sunflower", xIndex, yIndex);
					}
					else if(plantChoice.equals("peashooter") && checkEnoughMoney(PeaShooter.getPrice()) && PeaShooter.getTimeLeftToPlant() <= 0) {
						placePlants(255+xIndex*80 - 20, 80 + yIndex*100, "peashooter", xIndex, yIndex);
					}
					else if(plantChoice.equals("snowpeashooter") && checkEnoughMoney(snowPeaShooter.getPrice()) && snowPeaShooter.getTimeLeftToPlant() <= 0) {
						placePlants(255+xIndex*80 - 20, 80 + yIndex*100, "snowpeashooter", xIndex, yIndex);
					}
					else if(plantChoice.equals("chomper") && checkEnoughMoney(Chomper.getPrice()) && Chomper.getTimeLeftToPlant() <= 0) {
						placePlants(255+xIndex*80 - 20, 80 + yIndex*100, "chomper", xIndex, yIndex);
					}
					else if(plantChoice.equals("walnut") && checkEnoughMoney(Walnut.getPrice()) && Walnut.getTimeLeftToPlant() <= 0) {
						placePlants(255+xIndex*80 - 20, 80 + yIndex*100, "walnut", xIndex, yIndex);
					}
				}
				sunflower.setVisible(false);
				peashooter.setVisible(false);
				snowpeashooter.setVisible(false);
				chomper.setVisible(false);
				walnut.setVisible(false);
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
				this.peashooterList.add(o);
				this.setScore(this.getScore() - PeaShooter.getPrice());
//				reset plant time
				PeaShooter.resetTimeLeftToPlant();
//				set Place time
				PeaShooter.setPlaceTime(System.nanoTime());
				
			}
			else if(plantChoice.equals("sunflower")) {
				o = new Sunflower(positionX, positionY);
				this.nonShooterPlantsList.add(o);
				this.setScore(this.getScore() - Sunflower.getPrice());
//				reset plant time
				Sunflower.resetTimeLeftToPlant();
//				set place time
				Sunflower.setPlaceTime(System.nanoTime());
			}
			else if(plantChoice.equals("snowpeashooter")) {
				o = new snowPeaShooter(positionX, positionY);
				this.peashooterList.add(o);
				this.setScore(this.getScore() - snowPeaShooter.getPrice());
//				reset plant time
				snowPeaShooter.resetTimeLeftToPlant();
//				set place time
				snowPeaShooter.setPlaceTime(System.nanoTime());
			}
			else if(plantChoice.equals("chomper")) {
				o = new Chomper(positionX, positionY);
				this.nonShooterPlantsList.add(o);
				this.setScore(this.getScore() - Chomper.getPrice());
//				reset plant time
				Chomper.resetTimeLeftToPlant();
//				set place time
				Chomper.setPlaceTime(System.nanoTime());
			}
			else if(plantChoice.equals("walnut")) {
				o = new Walnut(positionX, positionY);
				this.nonShooterPlantsList.add(o);
				this.setScore(this.getScore() - Walnut.getPrice());
//				reset plant time
				Walnut.resetTimeLeftToPlant();
//				set place time
				Walnut.setPlaceTime(System.nanoTime());
			}
			
			this.base.getChildren().add(o.getSprite());
		}
		bringComponentsOnTop();
	}
	
	public void pause(Pane p, backyard_controller base) {
		p.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				backyard_controller.setPauseTime(System.nanoTime());
				System.out.println("clicking menu");
				Stage s = Main.getStage();
				s.setScene(Main.getInGameScene());
				base.setIsGamePaused(true);
				
				Main.playMainPageSound();
				
			}
		});
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int newScore) {
		this.score = newScore;
		this.updateScoreBoard();
	}
	
	public void collectSun(sunToken s, backyard_controller base) {
		Pane p = s.getSprite();
		p.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				p.setTranslateX(-10);
				p.setTranslateY(-10);
				p.setVisible(false);
				base.getSunTokenList().remove(s);
				base.setScore(base.getScore() + 50);
				System.out.println("new score is: "+base.getScore());
			}
		});
	}
	
	public void bringComponentsOnTop() {

		m.getSprite().toFront();
		
		for(sunToken s: this.getSunTokenList()) {
			s.getSprite().toFront();
		}
		sh.getSprite().toFront();
		
//		System.out.println("calling");
	}
	
	public ArrayList<sunToken> getSunTokenList() {
		return this.sunTokenList;
	}
	
//	INCOMPLETE: Zombies, place time!
	public void resetGame() {
		for(plants p: this.getpeashooterList()) {
			p.getSprite().setVisible(false);
			p.setPosition(-100, -100);
		}
		for(plants p: this.getNonShooterPlantsList()) {
			p.getSprite().setVisible(false);
			p.setPosition(-100, -100);
		}
		for(Bullet b:this.getBulletsList()) {
			b.getSprite().setVisible(false);
			b.setPosition(-100, -100);
		}
		for(sunToken p: this.getSunTokenList()) {
			Pane s = p.getSprite();
			s.setVisible(false);
			s.setTranslateX(-100);
			s.setTranslateY(-100);
		}
		for(lawnMower l: lawnMower.getLawnMowerList()) {
			l.getSprite().setVisible(false);
		}
		
		this.peashooterList = new ArrayList<plants>();
		this.nonShooterPlantsList = new ArrayList<plants>();
		this.sunTokenList = new ArrayList<sunToken>();
		this.bullets = new ArrayList<Bullet>();
		this.backyardMatrix = new int[9][9];
		lawnMower.createlawnMowers(base);
		
		
		this.setScore(50);
	}

	
	
}
