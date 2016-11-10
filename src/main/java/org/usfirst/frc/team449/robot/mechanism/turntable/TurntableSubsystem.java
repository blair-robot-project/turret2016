package org.usfirst.frc.team449.robot.mechanism.turntable;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .commands.DefaultTurntableGroup;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Turntable subsystem
 */
public class TurntableSubsystem extends MappedSubsystem {
    /**
     * Talon speed controller used to turn the turntable
     */
    private CANTalonSRX canTalonSRX;
    /**
     * OI to get throttle from
     */
    private TurntableOI oi;

    /**
     * Creates a mapped subsystem and sets its map
     *
     * @param map the map of constants relevant to this
     *            subsystem
     */
    public TurntableSubsystem(RobotMap map, TurntableOI
            oi) {
        super(map);
        TurntableMap turntableMap = (TurntableMap) map;
        this.oi = oi;
        canTalonSRX = new CANTalonSRX(turntableMap.canTalonSRXMap);
        System.out.println("TurntableSubsystem " +
                "constructed");
    }

    /**
     * Reset the encoder tick counter to a set value
     * @param pos value to set the tick counter to
     */
    public void setEncPos(int pos) {
        canTalonSRX.setEncPos(pos);
    }

    /**
     * @return encoder tick count
     */
    public double getEncPosition() {
        return canTalonSRX.getEncPosition();
    }

    /**
     * Feed a number to the speed controller. Either
     * voltage command or PID setpoint
     * @param sp
     */
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
        System.out.println("TurntableSubsystem " +
                "initDefaultCommand started");
        setDefaultCommand(new DefaultTurntableGroup(this,
         oi       ));
        System.out.println("TurntableSubsystem " +
                "initDefaultCommand finished");
    }
}
