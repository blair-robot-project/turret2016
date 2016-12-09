package org.usfirst.frc.team449.robot.mechanism.turntable.commands;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;


/**
 * Created by BlairRobot on 2016-11-05.
 */
public class VBusCenter extends ReferencingCommand {
	private long startTime;
	private TurntableSubsystem turntableSubsystem;

	public VBusCenter(TurntableSubsystem turntableSubsystem, double timeout) {
		super(turntableSubsystem, timeout);
		requires(Robot.turntableSubsystem);
		this.turntableSubsystem = turntableSubsystem;
	}

	@Override
	protected void initialize() {
		startTime = System.nanoTime();
		System.out.println("VBusCenter init");
	}

	@Override
	protected void execute() {
		turntableSubsystem
				.setControlMode(CANTalon
						.TalonControlMode.PercentVbus);
		turntableSubsystem.setPercentVbus(0.5);

		SmartDashboard.putNumber("Turntable Encoder", turntableSubsystem.getEncPosition());
		SmartDashboard.putNumber("Turtable Position", turntableSubsystem.getPosition());
	}

	@Override
	protected boolean isFinished() {
		//        return Math.abs(turntableSubsystem.getEncPosition()) < 500;
		return System.nanoTime() - startTime > 1e9;
	}

	@Override
	protected void end() {
		System.out.printf("FINISHED");
		System.out.println("VBusCenter end");
	}

	@Override
	protected void interrupted() {
		System.out.print("VBusCenter interrupted");
	}
}
