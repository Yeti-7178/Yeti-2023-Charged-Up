// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;


//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Auton.*;
import frc.robot.Constants.ChassisConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.Auton.AutoTest;
import frc.robot.commands.drive.DefaultDrive;
// import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger; //this is imported multiple times so I deleted it
import frc.robot.subsystems.Arm;



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
  SendableChooser<Command> m_autonChooser = new SendableChooser<>();
  // Replace with CommandPS4Controller or CommandJoystick if needed

  XboxController m_driverController = new XboxController(OIConstants.controller1);
  XboxController m_coDriverController = new XboxController(OIConstants.controller2);

  

  public RobotContainer()
  {
    CameraServer.startAutomaticCapture();

    configureBindings();
    m_chassisSubsystem.setDefaultCommand
    (
      new DefaultDrive(m_chassisSubsystem,
      () -> -m_driverController.getLeftY(),
      () -> m_driverController.getRightX(),
      () -> ChassisConstants.squareInputs)
    );
    m_arm.intakeDeploy();

    if (Math.abs(m_coDriverController.getLeftY()) < ArmConstants.kJoystickSensitivity) {
      m_arm.setArmSpeed(m_coDriverController.getLeftY());
    }


    // add more to have more auton options
   // m_autonChooser.addOption("AutonTest",new AutoTest(m_chassisSubsystem,m_arm));
    m_autonChooser.addOption("Auton place a cone/cube and Move out of the hub station.",new Autogobackandplathform(m_chassisSubsystem,m_arm));
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
    new JoystickButton(m_coDriverController, Button.kY.value)
      .onTrue(
        new InstantCommand(m_arm::clawExtendDeploy)
      );
      //Extends the claw out
      new JoystickButton(m_driverController, Button.kRightBumper.value)
      .onTrue(
        new InstantCommand(m_arm::clawDeploy)
      );
      new JoystickButton(m_coDriverController, Button.kA.value)
      .onTrue(
        new InstantCommand(m_arm::ClawRotationDeploy)
      );
      new JoystickButton(m_coDriverController, Button.kRightBumper.value)
      //I don't understand why the spacing changes here or if there's reason behind that
.onTrue(
  new InstantCommand(m_arm::armDown)
)
.onFalse(
  new InstantCommand(m_arm::armStop)
);
new JoystickButton(m_coDriverController, Button.kLeftBumper.value)
.onTrue(
  new InstantCommand(m_arm::armUp)
)
.onFalse(
  new InstantCommand(m_arm::armStop)
);
new JoystickButton(m_coDriverController, Button.kX.value)
.onTrue(
  new InstantCommand(m_arm::intakeDeploy)
);




}
  
  

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
