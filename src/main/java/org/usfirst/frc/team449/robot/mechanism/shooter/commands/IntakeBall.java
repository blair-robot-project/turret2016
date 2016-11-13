package org.usfirst.frc.team449.robot.mechanism.shooter.commands;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.shooter.ShooterSubsystem;

/**
 * Created by Blair Robot Project on 11/10/2016.
 */
public class IntakeBall extends ReferencingCommand{

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
    public IntakeBall(MappedSubsystem subsystem) {
        super(subsystem);
        shooterSubsystem = (ShooterSubsystem) subsystem;
        System.out.println("IntakeBall constructed");
    }

    @Override
    protected void initialize(){
        shooterSubsystem.setIntakeControlMode(CANTalon.TalonControlMode.PercentVbus);
        System.out.println("IntakeBall init");
    }

    @Override
    protected void execute(){
        shooterSubsystem.setIntakeByMode(0.2);
        shooterSubsystem.setIntaking(true);
        System.out.println("IntakeBall executed");
    }

    @Override
    protected boolean isFinished(){
        return true;
    }

    @Override
    protected void end(){
        System.out.println("IntakeBall end");
    }

    @Override
    protected void interrupted(){
        System.out.println("IntakeBall interrupted");
    }
}
