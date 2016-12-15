package org.usfirst.frc.team449.robot.mechanism.turntable.commands;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Created by BlairRobot on 2016-12-10.
 * Turns to a angle determined by the Z axis.
 */
public class PositionTurn extends ReferencingCommand {

	TurntableOI oi;
	TurntableSubsystem turntableSubsystem;

	public PositionTurn(TurntableSubsystem turntableSubsystem, TurntableOI oi) {
		super(turntableSubsystem);
		requires(turntableSubsystem);
		System.out.println("DefaultTurn start construct");
		this.oi = oi;
		this.turntableSubsystem = turntableSubsystem;
		turntableSubsystem.setControlMode(CANTalon.TalonControlMode.Position);
		System.out.println("PositionTurn constructed");
	}

	protected void initialize(){
		turntableSubsystem.setPosition(TurntableSubsystem.degreesToNative(oi.getTurntableThrottle()*135));
	}

	@Override
	protected void execute() {
		turntableSubsystem.log();
		turntableSubsystem.setPosition(TurntableSubsystem.degreesToNative(oi.getTurntableThrottle()*135));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
