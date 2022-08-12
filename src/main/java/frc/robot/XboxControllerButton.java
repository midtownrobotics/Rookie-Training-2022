package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

public class XboxControllerButton extends Button {

  XboxController xbox;
  int axis;

  public XboxControllerButton(XboxController xbox, int axis) {
    this.xbox = xbox;
    this.axis = axis;
  }

  @Override
  public boolean get() {
    return Math.abs(xbox.getRawAxis(axis)) >= 0.2;
  }

  public double getRawAxis() {
    return xbox.getRawAxis(axis);
  }
}
