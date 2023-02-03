package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
// import edu.wpi.first.;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants;

public class Chassis extends SubsystemBase {
    CANSparkMax m_frontLeft = new CANSparkMax(ChassisConstants.kLeftFrontPort, MotorType.kBrushless);
    CANSparkMax m_rearLeft = new CANSparkMax(ChassisConstants.kLeftRearPort, MotorType.kBrushless);
    MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);

    CANSparkMax m_frontRight = new CANSparkMax(ChassisConstants.kRightFrontPort, MotorType.kBrushless);
    CANSparkMax m_rearRight = new CANSparkMax(ChassisConstants.kRightRearPort, MotorType.kBrushless);
    MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_rearRight);

    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    public Chassis()
    {
        m_frontLeft.restoreFactoryDefaults();
        m_frontRight.restoreFactoryDefaults();
        m_rearRight.restoreFactoryDefaults();
        m_rearLeft.restoreFactoryDefaults();
        m_frontLeft.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontRight.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_rearRight.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontLeft.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontLeft.setIdleMode(IdleMode.kCoast);
        m_frontRight.setIdleMode(IdleMode.kCoast);
        m_rearRight.setIdleMode(IdleMode.kCoast);
        m_frontLeft.setIdleMode(IdleMode.kCoast);
        m_frontLeft.setInverted(false);
        m_frontLeft.setInverted(false);
        m_frontRight.setInverted(true);
        m_rearRight.setInverted(true);
    }
    public void drive(double leftSpeed, double rightSpeed, Boolean joyInput){
        m_drive.tankDrive(leftSpeed, rightSpeed, joyInput);
    }

}

