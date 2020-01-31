package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Shooter extends Subsystem{

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    public void shoot(double speed){
        RobotMap.Shooter.set(speed);
    }

    public void stop(){
        RobotMap.Shooter.set(0);
    }
//Shooter code here

}