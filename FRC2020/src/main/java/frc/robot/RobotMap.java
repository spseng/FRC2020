package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
/*
Cross the Road Electronics (CTRE) Talon and Talon SR Speed Controller.

Note that the Talon uses the following bounds for PWM values. These values should work reasonably well for most controllers, but if users experience issues such as asymmetric behavior around the deadband or inability to saturate the controller in either direction, calibration is recommended. The calibration procedure can be found in the Talon User Manual available from CTRE.

2.037ms = full "forward"
1.539ms = the "high end" of the deadband range
1.513ms = center of the deadband range (off)
1.487ms = the "low end" of the deadband range
0.989ms = full "reverse"
*/

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;

public class RobotMap {

	// Talon Speed Controller
	public static WPI_TalonSRX LeftMotor = new WPI_TalonSRX(2);
	public static WPI_TalonSRX RightMotor = new WPI_TalonSRX(3);
	public static WPI_TalonSRX Winch = new WPI_TalonSRX(4);
	public static WPI_TalonSRX Shooter = new WPI_TalonSRX(5);
	public static WPI_TalonSRX Harvester = new WPI_TalonSRX(6);

	// Old Talon motors setup
	public static Spark Conveyor = new Spark(0);
	public static Spark Spinner = new Spark(2);
	public static Spark Loader = new Spark(3);

	// Digital Input setup
	public static DigitalInput topLimitSwitch = new DigitalInput(0);

	// Joysticks/Controllers setup
	public static Joystick leftJoystick = new Joystick(1);
	public static Joystick rightJoystick = new Joystick(0);
	public static XboxController xboxController = new XboxController(2);

	// Color Sensor setup
	public static I2C.Port i2cPort = I2C.Port.kOnboard;
	public static ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
	public static ColorMatch colorMatcher = new ColorMatch();

	// Relay
	public static Relay GreenLED = new Relay(3);
}
