package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.OI;


public class Shootball extends Command {
    OI oi = new OI();

    public Shootball() {
    }

    protected void initialize() {
    }

    protected void execute() {
        if (oi.shoot() == true) {
            Shooter.initiateShot();
		} else {
			Shooter.shooterStop();
		}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
    
    protected void interrupted() {
        
    }
}
