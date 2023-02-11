package frc.robot.commands.Auton;

import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.DriveDistance;
public class AutoTest extends SequentialCommandGroup {
    public AutoTest(Chassis chassis){
        addCommands(
            new SequentialCommandGroup(
                new DriveDistance(5, .2, chassis)
            )
        );
    }
}
