package org.usfirst.frc.team449.robot.mechanism.shooter;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.components.maps.CANTalonSRXMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismMap;

/**
 * Created by Blair Robot Project on 11/10/2016.
 */
public class ShooterMap extends MechanismMap {
	public CANTalonSRXMap intakeMap;
	public CANTalonSRXMap flywheelMap;

	/**
	 * creates a new Mechanism Map based on the configuration in the given json
	 * any maps in here are to be shared across all mechanism subsystems
	 *
	 * @param json a JSONObject containing the configuration for the maps in this
	 *             object
	 */
	public ShooterMap(JSONObject json) {
		super(json);
	}

}
