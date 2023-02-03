package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class DefaultDrive extends CommandBase {
    private final Chassis m_drive;
    private final DoubleSupplier m_leftSpeed;
    private final DoubleSupplier m_rightSpeed;
    public final BooleanSupplier m_joyInput;

    
    public DefaultDrive(Chassis sybsystem, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed, BooleanSupplier joyInput){
        m_drive = sybsystem;
        m_leftSpeed = leftSpeed;
        m_rightSpeed = rightSpeed;
        m_joyInput = joyInput;
        addRequirements(m_drive);
    }

    @Override
    public void execute(){
        m_drive.drive(m_leftSpeed.getAsDouble(), m_rightSpeed.getAsDouble(), m_joyInput.getAsBoolean());
    }
}
