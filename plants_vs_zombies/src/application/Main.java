package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/backyard.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			Long prevTime = new Long(System.nanoTime()).longValue();
			
			new AnimationTimer() {
				
				@Override
				public void handle(long currentTime) {
					backyard_controller.peaActor.update((currentTime - prevTime)/10000000000.0);
					
				}
			}.start();
			
			
//			new AnimationTimer() {
//				
//				@Override
//				public void handle(long currentTIme) {
//					backyard_controller.ab.update((currentTIme - prevTime)/1000000000.0);
//					
//				}
//			}.start();
			
			primaryStage.setScene(scene);
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
