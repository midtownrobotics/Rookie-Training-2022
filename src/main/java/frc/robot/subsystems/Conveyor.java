import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Conveyor extends SubsystemBase {
    WPI_TalonSRX conveyorMotor;
    public Conveyor(WPI_TalonSRX conveyorMotor) {
        this.conveyorMotor=conveyorMotor;

    }
    void motorOn(){
        conveyorMotor.set(1);
    }

    void motorOff(){
        conveyorMotor.set(0);
    }

    void motorReverse(){
        conveyorMotor.set(-1);
    }
 }
