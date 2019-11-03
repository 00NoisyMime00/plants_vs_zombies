package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class showel {
	private Pane image;
	
	public showel() {
		this.image = showel.form_image();
		this.image.setTranslateX(280);
		this.image.setTranslateY(5);
	}
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("shovel.png", 50, 50, false, true)));
		return pane;
	}
	
	public Pane getSprite() {
		return this.image;
	}
}
