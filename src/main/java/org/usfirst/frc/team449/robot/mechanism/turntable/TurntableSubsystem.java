package org.usfirst.frc.team449.robot.mechanism.turntable;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .commands.BangCalibrate;

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
        canTalonSRX = new CANTalonSRX(turntableMap.canTalonSRXMap);
    }

    public void setEncPos(int pos) {
        canTalonSRX.setEncPos(pos);
    }

    public double getEncVelocity() {
        return canTalonSRX.getEncVelocity();
    }

    public double getEncPosition() {
        return canTalonSRX.getEncPosition();
    }

    public void setByMode(double sp) {
        canTalonSRX.setByMode(sp);
    }

    public void setControlMode(CANTalon.TalonControlMode mode){
        canTalonSRX.setControlMode(mode);
    }

    public boolean isFwdSwitchClosed(){
        return canTalonSRX.isFwdSwitchClosed();
    }

    public boolean isRevSwitchClosed(){
        return canTalonSRX.isRevSwitchClosed();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new BangCalibrate(this, 10));
    }
}
