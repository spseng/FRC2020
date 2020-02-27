package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
//import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import frc.robot.OI;


public class Shootball extends Command {
    OI oi = new OI();

    public Shootball() {

    }// divide distance sensor volatge by 0.997

    protected void initialize() {

    }

    protected void execute() {

        if (oi.shoot() == true) {
            for (int shooterCycle = 0; shooterCycle < 200; shooterCycle++) {
                Shooter.initiateShot(oi.valueShooterSpeed);
				if (shooterCycle >= 100) {
					Shooter.loadingShot();
				}
			}
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
