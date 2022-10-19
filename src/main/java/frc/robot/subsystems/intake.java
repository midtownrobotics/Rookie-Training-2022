package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 

public class Intake extends SubsystemBase{
    private WPI_TalonSRX deployMotorMaster;
    private WPI_TalonSRX deployMotorFollower;
    private WPI_TalonSRX runnerMotor;
    private DigitalInput limitSwitchIntake1;
    private DigitalInput limitSwitchIntake2;

    public Intake(WPI_TalonSRX deployMotorMaster,WPI_TalonSRX deployMotorFollower,WPI_TalonSRX runnerMotor,DigitalInput limitSwitchIntake1,DigitalInput limitSwitchIntake2){
        this.deployMotorMaster = deployMotorMaster;
        this.deployMotorFollower = deployMotorFollower;
        this.runnerMotor = runnerMotor;
        this.limitSwitchIntake1 = limitSwitchIntake1;
        this.limitSwitchIntake2 = limitSwitchIntake2;
        
        deployMotorFollower.follow(deployMotorMaster);
        deployMotorFollower.setInverted(true);
    }

    public void motorRunOn(){
        runnerMotor.set(1);
    }

    public void motorRunOff(){
        runnerMotor.set(0);
    }

    public void motorRunReverse(){
        runnerMotor.set(-1);
    }

    public void deployIntake(){
        deployMotorMaster.set(-1);
    }

    public void retractIntake(){
        deployMotorMaster.set(1);
    }

    public void extendIntakeOff(){
        deployMotorMaster.set(0);
    }

    public boolean isExtended(){
        return !limitSwitchIntake1.get() && !limitSwitchIntake2.get();
    }
}
