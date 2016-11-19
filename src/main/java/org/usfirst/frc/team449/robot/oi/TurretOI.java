package org.usfirst.frc.team449.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.shooter.ois.ShooterOI;
import org.usfirst.frc.team449.robot.mechanism.turntable.ois.TurntableOI;

/**
 * OI used to control the turret
 */
public class TurretOI extends OISubsystem implements
        TurntableOI, ShooterOI {
    /**
     * Joystick used to control the turntable
     */
    private Joystick turntableJ;
    /**
     * Joystick used to control the flywheel
     */
    private Joystick flywheelJ;
    /**
     * Joystick used to control the injector wheel
     */
    private Button injectorB;
    /**
     * Joystick used to control the flywheel
     */
    private Button flywheelB;

    /**
     * Instantiate the TurretOI
     *
     * @param map configuration map
     */
    public TurretOI(RobotMap map) {
        super(map);
        //turntableJ = new Joystick(7);
        flywheelJ = new Joystick(0);
        //injectorB = new JoystickButton(turntableJ,2);
        //flywheelB = new JoystickButton(turntableJ,1);
    }

    /**
     * Map the buttons (called in Robot.robotInit after
     * all subsystems are instantiated).
     *
     * Does not do anything right now (no buttons to map)
     */
    public void mapButtons() {
        /*
        injectorB.whenPressed(new IntakeBall(Robot
                .shooterSubsystem));
        injectorB.whenReleased(new StopIntakeBall(shooterSubsystem));
        flywheelB.whenPressed(new AccelerateFlywheel(shooterSubsystem));
        flywheelB.whenReleased(new DecelerateFlywheel(shooterSubsystem));
        */
    }

    /**
     * @return turntable's throttle
     */
    @Override
    public double getTurntableVelocity() {
        //return turntableJ.getRawAxis(1);
        return 0;
    }

    public double getJoyValue(){
        return flywheelJ.getAxis(Joystick.AxisType.kY);
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
