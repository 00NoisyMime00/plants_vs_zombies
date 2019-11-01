package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class actor {
	private int health;
	private int attack_strength;
	private int attack_radius;
	private int speed;
	
	private ImageView image;
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
 
    public actor(double positionX, double positionY, double velocityX, double velocityY, ImageView image) {
    	this.positionX = positionX;
    	this.positionY = positionY;
    	this.velocityX = velocityX;
    	this.velocityY = velocityY;
    	this.image = image;
    	this.image.setLayoutX(this.positionX);
    	this.image.setLayoutY(this.positionY);
    	
    }
    
    public ImageView getSprite() {
    	return this.image;
    }
 
    public void update(double time){
        positionX += velocityX * time;
        positionY += velocityY * time;
    }
 
//    public void render(GraphicsContext gc){
//        gc.drawImage( image, positionX, positionY );
//    }
 
    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX,positionY,width,height);
    }
 
    public boolean intersect(actor s){
        return s.getBoundary().intersects( this.getBoundary() );
    }
}
