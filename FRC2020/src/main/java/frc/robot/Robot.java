/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.LiftGrabber;
import frc.robot.commands.autonomous;
import frc.robot.subsystems.*;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	XboxController xbox = RobotMap.xboxController;
	Joystick leftstick = RobotMap.leftJoystick;
	Joystick rightstick = RobotMap.rightJoystick;
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static OI m_oi;
	NetworkTableValue turn;
	NetworkTableInstance inst = NetworkTableInstance.getDefault();
	NetworkTable table = inst.getTable("datatable");
	double degree = 0;
	boolean Xon = false;
	boolean toggle = false;
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 * 
	 * Note that its ok to initialize the subsystems here because their commands won't be
	 * scheduled by the Scheduler until teleopPeriodic starts
	 */
	@Override
	public void robotInit() {
		
		
		CameraServer.getInstance().startAutomaticCapture();
		driveTrain = new DriveTrain();
		elevator = new Elevator();
		grabber = new Grabber();
		m_oi = new OI();
		RobotMap.compressor.setClosedLoopControl(true);
		RobotMap.Gyro1.calibrate();
		//m_chooser.addDefault("Default autonomous", new ButtonSelect());
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		while(turn == null) {
			turn = table.getEntry("turn").getValue();
			System.out.println("DATA NOT FOUND");
		}
		turn = table.getEntry("turn").getValue();
		degree = turn.getDouble();
		System.out.println("OUTPUT == "+degree);
		Robot.driveTrain.tankDrive(degree/5,degree/5);
		SmartDashboard.putNumber("DistanceVal", RobotMap.dist1.getValue());
		//Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		SmartDashboard.putBoolean("Limit Switch 1", RobotMap.limitSwitch1.get());
		SmartDashboard.putBoolean("Limit Switch 2", RobotMap.limitSwitch2.get());
		SmartDashboard.putNumber("Gyro1 angle:", RobotMap.Gyro1.getAngle());
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		Scheduler.getInstance().add(new DriveWithJoysticks());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic(){
		if (xbox.getAButtonPressed()) {
			Xon = false;
			//Scheduler.getInstance().add(new autonomous(0));
		}
		if (xbox.getXButtonPressed() && Xon == false) {
			Scheduler.getInstance().add(new autonomous(1));
			Xon = true;
		}
		if (leftstick.getTriggerPressed() && toggle == false) {
			Scheduler.getInstance().add(new autonomous(1));
			toggle = true;
		}
		if (leftstick.getTriggerReleased() && toggle == true) {
			Scheduler.getInstance().add(new autonomous(0));
			toggle = false;
		}
		if (xbox.getBButtonPressed()) {
			Scheduler.getInstance().add(new autonomous(2));
		}
		if (rightstick.getTriggerPressed() && toggle == false) {
			Scheduler.getInstance().removeAll();
			Scheduler.getInstance().add(new autonomous(2));
			toggle = true;
		}
		if (rightstick.getTriggerReleased()) {
			//Scheduler.getInstance().removeAll();
			Scheduler.getInstance().add(new autonomous(0));
			Scheduler.getInstance().add(new DriveWithJoysticks());
			toggle = false;
		}
		Scheduler.getInstance().run();
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
