package frc.robot.subsystems;

import java.util.concurrent.TimeUnit;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import frc.robot.RobotMap;

public class ColorCycle extends Subsystem {
    public static double colorCycleValue = 0;
    public static int colorsPassedValue = 0;

    public void initDefaultCommand() {
        // nice
    }

    public static void colorCycleStart() {
        RobotMap.Spinner.set(2.003);
        Color oneColor = RobotMap.colorSensor.getColor();
        ColorMatchResult firstColor = RobotMap.colorMatcher.matchClosestColor(oneColor);

        try {
            TimeUnit.SECONDS.sleep(1 / 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Color twoColor = RobotMap.colorSensor.getColor();
        ColorMatchResult secondColor = RobotMap.colorMatcher.matchClosestColor(twoColor);

        if (firstColor != secondColor) {
            colorCycleValue = colorCycleValue + 0.125;
            colorsPassedValue = colorsPassedValue + 1;
        }
    }

    public static void colorCycleStop() {
        RobotMap.Spinner.set(0);
        colorCycleValue = 0;
    }
}