package view.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import turtle.Turtle;
import turtle.TurtleController;

public class TurtleAvatarMenu extends TitledPane {
	
	public static final int THUMBNAIL_WIDTH = 50;
	public static final int THUMBNAIL_HEIGHT = 50;
	public static final String TURTLE_MENU_KEY = "TurtleMenu";
	private List<Button> myTurtleButtons;
	private VBox myTurtleList;
	private TurtleController myTurtleController;
	
	public TurtleAvatarMenu(ResourceBundle resources, TurtleController tc) {
		myTurtleController = tc;
		setupPane(resources);
		setupTurtleSelection();
//		initiateToInterp();
	}
	
	private void setupPane(ResourceBundle resources) {
		this.setText(resources.getString(TURTLE_MENU_KEY));
		this.setExpanded(false);
	}
	
	private void setupTurtleSelection() {
		myTurtleList = new VBox();
		myTurtleButtons = new ArrayList<>();
		makeButton("franklin.jpg", myTurtleList);
		makeButton("cute_turtle.png", myTurtleList);
		this.setContent(myTurtleList);

	}
	
	private void makeButton(String filepath, VBox vbox) {
		Button button = new Button();
		Image img = new Image(getClass().getClassLoader().getResourceAsStream(filepath));
		ImageView imgview = new ImageView(img);
		imgview.setFitWidth(THUMBNAIL_WIDTH);
		imgview.setFitHeight(THUMBNAIL_HEIGHT);
		button.setGraphic(imgview);
		button.setOnAction(e -> changeTurtleImage(img));
		myTurtleButtons.add(button);
		vbox.getChildren().add(button);
	}
	
	private void changeTurtleImage(Image img) {
		for (Turtle t: myTurtleController.getActiveTurtles()) {
			t.setImage(img);
		}
	}
	
	public List<Button> getTurtleButtons() {
		return myTurtleButtons;
	}
	
	public VBox getTurtleList() {
		return myTurtleList;
	}
	
}
