package org.usfirst.frc.team449.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurnTableOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class TurretOI extends OISubsystem implements
        TurnTableOI {
    private Joystick turntableJ;
    private Joystick injectorJ;
    private Joystick flywheelJ;

    public TurretOI(RobotMap map) {
        super(map);
        turntableJ = new Joystick(0);
        injectorJ = new Joystick(1);
        flywheelJ = new Joystick(2);
    }

    public void mapButtons() {
    }

    @Override
    public double getTurntableVelocity() {
        return turntableJ.getRawAxis(1);
    }

    @Override
    public double getDriveAxisLeft() {
        return 0;
    }

    @Override
    public double getDriveAxisRight() {
        return 0;
    }

    @Override
    public void toggleCamera() {
    }

    @Override
    protected void initDefaultCommand() {
    }
}
