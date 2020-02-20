package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Robot;

public class ColorCycle extends Subsystem {
    public static double colorCycleValue = 0;
    public static int colorsPassedValue = 0;

    public void initDefaultCommand() {
        // nice
    }

    public static void colorCycleStart() {
        RobotMap.Spinner.set(1);

        while (colorCycleValue < 3) {

            String firstColor = Robot.colorString;
            while (Robot.colorString == firstColor) {
                // nothing
            }

            colorCycleValue = colorCycleValue + 0.125;
            colorsPassedValue = colorsPassedValue + 1;
        }
    }

    public static void colorCycleStop() {
        RobotMap.Spinner.set(0);
        colorCycleValue = 0;
        colorsPassedValue = 0;
    }
}