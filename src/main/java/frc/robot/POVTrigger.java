package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class POVTrigger extends Trigger {
  XboxController controller;
  int angle;

  public POVTrigger(XboxController controller, int angle) {
    this.controller = controller;
    this.angle = angle;
  }

  @Override
  public boolean get() {
    return controller.getPOV() == angle;
  }
}
