package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Shoulder extends Subsystem {

    //Moves the shoulder talon
    public void moveShoulder(int bumper) {

        if (bumper == 1) {
            RobotMap.shoulder.set(1.0);

        } else if (bumper == 2) {
            RobotMap.shoulder.set(-1.0);

        }
        else{
            RobotMap.shoulder.set(0.0);
            
        }
    }

    public void stop() {
        RobotMap.shoulder.set(0.0);
    }

    public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MoveShoulder());
	}
}
