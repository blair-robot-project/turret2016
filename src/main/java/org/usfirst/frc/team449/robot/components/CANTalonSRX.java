package org.usfirst.frc.team449.robot.components;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.RobotMap;

/**
 * Created by BlairRobot on 2016-11-05.
 */
public class CANTalonSRX extends Component {

	CANTalon canTalon;

	protected double kP;
	protected double kI;
	protected double kD;
	protected double kF;

	public CANTalonSRX(RobotMap.CANTalonSRXMap m) {
		System.out.println(m.port);
		canTalon = new CANTalon(m.port);
		canTalon.setFeedbackDevice(CANTalon.FeedbackDevice.valueOf(m.feedbackDevice));
		canTalon.reverseSensor(m.reverseSensor);
		canTalon.reverseOutput(m.reverseOutput);
		canTalon.setInverted(m.isInverted);
		canTalon.configNominalOutputVoltage
				(+m.nominalOutVoltage, -m.nominalOutVoltage);
		canTalon.configPeakOutputVoltage(+m.peakOutVoltage,
				-m.peakOutVoltage);
		canTalon.setProfile(m.profile);

		kP = m.kP;
		kI = m.kI;
		kD = m.kD;
		kF = m.kF;
		setPIDF(m.kP, m.kI, m.kD, m.kF);
		canTalon.setPID(kP, kI, kD, kF, 0, 0, 0);

		canTalon.setProfile(0);

		canTalon.ConfigFwdLimitSwitchNormallyOpen(m.fwdLimNormOpen);
		canTalon.ConfigRevLimitSwitchNormallyOpen
				(m.revLimNormOpen);
		canTalon.enableLimitSwitch(m.fwdLimEnabled,
				m.revLimEnabled);
		canTalon.enableForwardSoftLimit(m.fwdSoftLimEnabled);
		canTalon.setForwardSoftLimit(m.fwdSoftLimVal);
		canTalon.enableReverseSoftLimit(m.revSoftLimEnabled);
		canTalon.setReverseSoftLimit(m.revSoftLimVal);
		canTalon.enableBrakeMode(m.brakeMode);
	}

	/**
	 * Method called in the constructor that sets the true PID values before they are handed to the Talon's internal
	 * PID controller.
	 * <p>
	 * When called in the constructor, this method takes the map's PIDF values as its arguments, scales them, and sticks
	 * them in the kP, kI, kD, kF fields. Later in the constructor, the Talon is given those fields as the final PIDF.
	 * <p>
	 * By default, the map PIDF values are untouched before they go into the Talon. However, you may want to specify
	 * a scaling factor between the map PIDF and the PIDF that is fed to the Talon. When you are PIDF tuning, you want
	 * to think of changing PIDF as fractions of a full response. However, robots have intrinsic scaling factors between
	 * the input units and the measured units and PIDF values, so this method allows you to specify external scaling
	 * factors so that you can have your fractional PIDF in one place on the map and your scaling in another place in
	 * the map.
	 * <p>
	 * To scale PIDF between the map and the Talon, override this method and change what the PIDF fields are assigned
	 * to.
	 * <p>
	 * Note that true PIDF have already been assigned for the first time in the constructor. If you do not want to scale
	 * anything, you do not need to fill out this method, and if you only want to scale one value, you can write in just
	 * that one value when you override this.
	 *
	 * @param mkP map kP
	 * @param mkI map kI
	 * @param mkD map kD
	 * @param mkF map KF
	 */
	protected void setPIDF(double mkP, double mkI, double mkD, double mkF) {
	}

	public double getFGain() {
		return canTalon.getF();
	}

	public boolean isEnabled() {
		return canTalon.isEnabled();
	}

	public boolean isAlive() {
		return canTalon.isAlive();
	}

	public CANTalon.TalonControlMode getControlMode() {
		return canTalon.getControlMode();
	}

	public void setControlMode(CANTalon.TalonControlMode
			                           mode) {
		canTalon.changeControlMode(mode);
	}

	public void setEncPos(int pos) {
		canTalon.setEncPosition(pos);
	}

	public double getOutputVoltage() {
		return canTalon.getOutputVoltage();
	}

	public double getEncVelocity() {
		return canTalon.getEncVelocity();
	}

	public double getEncPosition() {
		return canTalon.getEncPosition();
	}

	public double getPosition() {
		return canTalon.getPosition();
	}

	public double getPWPosition() {
		return canTalon.getPulseWidthPosition();
	}

	public double getAnalogPosition() {
		return canTalon.getAnalogInPosition();
	}

	public double getClosedLoopError() {
		return canTalon.getClosedLoopError();
	}

	public double getError() {
		return canTalon.getError();
	}

	public boolean isFwdSwitchClosed() {
		return canTalon.isFwdLimitSwitchClosed();
	}

	public boolean isRevSwitchClosed() {
		return canTalon.isRevLimitSwitchClosed();
	}

	/**
	 * Wrapper on the native CAN Talon set method.
	 * <p>
	 * When controlling using PercentVBUS, -1 <= sp <= 1
	 * When controlling using Speed, sp is in RPM (note: revolutions per MINUTE, not second)
	 *
	 * @param sp setpoint
	 */
	public void setByMode(double sp) {
		if (getControlMode() == CANTalon.TalonControlMode.Speed) {
			canTalon.set(sp * 60);
		} else {
			canTalon.set(sp);
		}
	}

	public void enableBrakeMode(boolean brake) {
		canTalon.enableBrakeMode(brake);
	}

	public double getSetpoint() {
		return canTalon.getSetpoint();
	}

	@Override
	public boolean getInverted() {
		return false;
	}

	@Override
	public void setInverted(boolean b) {
	}
}
