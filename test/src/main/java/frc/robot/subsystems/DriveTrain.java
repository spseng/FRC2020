package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem {

	public void initDefaultCommand() {
		// setDefaultCommand(new DriveWithJoysticks());
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		// Drives the motors
		RobotMap.LeftMotor.set(leftSpeed);
		RobotMap.RightMotor.set(rightSpeed);
	}

	public void stop() {
		// Stops the motors
		RobotMap.LeftMotor.set(0.0);
		RobotMap.RightMotor.set(0.0);
	}

}
