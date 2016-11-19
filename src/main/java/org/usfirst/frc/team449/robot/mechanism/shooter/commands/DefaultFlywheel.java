package org.usfirst.frc.team449.robot.mechanism.shooter.commands;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.shooter.ShooterSubsystem;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;

/**
 * Created by BlairRobot on 2016-11-13.
 */
public class DefaultFlywheel extends ReferencingCommand {
    private ShooterOI oi;
    private ShooterSubsystem shooterSubsystem;

    private final double MAX_SPEED = 26000; //PUT SPEED HERE

    public DefaultFlywheel(ShooterSubsystem shooterSubsystem, ShooterOI oi) {
        super(shooterSubsystem);
        requires(shooterSubsystem);
        System.out.println("DefaultFlywheel start construct");
        this.oi = oi;
        this.shooterSubsystem = shooterSubsystem;
        System.out.println("DefaultFlywheel constructed");
    }

    @Override
    protected void initialize() {
        shooterSubsystem.setFlywheelControlMode(CANTalon.TalonControlMode.Speed);
        shooterSubsystem.setEncPos(0);
        shooterSubsystem.setFlywheelByMode(0); //WITH THIS ONE
        System.out.println("DefaultTurn initialized");
    }

    @Override
    protected void execute() {
        shooterSubsystem.setFlywheelByMode(oi.getJoyValue());
        //shooterSubsystem.logData();
        SmartDashboard.putNumber("Joystick", oi.getJoyValue());
        //SmartDashboard.putNumber("Velocity Setpoint", oi.getJoyValue()*MAX_SPEED);
        SmartDashboard.putNumber("Raw Speed", shooterSubsystem.getFlywheelEncVel());
        SmartDashboard.putNumber("Speed (rev/s)", encToRevPerSec(shooterSubsystem.getFlywheelEncVel()));
        //SmartDashboard.putNumber("FGain", shooterSubsystem.getFlywheelFGain());
        //SmartDashboard.putNumber("Output Voltage", shooterSubsystem.getFlywheelOutputVoltage());
        //SmartDashboard.putNumber("PID Error", shooterSubsystem.getError());
        //SmartDashboard.putBoolean("PID Talon Enabled", shooterSubsystem.isEnabled());
        //SmartDashboard.putBoolean("PID Talon Alive", shooterSubsystem.isAlive());
        //SmartDashboard.putBoolean("PID Talon", shooterSubsystem.getControlMode().isPID());
        //shooterSubsystem.setFlywheelByMode(oi.getJoyValue()*MAX_SPEED); //SWITCH OUT WHICH IS COMMENTED
//        shooterSubsystem.setFlywheelByMode(1); //WITH THIS ONE
        //shooterSubsystem.setFlywheelByMode(MAX_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public static double encToRevPerSec(double enc){
        return enc/409.6;
    }
} //TYPE "gradle deploy"