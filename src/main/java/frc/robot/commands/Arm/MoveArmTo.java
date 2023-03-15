package frc.robot.commands.Arm;

import frc.robot.subsystems.Arm;
import frc.robot.Constants.ArmConstants;
//import com.revrobotics.SparkMaxPIDController;


public class MoveArmTo {
    public double errorPrevious = 0;
    private double error;
    private double proportionalInput;
    private double derivativeInput;
    private double integralInput;

    public MoveArmTo(int level, Arm arm/*, SparkMaxPIDController pidController*/) {
        double location = 0;
        //determine what level to move the motor to
        switch(level) {
            case 1: location = ArmConstants.kLowestLevelPosition;
                break;
            case 2: location = ArmConstants.kMiddleLevelPosition;
                break;
            case 3: location = ArmConstants.kHighestLevelPosition;
                break;
        }
        
        //set the error to the difference in position, may need to switch around the values
        error = location - arm.m_intakeMotor.getEncoder().getPosition();

        //establish values for the inputs
        proportionalInput = error * ArmConstants.kProportionalTweak;
        derivativeInput = (error - errorPrevious) * ArmConstants.kDerivativeTweak/ArmConstants.kdt;
        integralInput += ArmConstants.kIntegralTweak * error / ArmConstants.kdt;

        //to whoever reviews my code: feel free to find a better way to change the motor velocity, 
        //I just don't know a better way and I know that this works
        arm.m_intakeMotor.set(proportionalInput + derivativeInput + integralInput);

        //note to myself: it might be useful to use a setCANTimeout in whatever file this is called 
        //or maybe here to establish a clear and definite dt
    }
}
