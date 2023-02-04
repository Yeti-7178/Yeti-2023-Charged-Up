package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class DefaultDrive extends CommandBase {
    private final Chassis m_drive;
    private final DoubleSupplier m_xSpeed;
    private final DoubleSupplier m_zRotation;
    public final BooleanSupplier m_joyInput;

    
    public DefaultDrive(Chassis sybsystem, DoubleSupplier xSpeed, DoubleSupplier zRotation, BooleanSupplier joyInput){
        m_drive = sybsystem;
        m_xSpeed = xSpeed;
        m_zRotation = zRotation;
        m_joyInput = joyInput;
        addRequirements(m_drive);
    }

    @Override
    public void execute(){
        m_drive.drive(m_xSpeed.getAsDouble(), m_zRotation.getAsDouble(), m_joyInput.getAsBoolean());
    }
}
