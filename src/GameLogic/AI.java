package GameLogic;

import java.util.ArrayList;
import java.util.Random;

import Buttons.Piece;

public class AI {
	private boolean color; // true is red, false is blue
	private Piece[] currentGame;
	private ArrayList<Integer> compPieces, playerPieces;
	private boolean setup;
	private int setupCounter = 0;
	private boolean isTurn;
	Random r = new Random();

	public AI(boolean color, Piece[] currentGame) {
		this.color = color;
		if (color) {
			setupCounter = 16;
		} else {
			setupCounter = 16 + 6;
		}
		this.currentGame = currentGame;
		updatePieces(currentGame);
	}

	public void updatePieces(Piece[] gamePieces) {
		currentGame = gamePieces;
		compPieces = new ArrayList<Integer>();
		playerPieces = new ArrayList<Integer>();
		for (int i = 0; i < currentGame.length; i++) {
			if (color) {
				if (currentGame[i].getColor() == 'R') {
					compPieces.add(i);
				} else if (currentGame[i].getColor() == 'B') {
					playerPieces.add(i);
				}
			} else {
				if (currentGame[i].getColor() == 'B') {
					compPieces.add(i);
				} else if (currentGame[i].getColor() == 'R') {
					playerPieces.add(i);
				}
			}
		}
	}

	public void setSetup(boolean setup) {
		this.setup = setup;
	}

	public void placePiece() {

	}

	private boolean checkPossibleMills() {
		return false;
	}

	private void checkSetup() {
		if (color && setup) {
			if (setupCounter >= 16 + 6)
				setup = false;
		} else if (setup) {
			if (setupCounter >= 16 + 12)
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
			System.out.println(index2);
			System.out.println(setup);
			if (color) {
				while (!currentGame[index2].isEmpty()) {
					index2 = 0 + (int) (Math.random() * ((15 - 0) + 1));
					System.out.println(index2);
				}
				System.out.println(index2);
				currentGame[index2].setColor('R');
				currentGame[index2].setMoved(true);
				System.out.println("Color is :" + currentGame[index2].getColor());
			} else {
				while (!currentGame[index2].isEmpty()) {
					index2 = 0 + (int) (Math.random() * ((15 - 0) + 1));
					System.out.println(index2);
				}
				System.out.println(index2);
				currentGame[index2].setColor('B');
				currentGame[index2].setMoved(true);
				currentGame[index2].setPrevLoc(setupCounter);
				System.out.println("Color is :" + currentGame[index2].getColor());
			}
		} else {
			index = compPieces.get(r.nextInt(compPieces.size()));
			index2 = index - 1;
			boolean validMove = false;
			while (!validMove) {
				index = compPieces.get(r.nextInt(compPieces.size()));
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
						index2 = index +7;
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
				System.out.println("Index  : " + index);
				System.out.println("Index 2: " + index2);
				if (currentGame[index2].isEmpty()) validMove = true;
			}
			currentGame[index].setColor(' ');
			currentGame[index].setSetup(false);
			currentGame[index].setMoved(false);
			if (color){
				currentGame[index2].setColor('R');
				currentGame[index2].setMoved(true);
				currentGame[index2].setPrevLoc(index);
			}else{
				currentGame[index2].setColor('B');
				currentGame[index2].setMoved(true);
				currentGame[index2].setPrevLoc(index);
			}
		}
		return currentGame;

	}

	public Piece[] removePiece() {
		return currentGame;
	}

	public boolean getColor() {
		return color;
	}

	public void updateBoard(Piece[] currentGame) {
		this.currentGame = currentGame;
	}

	public void changeTurn() {
		if (isTurn) {
			isTurn = false;
		} else {
			isTurn = true;
		}
	}

}
