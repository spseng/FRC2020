package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class DriveWithJoysticks extends Command {
    OI oi = new OI();

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
