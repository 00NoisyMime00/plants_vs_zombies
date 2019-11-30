package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends actor{
	private boolean visible = false;
	private boolean isMoving = false;
	protected int damage = 20;
	
	public Bullet(Double positionX, Double positionY, Double velocityX, Pane bulletImage) {
		super(positionX, positionY, velocityX, 0, bulletImage);
	}
	
	public int getDamage() {
		return this.damage;
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


class snowPeaBullet extends Bullet{
	public snowPeaBullet(Double positionX, Double positionY, Double velocityX) {
		super(positionX, positionY, velocityX, form_image());
		this.damage = 30;
	}
	
	private static Pane form_image() {
		Image img = new Image("snowPeaBullet.png", 30, 30, false, true);
		ImageView view = new ImageView(img);
		Pane pane = new Pane(view);
		
		return pane;
	}
}
