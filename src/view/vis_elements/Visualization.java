package view.vis_elements;




import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import turtle.Turtle;
import turtle.TurtleController;
import view.canvas.DrawingWindow;
import view.canvas.ScrollingDrawingWindow;

/**
 * @author elizabethshulman
 * @author xlany
 * @param scene
 * 
 * Initializes and arranges each of the elements within the scene
 * 
 */
public class Visualization extends BorderPane implements IVisualConstants {


	
	private ResourceBundle myResources;
	private ScrollingDrawingWindow myScrollingDrawingWindow;
	private DrawingWindow myDrawingWindow;
	private TurtleController myTurtleController;
	private CommandTreeInterpreter interpreter;
	private ControlTextInput myControlTextInput;
	private ControlPanelRight myControlPanelRight;
	private ControlPanelLeft myControlPanelLeft;
	private Turtle myDefaultTurtle;
	private List<Turtle> myTurtles;
	
	
	public Visualization() {
		initializeAll();
		initializeLayout();
	}
	
	private void initializeAll() {
		setLanguage(DEFAULT_LANGUAGE);
		
		myScrollingDrawingWindow = new ScrollingDrawingWindow();
		myDrawingWindow = myScrollingDrawingWindow.getDrawingWindow();
		myTurtleController = new TurtleController(myDrawingWindow);
		myTurtles = myTurtleController.getActiveTurtles();
		
//		myDefaultTurtle = myScrollingDrawingWindow.getDefaultTurtle();
//		interpreter = new CommandTreeInterpreter(new ArrayList<Turtle>() {{
//			add(myDefaultTurtle);
//		}});
		interpreter = new CommandTreeInterpreter(myTurtles);
		myControlPanelRight = new ControlPanelRight(interpreter, myResources, myDrawingWindow);		
		myControlTextInput = new ControlTextInput(interpreter, this);
		myControlPanelLeft = new ControlPanelLeft(interpreter, myTurtles, myResources);
	}
	
	private void initializeLayout() {		
		this.setPadding(new Insets(20,20,20,20));
//		this.setTop(new InfoTop());
		this.setCenter(myScrollingDrawingWindow);
		this.setBottom(myControlTextInput);
		this.setRight(myControlPanelRight);
		this.setLeft(myControlPanelLeft);
		this.setWidth(Double.MAX_VALUE);
		this.setHeight(Double.MAX_VALUE);
		this.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
	}
	
	private void setLanguage(String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
	
	public void changeLanguage() {
		myResources = myControlPanelRight.getLanguage();
	}
	
	public ResourceBundle getLanguage() {
		changeLanguage();
		return myResources;
	}

}