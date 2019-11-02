package application;

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
    	this.image.setTranslateX(this.positionX);
    	this.image.setTranslateY(this.positionY);
    	System.out.println("x "+this.image.getLayoutX()+" y "+this.image.getLayoutY());
    	
    }
    
    public ImageView getSprite() {
    	return this.image;
    }
    
    public void setVelocity(double velocityX, double velocityY) {
    	this.velocityX = velocityX;
    	this.velocityY = velocityY;
    }
    
    public void setPosition(double positionX, double positonY) {
    	this.positionX = positionX;
    	this.positionY = positonY;
    }
 
    public void update(double time){
//    	System.out.println(this.image.getLayoutX()+" "+this.image.getLayoutY()+" "+this.image.getTranslateX());
        positionX += velocityX * time;
        positionY += velocityY * time;
        this.image.setTranslateX(positionX);
        this.image.setTranslateY(this.positionY);
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

class plants extends actor{
	public plants(double positionX, double positionY, ImageView plantImage){
		super(positionX, positionY, 0, 0, plantImage);
		System.out.println("sending "+(positionX));
	}
	
}

class PeaShooter extends plants{
	public PeaShooter(double positionX, double positionY) {
		super(positionX, positionY, form_image());
	}
	
	private static ImageView form_image() {
		Image img = new Image("tenor.gif", 120, 71, false, true);
		ImageView view = new ImageView(img);
		
		return view;
	}
}
