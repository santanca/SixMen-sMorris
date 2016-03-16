package application.Controller;								//name of the package

import javafx.event.ActionEvent;							//import javafx library
import javafx.event.EventHandler;							//import javafx library
import javafx.scene.control.ToggleButton;					//import javafx library

public class ButtonColor extends ToggleButton {				//class ButtonColor that extends toggleButton 
	private String color;									//initalize private varaible of type string called color
	public ButtonColor(double x, double y, String s) {		//function ButtonColor that takes in 3 parameters
		setMinWidth(100);									//set the min width
		setLayoutX(x);										//set the layout size using the input parameter
		setLayoutY(y);										//set the layout using the parameter y
		setText(s);											//set the text using the string s
		color = s;											//color is equal to s
		setUserData(s);										//use the s(the color) to set the users data

	}														//close bracket
	public String getColor(){								//fuction that gets the color of type string
		return color;										//returns the color
	}														//close bracket
}															//close bracket
