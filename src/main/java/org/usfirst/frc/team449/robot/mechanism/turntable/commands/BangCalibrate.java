package org.usfirst.frc.team449.robot.mechanism.turntable
        .commands;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .TurntableSubsystem;


/**
 * Created by BlairRobot on 2016-11-05.
 */
public class BangCalibrate extends ReferencingCommand {
    private boolean finished;
    private boolean hitLeft;

    private TurntableSubsystem turntableSubsystem;

    public BangCalibrate(TurntableSubsystem turntableSubsystem, double timeout) {
        super(turntableSubsystem, timeout);
        requires(Robot.turntableSubsystem);
        this.turntableSubsystem = turntableSubsystem;
        finished = false;
        hitLeft = false;
    }

    @Override
    protected void initialize() {
        System.out.println("BangCalibrate init");
    }

    @Override
    protected void execute() {
        if (!hitLeft) {
            turntableSubsystem
                    .setControlMode(CANTalon
                            .TalonControlMode.PercentVbus);
            turntableSubsystem.setByMode(0.5);
            if (turntableSubsystem.isFwdSwitchClosed()) {
                hitLeft = true;
                turntableSubsystem.setEncPos(0);
            }
        } else {
            turntableSubsystem.setByMode(-0.5);
            if (turntableSubsystem.isRevSwitchClosed()) {
                finished = true;
                turntableSubsystem.setByMode(0);
                turntableSubsystem.setEncPos((int) (turntableSubsystem.getEncPosition() / 2));
                turntableSubsystem.setLimit((long) (Math.abs(turntableSubsystem.getEncPosition()) * 0.49));
                SmartDashboard.putNumber("Limit", turntableSubsystem.getLimit());
            }
        }
        SmartDashboard.putNumber("Turntable Encoder", turntableSubsystem.getEncPosition());
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {
        System.out.println("BangCalibrate end");
    }

    @Override
    protected void interrupted() {
        System.out.print("BangCalibrate interrupted");
    }
}
