package frc.robot.commands.Auton;

import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Time;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import frc.robot.commands.Arm.ArmDistance;
import frc.robot.commands.drive.DriveDistance;
public class AutoTest extends SequentialCommandGroup {
     private final  Arm  m_arm;
     private final Time m_timer;
     
    public AutoTest(Chassis chassis,Arm arm,Time timer){
        m_arm = arm;
        m_timer = timer;
    

        addCommands(
            new SequentialCommandGroup(
                new InstantCommand(m_timer::start),
                RepeatCommand(
                    new InstantCommand(m_arm::armDown)
                    .until(m_timer::cheakTime)
                ),
                new InstantCommand(m_timer::stop)
                
                    // new InstantCommand(m_arm::clawDeploy),
                    // new WaitCommand(5),
                    // new InstantCommand(m_arm::clawExtendDeploy),
                    // new WaitCommand(5),
                    // new InstantCommand(m_arm::ClawRotationDeploy)

            )
            // new SequentialCommandGroup(
            //     new DriveDistance(-40, 0.2, chassis)
            // )
        );
    }
    private Command RepeatCommand(ParallelRaceGroup until) {
        return null;
    }
}
