package Buttons;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends Circle {								//piece class extends Circle class
	private char color;											//private varaibles needed for the impletentation of the adt
	private boolean empty;
	private int prevLoc, currentLoc;
	private boolean moved, removePiece, setupPhase;

	public Piece(Piece p) {									//constructor 
		this(p.getLayoutX(), p.getLayoutY(), p.getLoc());	
		moved = p.getMoved();
		removePiece = p.getRemovePiece();
		setupPhase = p.getSetupPhase();
		setColor(p.getColor());
		empty = p.isEmpty();

	}

	public Piece(double x, double y, int loc) {					//overloaded constructor
		moved = false;											//set varaibles this is for starting up the game
		removePiece = false;
		setupPhase = false;
		prevLoc = loc;
		currentLoc = loc;
		color = ' ';									//set color to nothing
		setColor(color);
		empty = true;
		setRadius(15);
		setLayoutX(x + 10);									//set the layout
		setLayoutY(y + 10);
		setStyle(						//set the style 
				"-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		this.setOnDragDetected(new EventHandler<MouseEvent>() {			//event handler for javafx 
			public void handle(MouseEvent event) {
				if (empty == false && (currentLoc >= 16 || !setupPhase || removePiece)) {					//starts a new game
					Dragboard db = startDragAndDrop(TransferMode.ANY);
					ClipboardContent content = new ClipboardContent();
					content.putString("DRAG ME");
					setCursor(Cursor.HAND);
					db.setContent(content);
				}
				event.consume();
			}
		});
		this.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != this && event.getDragboard().hasString() && empty == true
						&& (currentLoc < 16 || removePiece == true)) {
					setFill(Color.YELLOW);
				}
				event.consume();
			}
		});
		this.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				setColor(color);
				event.consume();
			}
		});
		this.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				event.acceptTransferModes(TransferMode.ANY);
				boolean success = false;
				int prev = ((Piece) event.getGestureSource()).getLoc();
				if ((((Piece) event.getGestureSource()).isEmpty()) == false && empty == true && currentLoc < 16
						&& (setupPhase == true || (prev == currentLoc - 1 || (prev == 15 && currentLoc == 8)
								|| (prev == 0 && currentLoc == 7) || (prev == 8 && currentLoc == 15)
								|| (prev == 7 && currentLoc == 0) || prev == currentLoc + 1
								|| (prev < 8 && currentLoc == prev + 8)
								|| (prev > 8 && prev < 16 && currentLoc == prev - 8)))) {
					setColor(((Piece) event.getGestureSource()).getColor());
					moved = true;
					((Piece) event.getGestureSource()).setColor(' ');
					((Piece) event.getGestureSource()).setSetup(false);
					((Piece) event.getGestureSource()).setMoved(false);
					setPrevLoc(prev);
					success = true;
				} else if (removePiece == true && currentLoc >= 16
						&& (((Piece) event.getGestureSource()).isEmpty()) == false) {
					((Piece) event.getGestureSource()).setColor(' ');
					prevLoc = currentLoc;
					removePiece = false;
				}

				event.setDropCompleted(success);
				event.consume();
			}
		});
		this.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != this) {
					event.acceptTransferModes(TransferMode.ANY);
				}
				event.consume();
			}
		});

	}

	public int getLoc() {										//getter for the Loc varaible
		return currentLoc;
	}

	public int getPrevLoc() {									//getter for the PrevLoc varaible 
		return prevLoc;
	}

	public void setRemovePiece(boolean n) {							//setter for the remove piece varaiable
		removePiece = n;
	}

	public boolean getRemovePiece() {								//getter for the remove piece varaible
		return removePiece;
	}

	public void setPrevLoc(int lc) {							//setter for the PrevLoc variable
		prevLoc = lc;
	}

	public boolean isEmpty() {									//getter for the empty varaible
		return empty;
	}

	public char getColor() {								//getter for the color varaible
		return color;
	}

	public boolean getMoved() {							//getter for the move varaiable 
		return moved;
	}

	public void setSetup(boolean n) {					//setter for the setup Phase varable
		setupPhase = n;
	}

	public boolean getSetupPhase() {			//getter for the setup Phase varable
		return setupPhase;
	}

	public void setMoved(boolean move) {					//setter for the move varaiable 
		moved = move;
	}

	public void setColor(char c) {					//color setter
		color = c;									
		switch (c) {								//switch using the parameter
		case 'B':									//if the color is blue its umempty and color is set to blue
			empty = false;
			setFill(Color.BLUE);
			break;
		case 'R':									//if the color is red its umempty and color is set to red
			empty = false;
			setFill(Color.RED);
			break;
		case ' ':									//if the color is null its empty and color is set to null
			empty = true;
			setFill(Color.BLACK);
			break;

		}
	}

}
