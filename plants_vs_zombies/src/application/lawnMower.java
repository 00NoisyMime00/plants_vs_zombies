package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//Lawn Mower
public class lawnMower extends actor{
	private static Pane base;
	private long startTime;
	private static ArrayList<lawnMower> allLawnMowers = new ArrayList<lawnMower>();
	
	public lawnMower(Double positionX, Double positionY) {
		super(positionX, positionY, 1, 0, form_image());
		lawnMower.base.getChildren().add(this.getSprite());
		this.startTime = System.nanoTime();
	}
	
	public long getStartTime() {
		return this.startTime;
	}
	
	public static void createlawnMowers(Pane base) {
		lawnMower.base = base;
		allLawnMowers = new ArrayList<lawnMower>();
		
		for(int i = 0; i < 5; i++) {
			lawnMower a = new lawnMower(new Double(160), new Double(70) + i*100);
			allLawnMowers.add(a);
		}
	}
	
	public static ArrayList<lawnMower> getLawnMowerList(){
		return allLawnMowers;
	}
	
//	public staic Pane getLawnMowers()
	
	private static Pane form_image() {
		Pane pane = new Pane(new ImageView(new Image("lawnMower.png", 100, 100, false, true)));
		return pane;
	}
}
