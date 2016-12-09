package org.usfirst.frc.team449.robot.mechanism.turntable;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.turntable.commands.DefaultTurntableGroup;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Turntable subsystem
 */
public class TurntableSubsystem extends MappedSubsystem {
	/**
	 * Talon speed controller used to turn the turntable
	 */
	private CANTalonSRX canTalonSRX;
	/**
	 * OI to get throttle from
	 */
	private TurntableOI oi;
	/**
	 * Largest magnitude position possible.
	 */
	private long limit = 1;

	/**
	 * Creates a mapped subsystem and sets its map
	 *
	 * @param map the map of constants relevant to this
	 *            subsystem
	 */
	public TurntableSubsystem(RobotMap map, TurntableOI oi) {
		super(map);
		TurntableMap turntableMap = (TurntableMap) map;
		this.oi = oi;
		canTalonSRX = new CANTalonSRX(turntableMap.canTalonSRXMap) {
			@Override
			protected void setPIDF(double mkP, double mkI, double mkD, double mkF) {
				// TODO put this in map instead of hardcoding
				kP = mkP * 2048 / 1.4e6;
			}
		};
		System.out.println("TurntableSubsystem constructed");
	}

	/**
	 * Reset the encoder tick counter to a set value
	 *
	 * @param pos value to set the tick counter to
	 */
	public void setEncPos(int pos) {
		canTalonSRX.setEncPos(pos);
	}

	/**
	 * @return encoder tick count
	 */
	public double getEncPosition() {
		return canTalonSRX.getEncPosition();
	}

	public double getPosition() {
		return canTalonSRX.getPosition();
	}

	public double getPWPosition() {
		return canTalonSRX.getPWPosition();
	}

	public double getAnalogPosition() {
		return canTalonSRX.getAnalogPosition();
	}

	/**
	 * Feed a number to the speed controller. Either
	 * voltage command or PID setpoint
	 *
	 * @param sp
	 */
	public void setByMode(double sp) {
		canTalonSRX.setByMode(sp);
	}

	public void setPosition(double sp) {
		canTalonSRX.setPosition(sp);
	}

	public void setControlMode(CANTalon.TalonControlMode mode) {
		canTalonSRX.setControlMode(mode);
	}

	public boolean isFwdSwitchClosed() {
		return canTalonSRX.isFwdSwitchClosed();
	}

	public boolean isRevSwitchClosed() {
		return canTalonSRX.isRevSwitchClosed();
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public double getError() {
		return canTalonSRX.getClosedLoopError();
	}

	public double getSetpoint() {
		return canTalonSRX.getSetpoint();
	}

	public double getOutputVoltage() {
		return canTalonSRX.getOutputVoltage();
	}

	@Override
	protected void initDefaultCommand() {
		System.out.println("TurntableSubsystem initDefaultCommand started");
		setDefaultCommand(new DefaultTurntableGroup(this, oi));
		System.out.println("TurntableSubsystem initDefaultCommand finished");
	}

	public void log() {
		SmartDashboard.putNumber("Position", getPosition());
		SmartDashboard.putNumber("Enc Position", getEncPosition());
		SmartDashboard.putNumber("PW Position", getPWPosition());
		SmartDashboard.putNumber("Analog Position", getAnalogPosition());
	}
}
