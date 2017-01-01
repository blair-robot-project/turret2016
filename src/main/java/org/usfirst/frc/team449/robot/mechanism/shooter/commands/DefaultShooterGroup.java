package org.usfirst.frc.team449.robot.mechanism.shooter.commands;


import org.usfirst.frc.team449.robot.ReferencingCommandGroup;
import org.usfirst.frc.team449.robot.mechanism.shooter.ShooterSubsystem;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;

/**
 * Created by BlairRobot on 2016-11-13.
 */
public class DefaultShooterGroup extends ReferencingCommandGroup {
	private ShooterOI oi;

	public DefaultShooterGroup(ShooterSubsystem
			                           shooterSubsystem,
	                           ShooterOI oi) {
		super(shooterSubsystem);
		this.oi = oi;

		requires(shooterSubsystem);

		//addSequential(new DefaultFlywheel(shooterSubsystem, oi));
		addSequential(new IntakeBall(shooterSubsystem), 1);
		for (int i = 0; i < 5; i++) {
			addSequential(new DecelerateFlywheel(shooterSubsystem), 5);
			addSequential(new AccelerateFlywheel(shooterSubsystem), 5);
		}
	}
}
