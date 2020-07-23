package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ColorCycle;
import frc.robot.OI;

public class ColorCycleController extends Command {
    OI oi = new OI();

    public ColorCycleController() {
    }

    protected void initialize() {
    }

    protected void execute() {
		if (oi.cycle() == true) {
			ColorCycle.colorCycleStart();
		} else if (oi.cycle() == false) {
	    	ColorCycle.colorCycleStop();
		} else if (ColorCycle.colorCycleValue == 3) {
            end();
            try { 
                Thread.sleep(250); 
            } 
            catch (Exception e) { 
                System.out.println(e); 
            }
        } 
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        ColorCycle.colorCycleStop();
    }

    protected void interrupted() {
        ColorCycle.colorCycleStop();
    }
}