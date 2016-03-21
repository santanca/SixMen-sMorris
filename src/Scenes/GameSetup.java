package Scenes;

public class GameSetup extends NewGame {
	public GameSetup() {
		addButtons();
		finishSetup();
		if (Math.random() > 0.5) {
			redTurn();
			setGameStateText("Red Player's Setup - DRAG PIECES");
		} else {
			blueTurn();
			setGameStateText("Blue Player's Setup - DRAG PIECES ");
		}
	}

	@Override
	public void animate() {
		if (checkMoves()) {
			switch (currentState) {
			case 8:
				setGameStateText("Blue Player's Setup - DRAG PIECES");
				blueTurn();
				setMovedFalse();
				break;
			case 9:
				setGameStateText("Red Player's Setup - DRAG PIECES");
				redTurn();
				setMovedFalse();
				break;
			}
		}
	}
	@Override
	protected void addButtons() {
		super.addButtons();
	}
}
