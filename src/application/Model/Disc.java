package application.Model;												//using the package application.model

import javafx.event.ActionEvent;										//import javafx library
import javafx.event.EventHandler;										//import javafx library
import javafx.scene.control.ToggleButton;								//import javafx library
import javafx.scene.shape.Circle;										//import javafx library

public class Disc extends ToggleButton {								//public class disc extends the togglebutton
	private char color = ' ';											//public char color is empty character 
	public Disc(double x,double y){										//constructor that takes in 2 parameters
		setLayoutX(x);													//set the layout as x 
		setLayoutY(y);													//set the layout as y
		setShape(new Circle(25));										//make new circle
		setStyle("-fx-font: 22 arial; -fx-base: #000000;");				//set the font
		setMinWidth(25);												//set the main width 
		setMinHeight(25);												//set the min heright
		setMaxWidth(25);												//set the max width
		setMaxHeight(25);												//set the max height
		setNull();														//set null
	}																	//close bracket
	public char getColor(){												//public char get color
		return color;													//return charachter repersenting the color
	}																	//close bracket
	public void reset(){												//reset function
		color = ' ';													//color is an empty charchter
		setStyle("-fx-base: #000000;");									//set the font

	}																	//close bracket
	public void setRed(){												//set red function
		setOnAction(new EventHandler<ActionEvent>() {					//new action evenet handler
		
			@Override													//java override
			public void handle(ActionEvent event) {						//action evenet handler

				setStyle("-fx-font: 22 arial; -fx-base: #FF0000;");		//set the font
				color = 'R';											//set color to charachter R
			}															//close bracket
			
		});																//close brackets
	}																	//close bracket
	public void setBlue(){												//set blue function 
		setOnAction(new EventHandler<ActionEvent>() {					//new action evenet handler
													
			@Override													//override for java
			public void handle(ActionEvent event) {						//action evenet handler

				setStyle("-fx-font: 22 arial; -fx-base: #0000FF;");		//set the font
				color = 'B';											//change the color to B
			}															//close bracket

		});																//close brakets
	}																	//close bracket
	public void setNull(){												//public set null fuction
		setOnAction(new EventHandler<ActionEvent>() {					//new action evenet handler
	
			@Override													//java override
			public void handle(ActionEvent event) {						//pulic void handle takes one parameter of type actionEvent 

				setStyle("-fx-font: 22 arial; -fx-base: #000000;");		//set the font style
			}															//close bracket

		});																//multiple close bracket
	}																	//close bracket
}																		//close bracket
