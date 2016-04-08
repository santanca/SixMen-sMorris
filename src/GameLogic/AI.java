package GameLogic;

import java.util.ArrayList;

public class AI {
	private boolean currentColor; // true is red, false is blue
	private char[] currentGame;
	private boolean setup;
	private ArrayList<Integer> currentPieces;
	private boolean isTurn;
	
	public AI(){
		
	}
	public AI(boolean currentColor, char[] currentGame){
		this.currentColor = currentColor;
		this.currentGame = currentGame;
	}
	private void checkPossibleMills(){
		if (currentColor){
			
		}else{
			
		}
	}
	public void placePiece(){
		
	}
	public void updateBoard(char[] currentGame){
		this.currentGame = currentGame;
	}
	public void changeTurn(){
		if (isTurn){
			isTurn = false;
		}else{
			isTurn = true;
		}
	}
	
}
