package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class DriveWithJoysticks extends Command {
    OI oi = new OI();

    public DriveWithJoysticks() {
        //This allows only the current command to use the subsystem. 
		//Mainly used when the subsystem has a physicall device like a motor controller
        requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() { //Runs when the command is called by the Scheduler
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
