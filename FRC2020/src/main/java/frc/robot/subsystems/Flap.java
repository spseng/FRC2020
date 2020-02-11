package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Flap extends Subsystem{

    public boolean flapState = false;
    //status of flap; false => closed, true => open

    public void initDefaultCommand() {
    }

    public void move(){
        if (flapState == false){
            //move flap
            RobotMap.Flap.set(0.75);
            flapState = true;
        }
        else{
            //flapState is true/open
            //move flap
            RobotMap.Flap.set(-0.75);
            //change values based on actual robot
            flapState = false;
        } 
    }
}