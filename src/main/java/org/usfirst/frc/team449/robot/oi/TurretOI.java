package org.usfirst.frc.team449.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.shooter.commands.AccelerateFlywheel;
import org.usfirst.frc.team449.robot.mechanism.shooter.commands.DecelerateFlywheel;
import org.usfirst.frc.team449.robot.mechanism.shooter.commands.IntakeBall;
import org.usfirst.frc.team449.robot.mechanism.shooter.commands.StopIntakeBall;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;
import org.usfirst.frc.team449.robot.oi.components.SmoothedThrottle;
import org.usfirst.frc.team449.robot.oi.components.Throttle;

import static org.usfirst.frc.team449.robot.Robot.shooterSubsystem;

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

	private Button goToPositionB;

	/**
	 * Instantiate the TurretOI
	 *
	 * @param map configuration map
	 */
	public TurretOI(RobotMap map) {
		super(map);
		turntableJ = new Joystick(0);
		flywheelJ = new Joystick(1);
		injectorB = new JoystickButton(turntableJ, 2);
		flywheelB = new JoystickButton(turntableJ, 1);
		turntableThrottle = new SmoothedThrottle(turntableJ, 2);
		//goToPositionB = new JoystickButton(turntableJ,8);
	}

	/**
	 * Map the buttons (called in Robot.robotInit after
	 * all subsystems are instantiated).
	 * <p>
	 * Does not do anything right now (no buttons to map)
	 */
	public void mapButtons() {
		injectorB.whenPressed(new IntakeBall(shooterSubsystem));
		injectorB.whenReleased(new StopIntakeBall(shooterSubsystem));
		flywheelB.whenPressed(new AccelerateFlywheel(shooterSubsystem));
		flywheelB.whenReleased(new DecelerateFlywheel(shooterSubsystem));
		//goToPositionB.whenPressed(new JoystickStraightPositionControl(turntableSubsystem));
	}

	public double getTurntableThrottle(){return turntableJ.getAxis(Joystick.AxisType.kY);}

	@Override
	public double getTurntablePosition() {
		return turntableJ.getAxis(Joystick.AxisType.kZ);
	}

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
