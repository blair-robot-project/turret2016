package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.json.JSONObject;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .TurntableMap;
import org.usfirst.frc.team449.robot.mechanism.turntable
        .TurntableSubsystem;
import org.usfirst.frc.team449.robot.oi.OIMap;
import org.usfirst.frc.team449.robot.oi.TurretOI;

/**
 * Created by BlairRobot on 2016-11-07.
 */
public class Robot extends IterativeRobot {
    public static TurntableSubsystem turntableSubsystem;
    private static TurretOI oi;
    private static JSONObject cfg;

    public void robotInit() {
        cfg = MappedSubsystem.readConfig
                ("/home/lvuser/cfg.json");
        oi = new TurretOI(new OIMap(cfg));
        turntableSubsystem = new TurntableSubsystem(new
                TurntableMap(cfg), oi);

        oi.mapButtons();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
