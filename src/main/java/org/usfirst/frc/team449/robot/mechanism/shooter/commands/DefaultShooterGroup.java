package org.usfirst.frc.team449.robot.mechanism.shooter.commands;



import org.usfirst.frc.team449.robot.ReferencingCommandGroup;
import org.usfirst.frc.team449.robot.mechanism.shooter.ShooterSubsystem;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;

/**
 * Created by BlairRobot on 2016-11-13.
 */
public class DefaultShooterGroup  extends ReferencingCommandGroup {
    private ShooterOI oi;

    public DefaultShooterGroup(ShooterSubsystem
                                         shooterSubsystem,
                               ShooterOI oi) {
        super(shooterSubsystem);
        this.oi = oi;

        addSequential(new DefaultFlywheel
                (shooterSubsystem, oi));
    }
}
