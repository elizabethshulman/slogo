package command.Display;

import command.Command;
import turtle.TurtleController;

/**
 * @author elizabethshulman
 * Draws the image of the turtle in its current settings on the workspace display,
 * then returns the index of the turtle's image used for the stamp
 */
public class StampCommand implements Command {
	private TurtleController myController;
	
	public StampCommand(TurtleController tc) {
		this.myController = tc;
	}
	
	@Override
	public double execute() {
		return myController.stamp();
	}

}
