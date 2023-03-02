package frc.robot.commands.Auton;

import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.drive.DriveDistance;
public class AutoTest extends SequentialCommandGroup {
     private final  Arm  m_arm;
    public AutoTest(Chassis chassis,Arm arm){
        m_arm = arm;
        addCommands(
            new SequentialCommandGroup(
                
                    new InstantCommand(m_arm::clawDeploy),
                    new WaitCommand(5),
                    new InstantCommand(m_arm::clawExtendDeploy),
                    new WaitCommand(5),
                    new InstantCommand(m_arm::ClawRotationDeploy)

            )
            // new SequentialCommandGroup(
            //     new DriveDistance(-40, 0.2, chassis)
            // )
        );
    }
}
