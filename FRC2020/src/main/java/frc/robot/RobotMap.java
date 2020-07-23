package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.ctre.phoenix.sensors.CANCoder;
/*
Cross the Road Electronics (CTRE) Talon and Talon SR Speed Controller.

2.037ms = full "forward"
1.539ms = the "high end" of the deadband range
1.513ms = center of the deadband range (off)
1.487ms = the "low end" of the deadband range
0.989ms = full "reverse"
*/

public class RobotMap {

	//This is where motors and other component classes are instantiated.
	//Subsystem classes call these to interact with the Robot.

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
	public static DigitalInput bottomLimitSwitch = new DigitalInput(0);

	// Joysticks/Controllers setup
	public static Joystick leftJoystick = new Joystick(1);
	public static Joystick rightJoystick = new Joystick(0);
	public static XboxController xboxController = new XboxController(2);

	// Color Sensor setup
	public static I2C.Port i2cPort = I2C.Port.kOnboard;
	public static ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
	public static ColorMatch colorMatcher = new ColorMatch();

	// RPM SenSor setup
	public static CANCoder CANCoder = new CANCoder(7);

	// Distance sensor setup
	public static AnalogInput distanceSensor = new AnalogInput(0);

	// Relay
	public static Relay GreenLED = new Relay(3);
}
