package application.View;										

import application.Controller.ButtonColor;			//import javafx library
import application.Model.Disc;						//import javafx library
import application.Model.GameLogic;					//import javafx library
import javafx.animation.AnimationTimer;				//import javafx library
import javafx.application.Application;				//import javafx library
import javafx.stage.Stage;							//import javafx library
import javafx.scene.Scene;							//import javafx library
import javafx.scene.*;								//import javafx library
import javafx.scene.control.TextArea;				//import javafx library
import javafx.scene.control.ToggleButton;			//import javafx library
import javafx.scene.control.ToggleGroup;			//import javafx library
import javafx.scene.layout.Pane;					//import javafx library
import javafx.scene.paint.Color;					//import javafx library
import javafx.scene.canvas.*;						//import javafx library

public class graphics extends Application {				//class graphics extends application
	private Disc[] nodes = new Disc[16];				//intialize new private disc array
	private GameLogic gameLogic = new GameLogic(16);		//initialize new 
	private ToggleButton[] toggleButtons = new ToggleButton[6];	//intialize new private toggle class
	private TextArea txt = new TextArea();							//initalize new private text area  
	private int currentPlayer = -1;									//make current player -1

	@Override											//java override call
	public void start(Stage primaryStage) {				//public start takes one stage type parameter
		primaryStage.setTitle("Six Men's Morris");		//set the primary stage title as "six men morris"

		Group root = new Group();						//new object type group named root
		Scene scene = new Scene(root);					//new object type scene named sence
		primaryStage.setScene(scene);					//call the primary stage function on scene
		Canvas canvas = new Canvas(815, 800);			//set the canvas 
		root.getChildren().add(canvas);							//add to the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();		//make the canvas
		drawBoard(gc);											//draw the bord
		gc.setFill(Color.BLACK);								//set color to bloack

		Pane root2 = new Pane(root);
		root2.getChildren().add(txt);
		// Set up blank game pieces
		nodes[0] = new Disc(190, 90);			//set coordinates according to place on the grid
		nodes[1] = new Disc(390, 90);			//set coordinates according to place on the grid
		nodes[2] = new Disc(590, 90);			//set coordinates according to place on the grid
		nodes[3] = new Disc(290, 190);			//set coordinates according to place on the grid
		nodes[4] = new Disc(390, 190);			//set coordinates according to place on the grid
		nodes[5] = new Disc(490, 190);			//set coordinates according to place on the grid
		nodes[6] = new Disc(190, 290);			//set coordinates according to place on the grid
		nodes[7] = new Disc(290, 290);			//set coordinates according to place on the grid
		nodes[8] = new Disc(490, 290);			//set coordinates according to place on the grid
		nodes[9] = new Disc(590, 290);			//set coordinates according to place on the grid
		nodes[10] = new Disc(290, 390);			//set coordinates according to place on the grid
		nodes[11] = new Disc(390, 390);			//set coordinates according to place on the grid
		nodes[12] = new Disc(490, 390);			//set coordinates according to place on the grid
		nodes[13] = new Disc(190, 490);			//set coordinates according to place on the grid
		nodes[14] = new Disc(390, 490);			//set coordinates according to place on the grid
		nodes[15] = new Disc(590, 490);			//set coordinates according to place on the grid

		// Set up buttons
		toggleButtons[0] = new ButtonColor(50, 100, "Blue"); 	//set the buttons accordingly
		toggleButtons[1] = new ButtonColor(655, 100, "Red");	//set the buttons accordingly
		toggleButtons[2] = new ButtonColor(300, 550, "Finish");			//set the buttons accordingly
		toggleButtons[2].setDisable(true);								//disabe the toggle buttons
		toggleButtons[3] = new ButtonColor(400, 550, "Play");			//set the buttons accordingly
		toggleButtons[4] = new ButtonColor(600, 550, "Reset");				//set the buttons accordingly
		toggleButtons[5] = new ButtonColor(655, 200, "REMOVE PIECE");		//set the buttons accordingly
		ToggleGroup g1 = new ToggleGroup();								//create new toggle group 

		// resetBoard();
		for (ToggleButton t : toggleButtons) {				//for every toggle button 
			t.setToggleGroup(g1);							//set the toggle group
			root2.getChildren().add(t);						//reset the toggle group
		}													//close bracket
		for (Disc c : nodes) {								//	loop through the discs
			root2.getChildren().add(c);						//add to root2
		}													//close bracket

		setText(root2);										//set text to root2

		primaryStage.setScene(new Scene(root2, 815, 800));		//make new scene

		primaryStage.show();									//show the primary page
		useAnimation(primaryStage, root2, g1);					//use animation

	}															//close bracket

	private void resetBoard(Stage primaryStage, Pane root2) {		//function for resetting the board
		gameLogic.setHasErrors(false);								//set has wrrors to false 
		setText(root2);												//set text with root2 
		for (Disc c : nodes) {										//loop through the discs
			root2.getChildren().remove(c);
		}
		for (Disc c : nodes) {										//loop through the discs
			c.reset();
		}
		primaryStage.show();
		for (Disc c : nodes) {										//loop through the discs
			root2.getChildren().add(c);
		}
		for (ToggleButton t : toggleButtons) {						//loop through the toggle buttons
			t.setDisable(false);									//enable toggle button
		}
		toggleButtons[2].setDisable(true);							//disable toggle button
	}

	private void setText(Pane root2) {								//set the text
		txt.setLayoutX(100);										//set text layout
		txt.setLayoutY(600);										//set text  layout
		txt.setMaxHeight(190);										//set text  layout	
		txt.setMaxWidth(700);										//set text  layout
		txt.setText("Six Men's Morris: ");							//set text
		txt.appendText("\n1. To set the gameboard to a certain state.");					//set text
		txt.appendText("\n	- Pick a team to insert pieces for.");					//set text
		txt.appendText("\n	- Click on the board to place a piece for that team.");					//set text
		txt.appendText("\n	- You can switch the team by pressing the button with that team's colour.");					//set text
		txt.appendText("\n	- Press finish when you are done placing pieces.");					//set text
		txt.appendText("\n2. To start a new game.");					//set text
		txt.appendText("\n	- Press the new game button.");					//set text
		txt.appendText("\n	- A team will randomly be chosen to start.");					//set text

	}

	private void appendText(String s) {			//function for appending text
		txt.appendText("\n" + s);				//append the the text
	}										//close bracket
	private void setText(String s) {		//function for setting the text takes in one string
		txt.setText("\n" + s);			//set the text
	}									//close bracket

	private void useAnimation(Stage primaryStage, Pane root2, ToggleGroup g1) {		//function public void useAnimation takes three parameters of type stage Pane and ToggleGroup repectively

		new AnimationTimer() {								//new animation timer
			@Override										//override for java
			public void handle(long now) {					//publuic void handle takes parameter of type long
				gameLogic.updateState(nodes);					//update the gamne logic
				if (g1.getSelectedToggle() == null) { 		//if the selected toggle is equal to null
				} else if (g1.getSelectedToggle().getUserData() == "Blue") {			//else if the color is blue
					for (Disc c : nodes) {											//loop for the amount of discs
						c.setBlue();												//change color of c to blue
					}																//close bracket 
					toggleButtons[2].setDisable(false);									//enable the toggle button
				} else if (g1.getSelectedToggle().getUserData() == "REMOVE PIECE") {	//
					for (Disc c : nodes) {									//loop for the amount of discs
						c.setNull();										//change value to null
					}														//close brackets
				}else if (g1.getSelectedToggle().getUserData() == "Red") {		//
					for (Disc c : nodes) {									//loop for the amount of discs			
						c.setRed();											//set c to red
					}															//close bracket
					toggleButtons[2].setDisable(false);						//enable the toggle button
				} else if (g1.getSelectedToggle().getUserData() == "Finish") {
					for (Disc c : nodes) {						//loop through the disc c array
						c.setNull();							//make c null
					}											//close bracket
					toggleButtons[0].setDisable(true);				//disable the toggle
					toggleButtons[1].setDisable(true);				//disable the toggle
					toggleButtons[2].setDisable(true);			//disable the toggle
					toggleButtons[3].setDisable(true);			//disable the toggle
					toggleButtons[5].setDisable(true);		//disable the toggle
					gameLogic.updateState(nodes);			//update the nodes
					setText(gameLogic.getErrors());			//use text to display the errors(i.e. the reuslt of the game)
					g1.selectToggle(null);					//make the g1 toggle null
					if (gameLogic.hasErrors()) {			//if the gamelogic has errors 
						toggleButtons[3].setDisable(true);	//disable toggle buttons		
					}										//close bracket

				} else if (g1.getSelectedToggle().getUserData() == "Play") {			//else if the toggle is equal to play
					switch ((int) (2 * Math.random())) {			//switch statement
					case 0:								//case 0
						currentPlayer = 0;							//current player is equal to 0
						setText("\nRED PLAYER GOES FIRST");		//set text so the red player goes first 
						for (Disc c : nodes) {					//loop over disc arraty  of nodes	
							c.setRed();							//change the color to red

						}			
						break;											//break statement
					case 1:												//case one
						currentPlayer = 1;								//set current player as one
						setText("\nBLUE PLAYER GOES FIRST");			//blue player goes first
						for (Disc c : nodes) {				//loop over disc arraty  of nodes
							c.setBlue();			//set the color to blue 
						}
						break;		//break statement
					}

					//ends the game after one turn
					toggleButtons[0].setDisable(true);				//disable the toggle buttons
					toggleButtons[1].setDisable(true);			//disable the toggle buttons
					toggleButtons[2].setDisable(true);		//disable the toggle buttons
					toggleButtons[3].setDisable(true);		//disable the toggle buttons
					toggleButtons[5].setDisable(true);				//disable the toggle buttons
					g1.selectToggle(null);						//set the toggle as null

				} else if (g1.getSelectedToggle().getUserData() == "Reset") {			//eles if the toggle is equal to reset
					for (Disc c : nodes) {					//for the nodes in the disc array
						resetBoard(primaryStage, root2);			//reset the board
						g1.selectToggle(null);				//set the g1 toggle as null
					}								//close brakcet
				}									//close brakcet
			}										//close bracket
		}.start();									//start the code
	}												//cl;ose bracket

	private void drawBoard(GraphicsContext gc) {		//priavte void draw the board
		gc.setFill(Color.BLACK);							//set fill as black
		gc.setFill(Color.BEIGE);						//color the background as bagie
		gc.setStroke(Color.BLACK);						//set the color of the line to black
		gc.setLineWidth(4);								//set the line width
		gc.fillRect(200, 100, 400, 400);				//draw the board
		gc.strokeRect(200, 100, 400, 400);				//draw the lines on the board
		gc.strokeLine(400, 100, 400, 200);				//	draw the lines on the board
		gc.strokeLine(400, 400, 400, 500);			//draw the lines on the board
		gc.strokeLine(200, 300, 300, 300);				//draw the lines on the board
		gc.strokeLine(500, 300, 600, 300);				//draw the lines on the board
		gc.strokeRect(300, 200, 200, 200);			//draw the lines on the board
	}		//close bracket

	public static void main(String[] args) {	//main function 
		launch(args);			//launch arguments
	}				//close bracket
}				//close bracket
