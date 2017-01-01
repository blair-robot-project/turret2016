package org.usfirst.frc.team449.robot.mechanism.turntable.commands;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Created by BlairRobot on 2016-12-10.
 * Turns to a angle determined by the Z axis.
 */
public class JoystickStraightPositionControl extends ReferencingCommand {

	TurntableOI oi;
	TurntableSubsystem turntableSubsystem;

	public JoystickStraightPositionControl(TurntableSubsystem turntableSubsystem, TurntableOI oi) {
		super(turntableSubsystem);
		requires(turntableSubsystem);
		System.out.println("JoystickIntegratedPositionControl start construct");
		this.oi = oi;
		this.turntableSubsystem = turntableSubsystem;
		turntableSubsystem.setControlMode(CANTalon.TalonControlMode.Position);
		System.out.println("JoystickStraightPositionControl constructed");
	}

	protected void initialize(){
		turntableSubsystem.setPosition(TurntableSubsystem.degreesToNative(oi.getTurntablePosition()*135));
	}

	@Override
	protected void execute() {
		turntableSubsystem.log();
		turntableSubsystem.setPosition(TurntableSubsystem.degreesToNative(oi.getTurntablePosition()*135));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
