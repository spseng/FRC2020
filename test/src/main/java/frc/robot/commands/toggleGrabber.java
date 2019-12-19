 package frc.robot.commands;

import frc.robot.OI;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class toggleGrabber extends Command {
	OI oi;
	/**
	 * 
	 * @param type false is open, true is close
	 */
    public toggleGrabber() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	oi = new OI();
    	if(oi.grabberState()) {
    		Robot.grabber.close();
    	}
    	else {
    		Robot.grabber.open();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grabber.open();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
