package frc.robot.commands;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
public class DeployIntake extends CommandBase {
    Intake intake;
    public DeployIntake(Intake intake){
        this.intake = intake;
    }
    @Override
    public void initialize(){
        intake.deployIntake();
    }
    @Override
    public void end(boolean interrupted){
        intake.extendIntakeOff();
    }
}
