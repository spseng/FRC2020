package frc.robot.commands;

import frc.robot.OI;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {
    OI oi = Robot.m_oi;

    public MoveElevator() {
        requires(Robot.elevator);
    }

    protected void initialize() {
    }

    protected void execute() {
        // Passes OI into elevator
        Robot.elevator.moveElevator(oi.getElevatorSpeed());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
