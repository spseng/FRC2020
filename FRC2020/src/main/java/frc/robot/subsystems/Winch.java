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
    }

    // winch down
    public static void winchDown() {
        RobotMap.Winch.set(-1.0);
    }

    public static void winchStop() {
        RobotMap.Winch.set(0.0);
    }
}