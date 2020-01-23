package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SetElevator extends Command {

    boolean UoD;

    public SetElevator(boolean type) {
        UoD = type;
        requires(Robot.elevator);
    }

    protected void initialize() {
        
        if (UoD == true) {
            Robot.elevator.moveUp();

        } else {
            Robot.elevator.moveDown();

        }
    }

    protected void execute() {
        Robot.elevator.moveElevator(RobotMap.xboxController.getRawAxis(1));
    }

    protected boolean isFinished() {

        if (UoD == true) {
            return RobotMap.limitSwitchTop.get();

        } else {
            return RobotMap.limitSwitchBottom.get();

        }
    }

    protected void end() {
        Robot.elevator.stop();
    }

    protected void interrupted() {
        end();
    }
}
