package frc.robot.subsystems;

public class Climber extends SubsystemBase{

    private CANSparkMax winchMaster, winchFollower, pivotMaster, pivotFollower;
    private DigitalInput hookSwitch;
    private AnalogPotentiometer potMeter;

    public Climber(
        CANSparkMax winchMaster,
        CANSparkMax winchFollower,
        CANSparkMax pivotMaster,
        CANSparkMax pivotFollower,
        DigitalInput hookSwitch,
        AnalogPotentiometer potMeter){
        this.winchMaster = winchMaster;
        this.winchFollower = winchFollower;
        this.pivotMaster = winchMaster;
        this.pivotFollower = pivotFollower;
        this.hookSwitch = hookSwitch;
        this.potMeter = potMeter;

        this.winchFollower.follow(winchMaster, true);
        this.pivotFollower.follow(pivotMaster, true);
    
    }

    public runWinch(double power){
        this.winchMaster.set(power);
    }

    public runPivot(double power){
        this.pivotMaster.set(power);
    }

    
}


