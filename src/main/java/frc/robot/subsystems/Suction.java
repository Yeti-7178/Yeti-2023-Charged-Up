package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.ArmConstants;

public class Suction 
{
    final public Solenoid m_suction = new Solenoid(ArmConstants.kModuleID,PneumaticsModuleType.CTREPCM,3);
    public void suctionDeploy()
    {
        m_suction.toggle();
    }
}
