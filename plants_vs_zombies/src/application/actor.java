package application;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class actor {
	private int health;
	private int attack_strength;
	private int attack_radius;
	private int speed;
	
	private Pane image;
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
 
    public actor(double positionX, double positionY, double velocityX, double velocityY, Pane image) {
    	this.positionX = positionX;
    	this.positionY = positionY;
    	this.velocityX = velocityX;
    	this.velocityY = velocityY;
    	this.image = image;
    	this.image.setTranslateX(this.positionX);
    	this.image.setTranslateY(this.positionY);
    	this.width = image.getWidth();
    	this.height = image.getHeight();
    	
    }
    
    public Pane getSprite() {
    	return this.image;
    }
    
    public void setVelocity(double velocityX, double velocityY) {
    	this.velocityX = velocityX;
    	this.velocityY = velocityY;
    }
    
    public void setPosition(double positionX, double positonY) {
    	this.positionX = positionX;
    	this.positionY = positonY;
    	this.image.setTranslateX(this.positionX);
    	this.image.setTranslateY(this.positionY);
    }
 
    public void update(double time){
//    	System.out.println(this.image.getLayoutX()+" "+this.image.getLayoutY()+" "+this.image.getTranslateX());
        positionX += velocityX * time;
        positionY += velocityY * time;
        this.image.setTranslateX(positionX);
        this.image.setTranslateY(this.positionY);
    }
 
    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX,positionY,width,height);
    }
 
    public boolean intersect(actor s){
        return s.getBoundary().intersects( this.getBoundary() );
    }
}

class plants extends actor{
	private ArrayList<Bullet> bullets;
	
	public plants(double positionX, double positionY, Pane plantImage){
		super(positionX, positionY, 0, 0, plantImage);
//		System.out.println("sending "+(positionX));
	}
	
	public void detectEnemy() {
		
	}
	
	public Bullet attack(Pane p) {
		return null;
	}
	
}

class PeaShooter extends plants{
	public PeaShooter(double positionX, double positionY) {
		super(positionX, positionY, form_image());
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

class Bullet extends actor{
	private boolean visible = false;
	private boolean isMoving = false;
	private Long startTime;
	
	public Bullet(Double positionX, Double positionY, Double velocityX, Pane bulletImage) {
		super(positionX, positionY, velocityX, 0, bulletImage);
		this.startTime = new Long(System.nanoTime());
	}
	
	public Long getStartTime() {
		return this.startTime;
	}
}

class PeaBullet extends Bullet{
	public PeaBullet(Double positionX, Double positionY, Double velocityX) {
		super(positionX, positionY, velocityX, form_image());
	}
	
	private static Pane form_image() {
		Image img = new Image("pea.png", 50, 40, false, true);
		ImageView view = new ImageView(img);
		Pane pane = new Pane(view);
		
		return pane;
	}
}
