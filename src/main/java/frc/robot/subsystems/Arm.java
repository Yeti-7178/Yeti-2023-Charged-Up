package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends SubsystemBase
{
    
    public final CANSparkMax m_intakeMotor = new CANSparkMax(ArmConstants.motorPort, MotorType.kBrushless);
    final Compressor m_compress = new Compressor(ArmConstants.armPort, PneumaticsModuleType.CTREPCM);
    private RelativeEncoder m_intakeEncoder = m_intakeMotor.getEncoder();


    final Solenoid m_intakeDeploy = new Solenoid(ArmConstants.kModuleID,PneumaticsModuleType.CTREPCM,4);
    final Solenoid m_ClawDeploy = new Solenoid(ArmConstants.kModuleID,PneumaticsModuleType.CTREPCM,5);
    final Solenoid m_ClawExtendDeploy = new Solenoid(ArmConstants.kModuleID,PneumaticsModuleType.CTREPCM,7);
    final Solenoid m_ClawRotationDeploy= new Solenoid(ArmConstants.kModuleID,PneumaticsModuleType.CTREPCM,6);
//claw open/close-one pnumatic
//arm up/down-one motor
//claw out/in- two pnumatics
//claw flip down/straight-two pnumatics

    public Arm()
    {
        m_intakeMotor.restoreFactoryDefaults();
        m_intakeMotor.setSmartCurrentLimit(ArmConstants.kCurrentLimit);
        m_intakeMotor.setIdleMode(IdleMode.kBrake);
        
    }
    public void setArmSpeed(double ySpeed){
        m_intakeMotor.set(ySpeed);
    }

    public void armUp()
    {
        m_intakeMotor.set(ArmConstants.kHangMotorSpeed);
    }
    public void armDown()
    {
        m_intakeMotor.set(-ArmConstants.kHangMotorSpeed);
    }
    public void armStop()
    {
        m_intakeMotor.set(0.0);
    }

    public void intakeDeploy(){
        m_intakeDeploy.toggle();
    }
    public void clawDeploy(){
        m_ClawDeploy.toggle();

    }
    public void clawExtendDeploy(){
        m_ClawExtendDeploy.toggle();

    }
    public void ClawRotationDeploy(){
        m_ClawRotationDeploy.toggle();
    }
    public double getAverageEncoderDistanceInches(){
        return(ArmConstants.kEncoderConversionFactor * (m_intakeEncoder.getPosition())/2);
    }

    public void setArmDistence(double speed, double distence) {
        double armRotations = m_intakeMotor.getEncoder().getPosition();
        while(armRotations>= distence){
            m_intakeMotor.set(speed);
        }
        m_intakeMotor.set(0.0);
    }

    // public double getIntakeEncoder() {
    //     return m_intakeEncoder.getPosition();// I have no idea if this works
    // }
    // public void resetEncoders(){
    //     m_intakeEncoder.setPosition(0.0);

    // }
}