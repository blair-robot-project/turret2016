package org.usfirst.frc.team449.robot.mechanism.turntable
        .commands;


import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;


/**
 * Created by BlairRobot on 2016-11-05.
 */
public class BangCalibrate extends ReferencingCommand {
    private boolean finished;
    private boolean actuallyFinished;
    private boolean hitLeft;

    private TurntableSubsystem turntableSubsystem;

    public BangCalibrate(TurntableSubsystem turntableSubsystem, double timeout) {
        super(turntableSubsystem, timeout);
        requires(Robot.turntableSubsystem);
        this.turntableSubsystem = turntableSubsystem;
        finished = false;
        hitLeft = false;
        actuallyFinished = false;
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
        } else if (!finished) {
            turntableSubsystem.setByMode(-0.5);
            if (turntableSubsystem.isRevSwitchClosed()) {
                finished = true;
                turntableSubsystem.setByMode(0);
                turntableSubsystem.setEncPos((int) (turntableSubsystem.getEncPosition() / 2));
                turntableSubsystem.setLimit((long) (Math.abs(turntableSubsystem.getEncPosition()) * 0.49));
                System.out.println("FULL SWEEP " + turntableSubsystem.getEncPosition());
            }
        } else if (finished && Math.abs(turntableSubsystem.getEncPosition()) > 1000) {
            turntableSubsystem.setByMode(0.5);
        } else {
            actuallyFinished = true;
            System.out.println("ACTUALLY FINISHED");
        }
        turntableSubsystem.log();
    }

    @Override
    protected boolean isFinished() {
        return actuallyFinished;
    }

    @Override
    protected void end() {
        System.out.println("BANG CALIBRATE END POS " + turntableSubsystem.getEncPosition());
        System.out.println("BangCalibrate end");
    }

    @Override
    protected void interrupted() {
        System.out.print("BangCalibrate interrupted");
    }
}
