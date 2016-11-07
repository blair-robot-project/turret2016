package org.usfirst.frc.team449.robot.components;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .TurntableMap;

/**
 * Created by BlairRobot on 2016-11-05.
 */
public class CANTalonSRX extends Component {

    CANTalon canTalon;

    public CANTalonSRX(TurntableMap.CANTalonSRXMap m) {
        canTalon = new CANTalon(m.port);
        canTalon.setFeedbackDevice(m.feedbackDevice);
        canTalon.reverseSensor(m.reverseSensor);
        canTalon.reverseOutput(m.reverseOutput);
        canTalon.configNominalOutputVoltage
                (+m.nominalOutVoltage, -m.nominalOutVoltage);
        canTalon.configPeakOutputVoltage(+m.peakOutVoltage,
                0); // TODO figure out why this is zero
        canTalon.setProfile(m.profile);
        canTalon.setP(m.kP);
        canTalon.setI(m.kI);
        canTalon.setD(m.kD);
        canTalon.setF(m.kF);
        canTalon.ConfigFwdLimitSwitchNormallyOpen(m.fwdLimNormOpen);
        canTalon.ConfigRevLimitSwitchNormallyOpen
                (m.revLimNormOpen);
        canTalon.enableLimitSwitch(m.fwdLimEnabled,
                m.revLimEnabled);
        canTalon.enableForwardSoftLimit(m.fwdSoftLimEnabled);
        canTalon.setForwardSoftLimit(m.fwdSoftLimVal);
        canTalon.enableReverseSoftLimit(m.revSoftLimEnabled);
        canTalon.setReverseSoftLimit(m.revSoftLimVal);
    }

    public void setEncPos(int pos) {
        canTalon.setEncPosition(pos);
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

    public boolean isFwdSwitchClosed(){
        return canTalon.isFwdLimitSwitchClosed();
    }

    public boolean isRevSwitchClosed(){
        return canTalon.isRevLimitSwitchClosed();
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
