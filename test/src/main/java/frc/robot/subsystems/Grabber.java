package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.LiftGrabber;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Solenoid; 
/**
 *
 */
public class Grabber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void toggleCompressor() {
		boolean closedLoop = RobotMap.compressor.getClosedLoopControl();
		if(closedLoop) {
			RobotMap.compressor.setClosedLoopControl(false);
			SmartDashboard.putBoolean("compressor state (false is off): ", false);
		}
		else {
			RobotMap.compressor.setClosedLoopControl(true);
			SmartDashboard.putBoolean("compressor state (false is off): ", true);
		}
	}
	
	public void open() {
		RobotMap.grabber.set(false);
		SmartDashboard.putString("Grabber is: ", "open");
	}
	
	public void close() {
		SmartDashboard.putString("Grabber is: ", "closed");
		RobotMap.grabber.set(true);
	}
	
	public void moveGrabber(double speed) {
		if(speed<-0.5) {
			speed = -0.5;
		}
		RobotMap.thirdTalon.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftGrabber());
	}
}


/*
   //pneumatics claw     

   
   Solenoid clawopen = new Solenoid(0);
   Solenoid clawclose= new Solenoid(1);
   clawopen.set(true);

if (xbox.getRawBumperpressed(hand.kright)==true  && clawopen.get()==false)
{
	clawopen.set(true);
	clawclose.set(false);

}
else if (xbox.getRawBumperpressed(hand.kleft)==true && clawopen.get()==true)
{
	clawclose.set(true);
	clawopen.set(false);
}
else
{
	clawopen.set(false);
	clawclose.set(false);
}
*/

//claw lifter

//_ThirdTalon.set(xbox.getRawAxis(1));


