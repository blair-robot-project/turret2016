package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public abstract class ReferencingCommandGroup extends CommandGroup{
    MappedSubsystem subsystem;

    public ReferencingCommandGroup(MappedSubsystem
                                           mappedSubsystem) {
        subsystem = mappedSubsystem;
    }
}
