// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc1735.AerialAssist2014.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1735.AerialAssist2014.Robot;
/**
 *
 */
public class  ShootRetract extends Command {
    public ShootRetract() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(0.3); // run for no longer than this amount of seconds
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // This command will be called by a button, so assume full strength motion.
        // Function assumes negative value is "retraction"
        Robot.shooter.ShootInOut(-0.3);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean finished = Robot.shooter.reachedLimit("Min") ||
                           isTimedOut();
        if (Robot.m_debugOn && finished) System.out.println("ShootRetract command has finished.");
        return finished;
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.shooter.stop();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
