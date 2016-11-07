package org.usfirst.frc.team449.robot.mechanism.turntable.commands;


import org.usfirst.frc.team449.robot.ReferencingCommandGroup;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurnTableOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class DefaultTurntableGroup extends ReferencingCommandGroup {
    private TurnTableOI oi;
    public DefaultTurntableGroup(TurntableSubsystem
                                         turntableSubsystem,
                                 TurnTableOI oi) {
        super(turntableSubsystem);
        this.oi = oi;

        addSequential(new BangCalibrate
                (turntableSubsystem, 10));
        addSequential(new DefaultTurn(turntableSubsystem,
                oi));
    }
}
