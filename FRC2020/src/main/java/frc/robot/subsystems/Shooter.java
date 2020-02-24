package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Shooter extends Subsystem{

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    //starts up shooter motor and lets it speed up
    public static void initiateShot(double speed) {
        RobotMap.Shooter.set(speed);
    }
    
    //initiates shot, moves conveyorbelt and flap to load ball into shooter system
    public static void loadingShot(){
        RobotMap.Conveyor.set(1);
        RobotMap.Loader.set(-1);
    }

    public static void shooterStop() {
        RobotMap.Shooter.set(0);
    }
}