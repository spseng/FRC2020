package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;
import frc.robot.RobotMap;

public class ColorCycle extends Subsystem {
    public static double colorCycleValue = 0;
    public static int colorsPassedValue = 0;
	static Color Blue = ColorMatch.makeColor(0.143, 0.427, 0.429);
	static Color Green = ColorMatch.makeColor(0.197, 0.561, 0.240);
	static Color Red = ColorMatch.makeColor(0.561, 0.232, 0.114);
    static Color Yellow = ColorMatch.makeColor(0.361, 0.524, 0.113);
    static String FirstColor;
    static String SecondColor;
    
    public void initDefaultCommand() {
        // nice
    }

    public static void colorCycleStart() {
        Color match = RobotMap.colorSensor.getColor();
        ColorString(match, FirstColor);

        RobotMap.Spinner.set(1);
        try { 
            Thread.sleep(100); 
        } 
        catch (Exception e) { 
            System.out.println(e); 
        } 

        Color match2 = RobotMap.colorSensor.getColor();
        ColorString(match2, SecondColor);

        if (FirstColor != SecondColor) {
            colorCycleValue = colorCycleValue + 0.125;
            colorsPassedValue = colorsPassedValue + 1;
        }
    }

    public static void colorCycleStop() {
        RobotMap.Spinner.set(0);
        colorCycleValue = 0;
        colorsPassedValue = 0;
    }

    protected static void ColorString(Color colorIn, String stringOut) {
		if (colorIn == Blue) {
			stringOut = "Blue";
		} else if (colorIn == Red) {
			stringOut = "Red";
		} else if (colorIn == Green) {
			stringOut = "Green";
		} else if (colorIn == Yellow) {
			stringOut = "Yellow";
		} else {
			stringOut = "Unknown";
		}
    }
}