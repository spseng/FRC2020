package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;

public class OI {

	//Instantiate peripherals like joysticks here

	//Each method is called in a Command class to get data from the driver.
	//A Command will then call a Subsystem to send information to the robot.

	boolean grabberState = true;
	Joystick leftStick = RobotMap.leftJoystick;
	Joystick rightStick = RobotMap.rightJoystick;
	public XboxController xbox = RobotMap.xboxController;

	public static double valueShooterSpeed = 0.85;

	Button leftbutton1 = new JoystickButton(leftStick, 1), leftbutton2 = new JoystickButton(leftStick, 2);
	Button leftbutton3 = new JoystickButton(leftStick, 3), leftbutton4 = new JoystickButton(leftStick, 4);
	Button leftbutton5 = new JoystickButton(leftStick, 5), leftbutton6 = new JoystickButton(leftStick, 6);
	Button rightbutton1 = new JoystickButton(rightStick, 1), rightbutton2 = new JoystickButton(rightStick, 2);
	Button rightbutton3 = new JoystickButton(rightStick, 3), rightbutton4 = new JoystickButton(rightStick, 4);
	Button rightbutton5 = new JoystickButton(rightStick, 5), rightbutton6 = new JoystickButton(rightStick, 6);

	/*
	 * OI Interface: Drive Train: leftjoystick.Yaxis - left motor;
	 * rightjoystick.Yaxis - right motor Shooter: xbox right trigger starts the
	 * shooter :to make shooter go faster use D-Pad up button :to make shooter go
	 * slower use D-Pad down button Harvester: to run, hold down xbox Y button (when
	 * shooter running) Loader: to run, hold down xbox B button (when shooter
	 * running) Conveyor: to run, hold down xbox A button Making motors run
	 * backwards: Harvester: Holding top left bumper, and y reverses the harvester
	 * motor Loader: holding top left bumper, and a reverses the loader motor
	 * Conveyor: holding top left bumper, and b reverses the conveyor motor
	 */

	public double getLeftSpeed() {
		if (leftStick.getY() > 0.01) {
			return 0.8 * Math.pow(leftStick.getY(), 3) + 0.01;
		} else if (leftStick.getY() <= -0.01) {
			return 0.8 * Math.pow(leftStick.getY(), 3) - 0.01;
		} else {
			return 0.0;
		}
	}

	public double getRightSpeed() {
		if (rightStick.getY() > 0.01) {
			return 0.8 * -1.0 * Math.pow(rightStick.getY(), 3) - 0.01;
		} else if (rightStick.getY() < -0.01) {
			return 0.8 * -1.0 * Math.pow(rightStick.getY(), 3) + 0.01;
		} else {
			return 0.0;
		}
	}

	public boolean shoot() {
		if (xbox.getTriggerAxis(Hand.kRight) > 0.1) {
			return true;
		} else {
			return false;
		}
	}

	public int changeShooterSpeed() {
		if (xbox.getPOV() == 0) {
			return 1;
		} else if (xbox.getPOV() == 180) {
			return 2;
		} else {
			return 0;
		}
	}

	public boolean cycle() {
		if (xbox.getXButton() == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getXboxLeftBumper() {
		if (xbox.getBumper(Hand.kLeft) == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean harvester() {
		if (xbox.getYButton() == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean conveyor() {
		if (xbox.getBButton() == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean loader() {
		if (xbox.getAButton() == true) {
			return true;
		} else {
			return false;
		}
	}

	public double winch() {
		double pos = xbox.getY(Hand.kLeft);
		if (pos >= 0.01 || pos <= -0.01) {
			return pos;
		}
		
		return 0.0;
	}
}