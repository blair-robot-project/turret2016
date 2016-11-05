package org.usfirst.frc.team449.robot.mechanism.turntable;

import edu.wpi.first.wpilibj.CANTalon;
import org.json.JSONObject;
import org.usfirst.frc.team449.robot.RobotMap;
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

    public static class CANTalonSRXMap extends RobotMap
            .MapObject {
        public int port;
        public CANTalon.FeedbackDevice feedbackDevice;
        public boolean reverseSensor;
        public boolean reverseOutput;
        public double nominalOutVoltage;
        public double peakOutVoltage;
        public int profile;
        public double kP;
        public double kI;
        public double kD;
        public double kF;
        public boolean fwdLimNormOpen;
        public boolean revLimNormOpen;
        public boolean fwdLimEnabled;
        public boolean revLimEnabled;
        public boolean fwdSoftLimEnabled;
        public double fwdSoftLimVal;
        public boolean revSoftLimEnabled;
        public double revSoftLimVal;

        /**
         * This creates a Map based on the
         * <code>JSONObject</code> given to it
         * and a path down to this object
         *
         * @param json      the <code>JSONObject</code>
         *                  containing the values for this
         *                  object
         * @param objPath   the path to find this object
         *                  in the
         *                  <code>JSONObject</code>
         * @param enclosing <code>Class</code> one up
         *                  from this <code>MapObject</code>
         */
        public CANTalonSRXMap(JSONObject json, String objPath, Class enclosing) {
            super(json, objPath, enclosing);
        }
    }
}
