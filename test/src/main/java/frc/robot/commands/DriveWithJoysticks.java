/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveWithJoysticks extends Command {
	OI oi = Robot.m_oi;
	
    public DriveWithJoysticks() {
    	requires(Robot.driveTrain);// drivetrain is an instance of our Drivetrain subsystem
    }

    protected void initialize() {
    }

    /*
     * execute() - In our execute method we call a tankDrive method we have created in our subsystem. method takes two speeds as a parameter which we get from methods in the OI class.
     * These methods abstract the joystick objects so that if we want to change how we get the speed later we can do so without modifying our commands
     * (for example, if we want the joysticks to be less sensitive, we can multiply them by .5 in the getLeftSpeed method and leave our command the same).
     */
    protected void execute() {
    	Robot.driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
    }

    /*
     * isFinished - Our isFinished method always returns false meaning this command never completes on it's own. The reason we do this is that this command will be set as the default command for the subsystem. This means that whenever the subsystem is not running another command, it will run this command. If any other command is scheduled it will interrupt this command, then return to this command when the other command completes.
     */
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    // very important that motors get stopped when this command is temporarily interrupted
    protected void interrupted() {
    	Robot.driveTrain.stop();
    }
}
