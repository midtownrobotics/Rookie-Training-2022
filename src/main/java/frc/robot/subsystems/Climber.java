package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

    private CANSparkMax winchMaster, winchFollower, pivotMaster, pivotFollower;
    private DigitalInput hookSwitch;

    public Climber(
        CANSparkMax winchMaster,
        CANSparkMax winchFollower,
        CANSparkMax pivotMaster,
        CANSparkMax pivotFollower,
        DigitalInput hookSwitch) {
        this.winchMaster = winchMaster;
        this.winchFollower = winchFollower;
        this.pivotMaster = pivotMaster;
        this.pivotFollower = pivotFollower;
        this.hookSwitch = hookSwitch;
        

        this.winchFollower.follow(winchMaster, true);
        this.pivotFollower.follow(pivotMaster, true);
    
    }

    public void runWinch(double power){
        this.winchMaster.set(power);
    }

    public void runPivot(double power){
        this.pivotMaster.set(power);
    }
    

}