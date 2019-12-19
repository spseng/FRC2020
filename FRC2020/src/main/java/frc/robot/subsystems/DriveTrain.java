package frc.robot.subsystems;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoysticks;


import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	public void initDefaultCommand() {
		//setDefaultCommand(new DriveWithJoysticks());
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		// Drives the motors
		RobotMap.firstTalon.set(leftSpeed);
		RobotMap.secondTalon.set(rightSpeed);
		
	}
	
	// run both motors in the same set direction, because they are mounted opposite
	public void spin() {
		RobotMap.firstTalon.set(0.4);
		RobotMap.secondTalon.set(0.4);
	}
	
	public void stop() {
		RobotMap.firstTalon.set(0.0);
		RobotMap.secondTalon.set(0.0);
	}
	
}
