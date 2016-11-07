package org.usfirst.frc.team449.robot.mechanism.turntable
        .commands;

import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .ois.TurnTableOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class DefaultTurn extends ReferencingCommand {
    private TurnTableOI oi;
    private TurntableSubsystem turntableSubsystem;

    public DefaultTurn(TurntableSubsystem
                               turntableSubsystem,
                       TurnTableOI oi) {
        super(turntableSubsystem);
        requires(Robot.turntableSubsystem);
        System.out.println("DefaultTurn start construct");
        this.oi = oi;
        this.turntableSubsystem = turntableSubsystem;
        System.out.println("DefaultTurn constructed");
    }

    @Override
    protected void initialize() {
        turntableSubsystem.setByMode(0.0);
        System.out.println("DefaultTurn initialized");
    }

    @Override
    protected void execute() {
        System.out.println("THROTTLE: " + oi
                .getTurntableVelocity());
        turntableSubsystem.setByMode(oi
                .getTurntableVelocity());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}