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



