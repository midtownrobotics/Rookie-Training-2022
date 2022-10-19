package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;



public class ConveyorForward extends CommandBase {
    Conveyor conveyor;
    public ConveyorForward(Conveyor conveyor) {
        this.conveyor = conveyor;
    }
    @Override
    public void initialize(){
        conveyor.motorOn();
    }

    @Override
    public void end(boolean interrupted){
        conveyor.motorOff();
    }

    //limit Switches pressed?
    @Override
    public boolean isFinished(){
        return conveyor.isBallIn();
    }
}