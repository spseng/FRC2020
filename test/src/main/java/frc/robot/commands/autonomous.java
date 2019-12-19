package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.NetOutput;

public class autonomous extends Command {

	int setpointMid = 320;
	int setpointWidth = -150;
	double error;
	double reading;
	NetOutput obj;

	autonomous() {
		requires(Robot.driveTrain);
	}

	public autonomous(int choice) {
		// Initialize Subsystem Object
		obj = new NetOutput(choice);
	}

	protected void initialize() {
		Robot.driveTrain.stop();
	}

	protected void execute() {
		// Store networktables output
		double[] output = new double[2];
		output = obj.get_output_of_selected_action();

		// If stop signal is received, end command
		if (output[0] == 0) {
			end();
		} else {
			// PID
			double[] speeds = PID(output[0]);

			// If ball is closer than threshold, stop
			if (output[2] < setpointWidth && error < 100) {
				speeds[0] = 0;
				speeds[1] = 0;
			}

			// Actuate motors
			Robot.driveTrain.tankDrive(speeds[0], speeds[1]); // Turns each motor according to the error.

		}
	}

	protected double[] PID(double output) {

		double[] returnVals = new double[2];
		reading = output; // Get input
		error = reading - setpointMid; // Calculates error based on the predefined reference point
		double speed = 0.50; // Constant speed
		double turnConstant = 0.002; // P constant for turning

		// Track ball
		if (error > 0) {
			returnVals[1] = speed + turnConstant * error;
			returnVals[0] = -speed;
		} else if (error < 0) {
			returnVals[0] = -speed + turnConstant * error;
			returnVals[1] = speed;
		} else if (error == 0) {
			Robot.driveTrain.stop();
		}

		return returnVals;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}