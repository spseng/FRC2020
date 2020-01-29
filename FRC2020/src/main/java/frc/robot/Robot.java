package frc.robot;

//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.autonomous;
import frc.robot.subsystems.DriveTrain;
import java.util.concurrent.TimeUnit;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class Robot extends TimedRobot {
	XboxController xbox = RobotMap.xboxController;
	Joystick leftstick = RobotMap.leftJoystick;
	Joystick rightstick = RobotMap.rightJoystick;

	public static DriveTrain driveTrain;
	public static OI m_oi;

	boolean Xon = false;
	boolean toggle = false;
	boolean GreenLED_ON = false;

	private final I2C.Port i2cPort = I2C.Port.kOnboard;
	private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
	private final ColorMatch m_colorMatcher = new ColorMatch();
	private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
	private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
	private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
	private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

	@Override
	public void robotInit() {

		// CameraServer.getInstance().startAutomaticCapture();
		driveTrain = new DriveTrain();
		m_oi = new OI();
		RobotMap.Gyro1.calibrate();
		GreenLED_ON = false;

		m_colorMatcher.addColorMatch(kBlueTarget);
		m_colorMatcher.addColorMatch(kGreenTarget);
		m_colorMatcher.addColorMatch(kRedTarget);
		m_colorMatcher.addColorMatch(kYellowTarget);
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {

		// Init relay
		RobotMap.GreenLED.set(Relay.Value.kOn);
		RobotMap.GreenLED.set(Relay.Value.kReverse);

		// Init OI
		OICommands();
	}

	// periodically gets the detected color from Sensor
	@Override
	public void robotPeriodic() {
		Color detectedColor = m_colorSensor.getColor();
		String colorString;
		ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
		double IR = m_colorSensor.getIR();
		int proximity = m_colorSensor.getProximity();

		if (match.color == kBlueTarget) {
			colorString = "Blue";
		} else if (match.color == kRedTarget) {
			colorString = "Red";
		} else if (match.color == kGreenTarget) {
			colorString = "Green";
		} else if (match.color == kYellowTarget) {
			colorString = "Yellow";
		} else {
			colorString = "Unknown";
		}

		SmartDashboard.putNumber("Red", detectedColor.red);
		SmartDashboard.putNumber("Blue", detectedColor.blue);
		SmartDashboard.putNumber("Green", detectedColor.green);
		SmartDashboard.putNumber("Confidence", match.confidence);
		SmartDashboard.putString("Detected Color", colorString);
		SmartDashboard.putNumber("IR", IR);
		SmartDashboard.putNumber("Proximity", proximity);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		/*
		 * // Left trigger pressed, end OI commands and start tape script if
		 * (leftstick.getTriggerPressed() && toggle == false) {
		 * RobotMap.GreenLED.set(Relay.Value.kForward);
		 * Scheduler.getInstance().removeAll(); Scheduler.getInstance().add(new
		 * autonomous(1)); toggle = true; }
		 * 
		 * // Right trigger pressed, end OI commands and start ball script if
		 * (rightstick.getTriggerPressed() && toggle == false) {
		 * Scheduler.getInstance().removeAll(); Scheduler.getInstance().add(new
		 * autonomous(2)); toggle = true; }
		 * 
		 * // Both triggers released, end autoomous scripts and start OI scripts if
		 * (leftstick.getTriggerReleased() && rightstick.getTriggerReleased() && toggle)
		 * { DriverStation.reportError("TRIGGER", false);
		 * RobotMap.GreenLED.set(Relay.Value.kReverse); Scheduler.getInstance().add(new
		 * autonomous(0)); OICommands(); toggle = false; }
		 */
		Scheduler.getInstance().run();

		for (int colorCycle = 0; leftstick.getTriggerPressed() && toggle == false; SmartDashboard
				.putNumber("Colors Passed", colorCycle)) {
			Color oneColor = m_colorSensor.getColor();
			ColorMatchResult firstColor = m_colorMatcher.matchClosestColor(oneColor);
			try {
				TimeUnit.SECONDS.sleep(1 / 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Color twoColor = m_colorSensor.getColor();
			ColorMatchResult secondColor = m_colorMatcher.matchClosestColor(twoColor);
			if (firstColor != secondColor) {
				colorCycle = colorCycle + 1;
			}
		}
	}

	//Adds commands to scheduler after leaving vision system control
	void OICommands() {
		Scheduler.getInstance().add(new autonomous(0));
		Scheduler.getInstance().add(new DriveWithJoysticks());
	}

	@Override
	public void testPeriodic() {
	}
}
