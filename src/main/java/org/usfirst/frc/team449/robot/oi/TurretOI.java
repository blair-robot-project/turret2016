package org.usfirst.frc.team449.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;
import org.usfirst.frc.team449.robot.oi.components.SmoothedThrottle;
import org.usfirst.frc.team449.robot.oi.components.Throttle;

/**
 * OI used to control the turret
 */
public class TurretOI extends OISubsystem implements
		TurntableOI, ShooterOI {
	/**
	 * Joystick used to control the turntable
	 */
	private Joystick turntableJ;

	private Throttle turntableThrottle;
	/**
	 * Joystick used to control the flywheel
	 */
	private Joystick flywheelJ;
	/**
	 * Joystick used to control the injector wheel
	 */

	private Button injectorB;
	/**
	 * Joystick used to control the flywheel
	 */
	private Button flywheelB;

	private Button turntableRevB;
	private Button turntableFwdB;

	/**
	 * Instantiate the TurretOI
	 *
	 * @param map configuration map
	 */
	public TurretOI(RobotMap map) {
		super(map);
		turntableJ = new Joystick(2);
		flywheelJ = new Joystick(0);
		injectorB = new JoystickButton(flywheelJ, 2);
		flywheelB = new JoystickButton(flywheelJ, 1);
		turntableThrottle = new SmoothedThrottle(turntableJ, 2);
		turntableRevB = new JoystickButton(turntableJ, 7);
		turntableFwdB = new JoystickButton(turntableJ, 8);
	}


	/**
	 * Map the buttons (called in Robot.robotInit after
	 * all subsystems are instantiated).
	 * <p>
	 * Does not do anything right now (no buttons to map)
	 */
	public void mapButtons() {
//		injectorB.whenPressed(new IntakeBall(shooterSubsystem));
//		injectorB.whenReleased(new StopIntakeBall(shooterSubsystem));
//		flywheelB.whenPressed(new AccelerateFlywheel(shooterSubsystem));
//		flywheelB.whenReleased(new DecelerateFlywheel(shooterSubsystem));
//		turntableFwdB.whileHeld(new SlowTurnFwd(turntableSubsystem));
//		turntableRevB.whileHeld(new SlowTurnRev(turntableSubsystem));
	}

	/**
	 * @return turntable's throttle
	 */
	@Override
	public double getTurntableVelocity() {
		return turntableThrottle.getValue();
	}

	public double getJoyValue() {
		return flywheelJ.getAxis(Joystick.AxisType.kY);
	}

	public double getTurntableThrottle(){return turntableJ.getAxis(Joystick.AxisType.kZ);}

	/**
	 * Non-applicable method for driving a drive chassis
	 *
	 * @return 0
	 */
	// TODO take this out of OISubsystem in central repo
	@Override
	public double getDriveAxisLeft() {
		return 0;
	}

	/**
	 * Non-applicable method for driving a drive chassis
	 *
	 * @return 0
	 */
	// TODO take this out of OISubsystem in central repo
	@Override
	public double getDriveAxisRight() {
		return 0;
	}


	/**
	 * Non-applicable method for toggling a camera
	 *
	 * @return 0
	 */
	// TODO take this out of OISubsystem in central repo
	@Override
	public void toggleCamera() {

	}

	/**
	 * Non-applicable subsystem method
	 */
	// TODO implement this empty function in OISubsystem
	@Override
	protected void initDefaultCommand() {

	}
}
