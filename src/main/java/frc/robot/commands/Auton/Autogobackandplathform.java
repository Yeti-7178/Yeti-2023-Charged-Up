//Last used 3/23
package frc.robot.commands.Auton;

import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import frc.robot.commands.Arm.ArmDistance;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.drive.AutoBalance;
public class Autogobackandplathform extends SequentialCommandGroup {
    private final  Arm  m_arm;
    public Autogobackandplathform(Chassis chassis,Arm arm, AHRS ahrs){
        m_arm = arm;
        double kInitialRollOffset = ahrs.getRoll();
        addCommands(
                new SequentialCommandGroup(
                    //score the cube
                    new InstantCommand(m_arm::intakeDeploy),
                    new InstantCommand(m_arm::clawExtendDeploy),
                    new InstantCommand(m_arm::ClawRotationDeploy),
                    new WaitCommand(2),
                    new InstantCommand(m_arm::ClawRotationDeploy),
                    new WaitCommand(2),
                    new DriveDistance(4, .45, chassis),
                    new WaitCommand(2),
                    new InstantCommand(m_arm::clawDeploy),
                    new WaitCommand(2),
                    new DriveDistance(11, -.45, chassis),
                    new InstantCommand(m_arm::clawDeploy),
                    new InstantCommand(m_arm::clawExtendDeploy),
                    new InstantCommand(m_arm::intakeDeploy),
                    new WaitCommand(2),

                    //back up then get back to platform
                    new DriveDistance(100, -.2, chassis),
                    new WaitCommand(1),
                    new InstantCommand(arm::intakeDeploy),
                    new WaitCommand(0.5),
                    new DriveDistance(40, 0.2, chassis),
                    new RepeatCommand(
                        new AutoBalance(chassis, ahrs, kInitialRollOffset )//.withTimeout( 0.5)
                        //.andThen(new WaitCommand(0.5)
                    )
                )
               
                
           
            
         
            

        );
    }
}