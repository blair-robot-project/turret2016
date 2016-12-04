package org.usfirst.frc.team449.robot.mechanism.turntable
        .commands;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class DefaultTurn extends ReferencingCommand {
    private TurntableOI oi;
    private TurntableSubsystem turntableSubsystem;

    public DefaultTurn(TurntableSubsystem
                               turntableSubsystem,
                       TurntableOI oi) {
        super(turntableSubsystem);
        requires(Robot.turntableSubsystem);
        System.out.println("DefaultTurn start construct");
        this.oi = oi;
        this.turntableSubsystem = turntableSubsystem;
        System.out.println("DefaultTurn constructed");
    }

    @Override
    protected void initialize() {
        turntableSubsystem.setControlMode(CANTalon.TalonControlMode.Position);
        turntableSubsystem.setByMode(0);
        System.out.println("DefaultTurn initialized");
    }

    @Override
    protected void execute() {
        System.out.println("Throttle: " + oi.getTurntableVelocity());
        turntableSubsystem.setByMode(oi.getTurntableVelocity()*turntableSubsystem.getLimit());
        SmartDashboard.putNumber("Throttle", oi.getTurntableVelocity());
        SmartDashboard.putNumber("Turntable Encoder", turntableSubsystem.getEncPosition());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}