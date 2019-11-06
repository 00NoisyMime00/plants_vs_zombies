package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class sunToken {
	private double x;
	private double y;
	private Pane image;
	
	private static double getRandomPoint(double lower, double upper) {
		Random r = new Random();
		return r.nextDouble()*(upper - lower) + lower;
	}
	
	public sunToken() {
		this.x = sunToken.getRandomPoint(250, 1000);
		this.y = sunToken.getRandomPoint(65, 575);
		System.out.println("x "+x+" y "+y);
		this.image = form_image();
		this.image.setTranslateX(this.x);
		this.image.setTranslateY(this.y);
	}
	
	public Pane getSprite() {
		return this.image;
	}
	
	private static Pane form_image() {
		Pane p = new Pane(new ImageView(new Image("sunToken.png", 50, 50, false, true)));
		return p;
	}
	
	public static Pane generateSun() {
		return (new sunToken()).getSprite();
	}
}
