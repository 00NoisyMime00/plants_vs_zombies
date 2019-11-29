package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends actor{
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
	public void setStartTime(long time) {
		this.startTime = time;
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
