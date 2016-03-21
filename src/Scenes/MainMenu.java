package Scenes;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu extends ScreenScene{
	private int currentState;


	public MainMenu(){
		currentState = 0;
		Canvas canvas = new Canvas(800, 450);			//set the canvas
		getChildren().add(canvas);							//add to the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();		//make the canvas
		addButtons();
		Text t = new Text();
		t.setText("Six Men's Morris");
		t.setFont(Font.font ("Verdana", 40));
		t.setFill(Color.RED);
		t.setX(240 - 2.5);
		t.setY(75);
		this.getChildren().add(t);
		this.setPane();
	}
	public void animate(){

	}
	public int getState(){
		return currentState;
	}
	@Override
	public Scene getScreen() {
		return (new Scene(getPane(), 800, 450));
	}
	@Override
	public void setState(int state) {
		currentState = state;

	}
	@Override
	protected void addButtons() {
		Button play = new Button("PLAY GAME");
		play.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        currentState = 1;
		    }
		});
		play.setLayoutX(340 - 2.5);
		play.setLayoutY(135);
		play.setMinHeight(50);
		play.setMinWidth(150);
		play.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		Button load = new Button("LOAD GAME");
		load.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        currentState = 2;

		    }
		});
		load.setLayoutX(340 - 2.5);
		load.setLayoutY(205);
		load.setMinHeight(50);
		load.setMinWidth(150);
		load.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		Button set = new Button("SETUP GAME");
		set.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        currentState = 3;
		    }
		});
		set.setLayoutX(340 - 2.5);
		set.setLayoutY(275);
		set.setMinHeight(50);
		set.setMinWidth(150);
		set.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		Button exit = new Button("EXIT GAME");
		exit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        currentState = 4;
		    }
		});
		exit.setLayoutX(340 - 2.5);
		exit.setLayoutY(350);
		exit.setMinHeight(50);
		exit.setMinWidth(150);
		exit.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		this.getChildren().add(play);
		this.getChildren().add(load);
		this.getChildren().add(set);
		this.getChildren().add(exit);

	}
	@Override
	public void saveFile(File file) {
		// TODO Auto-generated method stub

	}
}
