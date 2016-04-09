package Scenes;

import java.io.BufferedReader; //import java libraries
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Buttons.Piece;
import GameLogic.AI;
import GameLogic.Logic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewGame extends ScreenScene {
	private AI AI;
	private Piece[] gamePieces; // private array of game pieces
	private int currentPlayer = 0; // current player intailzed to 0
	private Logic gameLogic; // private game logic object
	private Text text; // new text
	private boolean ai; // true if AI is present
	private String setup = " --- SETUP PHASE";
	protected int currentState; // current state
	// 5 - SET BOARD, NOT GAMEPLAY
	// 6 - Load Game
	// 7 - Setup Phase
	// 8 - Red TURN
	// 9 - Blue TURN
	// 10 - BLUE REMOVES PIECE ( blue has a mill )
	// 11 - RED REMOVES PIECE ( red has a mill )
	// 12 - Main Menu
	// 13 - SAVE GAME

	public NewGame() { // new game method

		resetBoard(false); // resets the board

	}

	public NewGame(boolean ai) { // new game method
		this.ai = ai;
	
		resetBoard(ai); // resets the board
		

	}

	public NewGame(File file) { // new game that takes in a file parameter
		resetBoard(false); // reset the board
		finishSetup(); // finish the setup
		try (BufferedReader br = new BufferedReader(new FileReader(file))) { // try
																				// buffer
																				// reader
			String line; // string line for the save file
			String[] lineArray; // line for different states
			while ((line = br.readLine()) != null) { // read the file
				lineArray = line.split(","); // save the states
				if (lineArray.length == 1) { // if the array line array is one
					currentPlayer = Integer.parseInt(line); // that is the
															// player state
				} else { // else
					for (int i = 0; i < gamePieces.length; i++) { // loop
																	// through
																	// the game
																	// pieces
						System.out.print(lineArray[i]); // print out all the
														// pieces states
						gamePieces[i].setColor(lineArray[i].charAt(0)); // set
																		// the
																		// colors
																		// according
																		// to
																		// the
																		// states
						if (i >= 16) { // if i is greater than or equal to 16
							if (gamePieces[i].getColor() != ' ') { // make the
																	// player
																	// color
																	// equal
																	// nothing
								gamePieces[i].setSetup(true); // set up the
																// setup
								for (int j = 0; j < 16; j++) { // loop amount of
																// times of the
																// amount of
																// pieces
									gamePieces[j].setSetup(true); // set up the
																	// pieces
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(currentPlayer); // print out the current player
		if (currentPlayer == 0) { // retuns what the player is
			redTurn();
		} else {
			blueTurn();
		}

	}

	public void setupAI() {
		boolean aiColor;
		if (Math.random() < 0.5) {
			aiColor = true; // AI plays red
		} else {
			aiColor = false; // AI plays blue
		}
		AI = new AI(aiColor, gamePieces);
	}

	private boolean isAITurn() {
		if (AI == null) {
			return false;
		} else {
			if (AI.getColor() &&  (currentState == 8 || currentState == 11)) {
				return true;
			} else if (!AI.getColor() && (currentState == 9 || currentState == 10)) {
				return true;
			}
		}
		return false;

	}

	public void setPieces(Piece[] pieces) { // sets the pieces
		for (Piece p : gamePieces) { // loop through the amount of game pieces
			this.getChildren().remove(p); // get childern and remove pieces
		}
		gamePieces = pieces; // game pieces us set to pieces
		for (Piece p : gamePieces) { // loop through the amount of game p
			this.getChildren().add(p); // adds childern
		}
	}

	private void startSetup() {
		for (Piece p : gamePieces) { // loops through the amount of game pieces
			p.setSetup(true); // set up the pieces
		}
	}

	private void redMill() {
		for (Piece t : gamePieces) { // loop through the amount of pieces
			t.setRemovePiece(true); // remoove the piece
			if (t.getColor() == 'B') { // if the color of the piece is blue
				t.setDisable(false); // enable the pieces
			} else if (t.getColor() == 'R') { // else if the piece is red
				t.setDisable(true); // enable the red piece
			}
		}
	}

	private void blueMill() {
		for (Piece t : gamePieces) { // loop through the game pieces
			t.setRemovePiece(true); // remove the piece
			if (t.getColor() == 'R') { // if the color is red
				t.setDisable(false); // enable the piece
			} else if (t.getColor() == 'B') { // if the color is blue
				t.setDisable(true); // disable the piece
			}
		}
	}

	private boolean checkRemove() {
		for (Piece p : gamePieces) { // loop through the amount of game pieces
			if (p.getRemovePiece() == false) // if remove piece is false
				return false; // return false
		}
		return true; // retrun true
	}

	private void removeRemove() {
		for (Piece p : gamePieces) { // loop through the amount of pieces
			if (p.getRemovePiece() == true) // if remove piece is true
				p.setRemovePiece(false); // remove piece is false
		}
	}

	protected void finishSetup() {
		for (Piece p : gamePieces) { // loop through the amount of pieces
			p.setSetup(false); // set the setup is false
		}
		setup = "";
	}

	private boolean isSetup() {
		for (int i = 16; i < gamePieces.length; i++) { // loop throught the
														// amount of pieces
			if (gamePieces[i].getSetupPhase()) { // if the game pieces is the
													// set phase
				return true; // return true
			}
		}
		return false; // else return false
	}

	public void animate() {
		if (ai){

			AI.updateBoard(gamePieces);
		}
		if (currentState == 11) { // if the state is 11
			if (!checkRemove()) { // if not remove check is true
				removeRemove(); // remove remove
				setGameStateText("Blue Player's Turn: Drag a Piece" + setup); // display
				blueTurn(); // blue turn
				setMovedFalse(); // set the moved false
				gameLogic.setNewState(gamePieces); // new game state using
			} else {
				if (isAITurn()) {
					gamePieces = AI.removePiece();
					gameLogic.setNewState(gamePieces);
				}
			}
		} else if (currentState == 10) {
			if (!checkRemove()) {
				removeRemove();
				setGameStateText("Red Player's Turn: Drag a Piece" + setup);
				redTurn();
				setMovedFalse();
				gameLogic.setNewState(gamePieces);
			} else {
				if (isAITurn()) {
					gamePieces = AI.removePiece();
					setPieces(gamePieces);
					gameLogic.setNewState(gamePieces);
				}
			}
		} else {
			int gameMove;
			if (!isSetup()) {
				if (ai){

					AI.setSetup(false);
				}
				finishSetup();
				gameMove = gameLogic.validMove(gamePieces, 1);
			} else {
				if (ai){

					AI.setSetup(true);
				}
				gameMove = gameLogic.validMove(gamePieces, 0);
			}

			
			if (checkMoves()) {
				switch (gameMove) {
				case 0:
					switch (currentState) {
					case 8:
						setGameStateText("Blue Player's Turn: Drag a Piece" + setup);
						blueTurn();
						setMovedFalse();
						break;
					case 9: // case 9
						setGameStateText("Red Player's Turn: Drag a Piece" + setup); // display
						redTurn(); // return red
						setMovedFalse(); // set moved false
						gameLogic.setNewState(gamePieces); // set new state with
															// the game logic
															// and pieces
						
						break;
					}
					break;
				case 1: // case 1
					System.out.println("Invalid Move"); // invalid move
					setPieces(gameLogic.getState()); // set the pieces using the
														// gamelogic and game
														// state
					break;
				case 2: // case 2
					setGameStateText("RED PLAYER WINS"); // display message
					disableAll(); // disable all end the game
					break;
				case 3: // case 3
					setGameStateText("BLUE PLAYER WINS"); // display message
					disableAll(); // disable all end game
					break;
				case 4: // case 4
					redMill(); // mill red
					setGameStateText("REMOVE A BLUE PIECE: Drag piece to one of the blue starting circles"); // display
					currentState = 11; // current state is 11
					break; // break
				case 5: // case 5
					blueMill(); // mill blue
					setGameStateText("REMOVE A RED PIECE: Drag piece to one of the red starting circles"); // display
					currentState = 10; // current state is 10
					break; // break
				}
			}
		}
	}

	public Piece[] getGamePieces() {
		return gamePieces; // return game pieces
	}

	protected boolean checkMoves() {
		for (Piece t : gamePieces) { // loop through the amount of pieces
			if (t.getMoved() == true) // if moved is equal to true
				return true; // return true
		}
		return false; // return false
	}

	protected void setMovedFalse() {
		for (Piece t : gamePieces) { // loop through the amount of game pieces
			if (t.getMoved() == true) // if moved is equal to true
				t.setMoved(false); // set moved is false
		}
	}

	protected void redTurn() { // reds turn
		currentState = 8; // current state is 8
		currentPlayer = 0; // player is 0
		for (Piece t : gamePieces) { // loop through the gamepieces
			if (t.getColor() == 'B') { // is the piece belongs to blue
				t.setDisable(true); // then disable the piece
			} else if (t.getColor() == 'R') { // else if the piece is red
				t.setDisable(false); // enable the piece
			}
		}
		if (isAITurn()) {
			setPieces(AI.makeMove());
			gameLogic.setNewState(gamePieces);
			
			blueTurn(); // return red
			setGameStateText("Blue Player's Turn: Drag a Piece" + setup);
			setMovedFalse();
		}
	}

	private void disableAll() { // disable all
		for (Piece t : gamePieces) { // loop through the amount of pieces
			t.setDisable(true); // disable all pieces
		}
	}

	private void enableAll() { // enable all (helps start the game)
		for (Piece t : gamePieces) { // loop throught the amount of pieces
			t.setDisable(false); // all pieces are avaliable
		}
	}

	protected void freeTurn() { // Logic for beginning phase of the game
		setGameStateText("PLACE PIECES FREELY"); // text to the screen
		currentState = 5; // current state is 5
		for (Piece t : gamePieces) { // loop through the amount of game pieces
			if (t.getColor() == 'B') { // if the color is blue
				t.setDisable(false); // disavle the piece
			} else if (t.getColor() == 'R') { // else id the color is red
				t.setDisable(false); // disable the piece
			}
		}
	}

	protected void setGameStateText(String t) { // set the text position and
												// size
		this.getChildren().remove(text);
		text = new Text(t);
		text.setLayoutX(200);
		text.setLayoutY(30);
		text.minWidth(200);
		text.maxWidth(200);
		this.getChildren().add(text);
	}

	protected void blueTurn() {
		currentState = 9; // current state is 9
		currentPlayer = 1; // current player is 1
		for (Piece t : gamePieces) { // loop through the amount of pieces
			if (t.getColor() == 'R') { // set color to red
				t.setDisable(true); // disable the piece
			} else if (t.getColor() == 'B') { // else make the color blue
				t.setDisable(false); // disable the piece
			}
		}
		if (isAITurn()) {
			setPieces(AI.makeMove());
			gameLogic.setNewState(gamePieces);
			
			redTurn(); // return red
			setGameStateText("Red Player's Turn: Drag a Piece" + setup);
			setMovedFalse();
		}
	}

	public void resetBoard(boolean ai) {
		text = new Text(""); // new null text
		this.getChildren().add(text); // get childern and add text
		gamePieces = new Piece[16 + 12];
		setBoard();
		gamePieces[0] = new Piece(190, 90, 0); // set the game pieces on the
												// board
		gamePieces[1] = new Piece(390, 90, 1);
		gamePieces[2] = new Piece(590, 90, 2);
		gamePieces[3] = new Piece(590, 290, 3); // set the game pieces on the
												// board
		gamePieces[4] = new Piece(590, 490, 4);

		gamePieces[5] = new Piece(390, 490, 5);
		gamePieces[6] = new Piece(190, 490, 6);
		gamePieces[7] = new Piece(190, 290, 7);

		gamePieces[8] = new Piece(290, 190, 8);
		gamePieces[9] = new Piece(390, 190, 9); // set the game pieces on the
												// board
		gamePieces[10] = new Piece(490, 190, 10);
		gamePieces[11] = new Piece(490, 290, 11);

		gamePieces[12] = new Piece(490, 390, 12);
		gamePieces[13] = new Piece(390, 390, 13);

		gamePieces[14] = new Piece(290, 390, 14);
		gamePieces[15] = new Piece(290, 290, 15); // set the game pieces on the
													// board

		for (int i = 16; i < 16 + 6; i++) { // sets up all the game pieces for
											// the board
			gamePieces[i] = new Piece(80, (125 + 40 * (i - 16)), i);
			gamePieces[i].setColor('R');
		}
		for (int i = 16 + 6; i < 16 + 12; i++) { // sets up all the game pieces
													// for the board
			gamePieces[i] = new Piece(700, (125 + 40 * (i - 16 - 6)), i);
			gamePieces[i].setColor('B');
		}
		gameLogic = new Logic(gamePieces);
		for (Piece t : gamePieces) {
			this.getChildren().add(t); // get childern and add piece
		}
		startSetup();
		addButtons();
		if (ai) {
			setupAI();
			AI.setSetup(true);
		}
		if (Math.random() > 0.5) { // random numvber less than 0.5 reds turn
			redTurn();
			setGameStateText("Red Player's Turn: Drag a Piece" + setup); // message
			
		} else {
			blueTurn(); // else blues turn
			setGameStateText("Blue Player's Turn: Drag a Piece" + setup);
	
		}
	}

	private void setBoard() {
		Canvas canvas = new Canvas(800, 800); // set the canvas
		getChildren().add(canvas); // add to the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D(); // make the canvas
		drawBoard(gc); // draw the board
		gc.setFill(Color.BLACK); // set the color to black
		Text t = new Text();
		t.setText("Red Player");
		t.setFont(Font.font("Verdana", 20));
		t.setFill(Color.RED);
		t.setX(40);
		t.setY(100);
		this.getChildren().add(t);
		Text g = new Text();
		g.setText("Blue Player");
		g.setFont(Font.font("Verdana", 20));
		g.setFill(Color.BLUE);
		g.setX(660);
		g.setY(100);
		this.getChildren().add(g);
		this.setPane();
	}

	public int getState() {
		return currentState;
	}

	private void drawBoard(GraphicsContext gc) { // priavte void draw the board
		gc.setFill(Color.BLACK); // set fill as black
		gc.setFill(Color.BEIGE); // color the background as bagie
		gc.setStroke(Color.BLACK); // set the color of the line to black
		gc.setLineWidth(4); // set the line width
		gc.fillRect(200, 100, 400, 400); // draw the board
		gc.strokeRect(200, 100, 400, 400); // draw the lines on the board
		gc.strokeLine(400, 100, 400, 200); // draw the lines on the board
		gc.strokeLine(400, 400, 400, 500); // draw the lines on the board
		gc.strokeLine(200, 300, 300, 300); // draw the lines on the board
		gc.strokeLine(500, 300, 600, 300); // draw the lines on the board
		gc.strokeRect(300, 200, 200, 200); // draw the lines on the board
	}

	@Override
	public void setState(int state) {
		currentState = state;

	}

	@Override
	protected void addButtons() {
		Button Save = new Button("SAVE GAME");
		Save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// 8 - Red TURN
				// 9 - Blue TURN
				if (currentState == 8) {
					currentState = 13;
				} else if (currentState == 9) {
					currentState = 13;
				}

			}
		});
		Save.setLayoutX(245);
		Save.setLayoutY(550);
		Save.setMinHeight(50);
		Save.setMinWidth(150);
		Save.setStyle(
				"-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		this.getChildren().add(Save);

		Button main = new Button("MAIN MENU");
		main.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				setState(12);
			} // set the page layout and the font and everything like that for
				// the main home screen
		});
		main.setLayoutX(405);
		main.setLayoutY(550);
		main.setMinHeight(50);
		main.setMinWidth(150);
		main.setStyle(
				"-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		this.getChildren().add(main);

		Button exit = new Button("EXIT");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				setState(4);
			}
		});
		exit.setLayoutX(565); // set the page layout and the font and everything
								// like that for the game screen
		exit.setLayoutY(550);
		exit.setMinHeight(50);
		exit.setMinWidth(150);
		exit.setStyle(
				"-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		this.getChildren().add(exit);

	}

	@Override
	public void saveFile(File file) { // save file that takes in a file
		try { // try
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, false)); // new
																					// buffer
																					// reader
																					// that
																					// creates
																					// a
																					// new
																					// file
			String s = Integer.toString(currentPlayer); // int converted into
														// string
			bw.write(s); // write the string s repersents the current player
			bw.newLine(); // new line
			for (int i = 0; i < gamePieces.length; i++) { // iterate over the
															// game pieces
				if (i != gamePieces.length - 1) { // if the length of the game
													// pieces is not equal to i
					bw.write(gamePieces[i].getColor() + ","); // write the state
																// of the game
																// pieces
																// seperated by
																// commas
				} else { // else
					bw.write(gamePieces[i].getColor()); // write in the file the
														// game pieces
				}
			}
			bw.newLine(); // new line
			bw.close(); // close the file

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(currentState); // prints out current state
		}

	}

}