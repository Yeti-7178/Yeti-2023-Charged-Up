// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.Auton.*;
import frc.robot.Constants.ChassisConstants;
import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.drive.AutoBalance;
import frc.robot.commands.drive.DefaultDrive;
// import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.NavSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import java.util.concurrent.TimeUnit;
import frc.robot.subsystems.Suction;
import frc.robot.subsystems.Time;
// import frc.robot.subsystems.MainAutoBalance;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  private final Chassis m_chassisSubsystem = new Chassis();
  public final Arm m_arm = new Arm();
  public final Suction m_suction = new Suction();
  public final Time m_timer = new Time();
  private final NavSubsystem m_navSubsystem;
  SendableChooser<Command> m_autonChooser = new SendableChooser<>();
  // Replace with CommandPS4Controller or CommandJoystick if needed

  XboxController m_driverController = new XboxController(OIConstants.controller1);
  XboxController m_coDriverController = new XboxController(OIConstants.controller2);

  private AHRS ahrs;
  private double kInitialYawOffset =0;
  public RobotContainer()
  {
    try {
      ahrs = new AHRS(SPI.Port.kMXP);
      ahrs.enableLogging(true);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }

    try {
      TimeUnit.SECONDS.sleep(2);
      //RoboRio is flipped 90 degrees so we now need yaw as to pitch, though I think this wrong - Marcus
      kInitialYawOffset = ahrs.getYaw();
    } catch (InterruptedException e) {
      DriverStation.reportError("An error in getting the navX Yaw: " + e.getMessage(), true);
    }

    m_navSubsystem = new NavSubsystem(ahrs, kInitialYawOffset);


    CameraServer.startAutomaticCapture();

    configureBindings();
    m_chassisSubsystem.setDefaultCommand
    (
      new DefaultDrive(m_chassisSubsystem,
      () -> -m_driverController.getLeftY()*.6,
      () -> m_driverController.getRightX()*.6,
      () -> ChassisConstants.squareInputs)
    );
    m_arm.intakeDeploy();




    // add more to have more auton options
   // m_autonChooser.addOption("AutonTest",new AutoTest(m_chassisSubsystem,m_arm));
    m_autonChooser.addOption("Place a cube, leave the community, go back on the platform and autobalance.",new Autogobackandplathform(m_chassisSubsystem,m_arm,ahrs, m_suction));
    m_autonChooser.addOption("Auton place a cone/cube and platform",new AutonConePlatform(m_arm,m_chassisSubsystem));
    m_autonChooser.addOption("Auton place",new Autoplaceblance(m_chassisSubsystem));


    Shuffleboard.getTab("Autonomous").add(m_autonChooser).withSize(2,1);
  

    
  }


  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // new Trigger(null, null)

    //claw e
  

      //Extends the claw out



      new JoystickButton(m_driverController, Button.kRightBumper.value)
      .onTrue(
        new InstantCommand(m_arm::clawDeploy)
      );
      new JoystickButton(m_driverController, Button.kA.value)
      .onTrue(
        new InstantCommand(m_suction::suctionDeploy)
      );
          
      new JoystickButton(m_driverController, Button.kLeftBumper.value)
      .whileTrue(
      new RepeatCommand(
        new AutoBalance(m_chassisSubsystem, ahrs, kInitialYawOffset, m_suction, m_arm)
      )
      );

      //delete before competition
      new JoystickButton(m_driverController, Button.kB.value)
      .onTrue(
        new InstantCommand(m_arm::armMoveUp)
      );
      new JoystickButton(m_driverController, Button.kX.value)
      .onTrue(
        new InstantCommand(m_arm::armMoveDown)
      );


      // co-driver DriverController buttons

      //moves the claw out
new JoystickButton(m_coDriverController, Button.kY.value)
.onTrue(
 new InstantCommand(m_arm::clawExtendDeploy)
);
    //claw rotation
new JoystickButton(m_coDriverController, Button.kA.value)
.onTrue(
  new InstantCommand(m_arm::ClawRotationDeploy)
);
// move the arm down
new JoystickButton(m_coDriverController, Button.kRightBumper.value)
.onTrue(
  new InstantCommand(m_arm::armDown)
)
.onFalse(
  new InstantCommand(m_arm::armStop)
);
//arm up
new JoystickButton(m_coDriverController, Button.kLeftBumper.value)
.onTrue(
  
  new InstantCommand(m_arm::armUp)
)
.onFalse(
  new InstantCommand(m_arm::armStop)
);


//arm stick thing that moves with claw on it.(what do we call that?)
new JoystickButton(m_coDriverController, Button.kX.value)
.onTrue(
  new InstantCommand(m_arm::intakeDeploy)
);

}


// new JoystickButton(m_driverController, Button.kLeftBumper.value)
// .onTrue(
//   new MainAutoBalance(m_chassisSubsystem, ahrs, kInitialYawOffset)
// )
// .onFalse(
//   m_chassisSubsystem.getDefaultCommand()
// );

// }
  
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */


  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autonChooser.getSelected();
  }


}
