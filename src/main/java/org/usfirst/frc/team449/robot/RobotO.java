/**
 * Example demonstrating the velocity closed-loop servo.
 * Tested with Logitech F350 USB Gamepad inserted into Driver Station]
 * <p>
 * Be sure to select the correct feedback sensor using SetFeedbackDevice() below.
 * <p>
 * After deploying/debugging this to your RIO, first use the left Y-stick
 * to throttle the Talon manually.  This will confirm your hardware setup.
 * Be sure to confirm that when the Talon is driving forward (green) the
 * position sensor is moving in a positive direction.  If this is not the cause
 * flip the boolena input to the SetSensorDirection() call below.
 * <p>
 * Once you've ensured your feedback device is in-phase with the motor,
 * use the button shortcuts to servo to target velocity.
 * <p>
 * Tweak the PID gains accordingly.
 */
package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotO extends IterativeRobot {
    private static final double OUT_2_PLATE_RATIO = 5.4;
    private static final double INTERNAL_2_OUT_RATIO = 1000;
    private static final double SCALE_FACTOR = 100.0 / 360.0;

    private double scaledEnc = 0;

    private boolean init = false;
    private boolean hitLeft = false;

    private CANTalon _talon = new CANTalon(1);
    private Joystick _joy = new Joystick(0);
    private StringBuilder _sb = new StringBuilder();
    private int _loops = 0;

    public void robotInit() {
        /* first choose the sensor */
        _talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        _talon.reverseSensor(false);
        _talon.reverseOutput(false);
        //_talon.configEncoderCodesPerRev(XXX), // if using FeedbackDevice.QuadEncoder
        //_talon.configPotentiometerTurns(XXX), // if using FeedbackDevice.AnalogEncoder or AnalogPot

        /* set the peak and nominal outputs, 12V means full */
        _talon.configNominalOutputVoltage(+0.0f, -0.0f);
        _talon.configPeakOutputVoltage(+12.0f, 0.0f);   // TODO figure out why this is 0.0f not -12.0f
        /* set closed loop gains in slot0 */
        _talon.setProfile(0);
        _talon.setF(0.1097);
        _talon.setP(0.22);
        _talon.setI(0);
        _talon.setD(0);

        _talon.ConfigFwdLimitSwitchNormallyOpen(true);
        _talon.ConfigRevLimitSwitchNormallyOpen(true);
        _talon.enableLimitSwitch(true, true);

        _talon.setForwardSoftLimit(percentToEnc(10));
        _talon.enableForwardSoftLimit(false);
        _talon.setReverseSoftLimit(percentToEnc(-10));
        _talon.enableReverseSoftLimit(false);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        if (!init) {
            if ((! hitLeft)) {
                _talon.changeControlMode(TalonControlMode.PercentVbus);
                _talon.set(.5);
                if (_talon.isFwdLimitSwitchClosed()) {
                    hitLeft = true;
                    _talon.setEncPosition(0);
                }
            } else {
                _talon.set(-.5);
                if (_talon.isRevLimitSwitchClosed()) {
                    init = true;
                    _talon.set(0);
                    _talon.setEncPosition(_talon.getEncPosition()/2);
                }
            }

        } else {
    	/* get gamepad axis */
            double leftYstick = _joy.getAxis(AxisType.kY);
            double motorOutput = _talon.getOutputVoltage() / _talon.getBusVoltage();
        /* prepare line to print */
            _sb.append("\tout:");
            _sb.append(motorOutput);
            _sb.append("\tspd:");
            _sb.append(_talon.getSpeed());

            if (_joy.getRawButton(1)) {
        	/* Speed mode */
                double targetSpeed = leftYstick * 1500.0; /* 1500 RPM in either direction */
                _talon.changeControlMode(TalonControlMode.Speed);
                _talon.set(targetSpeed); /* 1500 RPM in either direction */

        	/* append more signals to print when in speed mode. */
                _sb.append("\terr:");
                _sb.append(_talon.getClosedLoopError());
                _sb.append("\ttrg:");
                _sb.append(targetSpeed);
            } else {
        	/* Percent voltage mode */
                _talon.changeControlMode(TalonControlMode.PercentVbus);
                _talon.set(leftYstick);
            }

            if (++_loops >= 10) {
                _loops = 0;
                System.out.println(_sb.toString());
            }
            _sb.setLength(0);

            scaledEnc = encToPercent(_talon.getEncPosition());

            SmartDashboard.putNumber("Encoder value", scaledEnc);
            SmartDashboard.putBoolean("Forward Limit Switch", _talon.isFwdLimitSwitchClosed());
            SmartDashboard.putBoolean("Reverse Limit Switch", _talon.isRevLimitSwitchClosed());
        }
    }

    private int percentToEnc(double percent) {
        return (int) (percent / SCALE_FACTOR * INTERNAL_2_OUT_RATIO * OUT_2_PLATE_RATIO);
    }

    private double encToPercent(long enc){
        return (double) (enc) / OUT_2_PLATE_RATIO / INTERNAL_2_OUT_RATIO * SCALE_FACTOR;
    }
}