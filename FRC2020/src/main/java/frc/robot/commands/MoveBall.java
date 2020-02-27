package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.BallManagement;

public class MoveBall extends Command {
    OI oi = new OI();

    public MoveBall() {
    }

    protected void initialize() {
    }

    protected void execute() {
		if (oi.getXboxLeftBumper() == true) {
			if (oi.harvester() == true) {
				BallManagement.harvesterBackward();
			} else {
				BallManagement.harvesterStop();
			}

			if (oi.conveyor() == true) {
				BallManagement.conveyorBackward();
			} else {
				BallManagement.conveyorStop();
			}

			if (oi.loader() == true) {
				BallManagement.loaderForward();
			} else {
				BallManagement.loaderStop();
			}

		} else {
			if (oi.harvester() == true) {
				BallManagement.harvesterForward();
			} else {
				BallManagement.harvesterStop();
			}

			if (oi.conveyor() == true) {
				BallManagement.conveyorForward();
			} else {
				BallManagement.conveyorStop();
			}

			if (oi.loader() == true) {
				BallManagement.loaderBackward();
			} else {
				BallManagement.loaderStop();
			}
		}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
		BallManagement.harvesterStop();
		BallManagement.conveyorStop();
		BallManagement.loaderStop();
	}
	
	protected void interrupted() {
		BallManagement.harvesterStop();
		BallManagement.conveyorStop();
		BallManagement.loaderStop();
	}
}