package application;								//use application package
//orange banana
import javafx.event.EventHandler;					//import javafx library
import javafx.scene.input.MouseEvent;				//import javafx library
import javafx.scene.paint.Color;					//import javafx library
import javafx.scene.shape.Circle;					//import javafx library

public class Node extends Circle{					//class node extends circle

	protected int x;								//initaile protected int x
	protected  int y;								//initialize  protected int y
	protected  int radius = 15;						//procted int radius is 15
	protected  Color color;							//protected color named color
	protected  boolean isEmpty;						//protected boolean named isempty
	protected  Color myColor;						//proted color named mycolor

	public Node(){}									//public node constructor

	public Node(int myX, int myY){					//public node constructor taking in 2 parameters
		x = myX;									//x is equal to parameter
		y = myY;									//y is equal to parameter
		radius = 15;								//radius is equal to 15
		myColor = Color.BLACK;						//my color is equal to black
		isEmpty = true;								//isEmpty is equal to true
	}												//close bracket

	public int getX(){								//getter function
		return x;									//return x
	}												//close bracket

	public int getY(){								//getter function
		return y;									//reurn y
	}												//close bracket

	public int getRad(){							//getter function
		return radius;								//returns the radius
	}												//close bracket

	public Color getColor(){						//getter function
		return color;								//return the color
	}												//close bracket


}													//close bracket
