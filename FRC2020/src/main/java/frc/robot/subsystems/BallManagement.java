package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class BallManagement extends Subsystem {

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    public static void harvesterForward() {
        RobotMap.Harvester.set(0.4);
    }

    public static void harvesterBackward() {
        RobotMap.Harvester.set(-0.4);
    }

    public static void harvesterStop() {
        RobotMap.Harvester.set(0);
    }

    public static void loaderForward() {
        RobotMap.Loader.set(1);
    }

    public static void loaderBackward() {
        RobotMap.Loader.set(-1.0);
    }

    public static void loaderStop() {
        RobotMap.Loader.set(0);
    }

    public static void conveyorForward() {
        RobotMap.Conveyor.set(1);
    }

    public static void conveyorBackward() {
        RobotMap.Conveyor.set(-1);
    }

    public static void conveyorStop() {
        RobotMap.Conveyor.set(0);
    }
}