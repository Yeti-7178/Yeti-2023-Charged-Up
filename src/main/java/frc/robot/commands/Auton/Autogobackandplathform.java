package frc.robot.commands.Auton;

import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.Arm.ArmDistance;
import frc.robot.commands.drive.DriveDistance;
public class Autogobackandplathform extends SequentialCommandGroup {
    private final  Arm  m_arm;
    public Autogobackandplathform(Chassis chassis,Arm arm){
        m_arm = arm;
        addCommands(
            new SequentialCommandGroup(
                
               
           
                new InstantCommand(m_arm::clawDeploy),
                new DriveDistance(150, -.3, chassis),
                new DriveDistance(1, 0.3, chassis)
            )
         
            

        );
    }
}