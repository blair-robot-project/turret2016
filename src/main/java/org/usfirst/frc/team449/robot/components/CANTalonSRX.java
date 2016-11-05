package org.usfirst.frc.team449.robot.components;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * Created by BlairRobot on 2016-11-05.
 */
public class CANTalonSRX extends Component {

    CANTalon canTalon;

    public CANTalonSRX(int port, CANTalon.FeedbackDevice
            feedbackDevice, boolean reverseSensor,
                       boolean reverseOutput, double
                               nominalOutVoltage, double
                               peakOutVoltage, int
                               profile, double kP, double
                               kI, double kD, double kF,
                       boolean fwdLimNormOpen, boolean
                               revNormOpen, boolean
                               fwdLimEnabled, boolean
                               revLimEnabled, boolean
                               fwdSoftLimEnabled, double
                               fwdSoftLimVal, boolean
                               revSoftLimEnabled, double
                               revSoftLimVal) {
        canTalon = new CANTalon(port);
        canTalon.setFeedbackDevice(feedbackDevice);
        canTalon.reverseSensor(reverseSensor);
        canTalon.reverseOutput(reverseOutput);
        canTalon.configNominalOutputVoltage
                (+nominalOutVoltage, -nominalOutVoltage);
        canTalon.configPeakOutputVoltage(+peakOutVoltage,
                0); // TODO figure out why this is zero
        canTalon.setProfile(profile);
        canTalon.setP(kP);
        canTalon.setI(kI);
        canTalon.setD(kD);
        canTalon.setF(kF);
        canTalon.ConfigFwdLimitSwitchNormallyOpen(fwdLimNormOpen);
        canTalon.ConfigRevLimitSwitchNormallyOpen
                (revNormOpen);
        canTalon.enableLimitSwitch(fwdLimEnabled,
                revLimEnabled);
        canTalon.enableForwardSoftLimit(fwdSoftLimEnabled);
        canTalon.setForwardSoftLimit(fwdSoftLimVal);
        canTalon.enableReverseSoftLimit(revSoftLimEnabled);
        canTalon.setReverseSoftLimit(revSoftLimVal);
    }

    public void zero() {
        canTalon.setEncPosition(0);
    }

    public double getOutputVoltage() {
        return canTalon.getOutputVoltage();
    }

    public double getEncVelocity() {
        return canTalon.getEncVelocity();
    }

    public double getEncPosition() {
        return canTalon.getEncPosition();
    }

    public double getClosedLoopError() {
        return canTalon.getClosedLoopError();
    }

    public double getError() {
        return canTalon.getError();
    }

    public void setControlMode(CANTalon.TalonControlMode
                                       mode) {
        canTalon.changeControlMode(mode);
    }

    public void setByMode(double sp) {
        canTalon.set(sp);
    }

    @Override
    public boolean getInverted() {
        return false;
    }

    @Override
    public void setInverted(boolean b) {
    }
}
