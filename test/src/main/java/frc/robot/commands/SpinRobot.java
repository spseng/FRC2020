package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinRobot extends Command {

	double spinTo;
	boolean GyroComplete;
    public SpinRobot(double degrees) {
    	spinTo = degrees;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setTimeout(1.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.Gyro1.reset();
    	GyroComplete = false;
    	Robot.driveTrain.spin();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.Gyro1.getAngle() >= spinTo) {
    		GyroComplete = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isTimedOut() || GyroComplete);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
