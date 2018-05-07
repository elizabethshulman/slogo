package view.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import observables.Listener;
import turtle.Turtle;
import turtle.TurtleController;
import view.supplements.HBoxPenChanger;
/**
 * Class that allows user to change color and width for each turtle's pen
 * @author xlany, elizabethshulman
 *
 */
public class PenMenu extends TitledPane implements Listener {
	
	private TurtleController myTurtles;
	private List<Turtle> allTurtles;
	private ArrayList<HBoxPenChanger> penModifiers;
	private ListView<HBoxPenChanger> penDisplay;
	
	/**
	 * Constructor takes in
	 * @param resources for languages
	 * @param tc for collection of active turtles
	 * Sets up pen changers for each pen
	 */
	public PenMenu(ResourceBundle resources, TurtleController tc) {
		myTurtles = tc;
		initializeTitledPane();
		setupPenChangers();
		myTurtles.addTurtleListener(this);
	}
	
	/**
	 * Method initializes menu with penModifier and penDisplay
	 * Visualizes penDisplay
	 */
	private void initializeTitledPane() {
		this.setMaxWidth(Double.MAX_VALUE);
		this.setText("Change Pen Attributes");
		this.setExpanded(false);
		penModifiers = new ArrayList<HBoxPenChanger>();
		penDisplay = new ListView<HBoxPenChanger>(
				FXCollections.observableArrayList(penModifiers));
		this.setContent(penDisplay);
	}
	
	/**
	 * For each turtle, create (1) a pen color selector and (2) a button for changing pen width
	 */
	private void setupPenChangers() {
		allTurtles = myTurtles.getAllTurtles();
		penModifiers.clear();
		penModifiers.add(new HBoxPenChanger());
		allTurtles.forEach(turtle -> penModifiers.add(
				new HBoxPenChanger(turtle, myTurtles.getAllTurtles().indexOf(turtle))));
		penDisplay.setItems(FXCollections.observableArrayList(penModifiers));
	}
	
	/**
	 * Upon creation of a new turtle, this method initializes a corresponding pen-modification menu entry
	 * Overriden from Listener interface
	 */
	@Override
	public void update() {
		setupPenChangers();
	}
}
