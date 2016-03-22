package Scenes;

public class GameSetup extends NewGame {
	public GameSetup() {									
		addButtons();												//add buttons 
		finishSetup();												//finsh the setup
		if (Math.random() > 0.5) {									//generate a random number if its greater than 0.5 
			redTurn();												//then its reds turn 
			setGameStateText("Red Player's Setup - DRAG PIECES");	//display message
		} else {													//else
			blueTurn();												//blues turn 
			setGameStateText("Blue Player's Setup - DRAG PIECES ");	//display message
		}
	}

	@Override
	public void animate() {	
		if (checkMoves()) {					//if check moves
			switch (currentState) {			//swithc using the current state
			case 8:							//if state is 8 
				setGameStateText("Blue Player's Setup - DRAG PIECES");		//display message
				blueTurn();				//blue turn 
				setMovedFalse();			//set moved is false
				break;
			case 9:					//if the current state is 9 
				setGameStateText("Red Player's Setup - DRAG PIECES");		//display message
				redTurn();								//red turn 
				setMovedFalse();						//setmoved is false
				break;
			}
		}
	}
	@Override
	protected void addButtons() {				
		super.addButtons();			//add the buttons 
	}
}
