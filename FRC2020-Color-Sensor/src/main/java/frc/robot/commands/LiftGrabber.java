package frc.robot.commands;

import frc.robot.OI;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftGrabber extends Command {
    OI oi = Robot.m_oi;

    public LiftGrabber() {
        requires(Robot.grabber);
    }

    protected void initialize() {
    }

    protected void execute() {
        // Passes OI input into grabber
        Robot.grabber.moveGrabber(oi.getGrabberSpeed());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
