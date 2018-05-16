package application;
	
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		/*Get the size of maze*/
		Scanner input=new Scanner(System.in);
		System.out.println("Give the row Size and Column Size");
		int rowSize=input.nextInt();
		int colSize=input.nextInt();
		input.close();
		maze finMaze=new maze(rowSize,colSize);
		finMaze.generateMaze();
		
		try {
			primaryStage.setTitle("Maze Game");
			BorderPane root = new BorderPane();
			//root.setPrefSize(1000, 1000);
            BorderPane container=new BorderPane();
           //container.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            
            
          
            int i=0;
            int j =0;           
            for(i=0;i<10*rowSize;i+=10)
            {
            	for(j=0;j<10*colSize;j+=10)
            	{
            		cell newCell=finMaze.cells.get((j/10)*colSize+(i/10));
            		if(newCell.cellWall.top==true)
            		{
            			container.getChildren().add(new Line(i,j,i+10,j));
            		}
            		if(newCell.cellWall.left==true)
            		{
            			container.getChildren().add(new Line(i,j,i,j+10));
            		}
            		if(newCell.cellWall.right==true)
            		{
            			container.getChildren().add(new Line(i+10,j,i+10,j+10));
            		}
            		if(newCell.cellWall.bottom==true)
            		{
            			container.getChildren().add(new Line(i,j+10,i+10,j+10));
            		}            		            		
            	}
            } 
           
	        root.getChildren().add(container);
	        root.setAlignment(container, Pos.CENTER);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add("application/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
