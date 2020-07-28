/**
	
 * simulates window glitch 
 
 * @author Jafar Pathan (https://github.com/Jafar-Pathan)
 * @version 1.0
 * @copyright Feel free to change it and use it anywhere. Just provide link for github.
 * <b>!! WARNING :- DON'T OVER DO IT, BECAUSE IT CREATES NEW STAGE OBJECT EACH TIME WHEN PRIMARY STAGE IS DRAGGED. DRAGGING IT FOR TOO LONG WILL PUT IMPACT ON MEMORY. BE CAUTION.
 * 					TO CLOSE APPLICATION. CLOSE IT FROM WINDWOS TASKBAR. !!</b>

*/

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.Modality;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class WindowGlitch extends Application{
		
	private Stage glitchStage;
	private double dragOffsetX;
	private double dragOffsetY;
	private static Label dragLabel = new Label("Drag");
	
	public static void main(String args[]){
		Application.launch(args);
	}

	@Override
	public void start(Stage stage){
		//store the stage reference variable in the glitchStage
		this.glitchStage = stage;

		Scene scene = new Scene(new Group(dragLabel), 300, 200);

		//set the mouse dragged event handlers
		scene.setOnMousePressed(e -> handleMousePressed(e));
		scene.setOnMouseDragged(e -> handleMouseDragged(e));

		stage.setScene(scene);
		stage.setTitle("Glitched");
		stage.show();		
	}

	protected void handleMousePressed(MouseEvent e){
		//store x and y position
		this.dragOffsetX = e.getScreenX() - glitchStage.getX();
		this.dragOffsetY = e.getScreenY() - glitchStage.getY();
			
	}

	protected void handleMouseDragged(MouseEvent e){
		//move the stage to dragged point
		glitchStage.setX(e.getScreenX() - this.dragOffsetX);
		glitchStage.setY(e.getScreenY() - this.dragOffsetY);
		
		//create and display stage 
		showStage(glitchStage, glitchStage.getX(), glitchStage.getY());
				
	}

	public void showStage(Window owner,double x, double y){
		Stage subStage = new Stage();
		subStage.setScene(new Scene(new Group(dragLabel), 300, 200));
		subStage.initOwner(owner);
		subStage.setTitle("Glicthed");
		subStage.initModality(Modality.NONE);
		subStage.setX(x);
		subStage.setY(y);
		subStage.show();
	}
}