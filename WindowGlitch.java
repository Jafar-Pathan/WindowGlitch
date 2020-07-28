/**
	- simulates window glitch 
	- it creates one primary stage without any owner
/**
 * Demonstrates how three bounds - layoutBounds, boundsInLocal, and boundsInParent - are
 * computed for a node. JavaFX 2.2 is required to use this class.
 *
 * @author Kishori Sharan (www.jdojo.com)
 * @version 1.0
 *
 * <b>@copyright</b> You can use, modify, copy-paste the code for any purpose, in any way you see fit. No restrictions apply.
 

*/
import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.Modality;
import javafx.stage.Screen;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javafx.geometry.Rectangle2D;

public class WindowGlitch extends Application{
		
	private Stage glitchStage;
	private double dragOffsetX;
	private double dragOffsetY;
	
	public static void main(String args[]){
		Application.launch(args);
	}

	@Override
	public void start(Stage stage){
		//store the stage reference variable in the stage
		this.glitchStage = stage;
		Stage controlStage = new Stage();
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		controlStage.setX(0.0);//bounds.getMinX());
		controlStage.setY(0.0); //bounds.getMinY());
		controlStage.show();
		//controlStage.setX(Screen.getMinX());
		//controlStage.show();
		//showStage();
		Button close = new Button("Close");
		close.setOnAction(e -> Platform.exit());
		Scene scene = new Scene(new Group(close), 300, 200);
		//stage.setWidth(300);
		//stage.setHeight(200);
		//set the mouse dragged event handlers
		scene.setOnMousePressed(e -> handleMousePressed(e));
		scene.setOnMouseDragged(e -> handleMouseDragged(e));

		stage.setScene(scene);
		stage.setTitle("Window Glitch");
		stage.show();		
	}

	protected void handleMousePressed(MouseEvent e){
		this.dragOffsetX = e.getScreenX() - glitchStage.getX();
		this.dragOffsetY = e.getScreenY() - glitchStage.getY();
			
	}

	protected void handleMouseDragged(MouseEvent e){
		glitchStage.setX(e.getScreenX() - this.dragOffsetX);
		glitchStage.setY(e.getScreenY() - this.dragOffsetY);
		showStage(glitchStage, glitchStage.getX(), glitchStage.getY());
		//showStage(stage,e.getScreenX(), e.getScreenY());
		
	}

	public void showStage(Window owner,double x, double y){
		Stage subStage = new Stage();
		//stage.setWidth(300);
		//stage.setHeight(200);
		//stage.setScene(new Scene(new Group(), 300, 200));
		subStage.setWidth(300);
		subStage.setHeight(200);
		subStage.initOwner(owner);
		subStage.initModality(Modality.NONE);
		
		//subStage.initModality(Modality.WINDOW_MODAL);
		//subStage.initModality(Modality.APPLICATION_MODAL);
		subStage.setX(x);
		subStage.setY(y);
		subStage.show();
	}
}