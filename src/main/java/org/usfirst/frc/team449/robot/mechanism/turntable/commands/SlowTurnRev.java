package org.usfirst.frc.team449.robot.mechanism.turntable.commands;

import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.turntable.TurntableSubsystem;

/**
 * Created by BlairRobot on 2016-12-18.
 */
public class SlowTurnRev extends ReferencingCommand {

	double setpoint;
	TurntableSubsystem turntableSubsystem;

	public SlowTurnRev(TurntableSubsystem turntableSubsystem){
		super(turntableSubsystem);
		this.turntableSubsystem = turntableSubsystem;
		turntableSubsystem.setControlMode(CANTalon.TalonControlMode.Position);
	}

	protected void initialize(){
		setpoint = TurntableSubsystem.nativeToDegrees(turntableSubsystem.getPosition());
	}

	protected void execute(){
		turntableSubsystem.log();
		if(!turntableSubsystem.isRevSwitchClosed()){
			setpoint-=0.3;
		}
//		turntableSubsystem.setPosition(TurntableSubsystem.degreesToNative(setpoint));
		turntableSubsystem.setPosition(turntableSubsystem.getEncPosition()-TurntableSubsystem.degreesToNative(10));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void interrupted() {
		turntableSubsystem.setPercentVbus(0);
	}

	@Override
	protected void end() {
		turntableSubsystem.setPercentVbus(0);
	}
}
