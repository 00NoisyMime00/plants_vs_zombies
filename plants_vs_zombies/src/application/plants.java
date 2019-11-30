package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class plants extends actor{
	private ArrayList<Bullet> bullets;
	protected String type;
	
	public plants(double positionX, double positionY, Pane plantImage){
		super(positionX, positionY, 0, 0, plantImage);
//		System.out.println("sending "+(positionX));
	}
	
	public void detectEnemy() {
		
	}
	
	public Bullet attack(Pane p) {
		return null;
	}
	
	public String getType() {
		return this.type;
	}
	
}

class PeaShooter extends plants{
	private static int price = 100;
	private static long timeLeftToPlant = 0;
	private static long replantTime;
	private static long placeTime;
	
	public PeaShooter(double positionX, double positionY) {
		super(positionX, positionY, form_image());
		this.type = "peashooter";
		replantTime = 10;
		placeTime = System.nanoTime();
	}
	
	public static void resetTimeLeftToPlant() {
		timeLeftToPlant = replantTime;
	}
	
	public static void setPlaceTime(long time) {
		placeTime = time;
	}
	
	public static long getReplantTime() {
		return replantTime;
	}
	
	public static long getPlaceTime() {
		return placeTime;
	}
	
	public static void setTimeLeftToPlant(long time) {
		timeLeftToPlant = time;
	}
	
	public static long getTimeLeftToPlant() {
		return timeLeftToPlant;
	}
	
	private static Pane form_image() {
		Image img = new Image("moving_peashooter.gif", 100, 71, false, true);
		ImageView view = new ImageView(img);
		Pane pane = new Pane(view);
		
		return pane;
	}
	
	public static int getPrice() {
		return price;
	}
	
	public Bullet shoot(Pane p) {
		PeaBullet newPeaBullet = new PeaBullet(this.getSprite().getTranslateX() + 70, this.getSprite().getTranslateY(), new Double(2500));
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
	
	private static int price = 50;

	private static long timeLeftToPlant = 0;
	private static long replantTime;
	private static long placeTime;
	
	public Sunflower(double positionX, double positionY) {
		super(positionX, positionY, form_image());
		this.type = "sunflower";
		replantTime = 10;
		placeTime = System.nanoTime();
	}
	
	public static void resetTimeLeftToPlant() {
		timeLeftToPlant = replantTime;
	}
	
	public static void setPlaceTime(long time) {
		placeTime = time;
	}
	
	public static long getReplantTime() {
		return replantTime;
	}
	
	public static long getPlaceTime() {
		return placeTime;
	}
	
	public static void setTimeLeftToPlant(long time) {
		timeLeftToPlant = time;
	}
	
	public static long getTimeLeftToPlant() {
		return timeLeftToPlant;
	}
	
	private static Pane form_image() {
		Image img = new Image("moving_sunflower.gif", 140, 101, false, true);
		ImageView view = new ImageView(img);
		Pane pane = new Pane(view);
		
		return pane;
	}
	
	public static int getPrice() {
		return price;
	}
	
}


class snowPeaShooter extends plants{
	private static int price = 150;
	private static long timeLeftToPlant = 0;
	private static long replantTime;
	private static long placeTime;
	
	public snowPeaShooter(double positionX, double positionY) {
		super(positionX, positionY, form_image());
		this.type = "snowpeashooter";
		replantTime = 15;
		placeTime = System.nanoTime();
	}
	
	public static void resetTimeLeftToPlant() {
		timeLeftToPlant = replantTime;
	}
	
	public static void setPlaceTime(long time) {
		placeTime = time;
	}
	
	public static long getReplantTime() {
		return replantTime;
	}
	
	public static long getPlaceTime() {
		return placeTime;
	}
	
	public static void setTimeLeftToPlant(long time) {
		timeLeftToPlant = time;
	}
	
	public static long getTimeLeftToPlant() {
		return timeLeftToPlant;
	}
	
	private static Pane form_image() {
		Image img = new Image("icePeaShooterMoving.gif", 100, 71, false, true);
		ImageView view = new ImageView(img);
		Pane pane = new Pane(view);
		
		return pane;
	}
	
	public static int getPrice() {
		return price;
	}
	
	public Bullet shoot(Pane p) {
		snowPeaBullet newPeaBullet = new snowPeaBullet(this.getSprite().getTranslateX() + 70, this.getSprite().getTranslateY(), new Double(3500));
		p.getChildren().add(newPeaBullet.getSprite());
//		System.out.println("shooting..`");
		return newPeaBullet;
	}
	
	@Override
	public Bullet attack(Pane p) {
		return shoot(p);
	}
}
