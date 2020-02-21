package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
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

	// TalonSR motors setup
	public static VictorSP Conveyor = new VictorSP(1);
	public static VictorSP Spinner = new VictorSP(2);
	public static VictorSP Loader = new VictorSP(3);

	


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
