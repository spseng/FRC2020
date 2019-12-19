package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NetOutput extends Subsystem {	

	int input;
	NetworkTableInstance net = NetworkTableInstance.getDefault();
	NetworkTable chooser_table = net.getTable("chooser_data");
	NetworkTable ball_table = net.getTable("ball_data");
	NetworkTable tape_table = net.getTable("tape_data");

	public NetOutput(int user_input){
		// Initialize network tables 
		SmartDashboard.putNumber("DetectorScript choice", user_input);

		input = user_input;

		// Kill all processes and start selected script
		chooser(input);
		// Wait for startup 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 	public double[] get_output_of_selected_action() {
		//Value 0 stops all scripts

		SmartDashboard.putNumber("netOutput input", input);
		
		if (input == 1) {
			// Return network table value
			return get_data(tape_table);
		}
		else if (input == 2) {	
			// Return network table value
			return get_data(ball_table);
		}
		
		double [] def = new double [2];
		def[0] = 0;

		return def;
 	}

	public void chooser(int choice){		
		NetworkTableEntry choice_entry = chooser_table.getEntry("choice");
		choice_entry.setDouble(choice);
	}

	public double[] get_data(NetworkTable table){
		double [] def = new double [2];
		String[] netOut = new String[2];
		netOut[0] = table.getEntry("midpoint").getString("None Found");
		netOut[1] = table.getEntry("width").getString("None Found");

		DriverStation.reportError("Midpoint = " + netOut[0], false);
		DriverStation.reportError("Width = " + netOut[1], false);

		SmartDashboard.putString("midpoint", netOut[0]);
		SmartDashboard.putString("width", netOut[1]);
		
		if (netOut[0].contains("B")){
			def[0] = 320;
			def[1] = 0;
		}
		else{
			def = convert(netOut);
		}
		
		return def;
	}
	//This probably doesnt work, maybe
	public double[] convert (String[] string){
		int index;
		double [] p = new double [2];
		double p1;
		double p2;
		if (string[0].contains(",")) {
			index = string[0].indexOf(",");
			p1 = Double.valueOf(string[0].substring(1, index));
			p2 = Double.valueOf(string[1]);
			p[0] = p1;
			p[1] = p2;
		}
		return p;
	}

	@Override
	protected void initDefaultCommand() {

	}
 }