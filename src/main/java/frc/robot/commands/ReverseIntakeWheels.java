package frc.robot.commands;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
public class ReverseIntakeWheels extends CommandBase {
    Intake intake;
    public ReverseIntakeWheels(Intake intake){
        this.intake = intake;
    }
    @Override
    public void initialize(){
        intake.motorRunReverse();
    }
    @Override
    public void end(boolean interrupted){
        intake.motorRunOff();
    }
}
