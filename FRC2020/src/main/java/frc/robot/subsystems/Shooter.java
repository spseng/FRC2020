package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Shooter extends Subsystem{

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    //starts up shooter motor and lets it speed up
    public static void shooterShoot(double speed) {
        RobotMap.Conveyor.set(0);
        RobotMap.Shooter.set(speed);
    }

    public static void shooterStop() {
        RobotMap.Shooter.set(0);
    }

    //initiates shot, moves conveyorbelt and flap to load ball into shooter system
    public static void initiateShot(){
        RobotMap.Conveyor.set(1);
        RobotMap.Loader.set(1);
    }
}