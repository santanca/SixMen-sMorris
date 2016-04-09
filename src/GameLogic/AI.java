package GameLogic;

import java.util.ArrayList;
import java.util.Random;

import Buttons.Piece;

public class AI {
	private boolean color; // true is red, false is blue
	private Piece[] currentGame;			//an array of pieces for the current game state
	private ArrayList<Integer> compPieces, playerPieces;	//list of integer arrays for the computer and user
	private boolean setup;			
	private int setupCounter = 0;
	private boolean isTurn;
	Random r = new Random();		//random varaible r

	public AI(boolean color, Piece[] currentGame) {		//constructor that sets the values of color and sets the counter and current game also updates the pieces
		this.color = color;
		if (color) {
			setupCounter = 16;
		} else {
			setupCounter = 16 + 6;
		}
		this.currentGame = currentGame;
		updatePieces(currentGame);
	}

	public void setColor(boolean color) {		//setter that sets the color
		this.color = color;
	}

	private boolean checkIfValidMoves() {		//checks if any valid moves are possible to make
		updatePieces(currentGame);				//updates the current game board
		for (int index : compPieces) {			//loop through the amount of compieces
			if (index == 15) {	
				// valid moves are -7, -1, - 8
				if (currentGame[index - 7].isEmpty() || currentGame[index - 1].isEmpty()
						|| currentGame[index - 8].isEmpty()) {
					return true;
				}
			} else if (index == 7) {
				// valid moves are -7, -1, + 8
				if (currentGame[index - 7].isEmpty() || currentGame[index - 1].isEmpty()
						|| currentGame[index + 8].isEmpty()) {
					return true;
				}
			} else if (index % 2 == 1) {
				if (index < 7) {
					// valid moves are +/- 1 and + 8
					if (currentGame[index + 1].isEmpty() || currentGame[index - 1].isEmpty()
							|| currentGame[index + 8].isEmpty()) {
						return true;
					}
				} else {
					// valid moves are +/- 1 and - 8
					if (currentGame[index + 1].isEmpty() || currentGame[index - 1].isEmpty()
							|| currentGame[index - 8].isEmpty()) {
						return true;
					}
				}
			} else if (index == 0 || index == 8) {
				// valid moves are + 1 and + 7
				if (currentGame[index + 7].isEmpty() || currentGame[index + 1].isEmpty()) {
					return true;
				} 
			} else {
				// valid moves are +/- 1
				if (currentGame[index + 1].isEmpty() || currentGame[index - 1].isEmpty()) {
					return true;
				}
			}
		}
		System.out.println("NO VALID MOVES - COMPUTER LOSES");
		for (int index : compPieces){
			currentGame[index].setColor(' ');
			currentGame[index].setSetup(false);
			currentGame[index].setMoved(false);
		}
		return false;
	}

	public void updatePieces(Piece[] gamePieces) {			//updates the game board
		currentGame = gamePieces;							//sets the game pieces to the current game
		compPieces = new ArrayList<Integer>();				//intializes a new array list for the computer
		playerPieces = new ArrayList<Integer>();			//intializes a new array list for the player
		for (int i = 0; i < currentGame.length; i++) {		//loops through the amount of pieces if the color is true
			if (color) {
				if (currentGame[i].getColor() == 'R') {				//if the color of the piece is red add it to the computer piece
					compPieces.add(i);
				} else if (currentGame[i].getColor() == 'B') {			//if the color of thepiece is blue add it to the player piece
					playerPieces.add(i);
				}
			} else {											//else 
				if (currentGame[i].getColor() == 'B') {			//if the color is blue add one to the compieces or if the color is red add one to the player piece
					compPieces.add(i);
				} else if (currentGame[i].getColor() == 'R') {
					playerPieces.add(i);
				}
			}
		}
	}

	public void setSetup(boolean setup) {
		this.setup = setup;							//setter that sets the value of setup
	}


	private void checkSetup() {			//checks the setup
		if (color && setup) {					//if color and setup are true
			if (setupCounter >= 16 + 6)			//if the setupcounter is greater than or equal to 22 make setup false
				setup = false;
		} else if (setup) {						//else if setup is true
			if (setupCounter >= 16 + 12)		//if the setupcounter is greater than or equal to 28 setup is false
				setup = false;
		}
	}

	public Piece[] makeMove() {
		updatePieces(currentGame);
		checkSetup();
		int index = 0, index2 = 0;
		if (setup) {
			index = setupCounter;
			setupCounter++;
			currentGame[index].setColor(' ');
			currentGame[index].setSetup(false);
			currentGame[index].setMoved(false);
			index2 = 0 + (int) (Math.random() * ((15 - 0) + 1));
			if (color) {
				while (!currentGame[index2].isEmpty()) {
					index2 = 0 + (int) (Math.random() * ((15 - 0) + 1));
				}
				currentGame[index2].setColor('R');
				currentGame[index2].setMoved(true);
			} else {
				while (!currentGame[index2].isEmpty()) {
					index2 = 0 + (int) (Math.random() * ((15 - 0) + 1));
				}
				currentGame[index2].setColor('B');
				currentGame[index2].setMoved(true);
				currentGame[index2].setPrevLoc(setupCounter);
			}
		} else if(checkIfValidMoves()) {
			index = compPieces.get(r.nextInt(compPieces.size()));
			index2 = index - 1;
			boolean validMove = false;				//set valid move to false			
			while (!validMove) {					//while loop loops through and finds any invalid moves
				index = compPieces.get(r.nextInt(compPieces.size()));	//index for the computer pieces
				int rand = 0 + (int) (Math.random() * ((2) + 1));
				if (index == 15) {
					switch (rand) {
					case 0:
						index2 = index - 7;
						break;
					case 1:
						index2 = index - 1;
						break;
					case 2:
						index2 = index - 8;
						break;
					}
					// valid moves are -7, -1, - 8
				} else if (index == 7) {
					switch (rand) {
					case 0:
						index2 = index - 7;
						break;
					case 1:
						index2 = index - 1;
						break;
					case 2:
						index2 = index + 8;
						break;
					}
					// valid moves are -7, -1, + 8
				} else if (index % 2 == 1) {

					if (index < 7) {
						switch (rand) {
						case 0:
							index2 = index + 1;
							break;
						case 1:
							index2 = index - 1;
							break;
						case 2:
							index2 = index + 8;
							break;
						}
						// valid moves are +/- 1 and + 8
					} else {
						switch (rand) {
						case 0:
							index2 = index + 1;
							break;
						case 1:
							index2 = index - 1;
							break;
						case 2:
							index2 = index - 8;
							break;
						}
						// valid moves are +/- 1 and - 8
					}
				} else if (index == 0 || index == 8) {
					rand = 0 + (int) (Math.random() * ((1) + 1));
					switch (rand) {
					case 0:
						index2 = index + 1;
						break;
					case 1:
						index2 = index + 7;
						break;
					}
					// valid moves are + 1 and + 7
				} else {				
					rand = 0 + (int) (Math.random() * ((1) + 1));
					switch (rand) {
					case 0:
						index2 = index + 1;
						break;
					case 1:
						index2 = index - 1;
						break;
					}
					// valid moves are +/- 1

				}
				if (currentGame[index2].isEmpty())		//if the current game state is empty
					validMove = true;
			}
			currentGame[index].setColor(' ');
			currentGame[index].setSetup(false);
			currentGame[index].setMoved(false);
			if (color) {							//if color is true then set color of pice to red and move to index
				currentGame[index2].setColor('R');
				currentGame[index2].setMoved(true);
				currentGame[index2].setPrevLoc(index);
			} else {									//else set the color to blue and move to the index
				currentGame[index2].setColor('B');
				currentGame[index2].setMoved(true);
				currentGame[index2].setPrevLoc(index);
			}
		}
		return currentGame;				//return the current game

	}

	public Piece[] removePiece() {
		updatePieces(currentGame);
		int index = playerPieces.get(r.nextInt(playerPieces.size()));	//get the index of where you want to remove the piece
		currentGame[index].setColor(' ');		//change value to zero AKA a space character
		currentGame[index].setSetup(false);
		currentGame[index].setMoved(false);
		currentGame[index].setRemovePiece(true);		//remove the piece
		currentGame[index].setPrevLoc(index);	
		updatePieces(currentGame);			//update pieces using current game state
		System.out.println("Removed Piece :" + index);	
		return currentGame;						//return the currentgame
	}

	public boolean getColor() {
		return color;				//return the color 
	}

	public void changeTurn() {			
		if (isTurn) {				//if isturn is true
			isTurn = false;			//change it to false
		} else {
			isTurn = true;			//otherwise change istrue to true
		}
	}

}
