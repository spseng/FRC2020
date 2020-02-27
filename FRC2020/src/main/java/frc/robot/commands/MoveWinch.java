package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.Winch;
import frc.robot.OI;

public class MoveWinch extends Command {
    OI oi = new OI();

    public MoveWinch() {
    }

    protected void initialize() {
    }

    protected void execute() {
        // Passes OI input into drive train

        double speed = oi.winch();

        if (speed > 0.2 && !RobotMap.bottomLimitSwitch.get()) {
            Winch.setSpeed(-1.0);
        }
        else if ( speed < -0.2 ) {
            Winch.setSpeed(1.0);
        }
        else {
            Winch.winchStop();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Winch.winchStop();
    }
    
    protected void interrupted() {
        Winch.winchStop();
    }
}
