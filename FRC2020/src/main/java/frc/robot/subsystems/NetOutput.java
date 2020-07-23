package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NetOutput extends Subsystem {

	// set red is true and blue as false
	int input;
	NetworkTableInstance net = NetworkTableInstance.getDefault();
	NetworkTable team_data = net.getTable("team_data");
	NetworkTable ball_table = net.getTable("ball_data");
	NetworkTable ps_table = net.getTable("ps_data");

	public NetOutput() {

	}

	public void update_choice(int choice) {
		input = choice;
		SmartDashboard.putNumber("DetectorScript choice", choice);
	}

	//Blue: true
	//Red: false
	public void update_team_color(Boolean color) {
		NetworkTableEntry team_color = team_data.getEntry("team_color");
		team_color.setBoolean(color);
	}

	public double[] get_output_of_selected_action() {

		// Value 0 returns nothing
		SmartDashboard.putNumber("DetectorScript choice", input);

		if (input == 1) {
			// Return network table value
			return get_data(ps_table);
		} else if (input == 2) {
			// Return network table value
			return get_data(ball_table);
		}

		// If 0 is received, return 0
		double[] def = new double[3];
		def[0] = 0;

		return def;
	}

	public double[] get_data(NetworkTable table) {
		double[] def = new double[3];
		String[] netOut = new String[2];

		// Get midpoint and width from netwoktables
		netOut[0] = table.getEntry("midpoint").getString("None Found");
		netOut[1] = table.getEntry("width").getString("None Found");

		// If value contains disable constant, pass in default values
		if (netOut[0].contains("B")) {
			def[0] = 320;
			def[1] = 240;
			def[2] = 0;
		}
		// Else, parse input
		else {
			def = convert(netOut);
		}

		// For networktables communication test
		SmartDashboard.putNumber("Detected midpoint x", def[0]);
		SmartDashboard.putNumber("Detected midpoint y", def[1]);
		SmartDashboard.putNumber("Detected width/radius", def[2]);

		return def;
	}

	public double[] convert(String[] string) {
		int index;
		double[] p = new double[3];

		if (string[0].contains(",")) {
			index = string[0].indexOf(",");

			p[0] = Double.valueOf(string[0].substring(1, index));
			p[1] = Double.valueOf(string[0].substring(index + 1, string[0].length() - 2));
			p[2] = Double.valueOf(string[1]);

		}
		return p;
	}

	@Override
	protected void initDefaultCommand() {

	}
}