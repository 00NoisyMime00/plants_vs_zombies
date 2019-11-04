package application;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class shovel {
	private Pane image;
	
	public shovel() {
		this.image = shovel.form_image();
		this.image.setTranslateX(1000);
		this.image.setTranslateY(540);
		
		moveShowel(this.image);
		resetShowel(this.image);
	}
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("shovel.png", 50, 50, false, true)));
		return pane;
	}
	
	public Pane getSprite() {
		return this.image;
	}
	
	public void moveShowel(Pane p) {
		p.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				p.setTranslateX(event.getSceneX());
				p.setTranslateY(event.getSceneY());
//				System.out.println("Moving shovel");
				
			}
		});
	}
	
	public void resetShowel(Pane p) {
		p.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				p.setTranslateX(1000);
				p.setTranslateY(540);
//				System.out.println("reseting showel");
				
			}
		});
	}
	
	public static void removePlant() {
		
	}
}
