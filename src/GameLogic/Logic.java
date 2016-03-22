package GameLogic;

import Buttons.Piece;

public class Logic {
	private Piece[] currentState;						
	private int gameState;
	// 0 - VALID MOVE
	// 1 - Red player gets a mill
	// 2 - Blue player gets a mill
	// 3 - RED PLAYER WINS
	// 4 - BLUE PLAYER WINS
	// 5 - Invalid Move by Red
	// 6 - Invalid Move by Blue

	public Logic(Piece[] pS) {			//constructor for logic 
		gameState = 0;						//initialze game state
		currentState = copyArray(pS);		//copies array for current board state
	}
	public void setNewState(Piece[] p) {
		currentState = copyArray(p);						//sets a new state
	}

	private Piece[] copyArray(Piece[] p) {					//function for copying piece array 
		Piece[] temp = new Piece[p.length];					//new temporary piece array
		for (int i = 0; i < p.length; i++) {				//loop thorugh the pieces
			temp[i] = new Piece(p[i]);						//copy the arry
		}
		return temp;										//return temp
	}

	public int validMove(Piece[] newState, int turn) {			//function checks for a valid move
		// turn = 0 : Setup
		// turn = 1 : GamePlay
		gameState = 0;										//game state is 0
		if (turn == 1) {									//if turn is 1
			gameState = checkGameState(newState);			//checks the game state 
			if (gameState == 0) {							//if the game state is 0
				currentState = copyArray(currentState);		//currnet state remians
			}
		} else if (turn == 0) {								//else if the turn is 0
			switch (getMill(newState)) {					//switch and get mill of the new state
			case 'B':										//if blue
				gameState = 5;								//game state is 5
				break;			
				
			case 'R':										//if red
				gameState = 4;								//game state is 4
				break;
			case ' ':										//if nothing
				gameState = 0;								//game state is 0
				currentState = copyArray(currentState);		//current state remains
				break;	

			}

		}
		return gameState;									//return the game state
	}

	public Piece[] getState() {
		return currentState;						//getter returns the game state
	}

	private int checkGameState(Piece[] newState) {			//checks the game state
		int returnV = 0;									//set intial varaibles
		int cRed = 0;
		int cBlue = 0;
		for (Piece p : newState) {							//loop through the states
			if (p.getMoved()) {								//if piece is moved
				int prev = p.getPrevLoc();					//prev is set to the pervious state
				int next = p.getLoc();						//next is set to get logic which gets the logic
				if (next == prev + 1 || next == prev - 1) {	//if contioncns and statements
					returnV = 0;
				} else if (prev < 8 && next == prev + 8) {//if contioncns and statements
					returnV = 0;
				} else if (prev < 16 && next == prev - 8) {//if contioncns and statements
					returnV = 0;
				} else if (prev > 16 && next < 16) {//if contioncns and statements
					returnV = 0;
				} else if (prev == 0 && next == 7) {//if contioncns and statements
					returnV = 0;
				} else if (next == 0 && prev == 7) {//if contioncns and statements
					returnV = 0;
				} else if (prev == 15 && next == 8) {//if contioncns and statements
					returnV = 0;
				} else if (prev == 8 && next == 15) {//if contioncns and statements
					returnV = 0;
				} else {				//else
					return 1;			//return 1
				}
			}
			if (p.getLoc() < 16 && p.getColor() == 'B') {				//if the location is greater than 16 and the color is blue
				cBlue++;												//add one point to blue
			} else if (p.getLoc() < 16 && p.getColor() == 'R') {		//if the location is greater than 16 and the color is red
				cRed++;													//add one point to red
			}
		}
		if (cRed < 3) {													//if counter for red is abve 3
			return 3;													//retrun 3
		} else if (cBlue < 3) {											//if the counter for blue is less than 2
			return 2;													//return 2
		}
		switch (getMill(newState)) {									//get mill using the nre state
		case 'B':														//if the color is blue 
			return 5;													//retun 5
		case 'R':														//if the color is red 
			return 4;													//return 4
		case ' ':														//if the color is nothing 
			returnV = 0;												//return 0
		}
		return returnV;
	}

	private char getMill(Piece[] newState) {							
		char[] prevMill = new char[8];							//previous mill 
		char[] currentMill = new char[8];						//and current mill 
		for (int i = 1; i <= 15; i += 2) {						//for loop 
			int j = 0;	
			if (i + 1 == 8) {
				j = 0;
			} else if (i == 15) {
				j = 8;
			} else {
				j = i + 1;
			}

			if (currentState[i].getColor() == 'B' && currentState[i - 1].getColor() == 'B'					//if all conditions are met to remove a piece
					&& currentState[j].getColor() == 'B') {
				prevMill[i / 2] = 'B';																		//i/2 is equal to blue
			} else if (currentState[i].getColor() == 'R' && currentState[i - 1].getColor() == 'R'			//if all conditions are met to remove a piece
					&& currentState[j].getColor() == 'R') {										
				prevMill[i / 2] = 'R';																		//i/2 is equal to red
			} else {																						//else
				prevMill[i / 2] = ' ';																		//
			}

			if (newState[i].getColor() == 'B' && newState[i - 1].getColor() == 'B' && newState[j].getColor() == 'B') {
				currentMill[i / 2] = 'B';
			} else if (newState[i].getColor() == 'R' && newState[i - 1].getColor() == 'R'
					&& newState[j].getColor() == 'R') {
				currentMill[i / 2] = 'R';
			} else {
				currentMill[i / 2] = ' ';
			}
		}	
		for (int i = 0; i < 8; i++) {		
			if (prevMill[i] != currentMill[i]) {

				switch (currentMill[i]) {			//switch using mill
				case 'B':							//if blue 
					return 'B';						//return blue
				case 'R':							//if red
					return 'R';						//return red

				}
			}
		}
		return ' ';							//retrun nothing 
	}

}
