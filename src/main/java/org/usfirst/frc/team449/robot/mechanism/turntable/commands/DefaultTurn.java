package org.usfirst.frc.team449.robot.mechanism.turntable
        .commands;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class DefaultTurn extends ReferencingCommand {
    private TurntableOI oi;
    private TurntableSubsystem turntableSubsystem;

    public DefaultTurn(TurntableSubsystem
                               turntableSubsystem,
                       TurntableOI oi) {
        super(turntableSubsystem);
        System.out.println("DefaultTurn start construct");
        this.oi = oi;
        this.turntableSubsystem = turntableSubsystem;
        System.out.println("DefaultTurn constructed");
    }

    @Override
    protected void initialize() {
//        double setpoint = 0.00025 * .75;
        double setpoint = (100/4096);
        turntableSubsystem.setControlMode(CANTalon.TalonControlMode.Position);
        turntableSubsystem.setByMode(setpoint);
        System.out.println("DefaultTurn initialized");
    }

    @Override
    protected void execute() {
        double setpoint = 0;
        System.out.println("Throttle: " + oi.getTurntableVelocity());
        //turntableSubsystem.setByMode(oi.getTurntableVelocity()*turntableSubsystem.getLimit());
        //SmartDashboard.putNumber("Throttle", oi.getTurntableVelocity());
//        if (turntableSubsystem.getEncPosition() < 0){
//            turntableSubsystem.setByMode(1);
//        } else if (turntableSubsystem.getEncPosition() > 0){
//            turntableSubsystem.setByMode(-1);
//        } else {
//            turntableSubsystem.setByMode(0);
//        }
        SmartDashboard.putNumber("Turntable Setpoint", setpoint);
        SmartDashboard.putNumber("Turntable Error", turntableSubsystem.getError());
        SmartDashboard.putNumber("Turntable Encoder", turntableSubsystem.getEncPosition());
        SmartDashboard.putNumber("Turntable Output", turntableSubsystem.getOutputVoltage());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}