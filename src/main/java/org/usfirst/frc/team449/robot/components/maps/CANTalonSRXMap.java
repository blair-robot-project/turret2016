package org.usfirst.frc.team449.robot.components.maps;

import org.usfirst.frc.team449.robot.MapObject;

/**
 * A map for a CANTalonSRX motor controller.
 */
public class CANTalonSRXMap  extends MapObject {
    public int port;
    public int feedbackDevice;
    public boolean reverseSensor;
    public boolean reverseOutput;
    public boolean isInverted;
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
    public boolean brakeMode;


    public CANTalonSRXMap(maps.org.usfirst.frc.team449.robot.components.CANTalonSRXMap.CANTalonSRX message) {
        super(message);
        port = message.getPort();
        feedbackDevice = message.getFeedbackDevice();
        reverseSensor = message.getReverseSensor();
        reverseOutput = message.getReverseOutput();
        isInverted = message.getIsInverted();
        nominalOutVoltage = message.getNominalOutVoltage();
        peakOutVoltage = message.getPeakOutVoltage();
        profile = message.getProfile();
        kP = message.getKP();
        kI = message.getKI();
        kD = message.getKD();
        kF = message.getKF();
        fwdLimNormOpen = message.getFwdLimNormOpen();
        revLimNormOpen = message.getRevLimNormOpen();
        fwdLimEnabled = message.getFwdLimEnabled();
        revLimEnabled = message.getRevLimEnabled();
        fwdSoftLimEnabled = message.getFwdSoftLimEnabled();
        revSoftLimEnabled = message.getRevSoftLimEnabled();
        fwdSoftLimVal = message.getFwdSoftLimVal();
        revSoftLimVal = message.getRevSoftLimVal();
        brakeMode = message.getBrakeMode();
    }
}
