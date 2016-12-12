package org.usfirst.frc.team449.robot.mechanism.turntable.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Created by BlairRobot on 2016-12-10.
 */
public class PositionTurn extends ReferencingCommand {

	TurntableOI oi;
	TurntableSubsystem turntableSubsystem;

	public PositionTurn(TurntableSubsystem turntableSubsystem) {
		super(turntableSubsystem);
		requires(turntableSubsystem);
		System.out.println("DefaultTurn start construct");
		this.oi = oi;
		this.turntableSubsystem = turntableSubsystem;
		System.out.println("PositionTurn constructed");
	}

	protected void initialize(){
		double setpoint = SmartDashboard.getNumber("Turntable Setpoint");
		turntableSubsystem.setPosition(TurntableSubsystem.degreesToNative(setpoint));
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
