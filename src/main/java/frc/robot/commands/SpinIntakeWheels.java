package frc.robot.commands;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
public class SpinIntakeWheels extends CommandBase {
    Intake intake;
    public SpinIntakeWheels(Intake intake){
        this.intake = intake;
    }
    @Override
    public void initialize(){
        intake.motorRunOn();
    }
    @Override
    public void end(boolean interrupted){
        intake.motorRunOff();
    }
}
