package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;

public class OI {

	Joystick leftStick = RobotMap.leftJoystick;
	Joystick rightStick = RobotMap.rightJoystick;
	public XboxController xbox = RobotMap.xboxController;

	public int winchState = 0;
	public double valueShooterSpeed = 0.5;

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
		// System.out.println("getleftspeed.getY()=" + leftStick.getY() + "
		// Math.pow(gety),3)=" + Math.pow(leftStick.getY(), 3));
		if (leftStick.getY() > 0.01) {
			return 0.5 * Math.pow(leftStick.getY(), 3) - 0.01;
		} else if (leftStick.getY() <= -0.01) {
			return 0.5 * Math.pow(leftStick.getY(), 3) + 0.01;
		} else {
			return 0.0;
		}
	}

	public double getRightSpeed() {
		// System.out.println("getrightspeed.getY()=" + rightStick.getY() +
		// "Math.pow(rightstickvalue)=" + Math.pow(rightStick.getY(), 3));
		if (rightStick.getY() > 0.01) {
			return 0.5 * -1.0 * Math.pow(rightStick.getY(), 3) + 0.01;
		} else if (rightStick.getY() < -0.01) {
			return 0.5 * -1.0 * Math.pow(rightStick.getY(), 3) - 0.01;
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

	/*
	 * public boolean grabberState() { if (grabberState == false) { grabberState =
	 * true; return true; } else { grabberState = false; return false; } }
	 */
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

	public void winch() {
		if (xbox.getTriggerAxis(Hand.kLeft) >= 0.01) {
			winchState = 1;
		} else if (xbox.getTriggerAxis(Hand.kLeft) <= -0.01) {
			winchState = -1;
		} else {
			winchState = 0;
		}
	}
}