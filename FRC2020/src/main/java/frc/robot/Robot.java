package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer; 
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.MoveWinch;
import frc.robot.commands.Detector;
import frc.robot.commands.Shootball;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Winch;
import frc.robot.subsystems.BallManagement;
import frc.robot.subsystems.ColorCycle;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

public class Robot extends TimedRobot {

	XboxController xbox = RobotMap.xboxController;
	Joystick leftstick = RobotMap.leftJoystick;
	Joystick rightstick = RobotMap.rightJoystick;

	public static DriveTrain driveTrain;
	public static Shooter shooter;
	public static Winch winch;
	public static OI OI;
	public static String colorString;
	public Timer timer = new Timer(); 
	
	boolean Xon = false;
	boolean GreenLED_ON = false;
	String teamColor;
	int shooterCycle;

	public final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
	public final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
	public final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
	public final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

	DriverStation.Alliance blueTeam = Alliance.Blue;
	DriverStation.Alliance redTeam = Alliance.Red;

	@Override
	public void robotInit() {

		CameraServer.getInstance().startAutomaticCapture();
		driveTrain = new DriveTrain();
		OI = new OI();
		GreenLED_ON = false;

		RobotMap.colorMatcher.addColorMatch(kBlueTarget);
		RobotMap.colorMatcher.addColorMatch(kGreenTarget);
		RobotMap.colorMatcher.addColorMatch(kRedTarget);
		RobotMap.colorMatcher.addColorMatch(kYellowTarget);

		if (DriverStation.Alliance.valueOf("Blue") == blueTeam) {
			Scheduler.getInstance().add(new Detector(true));
			teamColor = "Blue";
		} else if (DriverStation.Alliance.valueOf("Red") == redTeam) {
			Scheduler.getInstance().add(new Detector(false));
			teamColor = "Red";
		} else {
			teamColor = "Invalid";
		}
		SmartDashboard.putString("Team Color", teamColor);
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
		timer.start();
	}

	@Override
	public void autonomousPeriodic() {
		boolean drive = true;
		double currentDistance = RobotMap.distanceSensor.getValue() * 0.125;

		if (currentDistance < 12){
			drive = false;
		}

		while ((timer.get() < 5)){ // the number represents seconds
			if (drive){
				Robot.driveTrain.tankDrive(1.0, 1.0); //change speed
			}
		}
		if (timer.get() > 5){
			Robot.driveTrain.stop();
		}
	}

	@Override
	public void teleopInit() {

		// Init relay
		RobotMap.GreenLED.set(Relay.Value.kOn);
		RobotMap.GreenLED.set(Relay.Value.kReverse);

		Scheduler.getInstance().add(new Shootball());
		Scheduler.getInstance().add(new MoveWinch());

		// Init OI
		OICommands();
	}

	@Override
	public void robotPeriodic() {
		// periodically gets the detected color from Sensor
		Color detectedColor = RobotMap.colorSensor.getColor();
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

		SmartDashboard.putNumber("Confidence", match.confidence);
		SmartDashboard.putString("Detected Color", colorString);
		SmartDashboard.putNumber("Proximity", proximity);
		SmartDashboard.putNumber("ShooterSpeed", OI.valueShooterSpeed);
		SmartDashboard.putNumber("Rotations Completed", ColorCycle.colorCycleValue);
		SmartDashboard.putNumber("Colors Passed", ColorCycle.colorsPassedValue);
		SmartDashboard.putNumber("Bottom Limit Switch", RobotMap.bottomLimitSwitch.get() ? 1:0);
		SmartDashboard.putNumber("Distance sensor value(in)", RobotMap.distanceSensor.getValue() * 0.125);
		SmartDashboard.putNumber("Encoder velocity(rpm)", RobotMap.CANCoder.getVelocity() / -360.0);

		if (ColorCycle.colorCycleValue == 3) {
			ColorCycle.colorCycleStop();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		if (OI.getXboxLeftBumper() == true) {
			if (OI.harvester() == true) {
				BallManagement.harvesterBackward();
			} else {
				BallManagement.harvesterStop();
			}

			if (OI.conveyor() == true) {
				BallManagement.conveyorBackward();
			} else {
				BallManagement.conveyorStop();
			}

			if (OI.loader() == true) {
				BallManagement.loaderForward();
			} else {
				BallManagement.loaderStop();
			}

		} else {
			if (OI.harvester() == true) {
				BallManagement.harvesterForward();
			} else {
				BallManagement.harvesterStop();
			}

			if (OI.conveyor() == true) {
				BallManagement.conveyorForward();
			} else {
				BallManagement.conveyorStop();
			}

			if (OI.loader() == true) {
				BallManagement.loaderBackward();
			} else {
				BallManagement.loaderStop();
			}
		}

		if (OI.changeShooterSpeed() == 1) {
			if (OI.valueShooterSpeed < 1) {
				OI.valueShooterSpeed = OI.valueShooterSpeed + 0.02;
			}
		} else if (OI.changeShooterSpeed() == 2) {
			if (OI.valueShooterSpeed > 0) {
				OI.valueShooterSpeed = OI.valueShooterSpeed - 0.02;
			}
		}

		if (OI.cycle() == true) {
			ColorCycle.colorCycleStart();
		} else if (OI.cycle() == false) {
			ColorCycle.colorCycleStop();
		}
	}

	void OICommands() {
		// Scheduler.getInstance().add(new Detector());
		Scheduler.getInstance().add(new DriveWithJoysticks());
	}

	@Override
	public void testPeriodic() {
	}
}