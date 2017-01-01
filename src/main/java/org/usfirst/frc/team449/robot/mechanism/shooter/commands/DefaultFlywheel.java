package org.usfirst.frc.team449.robot.mechanism.shooter.commands;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.shooter.ShooterSubsystem;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;

/**
 * Created by BlairRobot on 2016-11-13.
 * TODO: Delete this?
 */

public class DefaultFlywheel extends ReferencingCommand {
	private ShooterOI oi;
	private ShooterSubsystem shooterSubsystem;

	private final int CPR = 1024;
	private final double MAX_SPEED = 26000; //PUT SPEED HERE

	public DefaultFlywheel(ShooterSubsystem shooterSubsystem, ShooterOI oi) {
		super(shooterSubsystem);
		requires(shooterSubsystem);
		System.out.println("DefaultFlywheel start construct");
		this.oi = oi;
		this.shooterSubsystem = shooterSubsystem;
		System.out.println("DefaultFlywheel constructed");
	}

	@Override
	protected void initialize() {
		shooterSubsystem.setFlywheelEncPos(0);
		shooterSubsystem.setFlywheelSpeed(-1); //WITH THIS ONE
		shooterSubsystem.setIntakePercentVbus(1);
		System.out.println("JoystickIntegratedPositionControl initialized");
	}


	@Override
	protected void execute() {
		SmartDashboard.putNumber("Output Voltage", shooterSubsystem.getFlywheelOutputVoltage());
		SmartDashboard.putNumber("RPS Speed", encToRevPerSec(shooterSubsystem.getFlywheelEncVel()));
		SmartDashboard.putNumber("Raw Position", shooterSubsystem.getFlywheelEncPos());
		SmartDashboard.putNumber("Speed (rev/s)", shooterSubsystem.getFlywheelEncVel() / 409.6);
		System.out.println(shooterSubsystem.getFlywheelEncVel() / 409.6);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	private double encToRevPerSec(double enc) {
		return (enc * 10) / (CPR * 4);
	}

	private double revPerSecToEnc(double rps) {
		return (4 * CPR * rps) / 10;
	}
}
