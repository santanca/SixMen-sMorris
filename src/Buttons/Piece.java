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

public class Piece extends Circle {
	private char color;
	private boolean empty;
	private int prevLoc, currentLoc;
	private boolean moved, removePiece, setupPhase;

	public Piece(Piece p) {
		this(p.getLayoutX(), p.getLayoutY(), p.getLoc());
		moved = p.getMoved();
		removePiece = p.getRemovePiece();
		setupPhase = p.getSetupPhase();
		setColor(p.getColor());
		empty = p.isEmpty();

	}

	public Piece(double x, double y, int loc) {
		moved = false;
		removePiece = false;
		setupPhase = false;
		prevLoc = loc;
		currentLoc = loc;
		color = ' ';
		setColor(color);
		empty = true;
		setRadius(15);
		setLayoutX(x + 10);
		setLayoutY(y + 10);
		setStyle(
				"-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;");
		this.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (empty == false && (currentLoc >= 16 || !setupPhase || removePiece)) {
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

	public int getLoc() {
		return currentLoc;
	}

	public int getPrevLoc() {
		return prevLoc;
	}

	public void setRemovePiece(boolean n) {
		removePiece = n;
	}

	public boolean getRemovePiece() {
		return removePiece;
	}

	public void setPrevLoc(int lc) {
		prevLoc = lc;
	}

	public boolean isEmpty() {
		return empty;
	}

	public char getColor() {
		return color;
	}

	public boolean getMoved() {
		return moved;
	}

	public void setSetup(boolean n) {
		setupPhase = n;
	}

	public boolean getSetupPhase() {
		return setupPhase;
	}

	public void setMoved(boolean move) {
		moved = move;
	}

	public void setColor(char c) {
		color = c;
		switch (c) {
		case 'B':
			empty = false;
			setFill(Color.BLUE);
			break;
		case 'R':
			empty = false;
			setFill(Color.RED);
			break;
		case ' ':
			empty = true;
			setFill(Color.BLACK);
			break;

		}
	}

}
