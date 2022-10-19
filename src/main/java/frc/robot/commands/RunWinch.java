// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class RunWinch extends CommandBase {
  private final Climber climber;
  private double power;
  /**
   * Creates a new ExampleCommand.
   *
   * @param climber The subsystem used by this command.
   */
  public RunWinch(Climber climber, double power) {
    this.power = power;
    this.climber = climber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      climber.runWinch(power);
  }

 //ok so we need some sort of thing that takes values from -1 to 1 that we can operate using the joystick 
}