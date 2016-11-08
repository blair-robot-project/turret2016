package org.usfirst.frc.team449.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .ois.TurnTableOI;

/**
 * OI used to control the turret
 */
public class TurretOI extends OISubsystem implements
        TurnTableOI {
    /**
     * Joystick used to control the turntable
     */
    private Joystick turntableJ;
    /**
     * Joystick used to control the injector wheel
     */
    private Joystick injectorJ;
    /**
     * Joystick used to control the flywheel
     */
    private Joystick flywheelJ;

    /**
     * Instantiate the TurretOI
     *
     * @param map configuration map
     */
    public TurretOI(RobotMap map) {
        super(map);
        turntableJ = new Joystick(0);
        injectorJ = new Joystick(1);
        flywheelJ = new Joystick(2);
    }

    /**
     * Map the buttons (called in Robot.robotInit after
     * all subsystems are instantiated).
     *
     * Does not do anything right now (no buttons to map)
     */
    public void mapButtons() {
    }

    /**
     * @return turntable's throttle
     */
    @Override
    public double getTurntableVelocity() {
        return turntableJ.getRawAxis(1);
    }

    /**
     * Non-applicable method for driving a drive chassis
     *
     * @return 0
     */
    // TODO take this out of OISubsystem in central repo
    @Override
    public double getDriveAxisLeft() {
        return 0;
    }

    /**
     * Non-applicable method for driving a drive chassis
     *
     * @return 0
     */
    // TODO take this out of OISubsystem in central repo
    @Override
    public double getDriveAxisRight() {
        return 0;
    }

    /**
     * Non-applicable method for toggling a camera
     *
     * @return 0
     */
    // TODO take this out of OISubsystem in central repo
    @Override
    public void toggleCamera() {

    }

    /**
     * Non-applicable subsystem method
     */
    // TODO implement this empty function in OISubsystem
    @Override
    protected void initDefaultCommand() {

    }
}
