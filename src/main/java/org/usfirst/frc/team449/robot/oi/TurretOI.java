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
//    private Button button;

    public TurretOI(RobotMap map) {
        super(map);
        turntableJ = new Joystick(0);
        injectorJ = new Joystick(1);
        flywheelJ = new Joystick(2);

//        button = new JoystickButton(turntableJ, 1);
    }

    public void mapButtons() {
//        System.out.println("Map buttons start");
//        button.whenPressed(new DefaultTurn(Robot
//                .turntableSubsystem, this));
//        System.out.println("Map buttons finished");
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
