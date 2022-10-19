package frc.robot.commands;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
public class RetractIntake extends CommandBase {
    Intake intake;
    public RetractIntake(Intake intake){
        this.intake = intake;
    }
    @Override
    public void initialize(){
        intake.retractIntake();
    }
    @Override
    public void end(boolean interrupted){
        intake.extendIntakeOff();
    }
}
