// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc1735.AerialAssist2014;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc1735.AerialAssist2014.commands.*;
import org.usfirst.frc1735.AerialAssist2014.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
// These imports needed to implement the Autonomous Dispatcher functionality
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Robot extends IterativeRobot {
    // Old "pre-AutonomousDispatch" code:
    // Command autonomousCommand;
    // New code:
    AutonomousDispatcher autonomousCommand;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static CollectorRoller collectorRoller;
    public static CollectorDeployer collectorDeployer;
    public static Vision vision;
    public static Range range;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Add a separate command thread to poll the rangefinder and light the indicator to notify the driver/operator if we are "in range" for a shot.
    ControlShotLight controlShotLightCommand;
    
    // Create a master sensor diable flag to cover all robot sensors.
    // When set, pots and limit switches will not limit robot operations.
    // Each individual sensor will also have an override, but this one
    // trumps everything
    public static boolean m_masterSensorDisable;
    
    // Simple variable to turn off extra debug messages
    public static boolean m_debugOn;
    
    // High-pass filter.  Any joystick absolute value less than this should be clamped to zero.
    public static double m_joystickFilter = 0.15;
    
    // Implements a checkbox to support selectable Autonomous mode:
    public static SendableChooser AutoMode = new SendableChooser();
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        collectorRoller = new CollectorRoller();
        collectorDeployer = new CollectorDeployer();
        vision = new Vision();
        range = new Range();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // Default the master disable to "not disabled":
        m_masterSensorDisable = false;
        
        // Master "debug" verbosity switch
        m_debugOn = true;
        
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousDispatcher();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        // Instantiate the shot-in-range light polling thread
        controlShotLightCommand = new ControlShotLight();
        
        // Add a helpful print statement to indicate when we've really gotten through all the initialization procedures...
        System.out.println("Robot is initialized and ready for operation.");
    }
    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        
        // Schedule the thread that lights up the "shot in range" indicator
        if (controlShotLightCommand != null) controlShotLightCommand.start();
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        // Also schedule the thread that lights up the "shot in range" indicator... but only if we didn't already start it during Autonomous...
        if (controlShotLightCommand != null)
            if (!controlShotLightCommand.isRunning())
                controlShotLightCommand.start();
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
