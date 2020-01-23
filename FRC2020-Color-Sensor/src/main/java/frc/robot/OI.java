/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;

public class OI {

	boolean grabberState = true;
	Joystick leftStick = RobotMap.leftJoystick;
	Joystick rightStick = RobotMap.rightJoystick;
	XboxController xbox = RobotMap.xboxController;

	Button leftBumper = new JoystickButton(xbox, 5);
	Button rightBumper = new JoystickButton(xbox, 6);

	//Gets the pressed xbox bumper (Used in MoveShoulder command)
	//0 no bumper; 1 left bumper; 2 right bumper
	public int getBumpers(){
		if (leftBumper.get()){
			return 1;
		}
		else if (rightBumper.get()){	
			return 2;
		}
		else{
			return 0;
		}
	}

	//Not the actual speed of the elevator
	//Gets inputs from the xbox sticks
	public double getElevatorSpeed() {
		if (xbox.getRawAxis(5) > 0.1 || xbox.getRawAxis(5) < -0.1) {
			return xbox.getRawAxis(5);
		} else {
			return 0.0;
		}
	}

	public double getGrabberSpeed() {
		if (xbox.getRawAxis(1) > 0.1 || xbox.getRawAxis(1) < -0.1) {
			return xbox.getRawAxis(1);
		} else {
			return 0.0;
		}
	}

	public double getLeftSpeed() {
		if (leftStick.getY() > 0.1 || leftStick.getY() < -0.1) {
			return leftStick.getY();
		} else {
			return 0.0;
		}
	}

	public double getRightSpeed() {
		if (rightStick.getY() > 0.1 || rightStick.getY() < -0.1) {
			return -1.0 * rightStick.getY();
		} else {
			return 0.0;
		}
	}

	public boolean grabberState() {
		if (grabberState == false) {
			grabberState = true;
			return true;
		} else {
			grabberState = false;
			return false;
		}
	}
}
