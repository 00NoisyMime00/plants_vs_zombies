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
    	
        positionX += velocityX * time;
        positionY += velocityY * time;
        this.image.setTranslateX(positionX);
        this.image.setTranslateY(this.positionY);
        
        if(this instanceof Bullet) {
        	Bullet bullet = (Bullet)this;
        	if(bullet.getSprite().getTranslateX() >= 1050) {
        		bullet.setVelocity(0, 0);
        		bullet.setPosition(0, 0);
        		bullet.getSprite().setVisible(false);
        	}
        }
    }
 
    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX,positionY,width,height);
    }
 
    public boolean intersect(actor s){
        return s.getBoundary().intersects( this.getBoundary() );
    }
}


// Lawn Mower
class lawnMower extends actor{
	private static Pane base;
	
	public lawnMower(Double positionX, Double positionY) {
		super(positionX, positionY, 1, 0, form_image());
		lawnMower.base.getChildren().add(this.getSprite());
	}
	
	public static void createlawnMowers(Pane base) {
		lawnMower.base = base;
		for(int i = 0; i < 5; i++) {
			lawnMower a = new lawnMower(new Double(160), new Double(70) + i*100);
		}
	}
	
//	public staic Pane getLawnMowers()
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("lawnMower.png", 100, 100, false, true)));
		return pane;
	}
}

class plants extends actor{
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
	public PeaShooter(double positionX, double positionY) {
		super(positionX, positionY, form_image());
		this.type = "peashooter";
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
