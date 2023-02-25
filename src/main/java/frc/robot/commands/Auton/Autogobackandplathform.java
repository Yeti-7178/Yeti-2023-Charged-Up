package frc.robot.commands.Auton;

import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.commands.drive.DriveDistance;
public class Autogobackandplathform extends SequentialCommandGroup {
    public Autogobackandplathform(Chassis chassis){
        addCommands(
            new SequentialCommandGroup(
                    new DriveDistance(110, -.3, chassis),
                    new DriveDistance(1, 0.3, chassis)
            ),
            new SequentialCommandGroup(
                new WaitCommand(4),
                new DriveDistance(100, 0.3, chassis)
            )
            

        );
    }
}