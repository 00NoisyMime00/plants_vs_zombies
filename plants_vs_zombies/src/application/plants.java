package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class plants extends actor{
	private ArrayList<Bullet> bullets;
	protected String type;
	protected static int price;
	
	public plants(double positionX, double positionY, Pane plantImage){
		super(positionX, positionY, 0, 0, plantImage);
//		System.out.println("sending "+(positionX));
	}
	
	public void detectEnemy() {
		
	}
	
	public static int getPrice() {
		return price;
	}
	
	public Bullet attack(Pane p) {
		return null;
	}
	
	public String getType() {
		return this.type;
	}
	
}

class PeaShooter extends plants{
	public PeaShooter(double positionX, double positionY) {
		super(positionX, positionY, form_image());
		this.type = "peashooter";
		this.price = 100;
	}
	
	private static Pane form_image() {
		Image img = new Image("moving_peashooter.gif", 100, 71, false, true);
		ImageView view = new ImageView(img);
		Pane pane = new Pane(view);
		
		return pane;
	}
	
	public Bullet shoot(Pane p) {
		PeaBullet newPeaBullet = new PeaBullet(this.getSprite().getTranslateX() + 70, this.getSprite().getTranslateY(), new Double(8));
		p.getChildren().add(newPeaBullet.getSprite());
//		System.out.println("shooting..`");
		return newPeaBullet;
	}
	
	@Override
	public Bullet attack(Pane p) {
		return shoot(p);
	}
}

class Sunflower extends plants{
	public Sunflower(double positionX, double positionY) {
		super(positionX, positionY, form_image());
		this.type = "sunflower";
	}
	
	private static Pane form_image() {
		Image img = new Image("moving_sunflower.gif", 140, 101, false, true);
		ImageView view = new ImageView(img);
		Pane pane = new Pane(view);
		
		return pane;
	}
	
}
