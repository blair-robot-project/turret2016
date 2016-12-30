package org.usfirst.frc.team449.robot.mechanism.shooter;

import org.usfirst.frc.team449.robot.components.maps.CANTalonSRXMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismMap;

/**
 * Created by Blair Robot Project on 11/10/2016.
 */
public class ShooterMap extends MechanismMap {
	public CANTalonSRXMap intakeMap;
	public CANTalonSRXMap flywheelMap;

	/**
	 * Creates a new Mechanism Map based on the configuration in the given message.
	 * Any maps in here are to be shared across all Shooter subsystems.
	 *
	 * @param message The protobuf message with the data for this object.
	 */
	public ShooterMap(maps.org.usfirst.frc.team449.robot.mechanism.shooter.ShooterMap.Shooter message) {
		super(message.getSuper());
		intakeMap = new CANTalonSRXMap(message.getIntake());
		flywheelMap = new CANTalonSRXMap(message.getFlywheel());
	}

}
