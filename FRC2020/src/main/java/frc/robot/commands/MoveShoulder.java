package frc.robot.commands;

import frc.robot.OI;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveShoulder extends Command {
    OI oi = Robot.m_oi;

    public MoveShoulder() {
        requires(Robot.shoulder);
    }

    protected void initialize() {
    }

    protected void execute() {
        // Passes OI into elevator
        Robot.shoulder.moveShoulder(oi.getBumpers());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
