package frc.robot.commands;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {
	OI oi = Robot.m_oi;
	double error, newVal, reading, speed = 0;
	double setpoint = 180.0;
	double P = 1;
	
    public DriveStraight(double time, double inspeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	speed = inspeed;
    	setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
    	RobotMap.Gyro1.reset();
    }
    
    protected double[] PID() {
    	double[] returnVals = new double[2];
    	reading = RobotMap.Gyro1.getAngle(); //Gets angle form Gyro
    	error = reading - setpoint; //Calculates error based on the predefined reference point
    	newVal = P*error; //Multiplies the error by a constant

    	// ie the gyro is reading a value less then 180 degrees, meaning the robot needs to be adjusted to the right
    	// note that only a value of ~0.5 for speed should be fed in for best results, unless newVal is miniscule
    	if (newVal > 0.0) {
        	if (!((newVal + speed) > 1.0)) {
        		// left drive needs to be more powerful
        		returnVals[0] = newVal + speed;
        		returnVals[1] = -1.0 * (speed - newVal);
        		return returnVals;
        	}
        	// the weird case in which the gyro is reading a value so abnormal that either something is wrong or serious corrections need to be made
        	// for now drive equal power to both motors
        	else {
        		returnVals[0] = speed;
        		returnVals[1] = -1.0 * speed;
        		return returnVals;
        	}
    	}
    	// ie the gyro is reading a value greater then 180 degrees, meaning the robot needs to be adjusted to the left
    	else {
    		newVal = Math.abs(newVal);
    		if (!((newVal + speed) > 1.0)) {
        		// right drive needs to be more powerful
        		returnVals[0] = speed - newVal;
        		returnVals[1] = -1.0 * (speed + newVal);
        		return returnVals;
        	}
        	// the weird case in which the gyro is reading a value so abnormal that either something is wrong or serious corrections need to be made
        	// for now drive equal power to both motors
        	else {
        		returnVals[0] = speed;
        		returnVals[1] = -1.0 * speed;
        		return returnVals;
        	}
    	}

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		double[] speeds = PID();
    		Robot.driveTrain.tankDrive(speeds[0], speeds[1]); //Turns each motor according to the error. 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
