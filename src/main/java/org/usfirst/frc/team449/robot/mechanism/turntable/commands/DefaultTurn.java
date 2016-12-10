package org.usfirst.frc.team449.robot.mechanism.turntable.commands;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class DefaultTurn extends ReferencingCommand {
	private TurntableOI oi;
	private TurntableSubsystem turntableSubsystem;

	public DefaultTurn(TurntableSubsystem turntableSubsystem, TurntableOI oi) {
		super(turntableSubsystem);
		System.out.println("DefaultTurn start construct");
		this.oi = oi;
		this.turntableSubsystem = turntableSubsystem;
		System.out.println("DefaultTurn constructed");
	}

	@Override
	protected void initialize() {
		//        double setpoint = 0.00025 * .75;
		double setpoint = TurntableSubsystem.degreesToNative(-45);
		//turntableSubsystem.setControlMode(CANTalon.TalonControlMode.Disabled);
		turntableSubsystem.setPosition(setpoint);
		System.out.println("DefaultTurn initialized");
	}

	@Override
	protected void execute() {
		turntableSubsystem.log();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}