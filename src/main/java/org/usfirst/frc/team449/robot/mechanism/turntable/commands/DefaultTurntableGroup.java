package org.usfirst.frc.team449.robot.mechanism.turntable.commands;


import org.usfirst.frc.team449.robot.ReferencingCommandGroup;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class DefaultTurntableGroup extends ReferencingCommandGroup {
	private TurntableOI oi;

	public DefaultTurntableGroup(TurntableSubsystem
			                             turntableSubsystem,
	                             TurntableOI oi) {
		super(turntableSubsystem);
		requires(turntableSubsystem);
		this.oi = oi;

		//        addSequential(new BangCalibrate(turntableSubsystem, 10));
		//        addSequential(new VBusCenter(turntableSubsystem, 3));
		addSequential(new DefaultTurn(turntableSubsystem, oi));
		System.out.println("DONE WITH DEFAULT TURNTABLE GROUP");
	}
}
