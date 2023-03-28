package frc.robot.commands.Arm;



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

    public ArmDistance(double rotation,Arm subsystem){
        m_distance = rotation;
        m_arm = subsystem;
        addRequirements(m_arm);
    }

    @Override
    public void initialize(){
        m_complete = false;
        // start_encoders = m_arm.getAverageEncoderDistanceInches();
    }

    @Override
    public void end(boolean interrupted){
        m_arm.armStop();
    }

    @Override
    public void execute(){
        m_arm.setArmDistence(m_distance);
    }

    @Override
    public boolean isFinished(){
        return m_complete;
    }
}
