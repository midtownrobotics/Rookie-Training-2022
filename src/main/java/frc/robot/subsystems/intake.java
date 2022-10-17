package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 

public class intake extends SubsystemBase{
<<<<<<< HEAD
    

=======
    private WPI_TalonSRX deployMotorMaster;
    private WPI_TalonSRX deployMotorFollower;
    private WPI_TalonSRX runnerMotor;
    private DigitalInput limitSwitchIntake1;
    private DigitalInput limitSwitchIntake2;

    public intake(WPI_TalonSRX deployMotorMaster,WPI_TalonSRX deployMotorFollower,WPI_TalonSRX runnerMotor,DigitalInput limitSwitchIntake1,DigitalInput limitSwitchIntake2){
        this.deployMotorMaster = deployMotorMaster;
        this.deployMotorFollower = deployMotorFollower;
        this.runnerMotor = runnerMotor;
        this.limitSwitchIntake1 = limitSwitchIntake1;
        this.limitSwitchIntake2 = limitSwitchIntake2;
        
        deployMotorFollower.follow(deployMotorMaster);
        deployMotorFollower.setInverted(true);
    }
>>>>>>> 821cb24cb88685ddb2bbc06d785247d3fc133c6c
}
