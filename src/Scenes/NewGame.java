package Scenes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Buttons.Piece;
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
	private Piece[] gamePieces;
	private int currentPlayer = 0;
	private Logic gameLogic;
	private Text text;
	protected int currentState;
	// 5 - SET BOARD, NOT GAMEPLAY
	// 6 - Load Game
	// 7 - Setup Phase
	// 8 - Red TURN
	// 9 - Blue TURN
	// 10 - BLUE REMOVES PIECE ( blue has a mill )
	// 11 - RED REMOVES PIECE ( red has a mill )
	// 12 - Main Menu
	// 13 - SAVE GAME

	public NewGame() {

		resetBoard();

	}

	public NewGame(File file) {
		resetBoard();
		finishSetup();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    String[] lineArray;
		    while ((line = br.readLine()) != null) {
		       lineArray = line.split(",");
		       if (lineArray.length == 1){
		    	   currentPlayer = Integer.parseInt(line);
		       }else{
		    	   for (int i = 0; i < gamePieces.length; i ++){
		    		   System.out.print(lineArray[i]);
		    		   gamePieces[i].setColor(lineArray[i].charAt(0));
		    		   if (i >= 16){
		    			   if (gamePieces[i].getColor() != ' '){
		    				   gamePieces[i].setSetup(true);
		    				   for (int j = 0; j < 16; j ++){
		    					   gamePieces[j].setSetup(true);
		    				   }
		    			   }
		    		   }
		    	   }
		       }
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		 System.out.println(currentPlayer);
		if (currentPlayer == 0){
			redTurn();
		}else{
			blueTurn();
		}

	}
	public void setPieces(Piece[] pieces) {
		for (Piece p : gamePieces) {
			this.getChildren().remove(p);
		}
		gamePieces = pieces;
		for (Piece p : gamePieces) {
			this.getChildren().add(p);
		}
	}

	private void startSetup() {
		for (Piece p : gamePieces) {
			p.setSetup(true);
		}
	}

	private void redMill() {
		for (Piece t : gamePieces) {
			t.setRemovePiece(true);
			if (t.getColor() == 'B') {
				t.setDisable(false);
			} else if (t.getColor() == 'R') {
				t.setDisable(true);
			}
		}
	}

	private void blueMill() {
		for (Piece t : gamePieces) {
			t.setRemovePiece(true);
			if (t.getColor() == 'R') {
				t.setDisable(false);
			} else if (t.getColor() == 'B') {
				t.setDisable(true);
			}
		}
	}

	private boolean checkRemove() {
		for (Piece p : gamePieces) {
			if (p.getRemovePiece() == false)
				return false;
		}
		return true;
	}

	private void removeRemove() {
		for (Piece p : gamePieces) {
			if (p.getRemovePiece() == true)
				p.setRemovePiece(false);
		}
	}

	protected void finishSetup() {
		for (Piece p : gamePieces) {
			p.setSetup(false);
		}
	}

	private boolean isSetup() {
		for (int i = 16; i < gamePieces.length; i++) {
			if (gamePieces[i].getSetupPhase()) {
				return true;
			}
		}
		return false;
	}

	public void animate() {
		if (currentState == 11) {
			if (isSetup()){
				if (!checkRemove()){
					removeRemove();
					setGameStateText("Blue Player's Turn - SETUP PHASE  - DRAG PIECES");
					blueTurn();
					setMovedFalse();
					gameLogic.setNewState(gamePieces);

				}
			}else{
				if (!checkRemove()){
					removeRemove();
					setGameStateText("Blue Player's Turn");
					blueTurn();
					setMovedFalse();
					gameLogic.setNewState(gamePieces);

				}
			}

		} else if (currentState == 10) {
			if (isSetup()){
				if (!checkRemove()){
					removeRemove();
					setGameStateText("Red Player's Turn - SETUP PHASE  - DRAG PIECES");
					redTurn();
					setMovedFalse();
					gameLogic.setNewState(gamePieces);
				}
			}else{
				if (!checkRemove()){
					removeRemove();
					setGameStateText("Red Player's Turn");
					redTurn();
					setMovedFalse();
					gameLogic.setNewState(gamePieces);
				}
			}

		} else if (!isSetup()) {
			finishSetup();
			if (checkMoves()) {
				switch (gameLogic.validMove(gamePieces, 1)) {
				case 0:
					switch (currentState) {
					case 8:
						setGameStateText("Blue Player's Turn");
						blueTurn();
						setMovedFalse();
						gameLogic.setNewState(gamePieces);
						break;
					case 9:
						setGameStateText("Red Player's Turn");
						redTurn();
						setMovedFalse();
						gameLogic.setNewState(gamePieces);
						break;
					}
					break;
				case 1:
					System.out.println("Invalid move");
					setPieces(gameLogic.getState());
					break;
				case 2:
					setGameStateText("RED PLAYER WINS");
					disableAll();
					break;
				case 3:
					setGameStateText("BLUE PLAYER WINS");
					disableAll();
					break;
				case 4:
					redMill();
					setGameStateText("REMOVE A BLUE PIECE: Drag piece to one of the blue starting circles");
					currentState = 11;
					break;
				case 5:
					blueMill();
					setGameStateText("REMOVE A RED PIECE: Drag piece to one of the red starting circles");
					currentState = 10;
					break;
				}

			}
		} else {
			if (checkMoves()) {
				switch (gameLogic.validMove(gamePieces, 0)) {
				case 0:
					switch (currentState) {
					case 8:
						setGameStateText("Blue Player's Turn - SETUP PHASE  - DRAG PIECES");
						blueTurn();
						setMovedFalse();
						gameLogic.setNewState(gamePieces);
						break;
					case 9:
						setGameStateText("Red Player's Turn - SETUP PHASE  - DRAG PIECES");
						redTurn();
						setMovedFalse();
						gameLogic.setNewState(gamePieces);
						break;
					}
					break;
				case 4:
					redMill();
					setGameStateText("REMOVE A BLUE PIECE: Drag piece to one of the blue starting circles");
					currentState = 11;
					break;
				case 5:
					blueMill();
					setGameStateText("REMOVE A RED PIECE: Drag piece to one of the red starting circles");
					currentState = 10;
					break;
				}
			}
		}

	}

	public Piece[] getGamePieces() {
		return gamePieces;
	}

	protected boolean checkMoves() {
		for (Piece t : gamePieces) {
			if (t.getMoved() == true)
				return true;
		}
		return false;
	}

	protected void setMovedFalse() {
		for (Piece t : gamePieces) {
			if (t.getMoved() == true)
				t.setMoved(false);
		}
	}

	protected void redTurn() {
		currentState = 8;
		currentPlayer = 0;
		for (Piece t : gamePieces) {
			if (t.getColor() == 'B') {
				t.setDisable(true);
			} else if (t.getColor() == 'R') {
				t.setDisable(false);
			}
		}
	}
	private void disableAll(){
		for (Piece t : gamePieces) {
			t.setDisable(true);
		}
	}
	private void enableAll(){
		for (Piece t : gamePieces) {
			t.setDisable(false);
		}
	}
	protected void freeTurn() {
		setGameStateText("PLACE PIECES FREELY");
		currentState = 5;
		for (Piece t : gamePieces) {
			if (t.getColor() == 'B') {
				t.setDisable(false);
			} else if (t.getColor() == 'R') {
				t.setDisable(false);
			}
		}
	}

	protected void setGameStateText(String t) {
		this.getChildren().remove(text);
		text = new Text(t);
		text.setLayoutX(200);
		text.setLayoutY(30);
		text.minWidth(200);
		text.maxWidth(200);
		this.getChildren().add(text);
	}

	protected void blueTurn() {
		currentState = 9;
		currentPlayer = 1;
		for (Piece t : gamePieces) {
			if (t.getColor() == 'R') {
				t.setDisable(true);
			} else if (t.getColor() == 'B') {
				t.setDisable(false);
			}
		}
	}

	public void resetBoard() {
		text = new Text("");
		this.getChildren().add(text);
		gamePieces = new Piece[16 + 12];
		setBoard();
		gamePieces[0] = new Piece(190, 90, 0);
		gamePieces[1] = new Piece(390, 90, 1);
		gamePieces[2] = new Piece(590, 90, 2);
		gamePieces[3] = new Piece(590, 290, 3);
		gamePieces[4] = new Piece(590, 490, 4);

		gamePieces[5] = new Piece(390, 490, 5);
		gamePieces[6] = new Piece(190, 490, 6);
		gamePieces[7] = new Piece(190, 290, 7);

		gamePieces[8] = new Piece(290, 190, 8);
		gamePieces[9] = new Piece(390, 190, 9);
		gamePieces[10] = new Piece(490, 190, 10);
		gamePieces[11] = new Piece(490, 290, 11);

		gamePieces[12] = new Piece(490, 390, 12);
		gamePieces[13] = new Piece(390, 390, 13);

		gamePieces[14] = new Piece(290, 390, 14);
		gamePieces[15] = new Piece(290, 290, 15);

		for (int i = 16; i < 16 + 6; i++) {
			gamePieces[i] = new Piece(80, (125 + 40 * (i - 16)), i);
			gamePieces[i].setColor('R');
		}
		for (int i = 16 + 6; i < 16 + 12; i++) {
			gamePieces[i] = new Piece(700, (125 + 40 * (i - 16 - 6)), i);
			gamePieces[i].setColor('B');
		}
		gameLogic = new Logic(gamePieces);
		for (Piece t : gamePieces) {
			this.getChildren().add(t);
		}
		startSetup();
		addButtons();
		if (Math.random() > 0.5) {
			redTurn();
			setGameStateText("Red Player's Turn - SETUP PHASE  - DRAG PIECES");
		} else {
			blueTurn();
			setGameStateText("Blue Player's Turn - SETUP PHASE  - DRAG PIECES");
		}
	}

	private void setBoard() {
		Canvas canvas = new Canvas(800, 800); // set the canvas
		getChildren().add(canvas); // add to the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D(); // make the canvas
		drawBoard(gc); // draw the board
		gc.setFill(Color.BLACK);
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
				if (currentState == 8){
					currentState = 13;
				}else if (currentState == 9){
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
			}
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
		exit.setLayoutX(565);
		exit.setLayoutY(550);
		exit.setMinHeight(50);
		exit.setMinWidth(150);
		exit.setStyle(
				"-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		this.getChildren().add(exit);


	}

	@Override
	public void saveFile(File file) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
			String s = Integer.toString(currentPlayer);
			bw.write(s);
			bw.newLine();
			for (int i = 0 ; i < gamePieces.length; i ++){
				if (i != gamePieces.length - 1){
					bw.write(gamePieces[i].getColor() + ",");
				}else{
					bw.write(gamePieces[i].getColor());
				}
			}
			bw.newLine();
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			System.out.println(currentState);
		}

	}

}