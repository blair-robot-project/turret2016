package org.usfirst.frc.team449.robot.mechanism.turntable;

import org.json.JSONObject;
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
    public TurntableMap(JSONObject json) {
        super(json);
    }

}
