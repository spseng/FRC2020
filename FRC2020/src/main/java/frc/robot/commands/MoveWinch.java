package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
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

        if (speed > 0.0 && !RobotMap.topLimitSwitch.get()) {
            Winch.setSpeed(speed);
        }
        else if ( speed < 0.0 ) {//&& !RobotMap.bottomLimitSwitch.get()) {
            Winch.setSpeed(speed);
        }
        else {
            Winch.winchStop();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.winch.winchStop();
    }
    
    protected void interrupted() {
        Robot.winch.winchStop();
    }
}
