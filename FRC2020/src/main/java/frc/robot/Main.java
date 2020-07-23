/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

//This class looks pretty important but there's really not a whole lot to do here.
public final class Main {
	private Main() {
	}
	public static void main(String... args) {
    	RobotBase.startRobot(Robot::new);
	}
}
