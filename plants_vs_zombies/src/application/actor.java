package application;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class actor {
	protected int health;
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
    
    private double startPositionX;
    private double startPositionY;
    
    protected long startTime;
 
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
    	
    	this.startTime = System.nanoTime();
    	this.startPositionX = positionX;
    	this.startPositionY = positionY;
    	
    }
    
    public Pane getSprite() {
    	return this.image;
    }
    
    public double getStartPositionX() {
    	return this.positionX;
    }
    
    public double getStartPositionY() {
    	return this.startPositionY;
    }
    
    public void setStartPositionX(double x) {
    	this.startPositionX = x;
    }
    
    public void setStartPositionY(double y) {
    	this.startPositionY = y;
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
    
    public double getPostionX() {
    	return this.positionX;
    }
    
    public double getPositionY() {
    	return this.positionY;
    }
 
    public void update(double time){
    	if(!Main.getCurrentBase().getIsGamePaused()) {
	        positionX = this.startPositionX + velocityX * time;
	        positionY = this.startPositionY + velocityY * time;
	        this.image.setTranslateX(positionX);
	        this.image.setTranslateY(this.positionY);
	        
	        if(this instanceof Bullet) {
	        	Bullet bullet = (Bullet)this;
	        	if(bullet.getSprite().getTranslateX() >= 990) {
	        		bullet.setVelocity(0, 0);
	        		bullet.setPosition(0, 0);
	        		bullet.getSprite().setVisible(false);
	        	}
	        }
    	}
    	else {
			this.setStartTime(System.nanoTime());
			this.startPositionX = this.positionX;
			this.startPositionY = this.positionY;
		}
    }
 
    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX,positionY,width,height);
    }
 
    public boolean intersect(actor s){
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public long getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(long time) {
		this.startTime = time;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return this.health;
	}
}



