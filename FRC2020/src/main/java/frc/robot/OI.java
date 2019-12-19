/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotMap;
import frc.robot.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	boolean grabberState = true;
	Joystick leftStick = RobotMap.leftJoystick;
	Joystick rightStick = RobotMap.rightJoystick;

	
	/**
	 * kBumperLeft(5),
     * kBumperRight(6),
     * kStickLeft(9),
     * kStickRight(10),
     * kA(1),
     * kB(2),
     * kX(3),
     * kY(4),
     * kBack(7),
     * kStart(8)
	 */
	XboxController xbox = RobotMap.xboxController;
	
	// create buttons
	
	// left bumper to raise elevator
	Button raiseElevator = new JoystickButton(xbox, 5);
	
	// right bumper to lower elevator
	Button lowerElevator = new JoystickButton(xbox, 6);
	
	// Y button to toggle grabber
	Button toggleGrabber = new JoystickButton(xbox, 4);

	// a button
	Button swapCompressor = new JoystickButton(xbox, 1);
	
	public double getElevatorSpeed() {
		if(xbox.getRawAxis(5)>0.1 || xbox.getRawAxis(5)<-0.1) {
			return xbox.getRawAxis(5);
		}
		else {
			return 0.0;
		}
	}
	
	public double getGrabberSpeed() {
		if(xbox.getRawAxis(1)>0.1 || xbox.getRawAxis(1)<-0.1) {
			return xbox.getRawAxis(1);
		}
		else {
			return 0.0;
		}
	}
	public double getLeftSpeed() {
		if(leftStick.getY()>0.1 || leftStick.getY()<-0.1) {
			return leftStick.getY();
		}
		else {
			return 0.0;
		}
	}
	
	public double getRightSpeed() {
		if(rightStick.getY()>0.1 || rightStick.getY()<-0.1) {
			return -1.0 * rightStick.getY();
		}
		else {
			return 0.0;
		}
	}
	
	public boolean grabberState() {
		if (grabberState == false) {
			grabberState = true;
			return true;
		}
		else {
			grabberState = false;
			return false;
		}
	}
	public OI() {
		String[] buttonInfo =
		{
			"leftStick Y axis: move left drive",
			"rightStick Y axis: move right drive",
			"xbox leftStick Y axis: bring grabber in or out of robot",
			"xbox rightStick Y axis: move elevator up or down",
			"xbox leftBumper: raise elevator",
			"xbox rightBumper: lower elevator",
			"xbox Y Button: toggle pneumatic grabber",
		 	"xbox A Button: toggle pneumatic compressor"
		};
		SmartDashboard.putStringArray("Button Information!", buttonInfo);
		//swapCompressor.whenPressed(new toggleCompressor());
		//raiseElevator.whenPressed(new autonomous(0));
		//lowerElevator.whenPressed(new autonomous(2));
		//toggleGrabber.whenPressed(new autonomous(1));
	}
	
}
