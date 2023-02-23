import edu.wpi.first.wpilibj.Compressor;


import frc.robot.Constants.ArmConstants;


private class Arm extends SubsystemBase
{
    Compressor m_compress = new Compressor(armPort, PneumaticsModuleType.CTREPCM);

    public void enableArm{
        m_compress.enableAnalog();
    }
    public void disableArm{
        m_compress.disableArm();
    }
}