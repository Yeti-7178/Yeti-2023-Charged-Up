//If you need someone to yell at for bad code in this file yell at Andy
package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj.internal.DriverStationModeThread;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand; //apparently I don't need this ig
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Time;
import edu.wpi.first.wpilibj2.command.RepeatCommand;


public class AutonConePlatform extends SequentialCommandGroup {
    private final Arm m_arm;
    public AutonConePlatform(Arm arm, Chassis chassis) {
        m_arm = arm;
        

        addCommands
        (
            new SequentialCommandGroup(
                new InstantCommand(m_arm::setArmDistence)


            )
        );

        
    }


}
