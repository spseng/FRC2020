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
import frc.robot.commands.MoveBall;
import frc.robot.commands.ColorCycleController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Winch;
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
		//Instantiate subsystems here. 
		//Subsystem classes should only preform basic controls

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

	//These are functions for the Autonomous period
	@Override
	public void autonomousInit() {
		timer.start();
	}

	@Override
	public void autonomousPeriodic() {

		boolean drive = true;
		double time = 2.0;
		double time2 = 4.0;
		double currentDistance = RobotMap.distanceSensor.getValue() * 0.125;

		if (currentDistance < 40){
			drive = false;
			Robot.driveTrain.stop();
		}

		if ((timer.get() < time)){ // the number represents seconds
			if (drive){
				//Robot.driveTrain.tankDrive(0.5, 0.5); //change speed
				RobotMap.LeftMotor.set(-0.5);
				RobotMap.RightMotor.set(0.45);
			}
		}

		if ( time < timer.get() && timer.get() < time2) {
			RobotMap.LeftMotor.set(0.5);
			RobotMap.RightMotor.set(0.45);	
		}
	
		if (timer.get() > time2){
			Robot.driveTrain.stop();
		}
	}

	//Run at the beginning of the Teleop Period
	@Override
	public void teleopInit() {
		// Init relay
		RobotMap.GreenLED.set(Relay.Value.kOn);
		RobotMap.GreenLED.set(Relay.Value.kReverse);

		Scheduler.getInstance().add(new Shootball());
		Scheduler.getInstance().add(new MoveWinch());
		Scheduler.getInstance().add(new MoveBall());
		Scheduler.getInstance().add(new ColorCycleController());
		Scheduler.getInstance().add(new DriveWithJoysticks());
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
		SmartDashboard.putNumber("ShooterSpeed", frc.robot.OI.valueShooterSpeed);
		SmartDashboard.putNumber("Rotations Completed", ColorCycle.colorCycleValue);
		SmartDashboard.putNumber("Colors Passed", ColorCycle.colorsPassedValue);
		SmartDashboard.putNumber("Bottom Limit Switch", RobotMap.bottomLimitSwitch.get() ? 1:0);
		SmartDashboard.putNumber("Distance sensor value(in)", RobotMap.distanceSensor.getValue() * 0.125);
		SmartDashboard.putNumber("Encoder velocity(rpm)", RobotMap.CANCoder.getVelocity() / -360.0);
	}

	@Override
	public void teleopPeriodic() {
		//After a command is added to the Scheduler, its execute function is 
		//called at each iteration of teleopPeriodic
		Scheduler.getInstance().run();

		if (OI.changeShooterSpeed() == 1) {
			if (frc.robot.OI.valueShooterSpeed < 1) {
                frc.robot.OI.valueShooterSpeed = frc.robot.OI.valueShooterSpeed + 0.02;
			}
		} else if (OI.changeShooterSpeed() == 2) {
			if (frc.robot.OI.valueShooterSpeed > 0) {
                frc.robot.OI.valueShooterSpeed = frc.robot.OI.valueShooterSpeed - 0.02;
			}
        }
	}

	@Override
	
	public void testPeriodic() {
	}
}