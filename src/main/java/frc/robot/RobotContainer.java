// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ConveyorBackward;
import frc.robot.commands.ConveyorForward;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.RetractIntake;
import frc.robot.commands.ReverseIntakeWheels;
import frc.robot.commands.SpinIntakeWheels;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Outtake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final WPI_TalonSRX CAN10 = new WPI_TalonSRX(10);
  private final WPI_VictorSPX CAN11 = new WPI_VictorSPX(11);
  private final WPI_VictorSPX CAN12 = new WPI_VictorSPX(12);

  private final WPI_TalonSRX CAN20 = new WPI_TalonSRX(20);
  private final WPI_VictorSPX CAN21 = new WPI_VictorSPX(21);
  private final WPI_VictorSPX CAN22 = new WPI_VictorSPX(22);

  private final WPI_TalonSRX CAN30 = new WPI_TalonSRX(30);
  private final WPI_TalonSRX CAN31 = new WPI_TalonSRX(31);
  private final WPI_TalonSRX CAN32 = new WPI_TalonSRX(32);

  private final WPI_TalonSRX CAN40 = new WPI_TalonSRX(40);
  private final CANSparkMax CAN41 = new CANSparkMax(41, MotorType.kBrushless);

  private final CANSparkMax CAN50 = new CANSparkMax(50, MotorType.kBrushless);
  private final CANSparkMax CAN51 = new CANSparkMax(51, MotorType.kBrushless);
  private final CANSparkMax CAN52 = new CANSparkMax(52, MotorType.kBrushless);
  private final CANSparkMax CAN53 = new CANSparkMax(53, MotorType.kBrushless);

  private final DigitalInput DIO0 = new DigitalInput(0);
  private final DigitalInput DIO1 = new DigitalInput(1);
  private final DigitalInput DIO5 = new DigitalInput(5);
  private final DigitalInput DIO3 = new DigitalInput(3);
  private final DigitalInput DIO4 = new DigitalInput(4);

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Intake intake = new Intake(CAN31,CAN32,CAN30,DIO3,DIO4);

  private final Outtake outtake = new Outtake(CAN41); 
  
  
  private final Climber climber = new Climber(CAN52, CAN53, CAN50, CAN51, DIO0);

  private final Conveyor conveyor = new Conveyor(CAN40,DIO5,DIO1);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final ConveyorForward conveyorForward = new ConveyorForward(conveyor);
  private final ConveyorBackward conveyorBackward = new ConveyorBackward(conveyor);
  private final  OuttakeCommand outtakeCommand = new OuttakeCommand(outtake);
  private final XboxController driver = new XboxController(0);
  private final XboxController operator = new XboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Driver buttons
    // JoystickButton dA = new JoystickButton(driver,
    // XboxController.Button.kA.value);
    // JoystickButton dB = new JoystickButton(driver,
    // XboxController.Button.kB.value);
    // JoystickButton dX = new JoystickButton(driver,
    // XboxController.Button.kX.value);
    // JoystickButton dY = new JoystickButton(driver,
    // XboxController.Button.kY.value);
    //JoystickButton dLB = new JoystickButton(driver,
    //XboxController.Button.kLeftBumper.value);
    // JoystickButton dRB = new JoystickButton(driver,
    // XboxController.Button.kRightBumper.value);
    //XboxControllerButton dLT = 
    //new XboxControllerButton(driver, XboxController.Axis.kLeftTrigger.value);
    // XboxControllerButton dRT =
    // new XboxControllerButton(driver, XboxController.Axis.kRightTrigger.value);
    // JoystickButton dBack = new JoystickButton(driver,
    // XboxController.Button.kBack.value);
    // JoystickButton dStart = new JoystickButton(driver,
    // XboxController.Button.kStart.value);
    // XboxControllerButton dLeftY =
    // new XboxControllerButton(driver, XboxController.Axis.kLeftY.value);
    // XboxControllerButton dLeftX =
    // new XboxControllerButton(driver, XboxController.Axis.kLeftX.value);
    // XboxControllerButton dRightY =
    // new XboxControllerButton(driver, XboxController.Axis.kRightY.value);
    // XboxControllerButton dRightX =
    // new XboxControllerButton(driver, XboxController.Axis.kRightX.value);

    // POVTrigger dDPadUp = new POVTrigger(driver, 0);
    // POVTrigger dDPadRight = new POVTrigger(driver, 90);
    // POVTrigger dDPadDown = new POVTrigger(driver, 180);
    // POVTrigger dDPadLeft = new POVTrigger(driver, 270);
    
    // Operator buttons
    JoystickButton oA = new JoystickButton(operator, XboxController.Button.kA.value);
    //JoystickButton oB = new JoystickButton(operator, XboxController.Button.kB.value);
    // JoystickButton oX = new JoystickButton(operator, XboxController.Button.kX.value);
    // JoystickButton oY = new JoystickButton(operator, XboxController.Button.kY.value);
    //JoystickButton oLB = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
    JoystickButton oRB = new JoystickButton(operator, XboxController.Button.kRightBumper.value);
    //XboxControllerButton oLT =
    //new XboxControllerButton(operator, XboxController.Axis.kLeftTrigger.value);
    XboxControllerButton oRT =
    new XboxControllerButton(operator, XboxController.Axis.kRightTrigger.value);
    JoystickButton oLB = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
    //JoystickButton oRB = new JoystickButton(operator, XboxController.Button.kRightBumper.value);
    XboxControllerButton oLT =
    new XboxControllerButton(operator, XboxController.Axis.kLeftTrigger.value);
    // XboxControllerButton oRT =
    // new XboxControllerButton(operator, XboxController.Axis.kRightTrigger.value);
    // JoystickButton oBack = new JoystickButton(operator, XboxController.Button.kBack.value);
    // JoystickButton oStart = new JoystickButton(operator, XboxController.Button.kStart.value);
     XboxControllerButton oLeftY =
     new XboxControllerButton(operator, XboxController.Axis.kLeftY.value);
    // XboxControllerButton oLeftX =
    // new XboxControllerButton(operator, XboxController.Axis.kLeftX.value);
    XboxControllerButton oRightY =
    new XboxControllerButton(operator, XboxController.Axis.kRightY.value);
    // XboxControllerButton oRightX =
    // new XboxControllerButton(operator, XboxController.Axis.kRightX.value);
    POVTrigger oDPadUp = new POVTrigger(operator, 0);
    // POVTrigger oDPadRight = new POVTrigger(operator, 90);
    POVTrigger oDPadDown = new POVTrigger(operator, 180);
    // POVTrigger oDPadLeft = new POVTrigger(operator, 270);

    oLeftY.whileActiveContinuous(
      new InstantCommand(() -> climber.runWinch(oLeftY.getRawAxis()), climber)
    ).whenInactive(new InstantCommand(() -> climber.runWinch(0), climber));
    oRightY.whileActiveContinuous(
      new InstantCommand(() -> climber.runPivot(oRightY.getRawAxis()), climber)
    ).whenInactive(new InstantCommand(() -> climber.runPivot(0), climber));
    oRB.whenHeld(new SpinIntakeWheels(intake));
    oRT.whenHeld(new ReverseIntakeWheels(intake));
    oDPadUp.whileActiveOnce(new DeployIntake(intake));
    oDPadDown.whileActiveOnce(new RetractIntake(intake));
    oLB.whenHeld(conveyorForward);
    oLT.whenHeld(conveyorBackward);
    oA.whenHeld(outtakeCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
// Test Test