package org.usfirst.frc.team449.robot.mechanism.turntable;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.MechanismSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.commands.DefaultTurntableGroup;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Turntable subsystem
 */
public class TurntableSubsystem extends MechanismSubsystem {
	private boolean fwdEverPressed;
	private boolean revEverPressed;
	final static double NATIVE2INTERNAL_ROT = 4096.0;

	final static double INTERNAL2OUTPUT = 100.0;

	final static double OUT2TURNTABLE = 5.37;   // TODO figure out what this is EXACTLY
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
	public TurntableSubsystem(maps.org.usfirst.frc.team449.robot.mechanism.turntable.TurntableMap.Turntable map, TurntableOI oi) {
		super(map.getMechanism());
		this.map = map;
		this.oi = oi;
		canTalonSRX = new CANTalonSRX(map.getCanTalonSRX()) {
			@Override
			protected void setPIDF(double mkP, double mkI, double mkD, double mkF) {
				// TODO put this in map instead of hardcoding
				kP = mkP * 2048. / 1.4e6;
				kI = mkI * 2048. / 1.4e6;
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
		canTalonSRX.canTalon.setEncPosition(pos);
	}

	public void setPos(int pos) {
		canTalonSRX.canTalon.setPosition(pos);
	}

	/**
	 * @return encoder tick count
	 */
	public double getEncPosition() {
		return canTalonSRX.canTalon.getEncPosition();
	}

	public double getPosition() {
		return canTalonSRX.canTalon.getPosition();
	}

	public double getPWPosition() {
		return canTalonSRX.canTalon.getPulseWidthPosition();
	}

	public double getAnalogPosition() {
		return canTalonSRX.canTalon.getAnalogInPosition();
	}

	public void setPercentVbus(double percentVbus) {
		canTalonSRX.setPercentVbus(percentVbus);
	}

	public void setPosition(double sp) {
		canTalonSRX.setPosition(sp);
	}

	public void setControlMode(CANTalon.TalonControlMode mode) {
		canTalonSRX.canTalon.changeControlMode(mode);
	}

	public boolean isFwdSwitchClosed() {
		return canTalonSRX.canTalon.isFwdLimitSwitchClosed();
	}

	public boolean isRevSwitchClosed() {
		return canTalonSRX.canTalon.isRevLimitSwitchClosed();
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public double getError() {
		return canTalonSRX.canTalon.getClosedLoopError();
	}

	public double getSetpoint() {
		return canTalonSRX.canTalon.getSetpoint();
	}

	public double getOutputVoltage() {
		return canTalonSRX.canTalon.getOutputVoltage();
	}

	public static double degreesToNative(double degrees) {
		return degrees / 360 * NATIVE2INTERNAL_ROT * INTERNAL2OUTPUT * OUT2TURNTABLE;
	}

	public static double nativeToDegrees(double nativeUnits) {
		return nativeUnits / OUT2TURNTABLE / INTERNAL2OUTPUT / NATIVE2INTERNAL_ROT * 360;
	}

	@Override
	protected void initDefaultCommand() {
		System.out.println("TurntableSubsystem initDefaultCommand started");
		setDefaultCommand(new DefaultTurntableGroup(this, oi));
		System.out.println("TurntableSubsystem initDefaultCommand finished");
	}

	public void log() {
		if (canTalonSRX.canTalon.isFwdLimitSwitchClosed() && !fwdEverPressed) {
			fwdEverPressed = true;
		}
		if (canTalonSRX.canTalon.isRevLimitSwitchClosed() && !revEverPressed) {
			revEverPressed = true;
		}
	}
}
