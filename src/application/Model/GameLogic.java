package application.Model;																										//use the application.Model package																	

public class GameLogic {																										//gamelogic class
	private char[] board;																										//initialize char array
	private char[] errorBoard;																									//intialize private char array
	private boolean boolErrors = false;																							//priavate boolean boolerrors initialized as false

	public GameLogic(int length) {																								//game logic constructor takes one parameter
		board = new char[length];																								//initialize new board
		errorBoard = new char[length];																							//initialize new errorborad
		for (char c : board) {																									//loop over the board
			c = ' ';																											//c is an empty charchter
		}																														//close bracket
		for (char c : errorBoard) {																								//loop over the error board
			c = ' ';																											//assign c to an empty charchter
		}																														//close bracket
	}																															//close bracket

	public boolean hasErrors() {																								//public boolean has errors 
		return boolErrors;																										//return the boolErrors
	}																															//close bracket

	public void setHasErrors(Boolean b) {																						//public void seterrors takes in one parameter boolean B
		boolErrors = b;																											//boolErrors is equal to B
	}																															//close bracket

	public void updateState(Disc[] gamePieces) {																				//puclic functions that takes in one disc array
		for (int i = 0; i < gamePieces.length; i++) {																			//loop throght the amount of pieces
			board[i] = gamePieces[i].getColor();																				//get the color and save it into the array
		}																														//close bracket
	}																															//close bracket

	public char[] getErrorArray() {																								//public function to get error board
		return errorBoard;																										//return the errorborad

	}																															//close bracket

	public String getErrors() {																									//new function get errors

		String errors = "";																										//intialize new string as null string
		int counterRed = 0, counterBlue = 0;																					//initaize 2  new ints as 0
		for (int i = 0; i < board.length; i++) {																				//loop over the lenght of the board
			if (board[i] == 'R') {																								//if the borad ith value is equal to charcter R 
				counterRed++;																									//add one to the counter

			} else if (board[i] == 'B') {																						//else if the ith value of board is equal to the charchter B 
				counterBlue++;																									//add one to counter
			}																													//close bracket
		}																														//close bracket

		for (int i = 0; i < board.length; i++) {																				//iterates over the length of the board

			switch (i) {																										//switch statement
			case 0:																												//0th case
				if ((board[i] != ' ' && board[6] != board[i] && board[6] != ' ')) {												//
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 6 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//set boolErrors to true
				}																												//close bracket
				break;																											//break statement

			case 1:																												//1st case
				if ((board[i] != ' ' && board[4] != board[i] && board[4] != ' ')) {												//if the board[i] is not equal to empty charchter and board[4] is not equal to board[i] and board[4] is not equal to empty chrachter
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 4 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//set boolErrors to true
				}																												//close bracket
				break;																											//break statement

			case 2:																												//2nd case
				if ((board[i] != ' ' && board[9] != board[i]&& board[9] != ' ')) {												//conditional statement
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 9 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//set boolErrors to true
				}																												//close bracket
				break;																											//break statement

			case 3:																												//3rd case
				if ((board[i] != ' ' && board[7] != board[i]&& board[7] != ' ')) {												//conditional statement
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 7 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//set boolErrors to true
				}																												//close statement
				break;																											//break statement

			case 5:																												//5th case
				if ((board[i] != ' ' && board[8] != board[i]&& board[8] != ' ')) {												//conditional statement
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 8 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//set boolErrors to true
				}																												//close statement
				break;																											//break statement
			case 6:																												//6th case
				if ((board[i] != ' ' && board[13] != board[i]&& board[13] != ' ')) {											//
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 13 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//set boolErrors to true
				}																												//close bracket
				break;																											//break statement
			case 7:																												//7th case
				if ((board[i] != ' ' && board[10] != board[i]&& board[10] != ' ')) {											//if board[i] is not equal to empty charchter and board[10] is mot equal to board[i] and board[10] is not equal to emoty charchter 
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 10 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//boolErrors is set to true
				}																												//close statement
				break;																											//break statement 
			case 8:																												//8th case
				if ((board[i] != ' ' && board[12] != board[i]&& board[12] != ' ')) {											//if the board[i] is not equal empty charchter and board[12] is not equal to board[i] and board[12] is not equal to empty charchter
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 12 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//change boolErrors to true
				}																												//close statement
				break;																											//break statement
			case 9:																												//9th case
				if ((board[i] != ' ' && board[15] != board[i]&& board[15] != ' ')) {											//if the board[i] not equal empty charchter and board[15] not equal board[i] and board[15] is not equal to empty charchter
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 15 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//make boolserror true
				}																												//close bracket
				break;																											//break statement
			case 11:																											//11th case
				if ((board[i] != ' ' && board[14] != board[i]&& board[14] != ' ')) {											//if the board [i] is empty and board[14] is not equal to board[i] and board[14] is not equal empty charchter
					errors += "\n-Red/Blue pair cannot support each other, Position ( " + i + "," + 14 + ").";					//saves the string into the variable errors
					boolErrors = true;																							//change boolErrors to true
				}																												//close statement
				break;																											//break statement

			}																													//close bracket
		}																														//close bracket

		if (counterBlue > 6) {																									//if blue is greater than 6	
			errors += "\n-Too many blue pieces.";																				//saves the string into the variable errors
			boolErrors = true;																									//change the boolerrors to true

		} else if (counterBlue <= 2 && counterRed > 2) {																		//else if the blue counter is less than equal to 2 and red counter is greater than 2
			errors += "\n-Red has won.";																						//saves the string into the variable errors
			boolErrors = true;																									//set boolerorrs to true

		}																														//close bracket
		if (counterBlue <= 2 && counterRed <= 2) {																				//if the blue pieces and red pieces are less than 2
			errors += "\n-Invalid game board.";																					//the game board is invaild
			boolErrors = true;																									//change the boolerorrs to true
		}																														//close brackets

		if (counterRed > 6) {																									//if red is less than 6 or there isnt enough red pieces
			errors += "\n-Too many red pieces.";																				//saves the string into the variable errors
			boolErrors = true;																									//change the value of boolerrors
		} else if (counterRed <= 2 && counterBlue > 2) {																		//else if statement if blue counter and red counter are either greater than or less than 2 respectively 
			errors += "\n-Blue has won.";																						//saves string to the varaible errors
			boolErrors = true;																									//set the boolerrors to true meaning there are errors

		}																														//close bracket
		return ("Errors: " + errors);																							//returns the the result 
	}																															//close bracket
}																																//close bracket
