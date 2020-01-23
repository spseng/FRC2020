/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;

public class RobotMap {

	// Talons
	public static WPI_TalonSRX LeftMotor = new WPI_TalonSRX(1);
	public static WPI_TalonSRX RightMotor = new WPI_TalonSRX(2);
	public static WPI_TalonSRX Winch = new WPI_TalonSRX(3);

	// Spark motors setup
	public static Spark rightGrabber = new Spark(0);
	public static Spark leftGrabber = new Spark(1);
	public static Spark shoulder = new Spark(2);

	// Joysticks setup
	public static Joystick leftJoystick = new Joystick(1);
	public static Joystick rightJoystick = new Joystick(0);
	public static XboxController xboxController = new XboxController(2);

	// Sensors setup
	public static ADXRS450_Gyro Gyro1 = new ADXRS450_Gyro();

	// Limit Switches
	public static DigitalInput limitSwitchTop = new DigitalInput(0);
	public static DigitalInput limitSwitchBottom = new DigitalInput(1);

	// Relay
	public static Relay GreenLED = new Relay(3);

}
