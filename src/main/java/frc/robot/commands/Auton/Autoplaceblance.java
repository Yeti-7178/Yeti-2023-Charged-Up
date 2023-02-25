package frc.robot.commands.Auton;


import frc.robot.subsystems.Chassis;

import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.commands.drive.DriveDistance;

public class Autoplaceblance extends SequentialCommandGroup {
    public Autoplaceblance(Chassis chassis){
        addCommands(
            new SequentialCommandGroup(

                    new DriveDistance(110, -.3, chassis),
                     new DriveDistance(1, 0.3, chassis)
            )
           
        
        );
    }
    
}