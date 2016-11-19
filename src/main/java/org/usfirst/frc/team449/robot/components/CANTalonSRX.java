package org.usfirst.frc.team449.robot.components;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.RobotMap;

/**
 * Created by BlairRobot on 2016-11-05.
 */
public class CANTalonSRX extends Component {

    CANTalon canTalon;

    public CANTalonSRX(RobotMap.CANTalonSRXMap m) {
        System.out.println(m.port);
        canTalon = new CANTalon(m.port);
        canTalon.setFeedbackDevice(CANTalon.FeedbackDevice.valueOf(m.feedbackDevice));
        canTalon.reverseSensor(m.reverseSensor);
        canTalon.reverseOutput(m.reverseOutput);
        canTalon.setInverted(m.isInverted);
        canTalon.configNominalOutputVoltage
                (+m.nominalOutVoltage, -m.nominalOutVoltage);
        canTalon.configPeakOutputVoltage(+m.peakOutVoltage,
        //        0.0f);
                -m.peakOutVoltage);
        canTalon.setProfile(m.profile);
        canTalon.setP(m.kP);
        canTalon.setI(m.kI);
        canTalon.setD(m.kD);
        canTalon.setF(m.kF);
        canTalon.setPID(m.kP, m.kI, m.kD, m.kF, 0, 0, 0);
        canTalon.setProfile(0);
        canTalon.ConfigFwdLimitSwitchNormallyOpen(m.fwdLimNormOpen);
        canTalon.ConfigRevLimitSwitchNormallyOpen
                (m.revLimNormOpen);
        canTalon.enableLimitSwitch(m.fwdLimEnabled,
                m.revLimEnabled);
        canTalon.enableForwardSoftLimit(m.fwdSoftLimEnabled);
        canTalon.setForwardSoftLimit(m.fwdSoftLimVal);
        canTalon.enableReverseSoftLimit(m.revSoftLimEnabled);
        canTalon.setReverseSoftLimit(m.revSoftLimVal);
        canTalon.enableBrakeMode(m.brakeMode);
    }

    public double getFGain() {
        return canTalon.getF();
    }

    public boolean isEnabled() {
        return canTalon.isEnabled();
    }

    public boolean isAlive() {
        return canTalon.isAlive();
    }

    public CANTalon.TalonControlMode getControlMode() {
        return canTalon.getControlMode();
    }

    public void setEncPos(int pos) {
        canTalon.setEncPosition(pos);
    }

    public double getOutputVoltage() {
        return canTalon.getOutputVoltage();
    }

    public double getEncVelocity() {
        //System.out.println("Enc Vel: " + canTalon.getEncVelocity());
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

    public boolean isFwdSwitchClosed(){
        return canTalon.isFwdLimitSwitchClosed();
    }

    public boolean isRevSwitchClosed(){
        return canTalon.isRevLimitSwitchClosed();
    }

    public void setByMode(double sp) {
        canTalon.set(sp);
    }

    public void enableBrakeMode (boolean brake){
        canTalon.enableBrakeMode(brake);
    }

    @Override
    public boolean getInverted() {
        return false;
    }

    @Override
    public void setInverted(boolean b) {
    }
}
