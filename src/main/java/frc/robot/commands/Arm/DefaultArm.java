package frc.robot.commands.Arm;

import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class DefaultArm extends CommandBase {
    private final Arm m_arm;
    private final DoubleSupplier m_ySpeed;
    public final BooleanSupplier m_joyInput;

    
    public DefaultArm(Arm subsystem, DoubleSupplier ySpeed, BooleanSupplier joyInput){ 
        //why is it "sybsystem" - andy
        m_arm = subsystem;
        m_ySpeed = ySpeed;
        m_joyInput = joyInput;
        addRequirements(m_arm);
    }

    @Override
    public void execute(){
        m_arm.arm(m_ySpeed.getAsDouble(), m_joyInput.getAsBoolean());
    }
}
