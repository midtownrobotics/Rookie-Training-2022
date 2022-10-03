package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Outtake extends SubsystemBase {
    CANSparkMax shooter;
    public Outtake(CANSparkMax shooter){
        this.shooter = shooter;
    }
    public void motorOn(){
        shooter.set(1);
    
    }
    public void motorOff(){
        shooter.set(0);
    }
}
