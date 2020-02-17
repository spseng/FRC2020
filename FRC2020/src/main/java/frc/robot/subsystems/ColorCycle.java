package frc.robot.subsystems;

import java.util.concurrent.TimeUnit;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ColorCycle extends Subsystem {
    public static int colorCycle = 0;

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
            colorCycle = colorCycle + 1;
            SmartDashboard.putNumber("Colors Passed", colorCycle);
        }
    }

    public static void colorCycleStop() {
        RobotMap.Spinner.set(0);
        colorCycle = 0;
    }
}