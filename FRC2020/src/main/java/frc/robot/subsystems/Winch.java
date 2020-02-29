package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Winch extends Subsystem {

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    public static void setSpeed(double speed) {
        RobotMap.Winch.set(speed/2);
    }

    public static void winchStop() {
        RobotMap.Winch.stopMotor();
    }
}