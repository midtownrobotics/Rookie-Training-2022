import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

public class Conveyor extends SubsystemBase {
    WPI_TalonSRX conveyorMotor;
    DigitalInput limitSwitchConveyor1;
    DigitalInput limitSwitchConveyor2;
    public Conveyor(WPI_TalonSRX conveyorMotor,DigitalInput limitSwitchConveyor1,DigitalInput limitSwitchConveyor2) {
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

    boolean isBallIn(){
        return !limitSwitchConveyor1.get() || !limitSwitchConveyor2.get();
    }
 }
