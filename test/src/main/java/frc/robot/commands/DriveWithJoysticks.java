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

public class DriveWithJoysticks extends Command {
    OI oi = Robot.m_oi;

    public DriveWithJoysticks() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
        // Passes OI input into drive train
        Robot.driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
    
    protected void interrupted() {
        Robot.driveTrain.stop();
    }
}
