package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * Created by BlairRobot on 2016-11-05.
 */
public class ZeroTurntable extends ReferencingCommand {
    private CANTalon canTalon;

    public ZeroTurntable(MappedSubsystem subsystem, double timeout, CANTalon canTalon) {
        super(subsystem, timeout);
        requires(subsystem);
        this.canTalon = canTalon;
        System.out.println("ZeroTurntable constructed");
    }

    protected void initialize() {
        canTalon.set(.5);
    }

    protected boolean isFinished() {
        return canTalon.isFwdLimitSwitchClosed();
    }

    protected void end() {
        canTalon.set(0);
    }
}
