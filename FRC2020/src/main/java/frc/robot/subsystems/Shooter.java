package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.OI;

public class Shooter extends Subsystem{

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    //starts up shooter motor and lets it speed up
    public static void initiateShot() {
        RobotMap.Shooter.set(OI.valueShooterSpeed);
    }

    public static void shooterStop() {
        RobotMap.Shooter.set(0);
    }
}