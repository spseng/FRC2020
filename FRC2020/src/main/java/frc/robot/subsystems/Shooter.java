package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Shooter extends Subsystem{

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    public static void shooterShoot(double speed) {
        RobotMap.Shooter.set(speed);
    }

    public static void shooterStop() {
        RobotMap.Shooter.set(0);
    }
}