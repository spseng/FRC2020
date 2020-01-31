package frc.robot.subsystems;

import java.util.concurrent.TimeUnit;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;

public class ColorCycle extends Subsystem{

    boolean toggle = Robot.toggle;

    public void initDefaultCommand() {
        /*
        for (int colorCycle = 0; leftstick.getTriggerPressed() && toggle == false; SmartDashboard
                .putNumber("Colors Passed", colorCycle)) {
            Color oneColor = m_colorSensor.getColor();
            ColorMatchResult firstColor = m_colorMatcher.matchClosestColor(oneColor);
            try {
                TimeUnit.SECONDS.sleep(1 / 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Color twoColor = m_colorSensor.getColor();
            ColorMatchResult secondColor = m_colorMatcher.matchClosestColor(twoColor);
            if (firstColor != secondColor) {
                colorCycle = colorCycle + 1;
            }
        }
        */
    }

}