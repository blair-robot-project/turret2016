package org.usfirst.frc.team449.robot.mechanism.shooter;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.CANTalonSRX;
import org.usfirst.frc.team449.robot.mechanism.shooter.commands.DefaultShooterGroup;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Shooter Subsystem
 */
public class ShooterSubsystem extends MappedSubsystem {
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
    public ShooterSubsystem(RobotMap map, ShooterOI oi) {
        super(map);
        ShooterMap shooterMap = (ShooterMap) map;
        this.oi = oi;
        intake = new CANTalonSRX(shooterMap.intakeMap);
        flywheel = new CANTalonSRX(shooterMap.flywheelMap);
        hasBall = false;
        isIntaking = false;
        isAccelerated = false;
        try (FileWriter fw = new FileWriter("/home/lvuser/shooterLog.csv", true)) {
            fw.write("Time,Throttle,EncVal");
        }catch (IOException e) {
            e.printStackTrace();
        }

        startTime = System.nanoTime();
        System.out.println("ShooterSubsystem constructed");
    }

    @Override
    protected void initDefaultCommand() {
        System.out.println("ShooterSubsystem initDefaultCommand started");
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

    public void setFlywheelByMode(double sp) {
        flywheel.setByMode(sp);
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

    public void setIntakeByMode(double sp) {
        intake.setByMode(sp);
    }

    public double getFlywheelEncVel() {
        return flywheel.getEncVelocity();
    }

    public double getFlywheelVoltage(){
        return flywheel.getOutputVoltage();
    }

    public double getFlywheelEncPos(){
        return flywheel.getEncPosition();
    }

    public double getFlywheelFGain() {
        return flywheel.getFGain();
    }

    public double getFlywheelOutputVoltage() {
        return flywheel.getOutputVoltage();
    }

    public double getError() {
        return flywheel.getError();
    }

    public boolean isEnabled() {
        return flywheel.isEnabled();
    }

    public boolean isAlive() {
        return flywheel.isAlive();
    }

    public CANTalon.TalonControlMode getFlywheelControlMode() {
        return flywheel.getControlMode();
    }

    public void logData() {
        try (FileWriter fw = new FileWriter("/home/lvuser/shooterLog.csv", true)) {
            StringBuilder sb = new StringBuilder();
            sb.append((System.nanoTime() - startTime)/100);
            sb.append(",");
            sb.append(oi.getJoyValue());
            sb.append(",");
            sb.append(getFlywheelEncVel()/409.6);
            sb.append("\n");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
