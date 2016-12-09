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
                -m.peakOutVoltage);
        canTalon.setProfile(m.profile);

        /*
        p = 1023 <edges> * response <unit-less> / maxError <<native> / <100 ms>>

        response = kP   // scale factor to increase by for a given error; set in cfg

        maxError = 3.072559 <<rev>/<sec>> // measured
        maxError = 3.072559 <<rev> / <sec>> * 4096 <<native> / <rev>> * .1 <<sec> / <100 ms>>
        maxError = 3.072559 * 4096 * 0.1 <native> / <100ms>

        p = 1023 * kP / (30.72559 * 4096 * 0.1) <edges> *  <100 ms> / <native>
         */
//        double p = (m.kP * 1023) / (30.72559 * 4096 * 0.1);

        double p = (m.kP * 2048 / 1.4e6);

        /**/
        double i = (m.kI * 1023) / (30.72559 * 4096 * 0.1);

        /**/
        double d = m.kD;

        /*
        f = max output / max speed <native units>
        f = 1023 / (64 rev/sec * 60 sec/min * 1/60 min/sec * 1 / 10 (sec / <100 ms>) * 4096 native / rev)
        f = 1023 / (64 * 60  * 1/60  * 1 / 10 ( / <100 ms>) * 4096 native)
        f = 1023/ (64 / 10 * 4096 (native / <100 ms>))
        */
        double f = (double) (1023.0) / ((double) (m.kF) * (double) (409.6));

        canTalon.setPID(p, i, d, f, 0, 0, 0);
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
        return canTalon.getEncVelocity();
    }

    public double getEncPosition() {
        return canTalon.getEncPosition();
    }

    public double getPosition() {
        return canTalon.getPosition();
    }

    public double getPWPosition() {
        return canTalon.getPulseWidthPosition();
    }

    public double getAnalogPosition() {
        return canTalon.getAnalogInPosition();
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

    public boolean isFwdSwitchClosed() {
        return canTalon.isFwdLimitSwitchClosed();
    }

    public boolean isRevSwitchClosed() {
        return canTalon.isRevLimitSwitchClosed();
    }

    /**
     * Wrapper on the native CAN Talon set method.
     *
     * When controlling using PercentVBUS, -1 <= sp <= 1
     * When controlling using Speed, sp is in RPM (note: revolutions per MINUTE, not second)
     *
     * @param sp setpoint
     */
    public void setByMode(double sp) {
        if(getControlMode() == CANTalon.TalonControlMode.Speed){
            canTalon.set(sp * 60);
        } else {
            canTalon.set(sp);
        }
    }

    public void enableBrakeMode(boolean brake) {
        canTalon.enableBrakeMode(brake);
    }

    public double getSetpoint(){
        return canTalon.getSetpoint();
    }

    @Override
    public boolean getInverted() {
        return false;
    }

    @Override
    public void setInverted(boolean b) {
    }
}
