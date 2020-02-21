package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.NetOutput;
import frc.robot.OI;

public class Detector extends Command {

	OI oi = new OI();

	int setpointMid = 320;
	int setpointWidth = -150;
	double error;
	double reading;
	NetOutput obj;

	public Detector(boolean color) {
		// Initialize Subsystem Object
		obj = new NetOutput();
		obj.update_team_color(color);
	}

	protected void initialize(boolean color) {
		Robot.driveTrain.stop();
		obj.update_team_color(color);
	}

	protected void execute() {
		int script_choice=0;
		if (false) {
			script_choice = get_script_choice();
			obj.update_choice(script_choice);

			// Store networktables output
			double[] output = new double[2];
			output = obj.get_output_of_selected_action();

			// If stop signal is received, end command
			if (output[0] == 0) {
					//As of now, if theres no script we just dont control motors

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
	}

	protected int get_script_choice() {
		//OI commands here
		return 0;
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