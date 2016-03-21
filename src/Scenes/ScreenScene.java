package Scenes;
import java.io.File;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class ScreenScene extends Group{
	private Pane pane;
	public ScreenScene(){
		pane = new Pane(this);
	}
	protected void setPane(){
		pane = new Pane(this);
	}
	protected Pane getPane(){
		return pane;
	}
	public Scene getScreen() {
		return (new Scene(pane, 800, 650));
	}
	public abstract void animate();
	public abstract int getState();
	public abstract void setState(int state);
	protected abstract void addButtons();
	public abstract void saveFile(File file);
}
