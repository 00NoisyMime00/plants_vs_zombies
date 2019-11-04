package application;
	
import java.io.File;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			
			FXMLLoader loader;
			Parent root;
			
//			backyard
			loader = new FXMLLoader(getClass().getResource("/application/backyard.fxml"));
			root = loader.load();
			backyard_controller base = loader.getController();
			
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
			
			
			Long prevTime[] = new Long[] {new Long(System.nanoTime()).longValue()};
			
//			TODO: convert to a separate handler class
			new AnimationTimer() {
				
				@Override
				public void handle(long currentTime) {
					
					if(!base.getIsGamePaused()) {
						if((currentTime - prevTime[0])/1000000000.0 >= 2) {
							for(plants p: base.getpeashooterList()) {
								base.getBulletsList().add(p.attack(base.getBase()));
								prevTime[0] = currentTime;
							}
						}
						
						ArrayList<plants> allPlants = base.getCardPlantList();
						for(plants p: allPlants) {
							base.dragPlantsToPlace(p.getSprite(), p.getType());
						}
					}
					else {
//						TODO
					}
					
				}
			}.start();
			
			Long prevTimeLong[] = new Long[] {new Long(System.nanoTime())};
			new AnimationTimer() {
				
				@Override
				public void handle(long currentTIme) {
					for(Bullet b: base.getBulletsList()) {
						b.update((currentTIme - b.getStartTime())/10000000000.0);
					}
					
					
				}
			}.start();
			
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
