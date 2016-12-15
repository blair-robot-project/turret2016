package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.json.JSONObject;
import org.usfirst.frc.team449.robot.mechanism.shooter.ShooterSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableMap;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.oi.OIMap;
import org.usfirst.frc.team449.robot.oi.TurretOI;

/**
 * Turntable base Robot class
 */
public class Robot extends IterativeRobot {
	/**
	 * Turntable subsystem
	 */
	public static TurntableSubsystem turntableSubsystem;
	/**
	 * Shooter subsystem
	 */
	public static ShooterSubsystem shooterSubsystem;

	/**
	 * Turret-specific OI subsystem (has joysticks and
	 * buttons)
	 */
	private static TurretOI oi;

	/**
	 * Configuration map
	 */
	private static JSONObject cfg;

	/**
	 * Initialization block run only when code is
	 * deployed to the robot.
	 * <p>
	 * Instatiates subsystems and maps the OI buttons
	 */
	public void robotInit() {
		cfg = MappedSubsystem.readConfig("/home/lvuser/cfg.json");
		System.out.println("Start map init");
		oi = new TurretOI(new OIMap(cfg));
		System.out.println("Start turntable init");
		turntableSubsystem = new TurntableSubsystem(new TurntableMap(cfg), oi);
//		System.out.println("Start shooter init");
//		shooterSubsystem = new ShooterSubsystem(new ShooterMap(cfg), oi);

		// Map buttons AFTER all the subsystems are inited
		oi.mapButtons();
	}

	/**
	 * Block run every teleop cycle.
	 * <p>
	 * Runs Scheduler's commands.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
