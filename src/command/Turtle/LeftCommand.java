package command.Turtle;

import command.Command;
import turtle.Turtle;

public class LeftCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	
	public LeftCommand(double movement, Turtle turtle){
		myTurtle = turtle;
		myMovement = Math.toRadians(movement);
	}
	
	public double execute(){
		myTurtle.setDirection(myTurtle.getDirection() - myMovement);
		return myMovement;
	}
	
}
