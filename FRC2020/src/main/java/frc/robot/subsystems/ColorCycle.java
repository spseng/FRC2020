package frc.robot.subsystems;

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
        Color firstColor = RobotMap.colorSensor.getColor();
        ColorMatchResult matched = RobotMap.colorMatcher.matchClosestColor(firstColor); 
        RobotMap.Spinner.set(1);
        try { 
            Thread.sleep(250); 
        } 
        catch (Exception e) { 
            System.out.println(e); 
        } 
        if (matched != RobotMap.colorMatcher.matchClosestColor(RobotMap.colorSensor.getColor())) {
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