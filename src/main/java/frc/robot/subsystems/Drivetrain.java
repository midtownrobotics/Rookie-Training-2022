package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import java.util.Map;

public class Drivetrain extends SubsystemBase {

  private WPI_TalonSRX leftMaster, rightMaster;
  private WPI_VictorSPX leftFollower1, leftFollower2, rightFollower1, rightFollower2;

  private WPI_PigeonIMU pigeon;

  private DifferentialDriveOdometry odometry;
  public DifferentialDriveKinematics kinematics;

  private Field2d field;

  public static enum DriveMode {
    TANK,
    ARCADE,
    SPLIT_ARCADE
  }

  public static enum DampingMode {
    LINEAR,
    EXP_2,
    QUADRATIC,
    CUBIC
  }

  private SendableChooser<DriveMode> driveChooser = new SendableChooser<>();
  private SendableChooser<DampingMode> dampingChooser = new SendableChooser<>();
  private NetworkTableEntry deadzone, coefficient;

  /**
   * Basic DriveTrain model with 2 talons and 4 victors, and a gyro.
   *
   * @author benjaminborthwick, sampdubs
   */
  public Drivetrain(
      WPI_TalonSRX lMaster,
      WPI_VictorSPX lFollower1,
      WPI_VictorSPX lFollower2,
      WPI_TalonSRX rMaster,
      WPI_VictorSPX rFollower1,
      WPI_VictorSPX rFollower2) {
    super();
    leftMaster = lMaster;
    rightMaster = rMaster;
    leftFollower1 = lFollower1;
    leftFollower2 = lFollower2;
    rightFollower1 = rFollower1;
    rightFollower2 = rFollower2;
;

    leftMaster.setInverted(false);
    leftFollower1.setInverted(false);
    leftFollower2.setInverted(false);
    rightMaster.setInverted(true);
    rightFollower1.setInverted(true);
    rightFollower2.setInverted(true);

    leftMaster.setNeutralMode(NeutralMode.Brake);
    leftFollower1.setNeutralMode(NeutralMode.Brake);
    leftFollower2.setNeutralMode(NeutralMode.Brake);
    rightMaster.setNeutralMode(NeutralMode.Brake);
    rightFollower1.setNeutralMode(NeutralMode.Brake);
    rightFollower2.setNeutralMode(NeutralMode.Brake);

    leftMaster.configOpenloopRamp(0.25);
    rightMaster.configOpenloopRamp(0.25);

    leftMaster.setSensorPhase(true);
    rightMaster.setSensorPhase(true);

    leftFollower1.follow(leftMaster);
    leftFollower2.follow(leftMaster);
    rightFollower1.follow(rightMaster);
    rightFollower2.follow(rightMaster);


    // Initializing the DriveTrain
    tankDrive(0, 0);



    field = new Field2d();
    SmartDashboard.putData("Field", field);

    driveChooser.setDefaultOption("Split Arcade", DriveMode.SPLIT_ARCADE);
    driveChooser.addOption("Tank", DriveMode.TANK);
    driveChooser.addOption("Arcade", DriveMode.ARCADE);

    dampingChooser.setDefaultOption("Linear", DampingMode.LINEAR);
    dampingChooser.addOption("Exponential 2", DampingMode.EXP_2);
    dampingChooser.addOption("Quadratic", DampingMode.QUADRATIC);
    dampingChooser.addOption("Cubic", DampingMode.CUBIC);

    Shuffleboard.getTab("Drive").add("Drive Mode", driveChooser);
    Shuffleboard.getTab("Drive").add("Damping Function", dampingChooser);

    deadzone =
        Shuffleboard.getTab("Drive")
            .add("Deadzone", 0.05)
            .withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", 0, "max", 1))
            .getEntry();
    coefficient =
        Shuffleboard.getTab("Drive")
            .add("Coefficient", 1)
            .withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", 0.5, "max", 1))
            .getEntry();
  }

  @Override
  public void periodic() {
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
  }

  /**
   * Sets percent power individually to both sides, 1 is 100%, -1 is -100% Created because move()
   * sets power equally to both sides
   *
   * @param leftPower Percent power for the left side of the driveTrain
   * @param rightPower Percent power for the right side of the driveTrain
   */
  public void tankDrive(double leftPower, double rightPower) {
    leftMaster.set(ControlMode.PercentOutput, leftPower);
    rightMaster.set(ControlMode.PercentOutput, rightPower);
  }

  /**
   * Arcade drive with a given forward and turn rate
   *
   * @param forward Percent power for moving forward/backward
   * @param turn Percent power for turning the driveTrain
   */
  public void arcadeDrive(double forward, double turn) {
    leftMaster.set(ControlMode.PercentOutput, forward + turn);
    rightMaster.set(ControlMode.PercentOutput, forward - turn);
  }

  /** Stop all motors on the driveTrain */
  public void stop() {
    this.tankDrive(0, 0);
  }

  /**
   * Gets the left encoder's displacement in meters
   *
   * @return a double that represent's the left displacement in meters
   */

  public void driveWithInputs(double leftX, double leftY, double rightX, double rightY) {
    final double deadzoneVal = deadzone.getDouble(0.05);
    final double coefficientVal = coefficient.getDouble(1);

    leftX = (Math.abs(leftX) > deadzoneVal) ? leftX : 0;
    leftY = (Math.abs(leftY) > deadzoneVal) ? leftY : 0;
    rightX = (Math.abs(rightX) > deadzoneVal) ? rightX : 0;
    rightY = (Math.abs(rightY) > deadzoneVal) ? rightY : 0;

    if (driveChooser.getSelected() != DriveMode.TANK) {
      switch (dampingChooser.getSelected()) {
        default:
        case LINEAR:
          break;
        case EXP_2:
          rightX = Math.pow(2, rightX) - 1;
          break;
        case QUADRATIC:
          rightX = rightX * rightX;
          break;
        case CUBIC:
          rightX = rightX * rightX * rightX;
          break;
      }
      rightX *= coefficientVal;
    }

    switch (driveChooser.getSelected()) {
      default:
      case SPLIT_ARCADE:
        arcadeDrive(leftY, rightX);
        break;
      case ARCADE:
        arcadeDrive(leftY, leftX);
        break;
      case TANK:
        tankDrive(leftY, rightY);
    }
  }
}