package command.Control;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;
import turtle.TurtleController;
/**
 * ask all available turtles that satisfy the given condition to execute some commands 
 */
public class AskWithCommand implements Command{
	private CommandTreeInterpreter myInterpreter;
	private List<Integer> myTemporaryActiveTurtleIndices;
	private CommandNode myCondition;
	private List<CommandNode> mySubCommands;
	private List<Integer> myOldActiveTurtleIndices;
	
	public AskWithCommand(CommandNode condition, CommandNode subcommandsParent, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myCondition = condition.getNodeChildren().get(0);
		myTemporaryActiveTurtleIndices = new ArrayList<>();
		mySubCommands = subcommandsParent.getNodeChildren();
		myOldActiveTurtleIndices = myInterpreter.getCurrentActiveTurtleIndices();
	}
	
	/**
	 * iterates through all available turtles to evaluate their condition values, adds the non-zero ones to the new active turtle list, runs some commands on the new active turtle list, and sets the active turtle list back to previous at the end of this command execution 
	 */
	public double execute() {
		TurtleController myTurtleController = myInterpreter.getTurtleController();
		for (int i = 1; i <= myInterpreter.getCurrentAvailableTurtles().size(); i++) { // creates an active list of turtles that satisfies the condition 
			myInterpreter.getTurtleController().setCurrentTurtleIndex(i);
			myInterpreter.interpretTree(myCondition);
			if (myCondition.getNodeValue()!=0) {
				myTemporaryActiveTurtleIndices.add(myInterpreter.getCurrentActiveTurtleIndex());
			}
		}
		myTurtleController.resetActiveTurtles(myTemporaryActiveTurtleIndices);
		for (int i = 0; i < mySubCommands.size(); i++) {
			myInterpreter.interpretTree(mySubCommands.get(i));
		}
		myTurtleController.resetActiveTurtles(myOldActiveTurtleIndices);
		if (mySubCommands.size() != 0) {
			return (double) mySubCommands.get(mySubCommands.size()-1).getNodeValue();
		}
		else {
			return 0.0;
		}
	}
}
