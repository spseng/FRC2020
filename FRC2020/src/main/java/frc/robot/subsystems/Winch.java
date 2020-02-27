package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Winch extends Subsystem {

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    // winch up
    public static void winchUp() {
        RobotMap.Winch.set(1.0);
        System.out.println("winchup");
    }

    // winch down
    public static void winchDown() {
        RobotMap.Winch.set(-1.0);
        System.out.println("winchdown");
  
    }

    public static void setSpeed(double speed) {
        RobotMap.Winch.set(speed/2);
    }

    public static void winchStop() {
        RobotMap.Winch.stopMotor();
    }
}