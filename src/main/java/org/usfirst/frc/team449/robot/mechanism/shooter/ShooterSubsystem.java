package org.usfirst.frc.team449.robot.mechanism.shooter;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.MechanismSubsystem;
import org.usfirst.frc.team449.robot.mechanism.shooter.commands.DefaultShooterGroup;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Shooter Subsystem
 */
public class ShooterSubsystem extends MechanismSubsystem {

	private final double NATIVE = 409.6;

	/**
	 * The motor on the lower bar to suck up the ball.
	 */
	private CANTalonSRX intake;
	/**
	 * The motor on the upper bar that spins at high speed to launch the ball.
	 */
	private CANTalonSRX flywheel;
	/**
	 * OI to get the fire button from
	 */
	private ShooterOI oi;
	/**
	 * Whether there is a ball in the shooter.
	 */
	private boolean hasBall;
	/**
	 * Whether the intake is currently sucking in a ball
	 */
	private boolean isIntaking;
	/**
	 * Whether the flywheel uis accelerated or not
	 */
	private boolean isAccelerated;
	/**
	 * The time when the subsystem was initiated
	 */
	private long startTime;

	/**
	 * Creates a mapped subsystem and sets its map
	 *
	 * @param map the map of constants relevant to this
	 *            subsystem
	 */
	public ShooterSubsystem(maps.org.usfirst.frc.team449.robot.mechanism.shooter.ShooterMap.Shooter map, ShooterOI oi) {
		super(map.getMechanism());
		this.oi = oi;
		intake = new CANTalonSRX(map.getIntake());
		flywheel = new CANTalonSRX(map.getFlywheel()) {
			@Override
			protected void setPIDF(double mkP, double mkI, double mkD, double mkF) {
				// TODO put this in map instead of hardcoding
				kP = (mkP * 1023) / (30.72559 * 4096 * 0.1);
				kI = (mkI * 1023) / (30.72559 * 4096 * 0.1);
				kF = 1023.0 / (mkF * 409.6);
			}
		};
		hasBall = false;
		isIntaking = false;
		isAccelerated = false;
		System.out.println("ShooterSubsystem constructed");
	}

	@Override
	protected void initDefaultCommand() {
		System.out.println("ShooterSubsystem initDefaultCommand started");
		try (PrintWriter writer = new PrintWriter("/home/lvuser/shooterLog.csv")) {
			writer.println("Time,Setpoint,EncVel,Error");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		startTime = System.nanoTime();
		setDefaultCommand(new DefaultShooterGroup(this, oi));
		System.out.println("ShooterSubsystem.initDefaultCommand finished");
	}

	public void setIntaking(boolean isIntaking) {
		this.isIntaking = isIntaking;
	}

	public void setAccelerated(boolean isAccelerated) {
		this.isAccelerated = isAccelerated;
	}

	public void setFlywheelControlMode(CANTalon.TalonControlMode mode) {
		flywheel.setControlMode(mode);
	}

	public void setFlywheelSpeed(double sp) {
		flywheel.setSpeed(sp);
	}

	public void setFlywheelEncPos(int enc) {
		flywheel.setEncPos(enc);
	}

	public void enableFlywheelBrakeMode(boolean brake) {
		flywheel.enableBrakeMode(brake);
	}

	public void setIntakeControlMode(CANTalon.TalonControlMode mode) {
		intake.setControlMode(mode);
	}

	public void setIntakePercentVbus(double percentVbus) {
		intake.setPercentVbus(percentVbus);
	}

	public double getFlywheelEncVel() {
		return flywheel.getEncVelocity();
	}

	public double getFlywheelEncPos() {
		return flywheel.getEncPosition();
	}

	public double getFlywheelOutputVoltage() {
		return flywheel.getOutputVoltage();
	}

	public double getFlywheelClosedLoopError() {
		return flywheel.getClosedLoopError();
	}

	public boolean isFlywheelEnabled() {
		return flywheel.isEnabled();
	}

	public double getFlywheelSetpoint() {
		return flywheel.getSetpoint();
	}

	public CANTalon.TalonControlMode getFlywheelControlMode() {
		return flywheel.getControlMode();
	}

	public void logData(double sp) {
		try (FileWriter fw = new FileWriter("/home/lvuser/shooterLog.csv", true)) {
			StringBuilder sb = new StringBuilder();
			sb.append((System.nanoTime() - startTime) / 100);
			sb.append(",");
			sb.append(sp);
//			SmartDashboard.putNumber("Flywheel Setpoint", getFlywheelSetpoint() / 60);
			sb.append(",");
			sb.append(getFlywheelEncVel() / NATIVE);
//			SmartDashboard.putNumber("Flywheel Velocity", getFlywheelEncVel() / NATIVE);
			sb.append(",");
			sb.append(getFlywheelClosedLoopError() / NATIVE);
//			SmartDashboard.putNumber("Flywheel Error", getFlywheelClosedLoopError() / NATIVE);
//			SmartDashboard.putNumber("Flywheel Output Voltage", getFlywheelOutputVoltage());
			sb.append("\n");
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
