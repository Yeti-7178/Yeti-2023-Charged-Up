// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.fasterxml.jackson.databind.deser.impl.FailingDeserializer;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{

  public static final class ChassisConstants
  {
    public static final int kLeftFrontPort1 = 1;
    public static final int kLeftFrontPort2 = 2;
    public static final int kRightFrontPort1 = 5;
    public static final int kRightFrontPort2 = 6;
    public static final int kRightRearPort1 = 7;
    public static final int kRightRearPort2 = 8;
    public static final int kLeftRearPort1 = 3;
    public static final int kLeftRearPort2 = 4;
    public static final int amrmortor = 10;

    public static final boolean squareInputs = true;

    public static final int kCurrentLimit = 40;
    
    public static final double kAutoRotationSpeed = 0.2; // speed to rotate for auto aim
    public static final double kEncoderConversionFactor = 6 * Math.PI / 9.52;
    
  }
  public static final class OIConstants{
    public static final int controller1 = 0;
    public static final int controller2 = 2;
  }
  public static final class ArmConstants{
    public static final int armPort = 10;
    public static final int motorPort = 20;
    public static final int kModuleID = 10;
    public static final int kCurrentLimit = 40;
    public static final double kHangMotorSpeed = 20;
    public static final double kEncoderConversionFactor = 6 * Math.PI / 9.52;
  }

  
}
