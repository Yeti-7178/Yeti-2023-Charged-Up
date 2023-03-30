//Last used 3/23
package frc.robot.commands.Auton;

import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Suction;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.drive.AutoBalance;
public class Autogobackandplathform extends SequentialCommandGroup {
    private final  Arm  m_arm;
    private Suction m_suction;
    public Autogobackandplathform(Chassis chassis,Arm arm, AHRS ahrs, Suction suction){
        m_arm = arm;
        m_suction = suction;
        double kInitialRollOffset = ahrs.getRoll();
        
        addCommands(
            new ParallelCommandGroup(
                new SequentialCommandGroup(
                    //new InstantCommand(m_arm::compressorDisable),

                    //score the cube
                    new InstantCommand(m_arm::intakeDeploy),
                    new DriveDistance(4, .3, chassis),
                    new InstantCommand(m_arm::clawExtendDeploy),
                    new WaitCommand(1),
                    
                    //new WaitCommand(0.5),
                    new InstantCommand(m_arm::clawDeploy),
                    //new WaitCommand(2),
                    new DriveDistance(11, -.45, chassis),
                    new InstantCommand(m_arm::clawDeploy),
                    new InstantCommand(m_arm::clawExtendDeploy),
                    new InstantCommand(m_arm::intakeDeploy),
                    new WaitCommand(0.5),

                    //new InstantCommand(m_arm::armMoveDown),

                    //back up then get back to platform
                    new DriveDistance(93, -.25, chassis),
                    new WaitCommand(1),
                    
                    //new InstantCommand(m_arm::intakeDeploy),
                    new WaitCommand(0.5),
                    new DriveDistance(38, 0.22, chassis),
                    new RepeatCommand(
                        new AutoBalance(chassis, ahrs, kInitialRollOffset, suction, m_arm )//.withTimeout( 0.5)
                        //.andThen(new WaitCommand(0.5)
                    )
                ),
                //just deploy suction after 14.75 seconds
                new SequentialCommandGroup(
                    new WaitCommand(14.75),
                    new InstantCommand(m_suction::suctionDeploy)
                    )
                )
            );
        
               
                
           
            
         
            

        
    }
}