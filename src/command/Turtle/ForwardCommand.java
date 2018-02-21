package command.Turtle;

import command.Command;
import turtle.Turtle;

public class ForwardCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	
	public ForwardCommand(Turtle turtle, double movement){
		myTurtle = turtle;
		myMovement = movement;
	}
	
	public double execute(){
		// here goes how to change turtle position
		myTurtle.changeX(myTurtle.getX() + Math.sin(myTurtle.getDirection())*myMovement);
		myTurtle.changeY(myTurtle.getY() + Math.cos(myTurtle.getDirection())*myMovement);
		myTurtle.update();
		return myMovement;
	}
	
}