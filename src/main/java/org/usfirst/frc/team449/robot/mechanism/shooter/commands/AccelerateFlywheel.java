package org.usfirst.frc.team449.robot.mechanism.shooter.commands;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.shooter.ShooterSubsystem;

/**
 * Created by Blair Robot Project on 11/10/2016.
 */
public class AccelerateFlywheel extends ReferencingCommand {

	private ShooterSubsystem shooterSubsystem;

	/**
	 * <p>
	 * Instantiates a new <code>ReferencingCommand</code> with a given <code>Robot.java</code> class. This is used by
	 * build season code commands calling these library commands with a separate <code>Robot.java</code> not in the
	 * 449 central repo.
	 * </p>
	 *
	 * @param subsystem the subsystem that the <code>ReferencingCommand</code> belongs to
	 */
	public AccelerateFlywheel(MappedSubsystem subsystem) {
		super(subsystem);
		shooterSubsystem = (ShooterSubsystem) subsystem;
		System.out.println("AccelerateFlywheel constructed");
	}

	@Override
	protected void initialize() {
		shooterSubsystem.flywheel.setSpeed(50);
		shooterSubsystem.setAccelerated(true);
		System.out.println("AccelerateFlywheel init");
	}

	@Override
	protected void execute() {
		shooterSubsystem.logData(50);
		System.out.println("AccelerateFlywheel executed");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
//		shooterSubsystem.flywheel.setSpeed(0.0);
		System.out.println("AccelerateFlywheel end");
	}

	@Override
	protected void interrupted() {
		shooterSubsystem.flywheel.setSpeed(0.0);
		System.out.println("AccelerateFlywheel interrupted");
	}
}
