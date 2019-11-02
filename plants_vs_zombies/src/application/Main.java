package application;
	
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/backyard.fxml"));
			Parent root = loader.load();
			backyard_controller base = loader.getController();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			Long prevTime[] = new Long[] {new Long(System.nanoTime()).longValue()};
			
			new AnimationTimer() {
				
				@Override
				public void handle(long currentTime) {
//					backyard_controller.peaActor.update((currentTime - prevTime)/10000000000.0);
//					backyard_controller.bullets.update((currentTime - prevTime)/10000000000.0);
//					for(Bullet b: backyard_controller.bullets) {
//						b.update((currentTime - prevTime[0])/10000000000.0);
//					}
//					System.out.println((currentTime - prevTime)/1000000000.0);
					if((currentTime - prevTime[0])/1000000000.0 >= 2) {
//						System.out.println("here?");
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
			}.start();
			
			Long prevTimeLong[] = new Long[] {new Long(System.nanoTime())};
			new AnimationTimer() {
				
				@Override
				public void handle(long currentTIme) {
//					System.out.println("here");
					for(Bullet b: base.getBulletsList()) {
						b.update((currentTIme - b.getStartTime())/10000000000.0);
//						prevTimeLong[0] = currentTIme;
					}
					
				}
			}.start();
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
