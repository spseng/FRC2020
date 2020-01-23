package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Grabber extends Subsystem {

	public void moveGrabber(double speed) {
		RobotMap.leftGrabber.set(speed);
		RobotMap.rightGrabber.set(1.0 * speed);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new LiftGrabber());
	}
}
