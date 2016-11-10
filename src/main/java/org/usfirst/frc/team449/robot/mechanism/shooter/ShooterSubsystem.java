package org.usfirst.frc.team449.robot.mechanism.shooter;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;

/**
 * Shooter Subsystem
 */
public class ShooterSubsystem extends MappedSubsystem{
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
     * Creates a mapped subsystem and sets its map
     *
     * @param map the map of constants relevant to this
     *            subsystem
     */
    public ShooterSubsystem(RobotMap map, ShooterOI oi){
        super(map);
        ShooterMap shooterMap = (ShooterMap) map;
        this.oi = oi;
        intake = new CANTalonSRX(shooterMap.intakeMap);
        flywheel = new CANTalonSRX(shooterMap.flywheelMap);
        hasBall = false;
        isIntaking = false;
        isAccelerated = false;
        System.out.println("ShooterSubsystem constructed");
    }

    @Override
    protected void initDefaultCommand() {
        System.out.println("ShooterSubsystem initDefaultCommand started");
        //setDefaultCommand(new DefaultShooterGroup(this, oi));
        System.out.println("ShooterSubsystem.initDefaultCommand finished");
    }

    public void setIntaking(boolean isIntaking){
        this.isIntaking = isIntaking;
    }

    public void setAccelerated(boolean isAccelerated){
        this.isAccelerated = isAccelerated;
    }

    public void setFlywheelControlMode(CANTalon.TalonControlMode mode){
        flywheel.setControlMode(mode);
    }

    public void setFlywheelByMode(double sp){
        flywheel.setByMode(sp);
    }

    public void enableFlywheelBrakeMode(boolean brake){
        flywheel.enableBrakeMode(brake);
    }

    public void setIntakeControlMode(CANTalon.TalonControlMode mode){
        intake.setControlMode(mode);
    }

    public void setIntakeByMode(double sp){
        intake.setByMode(sp);
    }
}
