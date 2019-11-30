package application;

import javafx.scene.image.Image;



import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.*; 

public class Zombie extends actor{

	protected String type;
	protected static ArrayList<Zombie> allZombies = new ArrayList<Zombie>();
	protected static long lastGeneratedTime = System.nanoTime();
	
	private int[] oldIndices = new int[2];
		
	public Zombie(double positionY, Pane zombieImage){
		super(1000, positionY, 0, 0, zombieImage);
		allZombies.add(this);
		lastGeneratedTime = System.nanoTime();
		this.oldIndices = backyard_controller.getIndices(this.getPostionX(), this.getPositionY());
		
	}
	
	protected static int randNumberGenerator() {
        Random rand = new Random();

	    int randomNum = rand.nextInt(5);
	    return randomNum;

		
	}
	
	public void detectEnemy() {
		
	}
	
	public int[] getOldIndices() {
		return this.oldIndices;
	}
	
	public void setOldIndices(int[] indices) {
		this.oldIndices[0] = indices[0];
		this.oldIndices[1] = indices[1];
	}
	
	public static long getLastGeneratedTime() {
		return lastGeneratedTime;
	}
	
	public static void setLastGeneratedTime(long time) {
		lastGeneratedTime = time;
	}
	
	public Bullet attack(Pane p) {
		return null;
	}
	
	public String getType() {
		return this.type;
	}
	
	public static ArrayList<Zombie> getZombiesList(){
		return allZombies;
	}
	
	public static Zombie create_zombie(String name) {
		int yIndex = Zombie.randNumberGenerator();
		double yCord = backyard_controller.getYCoordinate(yIndex);
		
		normal_zombie newZombie = null;
		
		if(name.equals("normal zombie")) {
			newZombie = new normal_zombie(yCord);
			
		}
		return newZombie;
	}

}



class normal_zombie extends Zombie{
	
	public normal_zombie(double positionY) {
		
		super(positionY, form_image());
		this.type = "normal zombie";
		this.setVelocity(-50, 0);	
		
		this.setHealth(100);
	}
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("normal_zombie.gif", 100, 100, false, true)));
		return pane;
	}

}


class dancing_zombie extends Zombie{
	
	public dancing_zombie(double positionY) {
		super(positionY, form_image());
		this.type = "dancing zombie";
	}
	


	
	public void create_dancing_zombie() {
		
		
	}
	
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("dancing_zombie.png", 100, 100, false, true)));
		return pane;
	}
	
	
}

class football_zombie extends Zombie{
	
	public football_zombie(double positionY) {
		super(positionY, form_image());
		this.type = "football zombie";
	}
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("football_zombie.png", 100, 100, false, true)));
		return pane;
	}
	
	
}

class pole_zombie extends Zombie{
	
	public pole_zombie(double positionY) {
		super(positionY, form_image());
		this.type = "pole zombie";
	}
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("pole_zombie.png", 100, 100, false, true)));
		return pane;
	}
	
	
}

class cone_zombie extends Zombie{
	
	public cone_zombie(double positionY) {
		super(positionY, form_image());
		this.type = "cone zombie";
	}
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("pole_zombie.png", 100, 100, false, true)));
		return pane;
	}
	
	
}

