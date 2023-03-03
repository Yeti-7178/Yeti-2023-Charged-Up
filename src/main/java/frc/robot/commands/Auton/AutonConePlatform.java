//If you need someone to yell at for bad code in this file yell at Andy
package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj.internal.DriverStationModeThread;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand; //apparently I don't need this ig
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.Arm.ArmDistance;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Chassis;

public class AutonConePlatform extends SequentialCommandGroup {
    private final Arm m_arm;
    public AutonConePlatform(Arm arm, Chassis chassis) {
        m_arm = arm;
        addCommands(
            new SequentialCommandGroup(
                //deploy the arm
              
                    //we may need to move this to after the DriveDistance if the center of gravity is too high while moving
                    //also we should probably isolate and test this first
                  
                
                   
                //drop the item
                // new DriveDistance(0, .2, chassis), //ADD VALUES
                // new DriveDistance(.5, -0.45, chassis),
                // new DriveDistance(.6, 0.45, chassis),
                

                new InstantCommand(m_arm::ClawRotationDeploy),
                new WaitCommand(2),
                new InstantCommand(m_arm::ClawRotationDeploy),
                new WaitCommand(2),
                new InstantCommand(m_arm::clawDeploy),
                new WaitCommand(2),
                new DriveDistance(9, -.45, chassis),
                new InstantCommand(m_arm::clawDeploy),
                new WaitCommand(2),

                new DriveDistance(115, -0.45, chassis)
               
                // new WaitCommand(5),
                // new InstantCommand(m_arm::clawDeploy),
                // new WaitCommand(2)
                // new InstantCommand(m_arm::clawExtendDeploy),
                //leave the community
                // 
                //new WaitCommand(2),
                //new DriveDistance(60, 0.45, chassis)
                
            )
        );
    }

}
