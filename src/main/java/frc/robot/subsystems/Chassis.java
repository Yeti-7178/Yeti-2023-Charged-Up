package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants;

public class Chassis extends SubsystemBase {
    CANSparkMax m_frontLeft = new CANSparkMax(ChassisConstants.kLeftFrontPort, MotorType.kBrushless);
    CANSparkMax m_rearLeft = new CANSparkMax(ChassisConstants.kLeftRearPort, MotorType.kBrushless);


    CANSparkMax m_frontRight = new CANSparkMax(ChassisConstants.kRightFrontPort, MotorType.kBrushless);
    CANSparkMax m_rearRigth = new CANSparkMax(ChassisConstants.kRightRearPort, MotorType.kBrushless);



    // SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    // SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRigth);

    // DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
    public Chassis()
    {

    }

}

