package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  private CANSparkMax winchSpark, winchFollower, pivotSpark;
  private DigitalInput hookLimit;
  private SparkMaxPIDController winchPID, pivotPID;
  private double winchTarget = 0, pivotTarget = 0;

  public Climber(
      CANSparkMax pivotMaster,
      CANSparkMax pivotFollower,
      CANSparkMax winchMaster,
      CANSparkMax winchFollower,
      DigitalInput hookLimit) {
    this.winchSpark = winchMaster;
    this.pivotSpark = pivotMaster;
    this.hookLimit = hookLimit;
    this.winchFollower = winchFollower;

    winchFollower.follow(winchMaster, true);
    pivotFollower.follow(pivotMaster, true);
    winchSpark.setOpenLoopRampRate(Constants.winch_rampRate);
    pivotSpark.setOpenLoopRampRate(Constants.pivot_rampRate);
    winchSpark.setClosedLoopRampRate(Constants.winch_rampRate);
    pivotSpark.setClosedLoopRampRate(Constants.pivot_rampRate);
    winchPID = winchSpark.getPIDController();
    pivotPID = pivotSpark.getPIDController();

    winchPID.setP(Constants.winch_kP);
    winchPID.setI(Constants.winch_kI);
    winchPID.setD(Constants.winch_kD);
    winchPID.setFF(Constants.winch_kF);
    winchPID.setIZone(Constants.winch_iZone);
    pivotPID.setP(Constants.pivot_kP);
    pivotPID.setI(Constants.pivot_kI);
    pivotPID.setD(Constants.pivot_kD);
    pivotPID.setFF(Constants.pivot_kF);
    pivotPID.setIZone(Constants.pivot_iZone);
    pivotPID.setOutputRange(-1, 1);

    winchSpark.getEncoder().setPosition(0);
    pivotSpark.getEncoder().setPosition(0);
    setPivotPosition(0);
  }

  public void runWinch(double power) {
    winchSpark.set(power);
  }

  public void setWinchPosition(double setpoint) {
    winchTarget = setpoint;
    winchPID.setReference(setpoint, CANSparkMax.ControlType.kPosition);
  }

  public void setWinchRelativePosition(double setpoint) {
    setWinchPosition(getWinchPosition() + setpoint);
  }

  public void runPivot(double power) {
    pivotSpark.set(power);
  }

  public double pivotPosition() {
    return pivotSpark.getEncoder().getPosition();
  }

  public double winchPosition() {
    return winchSpark.getEncoder().getPosition();
  }

  public void lockPivot() {
    setPivotPosition(pivotSpark.getEncoder().getPosition());
  }

  public void upPivot() {
    setPivotPosition(pivotSpark.getEncoder().getPosition() - 5);
  }

  public void downPivot() {
    setPivotPosition(pivotSpark.getEncoder().getPosition() + 5);
  }

  public void downWinch() {
    setWinchPosition(winchSpark.getEncoder().getPosition() + 5);
  }

  public void upWinch() {
    setWinchPosition(winchSpark.getEncoder().getPosition() - 5);
  }

  public double getWinchPosition() {
    return winchSpark.getEncoder().getPosition();
  }

  public double getPivotPosition() {
    return pivotSpark.getEncoder().getPosition();
  }

  public void onStartup() {
    pivotTarget = 0;
    winchTarget = 0;
    cancelPivotPID();
    cancelWinchPID();
    resetPivot();
    resetWinch();
  }

  public void setPivotPosition(double setpoint) {
    pivotTarget = setpoint;
    pivotPID.setReference(setpoint, CANSparkMax.ControlType.kPosition);
  }

  public void setPivotRelativePosition(double setpoint) {
    setPivotPosition(getPivotPosition() + setpoint);
  }

  public boolean isHookPressed() {
    return !hookLimit.get();
  }

  public void resetWinch() {
    winchSpark.getEncoder().setPosition(0);
  }

  public void cancelWinchPID() {
    winchPID.setReference(0, CANSparkMax.ControlType.kVoltage);
  }

  public void resetPivot() {
    pivotSpark.getEncoder().setPosition(0);
  }

  public void cancelPivotPID() {
    pivotPID.setReference(0, CANSparkMax.ControlType.kVoltage);
  }

  public boolean winchPIDFinished() {
    return Math.abs(winchSpark.getEncoder().getPosition() - winchTarget)
            < Constants.winch_position_tolerance
        && Math.abs(winchSpark.getEncoder().getVelocity()) < Constants.winch_velocity_tolerance;
  }

  public boolean pivotPIDFinished() {
    return Math.abs(pivotSpark.getEncoder().getPosition() - pivotTarget)
            < Constants.pivot_position_tolerance
        && Math.abs(pivotSpark.getEncoder().getVelocity()) < Constants.winch_velocity_tolerance;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    builder.addDoubleProperty("winch encoder", winchSpark.getEncoder()::getPosition, null);
    builder.addDoubleProperty("pivot encoder", pivotSpark.getEncoder()::getPosition, null);
    builder.addDoubleProperty("winch target", () -> winchTarget, null);
    builder.addDoubleProperty("pivot target", () -> pivotTarget, null);
    builder.addBooleanProperty("Hook Limit", this::isHookPressed, null);
    builder.addDoubleProperty("Winch Master Temperature", winchSpark::getMotorTemperature, null);
    builder.addDoubleProperty(
        "Winch Follower Temperature", winchFollower::getMotorTemperature, null);
    builder.addDoubleProperty("pivot power", pivotSpark::getAppliedOutput, null);
    builder.addDoubleProperty("pivot velocity", pivotSpark.getEncoder()::getVelocity, null);
    builder.addDoubleProperty("pivot P", pivotPID::getP, (val) -> pivotPID.setP(val));
    builder.addDoubleProperty("pivot I", pivotPID::getI, (val) -> pivotPID.setI(val));
    builder.addDoubleProperty("pivot D", pivotPID::getD, (val) -> pivotPID.setD(val));
    builder.addDoubleProperty("winch power", winchSpark::getAppliedOutput, null);
    builder.addDoubleProperty("winch velocity", winchSpark.getEncoder()::getVelocity, null);
    builder.addDoubleProperty("winch P", winchPID::getP, (val) -> winchPID.setP(val));
    builder.addDoubleProperty("winch I", winchPID::getI, (val) -> winchPID.setI(val));
    builder.addDoubleProperty("winch D", winchPID::getD, (val) -> winchPID.setD(val));
  }
}
