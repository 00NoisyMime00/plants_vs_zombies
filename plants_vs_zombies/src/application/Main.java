package application;
	
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene sceneBackyard;
	private static Scene sceneInGameMenu;
	private static Scene sceneMainPage;
	
	private static MediaPlayer mediaPlayer;
	
//	class Objects
	private static backyard_controller base;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			
			FXMLLoader loader;
			Parent root;
			
//			backyard
			loader = new FXMLLoader(getClass().getResource("/application/backyard.fxml"));
			root = loader.load();
			Main.base = loader.getController();
			
			Scene sceneBackyard = new Scene(root);
			
			sceneBackyard.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.sceneBackyard = sceneBackyard;
			primaryStage.setScene(sceneBackyard);
			
			
			
//			In-Game Menu
			loader = new FXMLLoader(getClass().getResource("/application/inGameMenu.fxml"));
			root = loader.load();
			ingameMenuController ingameMenu = loader.getController();
			
			Scene sceneInGameMenu = new Scene(root);
			Main.sceneInGameMenu = sceneInGameMenu;
//			primaryStage.setScene(sceneInGameMenu);
			
			
//			Main page
			loader = new FXMLLoader(getClass().getResource("/application/mainPage.fxml"));
			root = loader.load();
			mainPageController mainPage = loader.getController();
			
			Scene sceneMainPage = new Scene(root);
			Main.sceneMainPage = sceneMainPage;
			primaryStage.setScene(sceneMainPage);
			
			
			
				
//			Handle all plants eg. peashooter shooting
			plantsActionHandler.getInstance(base).start();
			
			
//			Handles bullets movement
			bulletsMovementHandler.getInstance(base).start();
			
			
//			Handles removing sunTokens after some time
			removeSunTokensAfterSometime.getInstance().start();
			
//			Handles replanting time
			replantTimeHandler.getInstance().start();
			
			playMainPageSound();
	
			
			primaryStage.setResizable(false);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void playMainPageSound() {
		String musicFile = "soundtracks/mainMenuSoundtrack.mp3";     // For example
		
		if(mediaPlayer != null)
			mediaPlayer.stop();
		
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	public static void playGameSound() {
		String musicFile = "soundtracks/gamePlaySoundTrack.mp3";     // For example
		
		mediaPlayer.stop();
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	public static Scene getBackyarScene() {
		return sceneBackyard;
	}
	
	public static Scene getInGameScene() {
		return sceneInGameMenu;
	}
	
	public static Scene getMainPage() {
		return sceneMainPage;
	}
	
	public static backyard_controller getCurrentBase() {
		return Main.base;
	}
	
	public static void setBase(backyard_controller base) {
		Main.base = base;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void startNewGame(Stage primaryStage) throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/application/backyard.fxml"));
		Parent root = loader.load();
		Main.base = loader.getController();
		
		Scene sceneBackyard = new Scene(root);
		
		sceneBackyard.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		Main.sceneBackyard = sceneBackyard;
		primaryStage.setScene(sceneBackyard);
		
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}

class plantsActionHandler extends AnimationTimer{
	
	private Long prevTime[] = new Long[] {new Long(System.nanoTime()).longValue()};
	private backyard_controller base;
	
	private static plantsActionHandler instance;
	
	private plantsActionHandler(backyard_controller base) {
		this.base = base;
	}
	
	public static plantsActionHandler getInstance(backyard_controller base) {
		if(instance == null) {
			instance = new plantsActionHandler(base);
		}
		
		return instance;
	}

	@Override
	public void handle(long currentTime) {
		
		if(!base.getIsGamePaused()) {
			if((currentTime - prevTime[0])/1000000000.0 >= 2) {
				for(plants p: base.getpeashooterList()) {
					base.getBulletsList().add(p.attack(base.getBase()));
					prevTime[0] = currentTime;
					base.bringComponentsOnTop();
				}
			}
			
			ArrayList<plants> allPlants = base.getCardPlantList();
			for(plants p: allPlants) {
				base.dragPlantsToPlace(p.getSprite(), p.getType());
			}
			
		}
		else {
//			TODO
		}
		
	}
	
}

class bulletsMovementHandler extends AnimationTimer{
	
	private static bulletsMovementHandler instance;
	private backyard_controller base;
	
	private bulletsMovementHandler(backyard_controller base) {
		this.base = base;
	}

	
	public static bulletsMovementHandler getInstance(backyard_controller base) {
		if(instance == null) {
			instance = new bulletsMovementHandler(base);
		}
		
		return instance;
	}
	
	@Override
	public void handle(long currentTIme) {
		for(Bullet b: base.getBulletsList()) {
			b.update((currentTIme - b.getStartTime())/10000000000.0);
		}
		
	}
	
}

//If game is not paused, removes the first suntoken if it has been there for more than 5 seconds
class removeSunTokensAfterSometime extends AnimationTimer{
	private static removeSunTokensAfterSometime instance;
	
	private removeSunTokensAfterSometime() {
	}
	
	public static removeSunTokensAfterSometime getInstance() {
		if(instance == null) {
			instance = new removeSunTokensAfterSometime();
		}
		return instance;
	}
	
	@Override
	public void handle(long currentTime) {
		if(!Main.getCurrentBase().getIsGamePaused()) {
			ArrayList<sunToken> allSunTokens = Main.getCurrentBase().getSunTokenList();
			if(allSunTokens.size() > 0) {
				if((currentTime - allSunTokens.get(0).getStartTime())/1000000000 >= 15) {
					allSunTokens.get(0).getSprite().setVisible(false);
					allSunTokens.remove(0);
				}
			}
		}
	}
	
}

class replantTimeHandler extends AnimationTimer{
	private static replantTimeHandler instance;
	
	public static replantTimeHandler getInstance() {
		if(instance == null) {
			instance = new replantTimeHandler();
		}
		return instance;
	}
	
	private replantTimeHandler() {
		
	}

	@Override
	public void handle(long currentTime) {
		if(!Main.getCurrentBase().getIsGamePaused()) {
//			sunflower
			if(Sunflower.getTimeLeftToPlant() > 0) {
				backyard_controller.getCard("sunflower").setOpacity(0.5);
				Sunflower.setTimeLeftToPlant(Sunflower.getReplantTime() - ((currentTime - Sunflower.getPlaceTime())/1000000000) );
			}
			else {
				if(Main.getCurrentBase().checkEnoughMoney(Sunflower.getPrice())) {
					backyard_controller.getCard("sunflower").setOpacity(1);
				}
				else {
					backyard_controller.getCard("sunflower").setOpacity(0.5);
				}
			}
			
//			peashooter
			
			
//			chomper
			
//			snowpeashooter
			
//			walnut

		}
	}
	
}
