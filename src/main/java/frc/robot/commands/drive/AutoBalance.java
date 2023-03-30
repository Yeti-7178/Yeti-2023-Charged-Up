// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

//ShuffleBoard library
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//NAVX library
import com.kauailabs.navx.frc.AHRS;

//Subsystem
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Suction;
import frc.robot.subsystems.Arm;
import frc.robot.Constants.ArmConstants;
//Constants
import frc.robot.Constants.ChassisConstants;


import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoBalance extends CommandBase {
    private final Chassis m_drive;
    private AHRS NavX2;
    private Suction m_suction;
    private Arm m_arm;
    private final double kInitialRollOffset;
    boolean autoBalanceXMode = false;

    private double previousSpeed = 0;

    public AutoBalance(Chassis subsystem, AHRS Nav, double rollOffset, Suction suction, Arm arm){
        this.m_drive = subsystem;
        this.NavX2 = Nav;
        this.kInitialRollOffset = rollOffset; 
        this.m_suction = suction;
        this.m_arm = arm;


        

        addRequirements(m_drive);
    }

    @Override
    public void initialize(){
        m_drive.setBrakeMode();
    }

    @Override
    public void execute() {
        double rollAngleDegrees = -NavX2.getRoll();

        if ( !autoBalanceXMode && 
            (Math.abs(rollAngleDegrees) >= 
            Math.abs(ArmConstants.kOffBalanceAngleThresholdDegrees))) {
            autoBalanceXMode = true;
        }
        else if ( autoBalanceXMode && 
                (Math.abs(rollAngleDegrees) <= 
                Math.abs(ArmConstants.kOnBalanceAngleThresholdDegrees))) {
            autoBalanceXMode = false;
        }
        
        // Control drive system automatically, 
        // driving in direction of pitch angle,
        // with a magnitude based upon the angle
        
        if ( autoBalanceXMode ) {
            double pitchAngleRadians = rollAngleDegrees * (Math.PI / 180.0);
            double xAxisSpeed = Math.sin(pitchAngleRadians);
            double dSpeed = Math.abs(pitchAngleRadians) - Math.abs(previousSpeed);
            double speedInput = (xAxisSpeed * ArmConstants.kAutoBalanceMultiplier) - (dSpeed * ArmConstants.kAutoBalanceDerivativeMultiplier);

            /*double frontWheelsVelocity = (m_drive.getLeftFrontEncoderVelocity() + m_drive.getRightFrontEncoderVelocity())/2;
            double backWheelsVelocity = (m_drive.getLeftRearEncoderVelocity() + m_drive.getRightRearEncoderVelocity())/2;

            double frontSpeedInput = 0.6; //ArmConstants.kAutoBalanceMultiplier + (0.2 - frontWheelsVelocity) * 0.2;
            double backSpeedInput = 0.6; //ArmConstants.kAutoBalanceMultiplier + (0.2 - backWheelsVelocity) * 0.2;
            */
            
            
            

            //if (Math.abs(rollAngleDegrees) < 10) { speedInput = 0; } 
            /*if (dSpeed <= 0) {

            }*/

            
            //If we go too fast, the robot will go over the center of the pad and keep rocking back and forth.
            //If we go too slow, the robot will struggle to get over the charge pad since the ramp will make it slide downwards.
            //Brake mode SHOULD fix the latter issue, but it didn't seem to help that much.
            //We might want to consider using a cubic function instead of a sine function.
            
            m_drive.drive(speedInput,0, false);

            /*m_drive.m_frontLeft1.set(frontSpeedInput);
            m_drive.m_frontLeft2.set(frontSpeedInput);
            m_drive.m_frontRight1.set(frontSpeedInput);
            m_drive.m_frontRight2.set(frontSpeedInput);
            m_drive.m_rearLeft1.set(backSpeedInput);
            m_drive.m_rearLeft2.set(backSpeedInput);
            m_drive.m_rearRight1.set(backSpeedInput);
            m_drive.m_rearRight2.set(backSpeedInput);
            */


            SmartDashboard.putNumber("AutoBalanceSpeed", xAxisSpeed);
            SmartDashboard.putNumber("Tilt (Raidans)", pitchAngleRadians);
            SmartDashboard.putNumber("Autobalance dSpeed", dSpeed);

            previousSpeed = pitchAngleRadians;

            /*if (Math.abs(pitchAngleRadians) < 0.05 && !m_suction.m_suction.get()) {
                m_suction.suctionDeploy();
                //m_arm.compressorEnable();
            }*/
        }

        //If the robot is balanced, it should tell the motors to stop moving.
        else{
            m_drive.drive(0,0, false);
            /*if (!m_suction.m_suction.get()) {
                m_suction.suctionDeploy();
            }*/
        }
    }

    //This is for if DefaultDrive doesn't tell the motors to stop moving so the robot doesn't crash into a wall.
    @Override
    public void end(boolean interrupted){
        m_drive.drive(0, 0, false);
    }


}
