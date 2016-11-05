package org.usfirst.frc.team449.robot.mechanism.turntable;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;

/**
 * Created by BlairRobot on 2016-11-05.
 */
public class TurntableSubsystem extends MappedSubsystem {
    CANTalonSRX canTalonSRX;
    /**
     * Creates a mapped subsystem and sets its map
     *
     * @param map the map of constants relevant to this
     *            subsystem
     */
    public TurntableSubsystem(RobotMap map) {
        super(map);
        TurntableMap turntableMap = (TurntableMap) map;
        canTalonSRX = new CANTalonSRX(((TurntableMap)
                map).canTalonSRXMap);
    }

    public void zero() {
        canTalonSRX.zero();
    }

    public double getEncVelocity() {
        return canTalonSRX.getEncVelocity();
    }

    public double getEncPosition() {
        return canTalonSRX.getEncPosition();
    }

    protected void initDefaultCommand() {
    }
}
