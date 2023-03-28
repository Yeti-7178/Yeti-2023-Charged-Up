package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Time extends SubsystemBase {
    private Timer timer = new Timer();
    public void start(){
        timer.start();
    }
    public boolean cheakTime(){
        return(timer.hasElapsed(.50));
    }
    public void stop(){
        timer.stop();
    }
    public BooleanSupplier hasElapsed(double d) {
        return null;
    }
}
