package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Outtake;

public class OuttakeCommand extends CommandBase {
    public Outtake outtake;
    public OuttakeCommand(Outtake Outtake){
        this.outtake = Outtake;
        addRequirements(Outtake);
    }

    @Override
    public void initialize(){
        outtake.motorOn();
    } 

    @Override
    public void end(boolean interrupted){
        outtake.motorOff();
    }
}
