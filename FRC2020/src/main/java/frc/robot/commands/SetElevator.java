package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevator extends Command {

	boolean UoD;
	
    public SetElevator(boolean type) {
		UoD = type;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(UoD == true) {
    		Robot.elevator.moveUp();
    	}
    	else {
    		Robot.elevator.moveDown();
    	}
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(UoD==true) {
            return Robot.elevator.isTopSwitchSet();
    	}
    	else {
    		return Robot.elevator.isLowSwitchSet();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// precautionary
    	end();
    }
}
