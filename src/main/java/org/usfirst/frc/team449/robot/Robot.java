package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import maps.org.usfirst.frc.team449.robot.Turret2016Map;
import org.usfirst.frc.team449.robot.mechanism.shooter.ShooterSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.oi.TurretOI;

import java.io.IOException;

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
	private static maps.org.usfirst.frc.team449.robot.Turret2016Map.Turret2016 cfg;

	/**
	 * Initialization block run only when code is
	 * deployed to the robot.
	 * <p>
	 * Instatiates subsystems and maps the OI buttons
	 */
	public void robotInit() {
		try {
			cfg = (Turret2016Map.Turret2016) MappedSubsystem.readConfig("/home/lvuser/map.cfg", Turret2016Map.Turret2016.newBuilder());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Start map init");
		oi = new TurretOI(cfg.getOi());
		System.out.println("Start turntable init");
		turntableSubsystem = new TurntableSubsystem(cfg.getTurntable(), oi);
		System.out.println("Start shooter init");
		shooterSubsystem = new ShooterSubsystem(cfg.getShooter(), oi);


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
