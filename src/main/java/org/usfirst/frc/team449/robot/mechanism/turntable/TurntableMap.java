package org.usfirst.frc.team449.robot.mechanism.turntable;

import org.usfirst.frc.team449.robot.components.maps.CANTalonSRXMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismMap;

/**
 * Created by BlairRobot on 2016-11-05.
 */
public class TurntableMap extends MechanismMap {
	public CANTalonSRXMap canTalonSRXMap;

	/**
	 * creates a new Mechanism Map based on the
	 * configuration in the given json
	 * any maps in here are to be shared across all
	 * mechanism subsystems
	 *
	 * @param json a JSONObject containing the
	 *             configuration for the maps in this
	 *             object
	 */
	public TurntableMap(maps.org.usfirst.frc.team449.robot.mechanism.turntable.TurntableMap.Turntable message) {
		super(message.getSuper());
		canTalonSRXMap = new CANTalonSRXMap(message.getCanTalonSRX());
	}

}
