package slogo_team08;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.vis_elements.TabView;
public class Engine {

	private Stage myStage;
	private Scene myScene;
	private TabView slogoView;
	
	public Engine() {
		//no parameters needed to make a new engine
	}
	
	public void initializeSimulation(Stage primaryStage) {
		myStage = primaryStage;

		slogoView = new TabView();
		myScene = slogoView.getScene();
		myStage.setScene(myScene);
//		myStage.setMaximized(true);
//		myStage.setFullScreen(true);
		
		myStage.show();
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
	}
}
