package command.Control;

import java.util.List;

import command.Command;
import interpreter.CommandTreeInterpreter;
import parser.CommandNode;

public class MakeUserInstructionCommand implements Command {
	private String myCommandName;
	private List<CommandNode> myVariables;
	private List<CommandNode> mySubCommands;
	private CommandTreeInterpreter myInterpreter;
	
	public MakeUserInstructionCommand (CommandNode commandName, CommandNode variables, CommandNode subCommands, CommandTreeInterpreter tree) {
		myInterpreter = tree;
		myVariables = variables.getNodeChildren();
		mySubCommands = subCommands.getNodeChildren();
		myCommandName = commandName.getCommandName();
	}
	
	@Override
	public double execute() {
		System.out.println("got into makeuserintruction class");
		/*if (myInterpreter.getVariables().checkVariable(myCommandName)) {
			System.out.println("Failed to create user-defined method: method name already exists in variables");
			return 0.0; // if the commandName is already a variable, fails 
		}*/
		
		// TO-DO: if method already exists in default, then return 0.0 
		System.out.println("my parameter size:!! " + myVariables.size());
		CommandNode methodRoot = new CommandNode("Bracket", myCommandName, null, 0);
		for (CommandNode subcommand : mySubCommands) {
			methodRoot.addChild(subcommand);
		}
		System.out.println("my inside command is: " + methodRoot.getNodeChildren().get(0).getCommandName());
		System.out.println("right now it contains method :cs? "+myInterpreter.getUserCommands().containsKey(myCommandName));
		if (myInterpreter.getUserCommands().containsKey(myCommandName)) {
			myInterpreter.getUserCommands().replace(myCommandName, methodRoot);
			myInterpreter.getUserCommandParameters().replace(myCommandName, myVariables);
		}
		else {
			myInterpreter.getUserCommands().put(myCommandName, methodRoot);
			myInterpreter.getUserCommandParameters().put(myCommandName, myVariables);
		}
		System.out.println("command name: " + myCommandName);
		System.out.println("now mapping to command:? " + myInterpreter.getUserCommands().containsKey(myCommandName));
		/*
		for (CommandNode variable : myVariables) {
			if (myInterpreter.getUserCommands().containsKey(variable.getCommandName())) {
				myInterpreter.getUserCommands().remove(myCommandName);
				myInterpreter.getUserCommandParameters().remove(myCommandName);
				System.out.println("Failed to create user-defined method: parameters are user-defined methods");
				return 0.0; // if the variable used is already a user command, fails 
			}
		}*/
		
		
		return 1.0;
	}
	
}
