package org.usfirst.frc.team449.robot.mechanism.turntable;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .commands.DefaultTurntableGroup;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .ois.TurnTableOI;

/**
 * Created by BlairRobot on 2016-11-05.
 */
public class TurntableSubsystem extends MappedSubsystem {
    private CANTalonSRX canTalonSRX;
    private TurnTableOI oi;

    /**
     * Creates a mapped subsystem and sets its map
     *
     * @param map the map of constants relevant to this
     *            subsystem
     */
    public TurntableSubsystem(RobotMap map, TurnTableOI
            oi) {
        super(map);
        TurntableMap turntableMap = (TurntableMap) map;
        this.oi = oi;
        canTalonSRX = new CANTalonSRX(turntableMap
                .canTalonSRXMap);
        System.out.println("TurntableSubsystem " +
                "constructed");
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

    public void setControlMode(CANTalon.TalonControlMode
                                       mode) {
        canTalonSRX.setControlMode(mode);
    }

    public boolean isFwdSwitchClosed() {
        return canTalonSRX.isFwdSwitchClosed();
    }

    public boolean isRevSwitchClosed() {
        return canTalonSRX.isRevSwitchClosed();
    }

    @Override
    protected void initDefaultCommand() {
        System.out.println("TurntableSubsystem " +
                "initDefaultCommand started");
        setDefaultCommand(new DefaultTurntableGroup(this,
                oi));
        System.out.println("TurntableSubsystem " +
                "initDefaultCommand finished");
    }
}
