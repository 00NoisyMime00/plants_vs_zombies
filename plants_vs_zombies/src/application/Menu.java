package application;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Menu {
	private Pane image;
	private Double positionX;
	private Double positionY;
	
	private static ArrayList<Menu> allMenus = new ArrayList<Menu>();
	
	public Menu(Double postionX, Double positionY, Pane image) {
		this.image = image;
		this.positionX = positionX;
		this.positionY = positionY;
		System.out.println(image.getTranslateX());
		image.setTranslateX(postionX);
		image.setTranslateY(positionY);
		
		allMenus.add(this);
	}
	
	public Pane getSprite() {
		return this.image;
	}
	
	public void clicked(Stage primaryStage, Scene menuScene) {
		primaryStage.setScene(menuScene);
	}
	
	public void setPosition(Double positionX, Double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		
		this.image.setTranslateX(this.positionX);
		this.image.setTranslateY(this.positionY);
	}
	
	public ArrayList<Menu> getAllMenus(){
		return this.allMenus;
	}
}




class ingameMenu extends Menu{
	public ingameMenu() {
		super(new Double(1010) , new Double(10), form_image());
	}
	
	private static Pane form_image() {
		return new Pane(new ImageView(new Image("ingameMenuButton.png", 60, 60, false, true)));
	}
}
