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

	public Logic(Piece[] pS) {
		gameState = 0;
		currentState = copyArray(pS);
	}
	public void setNewState(Piece[] p) {
		currentState = copyArray(p);
	}

	private Piece[] copyArray(Piece[] p) {
		Piece[] temp = new Piece[p.length];
		for (int i = 0; i < p.length; i++) {
			temp[i] = new Piece(p[i]);
		}
		return temp;
	}

	public int validMove(Piece[] newState, int turn) {
		// turn = 0 : Setup
		// turn = 1 : GamePlay
		gameState = 0;
		if (turn == 1) {
			gameState = checkGameState(newState);
			if (gameState == 0) {
				currentState = copyArray(currentState);
			}
		} else if (turn == 0) {
			switch (getMill(newState)) {
			case 'B':
				gameState = 5;
				break;

			case 'R':
				gameState = 4;
				break;
			case ' ':
				gameState = 0;
				currentState = copyArray(currentState);
				break;

			}

		}
		return gameState;
	}

	public Piece[] getState() {
		return currentState;
	}

	private int checkGameState(Piece[] newState) {
		int returnV = 0;
		int cRed = 0;
		int cBlue = 0;
		for (Piece p : newState) {
			if (p.getMoved()) {
				int prev = p.getPrevLoc();
				int next = p.getLoc();
				if (next == prev + 1 || next == prev - 1) {
					returnV = 0;
				} else if (prev < 8 && next == prev + 8) {
					returnV = 0;
				} else if (prev < 16 && next == prev - 8) {
					returnV = 0;
				} else if (prev > 16 && next < 16) {
					returnV = 0;
				} else if (prev == 0 && next == 7) {
					returnV = 0;
				} else if (next == 0 && prev == 7) {
					returnV = 0;
				} else if (prev == 15 && next == 8) {
					returnV = 0;
				} else if (prev == 8 && next == 15) {
					returnV = 0;
				} else {
					return 1;
				}
			}
			if (p.getLoc() < 16 && p.getColor() == 'B') {
				cBlue++;
			} else if (p.getLoc() < 16 && p.getColor() == 'R') {
				cRed++;
			}
		}
		if (cRed < 3) {
			return 3;
		} else if (cBlue < 3) {
			return 2;
		}
		switch (getMill(newState)) {
		case 'B':
			return 5;
		case 'R':
			return 4;
		case ' ':
			returnV = 0;
		}
		return returnV;
	}

	private char getMill(Piece[] newState) {
		char[] prevMill = new char[8];
		char[] currentMill = new char[8];
		for (int i = 1; i <= 15; i += 2) {
			int j = 0;
			if (i + 1 == 8) {
				j = 0;
			} else if (i == 15) {
				j = 8;
			} else {
				j = i + 1;
			}

			if (currentState[i].getColor() == 'B' && currentState[i - 1].getColor() == 'B'
					&& currentState[j].getColor() == 'B') {
				prevMill[i / 2] = 'B';
			} else if (currentState[i].getColor() == 'R' && currentState[i - 1].getColor() == 'R'
					&& currentState[j].getColor() == 'R') {
				prevMill[i / 2] = 'R';
			} else {
				prevMill[i / 2] = ' ';
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

				switch (currentMill[i]) {
				case 'B':
					return 'B';
				case 'R':
					return 'R';

				}
			}
		}
		return ' ';
	}

}
