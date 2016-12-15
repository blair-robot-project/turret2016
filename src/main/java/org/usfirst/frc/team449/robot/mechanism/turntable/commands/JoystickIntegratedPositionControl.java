package org.usfirst.frc.team449.robot.mechanism.turntable.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class JoystickIntegratedPositionControl extends ReferencingCommand {
	private TurntableOI oi;
	private TurntableSubsystem turntableSubsystem;
	double integral = 0;

	public JoystickIntegratedPositionControl(TurntableSubsystem turntableSubsystem, TurntableOI oi) {
		super(turntableSubsystem);
		System.out.println("JoystickIntegratedPositionControl start construct");
		this.oi = oi;
		this.turntableSubsystem = turntableSubsystem;
		System.out.println("JoystickIntegratedPositionControl constructed");
	}

	@Override
	protected void initialize() {
		//        double setpoint = 0.00025 * .75;
		double setpoint = TurntableSubsystem.degreesToNative(-45);
		integral = deadband(oi.getTurntableThrottle())*0.02;
		//turntableSubsystem.setControlMode(CANTalon.TalonControlMode.Disabled);
		turntableSubsystem.setPosition(setpoint);
		System.out.println("JoystickIntegratedPositionControl initialized");
	}

	@Override
	protected void execute() {
		turntableSubsystem.log();

		/*
		If the integral is increased when the turntable can't actually move, there will be a delay before it can move
		in the opposite direction. So we need to do this.
		 */
		if((!turntableSubsystem.isFwdSwitchClosed() && !turntableSubsystem.isRevSwitchClosed())){
			integral+=deadband(oi.getTurntableThrottle())*0.02;
		} else if(turntableSubsystem.isFwdSwitchClosed() && deadband(oi.getTurntableThrottle())<0){
			integral+=deadband(oi.getTurntableThrottle())*0.02;
		} else if(turntableSubsystem.isRevSwitchClosed() && deadband(oi.getTurntableThrottle())>0){
			integral+=deadband(oi.getTurntableThrottle())*0.02;
		}

		turntableSubsystem.setPosition(TurntableSubsystem.degreesToNative(integral)*100);
		SmartDashboard.putNumber("Integral",integral);
		SmartDashboard.putNumber("Throttle",oi.getTurntableThrottle());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	private double deadband(double val){
		if(Math.abs(val)<0.08){
			return 0;
		}
		return val;
	}
}