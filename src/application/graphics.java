package application;					//use pakage application 
	
import javafx.application.Application;			//import javafx library
import javafx.event.ActionEvent;	//import javafx library
import javafx.event.Event;			//import javafx library
import javafx.event.EventHandler;	//import javafx library
import javafx.stage.Stage;			//import javafx library
import javafx.scene.Scene;			//import javafx library
import javafx.scene.*;				//import javafx library
import javafx.scene.control.Button;	//import javafx library
import javafx.scene.input.MouseEvent;//import javafx library
import javafx.scene.layout.BorderPane;//import javafx library
import javafx.scene.layout.StackPane;//import javafx library
import javafx.scene.paint.Color;//import javafx library
import javafx.scene.shape.*;//import javafx library
import javafx.scene.text.Font;//import javafx library
import javafx.scene.text.FontWeight;//import javafx library
import javafx.scene.canvas.*;//import javafx library


public class graphics extends Application {			//public class graphics extends application		
	Node[] nodes = new Node[16];					//create an array of nodes of length 16
	Node node = new Node();						//intialize a new node
	
	@Override									//java override call
	public void start(Stage primaryStage) {				//public void start takes in one parameter
		primaryStage.setTitle("Six Men's Morris");		//
		
		Group root = new Group();						//make new group named root
		Scene scene = new Scene(root);					//make new scene named sene that takes in root
		primaryStage.setScene(scene);					//set the primary scene as scene
		
		Canvas canvas = new Canvas (800,600);						//make new canvas
		root.getChildren().add(canvas);								//add canvas
		
		GraphicsContext gc = canvas.getGraphicsContext2D();									//create new graphics context
		Circle circle = new Circle(nodes[0].getCenterX(),nodes[0].getCenterY(),20);			//create new circle
		
		
		nodes[0] = new Node(90,90);			//generate black nodes accordingly
		nodes[1] = new Node(290,90);		//generate black nodes accordingly
		nodes[2] = new Node(490,90);		//generate black nodes accordingly
		nodes[3] = new Node(190,190);		//generate black nodes accordingly		
		nodes[4] = new Node(290,190);		//generate black nodes accordingly
		nodes[5] = new Node(390,190);		//generate black nodes accordingly
		nodes[6] = new Node(90,290);		//generate black nodes accordingly
		nodes[7] = new Node(190,290);		//generate black nodes accordingly
		nodes[8] = new Node(390,290);		//generate black nodes accordingly
		nodes[9] = new Node(490,290);		//generate black nodes accordingly
		nodes[10] = new Node(190,390);		//generate black nodes accordingly
		nodes[11] = new Node(290,390);		//generate black nodes accordingly
		nodes[12] = new Node(390,390);		//generate black nodes accordingly
		nodes[13] = new Node(90,490);		//generate black nodes accordingly
		nodes[14] = new Node(290,490);		//generate black nodes accordingly
		nodes[15] = new Node(490,490);		//generate black nodes accordingly
		
		drawBoard(gc,nodes);			//draw the board
		gc.setFill(Color.BLACK);		//set the board color to black
		
		for (int i = 0; i < nodes.length; i++) {			//loop over the amount of nodes
			//gc.fillOval(nodes[i].getX(), nodes[i].getY(), 25, 25);
			drawNode(gc,nodes[i]);						//draw the nodes
		}
		primaryStage.show();		// show the primary stage

	}

	
	private void drawNode(GraphicsContext gc,Node node){			
		gc.fillOval(node.getX(), node.getY(), 25, 25);				//set the graph line
	}
	
	private void drawDisc(GraphicsContext gc,disc mydisc){
		gc.fillOval(mydisc.getX(), mydisc.getY(), 25, 25);			//set the disc 
	}
	
	private void drawBoard(GraphicsContext gc,Node[] nodes){		
		gc.setFill(Color.BLACK);								//fill the color as black
		
		
		//gc.fillRect(x, y, w, h);
		gc.setFill(Color.BEIGE);							//set the background color to baige
		gc.setStroke(Color.BLACK);							//set the rectangle color
		gc.setLineWidth(4);								//set the line width
		//Font theFont = Font.font("Times new Roman", FontWeight.BOLD, 24);
		//gc.setFont(theFont);
		gc.fillRect(100, 100, 400, 400);				//fill the rectangle
		gc.strokeRect(100, 100, 400, 400);				//draw a rectangle
		
		gc.strokeLine(300, 100, 300, 200);				//draw a line
		gc.strokeLine(300, 400, 300, 500);				//draw a line 
		gc.strokeLine(100, 300, 200, 300);				//draw a line
		gc.strokeLine(400, 300, 500, 300);				//draw a line
	
		gc.strokeRect(200,200,200,200);					//draw rectangle
	}													//close bracket
	
	public static void main(String[] args) {			//main function
		launch(args);								//launch the arguments
	}
}																					
