package org.usfirst.frc.team449.robot.mechanism.turntable.commands;

import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;


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
			turntableSubsystem.setPercentVbus(0.5);
			if (turntableSubsystem.isFwdSwitchClosed()) {
				hitLeft = true;
				turntableSubsystem.setPos(0);
			}
		} else if (!finished) {
			turntableSubsystem.setPercentVbus(-0.5);
			if (turntableSubsystem.isRevSwitchClosed()) {
				finished = true;
				turntableSubsystem.setPercentVbus(0);
				turntableSubsystem.setPos((int) (turntableSubsystem.getPosition() / 2));
				turntableSubsystem.setLimit((long) (Math.abs(turntableSubsystem.getPosition()) * 0.49));
				System.out.println("FULL SWEEP " + turntableSubsystem.getPosition());
			}
		}
		turntableSubsystem.log();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
		System.out.println("BANG CALIBRATE END POS " + turntableSubsystem.getPosition());
		System.out.println("BangCalibrate end");
	}

	@Override
	protected void interrupted() {
		System.out.print("BangCalibrate interrupted");
	}
}
