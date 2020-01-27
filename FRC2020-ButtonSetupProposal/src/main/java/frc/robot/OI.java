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


	
	Joystick leftStick = RobotMap.leftJoystick;
	Joystick rightStick = RobotMap.rightJoystick;
	XboxController xbox = RobotMap.xboxController;

	Button leftbutton1 = new JoystickButton(leftStick, 1),
          leftbutton2 = new JoystickButton(leftStick, 2),
          leftbutton3 = new JoystickButton(leftStick, 3),
          leftbutton4 = new JoystickButton(leftStick, 4),
          leftbutton5 = new JoystickButton(leftStick, 5),
          leftbutton6 = new JoystickButton(leftStick, 6),
		  
		  rightbutton1 = new JoystickButton(rightStick, 1),
          rightbutton2 = new JoystickButton(rightStick, 2),
          rightbutton3 = new JoystickButton(rightStick, 3),
          rightbutton4 = new JoystickButton(rightStick, 4),
          rightbutton5 = new JoystickButton(rightStick, 5),
		  rightbutton6 = new JoystickButton(rightStick, 6);
		  
	//kA = 1, kB = 2, kX = 3, kY = 4, 
	//A-Button to increase, Y-Button to decrease shooter speed
	Button decreaseShooterSpeed = new JoystickButton(xbox, 1);
	Button increaseShooterSpeed = new JoystickButton(xbox, 4);

	//X and B for control panel spin (counterclockwise/clockwise)

	Button controlPanelClockwise = new JoystickButton(xbox, 3);
	Button controlPanelCounterclockwise = new JoystickButton(xbox, 4);


	//Gets the pressed xbox bumper (Used in MoveShoulder command)
	//0 no bumper; 1 left bumper; 2 right bumper

	


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
}
