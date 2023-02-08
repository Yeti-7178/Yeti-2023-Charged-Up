package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
// import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
// import edu.wpi.first.;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants;

public class Chassis extends SubsystemBase {
    CANSparkMax m_frontLeft1 = new CANSparkMax(ChassisConstants.kLeftFrontPort1, MotorType.kBrushless);
    CANSparkMax m_frontLeft2 = new CANSparkMax(ChassisConstants.kLeftFrontPort2, MotorType.kBrushless);
    CANSparkMax m_rearLeft1 = new CANSparkMax(ChassisConstants.kLeftRearPort1, MotorType.kBrushless);
    CANSparkMax m_rearLeft2 = new CANSparkMax(ChassisConstants.kLeftRearPort2, MotorType.kBrushless);
    MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft1,m_frontLeft2, m_rearLeft1,m_rearLeft2);

    CANSparkMax m_frontRight1 = new CANSparkMax(ChassisConstants.kRightFrontPort1, MotorType.kBrushless);
    CANSparkMax m_frontRight2 = new CANSparkMax(ChassisConstants.kRightFrontPort2, MotorType.kBrushless);
    CANSparkMax m_rearRight1 = new CANSparkMax(ChassisConstants.kRightRearPort1, MotorType.kBrushless);
    CANSparkMax m_rearRight2 = new CANSparkMax(ChassisConstants.kRightRearPort2, MotorType.kBrushless);
    MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight1,m_frontRight2, m_rearRight1,m_rearRight2);

    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    public Chassis()
    {
        m_frontLeft1.restoreFactoryDefaults();
        m_frontLeft2.restoreFactoryDefaults();
        m_frontRight1.restoreFactoryDefaults();
        m_frontRight2.restoreFactoryDefaults();
        m_rearRight1.restoreFactoryDefaults();
        m_rearRight2.restoreFactoryDefaults();
        m_rearLeft1.restoreFactoryDefaults();
        m_rearLeft2.restoreFactoryDefaults();
        m_frontLeft1.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontLeft2.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontRight1.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontRight2.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_rearRight1.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_rearRight2.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontLeft1.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontLeft2.setSmartCurrentLimit(ChassisConstants.kCurrentLimit);
        m_frontLeft1.setIdleMode(IdleMode.kCoast);
        m_frontLeft2.setIdleMode(IdleMode.kCoast);
        m_frontRight1.setIdleMode(IdleMode.kCoast);
        m_frontRight2.setIdleMode(IdleMode.kCoast);
        m_rearRight1.setIdleMode(IdleMode.kCoast);
        m_rearRight2.setIdleMode(IdleMode.kCoast);
        m_frontLeft1.setIdleMode(IdleMode.kCoast);
        m_frontLeft2.setIdleMode(IdleMode.kCoast);
        m_frontLeft1.setInverted(false);
        m_frontLeft2.setInverted(false);
        m_frontLeft1.setInverted(false);
        m_frontLeft2.setInverted(false);
        m_frontRight1.setInverted(true);
        m_frontRight2.setInverted(true);
        m_rearRight1.setInverted(true);
        m_rearRight2.setInverted(true);
    }
    public void drive(double xSpeed, double zRotation, boolean b){
        m_drive.arcadeDrive(xSpeed, -zRotation);
    }
    public double getAverageEncoderDistanceInches() {
        return 0;
    }
    public double drive1(){
        return 0;
    }

}

