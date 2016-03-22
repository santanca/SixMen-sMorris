package Scenes;
import java.io.File;						//import neccesay java libarabries

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class ScreenScene extends Group{			//abstract class that extends group
	private Pane pane;										//private pane
	public ScreenScene(){									
		pane = new Pane(this);								//pane is equal to new pane 
	}
	protected void setPane(){								//protected void set pane
		pane = new Pane(this);								//make new pance using this
	}
	protected Pane getPane(){
		return pane;								//return pane (getter)
	}
	public Scene getScreen() {			//get the screen
		return (new Scene(pane, 800, 650));	//return the new scene
	}
	public abstract void animate();				//abstract void animate
	public abstract int getState();				//abstract int get state which gets the state
	public abstract void setState(int state);	//abstract void set state sets the state
	protected abstract void addButtons();		//add buttons
	public abstract void saveFile(File file);		//save file to that takes in a file as a parameter
}
