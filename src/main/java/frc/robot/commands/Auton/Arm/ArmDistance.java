package frc.robot.commands.Auton.Arm;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
public class ArmDistance extends CommandBase{
    public final Arm m_arm;
    private double m_distance;
    private double m_speed;
    private double start_encoders;
    private boolean m_complete = false;

    public ArmDistance(double inches, double speed, Arm subsystem){
        m_distance = inches;
        m_speed = speed;
        m_arm = subsystem;
        addRequirements(m_arm);
    }

    @Override
    public void initialize(){
        m_complete = false;
        start_encoders = m_arm.getAverageEncoderDistanceInches();
    }

    @Override
    public void end(boolean interrupted){
        m_arm.armStop();
    }

    @Override
    public void execute(){
            if(Math.abs(m_arm.getAverageEncoderDistanceInches()-start_encoders)>=m_distance){
                m_arm.armStop();
                m_complete = true;
            }else{
                m_arm.setArmDistence(10);
            }
    
        // }else{
        //     if(Math.abs(m_drive.getAverageEncoderDistanceInches()-start_encoders)<=m_distance){
        //         m_drive.drive(0.0,0.0,0.0);
        //         m_complete = true;
        //     }else{
        //         m_drive.drive(m_speed,0.0,0.0);
        //     }
    
        // }
    }

    @Override
    public boolean isFinished(){
        return m_complete;
    }
}
