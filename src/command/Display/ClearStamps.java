package command.Display;

import command.Command;
import turtle.TurtleController;

/**
 * @author elizabethshulman
 * Removes all stamps that have been made
 */
public class ClearStamps implements Command {
	private TurtleController myController;
	
	

	public ClearStamps(TurtleController tc) {
		this.myController = tc;
	}
	
	/** Removes the current stamped ImageViews from the drawing window
	 * @return 1 if there were any stamps cleared, 0 otherwise	  
	 */	
	@Override
	public double execute() {
		return myController.clearStamps();
	}

}
