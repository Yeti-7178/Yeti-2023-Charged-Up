package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import com.revrobotics.SparkMaxLimitSwitch;

public class Arm extends SubsystemBase
{
    
    public final CANSparkMax m_intakeMotor = new CANSparkMax(ArmConstants.motorPort, MotorType.kBrushless);
    final Compressor m_compress = new Compressor(ArmConstants.armPort, PneumaticsModuleType.CTREPCM);
    public RelativeEncoder m_intakeEncoder = m_intakeMotor.getEncoder();
    public SparkMaxLimitSwitch bottomLimit = m_intakeMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    public SparkMaxLimitSwitch topLimit = m_intakeMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);


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

    //My idea is that this value can be set in other files such as auton or whatever
    public double moveUpTo = -30; //-47
    public double moveDownTo = 30; //130
    public void armMoveUp(){
        double startPos = m_intakeEncoder.getPosition();

        while (m_intakeEncoder.getPosition() > startPos + moveUpTo) {
            m_intakeMotor.set(ArmConstants.kHangMotorSpeed);

            //should stop the function if the motor hits the limit switch
            if (topLimit.isPressed()) {
                m_intakeMotor.set(0);
                return;
            }
        }
    }

    //this is just a copy of armMoveUp
    public void armMoveDown(){
        double startPos = m_intakeEncoder.getPosition();

        while (m_intakeEncoder.getPosition() < startPos + moveDownTo) {
            m_intakeMotor.set(-ArmConstants.kHangMotorSpeed);
            if (bottomLimit.isPressed()) {
                m_intakeMotor.set(0);
                return;
            }
        }
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
        return(m_intakeEncoder.getPosition());
    }
    public void setArmDistence() {
        m_intakeEncoder.setPosition(5);
    }

    public void armInit() {
        m_intakeEncoder.setPosition(0);

    }


    // compressor functions
    public void compressorDisable() {
        m_compress.disable();
    }

    public void compressorEnable() {
        m_compress.enableAnalog(0, 60);
    }
    

    // public double getIntakeEncoder() {
    //     return m_intakeEncoder.getPosition();// I have no idea if this works
    // }
    // public void resetEncoders(){
    //     m_intakeEncoder.setPosition(0.0);

    // }
    @Override
    public void periodic(){
        SmartDashboard.putNumber("Intake Rotations", m_intakeEncoder.getPosition());
    }
}