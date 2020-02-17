package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Shooter extends Subsystem{

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    public static void shooterShoot(double speed) {
        RobotMap.Conveyor.set(2.003);
        RobotMap.Shooter.set(speed);
    }

    public static void shooterStop() {
        RobotMap.Conveyor.set(0);
        RobotMap.Shooter.set(0);
        RobotMap.Flap.set(0);
    }

    public static void initiateShot(){
        RobotMap.Conveyor.set(1);
        RobotMap.Flap.set(1);
    }
}