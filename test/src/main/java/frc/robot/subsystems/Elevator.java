package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public boolean isLowSwitchSet() {
		if (RobotMap.limitSwitch2.get() == false) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isTopSwitchSet() {
		if (RobotMap.limitSwitch1.get() == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void moveUp() {
		RobotMap.fourthTalon.set(1.0);
	}
	public void moveDown() {
		RobotMap.fourthTalon.set(-1.0);
	}
	
	public void moveElevator(double speed) {
		if(speed>0) {
			boolean top = isTopSwitchSet();
			if(top) {
				// do nothing
			}
			else {
				RobotMap.fourthTalon.set(speed);
			}
		}
		if(speed<0) {
			boolean bot = isLowSwitchSet();
			if(bot) {
				// do nothing
			}
			else {
				RobotMap.fourthTalon.set(speed);
			}
		}
	}
	
	public void stop() {
		RobotMap.fourthTalon.set(0.0);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

