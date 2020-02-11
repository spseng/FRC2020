package frc.robot;

// import edu.wpi.first.wpilibj.CameraServer;
// import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.Detector;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ColorCycle;
import frc.robot.RobotMap;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

public class Robot extends TimedRobot {
	XboxController xbox = RobotMap.xboxController;
	Joystick leftstick = RobotMap.leftJoystick;
	Joystick rightstick = RobotMap.rightJoystick;

	public static DriveTrain driveTrain;
	public static OI OI;

	boolean Xon = false;
	public static boolean toggle = false;
	boolean GreenLED_ON = false;
	public final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
	public final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
	public final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
	public final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

	@Override
	public void robotInit() {

		// CameraServer.getInstance().startAutomaticCapture();
		driveTrain = new DriveTrain();
		OI = new OI();
		GreenLED_ON = false;

		RobotMap.colorMatcher.addColorMatch(kBlueTarget);
		RobotMap.colorMatcher.addColorMatch(kGreenTarget);
		RobotMap.colorMatcher.addColorMatch(kRedTarget);
		RobotMap.colorMatcher.addColorMatch(kYellowTarget);
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

	@Override
	public void robotPeriodic() {
		// periodically gets the detected color from Sensor
		Color detectedColor = RobotMap.colorSensor.getColor();
		String colorString;
		ColorMatchResult match = RobotMap.colorMatcher.matchClosestColor(detectedColor);
		int proximity = RobotMap.colorSensor.getProximity();

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
		SmartDashboard.putNumber("Proximity", proximity);

		if (OI.shoot() == true) {
			Shooter.shooterShoot(OI.shooterspeed);
		} else if (OI.shoot() == false) {
			Shooter.shooterStop();
		}

		if (OI.cycle() == true) {
			ColorCycle.colorCycleStart();
		} else if (OI.cycle() == false) {
			ColorCycle.colorCycleStop();
		}
	}

	@Override
	public void teleopPeriodic() {
		/*
		// Left trigger pressed, end OI commands and start tape script 
		if (leftstick.getTriggerPressed() && toggle == false) {
			RobotMap.GreenLED.set(Relay.Value.kForward);
			Scheduler.getInstance().removeAll(); 
			Scheduler.getInstance().add(newautonomous(1)); 
			toggle = true; 
		}
		
		// Right trigger pressed, end OI commands and start ball script 
		if (rightstick.getTriggerPressed() && toggle == false) {
			Scheduler.getInstance().removeAll(); 
			Scheduler.getInstance().add(newautonomous(2)); 
			toggle = true;
		}
			
		// Both triggers released, end autoomous scripts and start OI scripts 
		if (leftstick.getTriggerReleased() && rightstick.getTriggerReleased() && toggle){ 
			DriverStation.reportError("TRIGGER", false);
			RobotMap.GreenLED.set(Relay.Value.kReverse); 
			Scheduler.getInstance().add(newautonomous(0)); 
			OICommands(); toggle = false; 
		}
		*/
		Scheduler.getInstance().run();

	}

	//Adds commands to scheduler after leaving vision system control
	void OICommands() {
		Scheduler.getInstance().add(new Detector());
		Scheduler.getInstance().add(new DriveWithJoysticks());
	}

	@Override
	public void testPeriodic() {
	}
}
