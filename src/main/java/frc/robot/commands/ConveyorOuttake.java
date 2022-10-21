package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Outtake;
import frc.robot.subsystems.Conveyor;

public class ConveyorOuttake extends CommandBase {
    public Outtake outtake;
    public Conveyor conveyor;
    public ConveyorOuttake(Outtake outtake, Conveyor conveyor) {
        this.conveyor = conveyor;
        this.outtake = outtake;
    }

    @Override
    public void initialize(){
        conveyor.motorOn();
        outtake.motorOn();
    }

    @Override
    public void end(boolean interrupted){
        conveyor.motorOff();
        outtake.motorOff();
    }
}
