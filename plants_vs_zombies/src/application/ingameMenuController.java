package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ingameMenuController implements Initializable{
	@FXML
	Pane base;
	
	private ArrayList<Button> allButtons = new ArrayList<Button>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Button exitButton = new Button("exitButton");
		exitButton.setMinSize(230, 70);
		exitButton.setTranslateY(240);
		exitButton.setTranslateX(-360);
		exitButton.setOpacity(0);
		this.performAction(exitButton);
		base.getChildren().add(exitButton);
		
		Button saveGameButton = new Button("saveGameButton");
		saveGameButton.setMinSize(230, 70);
		saveGameButton.setTranslateY(240);
		saveGameButton.setOpacity(0);
		this.performAction(saveGameButton);
		base.getChildren().add(saveGameButton);
		
		Button resumeButton = new Button("resumeButton");
		resumeButton.setMinSize(230, 70);
		resumeButton.setTranslateY(240);
		resumeButton.setTranslateX(360);
		resumeButton.setOpacity(0);
		this.performAction(resumeButton);
		base.getChildren().add(resumeButton);
	}
	
	public ArrayList<Button> getAllButtonList(){
		return this.allButtons;
	}
	
	public void performAction(Button b) {
		b.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(b.getText().equals("exitButton")) {
					System.out.println("clicking exit to main menu");
					Stage stage = Main.getStage();
					stage.setScene(Main.getMainPage());
				}
				else if(b.getText().equals("saveGameButton")) {
					System.out.println("clicking save..");
				}
				else if(b.getText().equals("resumeButton")) {
					System.out.println("clicking resume");
					Stage stage = Main.getStage();
					stage.setScene(Main.getBackyarScene());
				}
				
			}
		});
	}
	
}
