package org.usfirst.frc.team449.robot.mechanism.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.shooter.commands.TestGreyhill;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Shooter Subsystem
 */
public class ShooterSubsystem extends MappedSubsystem {

	private final double NATIVE = 409.6;
	public CANTalonSRX intake;
	private ShooterOI oi;
	private long startTime;

	/**
	 * Creates a mapped subsystem and sets its map
	 *
	 * @param map the map of constants relevant to this
	 *            subsystem
	 */
	public ShooterSubsystem(RobotMap map, ShooterOI oi) {
		super(map);
		ShooterMap shooterMap = (ShooterMap) map;
		this.oi = oi;
		intake = new CANTalonSRX(shooterMap.intakeMap) {
			protected void setPIDF(double mkP, double mkI, double mkD, double mkF) {
				kP = 0;
				kI = 0;
				kD = 0;
				kF = 1;
			}
		};
		startTime = System.nanoTime();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TestGreyhill(this));
	}

	public void logData(double sp) {
		System.out.println(intake.canTalon.getEncVelocity());
		SmartDashboard.putNumber("INTAKE ENC VEL", intake.canTalon.getEncVelocity());
		SmartDashboard.putNumber("INTAKE ENC VEL SCALED", intake.canTalon.getEncVelocity() / 256. / 4. * 10.);
		try (FileWriter fw = new FileWriter("/home/lvuser/shooterLog.csv", true)) {
			StringBuilder sb = new StringBuilder();
			sb.append((System.nanoTime() - startTime) / 100);
			sb.append(",");
			sb.append(sp);
			sb.append("\n");
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
