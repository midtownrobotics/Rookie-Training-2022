// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  // TODO: Experimentally tune PID values
  public static double dt_kP = 2.,
      dt_kI = 0.,
      dt_kD = 2.,
      dt_kF = 0,
      dt_rampTime = 1,
      dt_maxVel = 80,
      dt_maxAcc = 20,
      dt_closedLoopError = 0,
      dt_maxIAccum = 0,
      dt_iZone = 0;

  // TODO: Run robot characterization
  // https://docs.wpilib.org/en/stable/docs/software/pathplanning/robot-characterization/characterization-routine.html#running-the-characterization-routine
  public static double ksVolts = 0.52269,
      kvVoltSecondsPerMeter = 2.4021,
      kaVoltSecondsSquaredPerMeter = 0.43354;
  public static double kRamseteB = 2.0, kRamseteZeta = 0.7;
  public static double kWheelRadiusInches = 3,
      kSensorGearRatio = 7.29,
      kCountsPerRev = 4,
      kMomentOfInertia = 7.5,
      kMass = 60.0,
      kTrackWidth = 0.7112;

  // TODO: Experimentally tune PID values
  public static double winch_kP = 0.03,
      winch_kI = 0,
      winch_kD = 0.25,
      winch_kF = 0,
      winch_iZone = 0,
      winch_rampRate = 1;
  public static double pivot_kP = 0.03,
      pivot_kI = 0,
      pivot_kD = 0.2,
      pivot_kF = 0,
      pivot_iZone = 0,
      pivot_rampRate = 1;

  // TODO: Experimentally find setpoints
  public static double winch_handoff = -65,
      winch_position_tolerance = 1.5,
      winch_velocity_tolerance = 60;
  public static double pivot_vertical = -75,
      pivot_vertical_to_ready = 22,
      pivot_ready_to_handoff = -7,
      pivot_position_tolerance = 3,
      pivot_velocity_tolerance = 100;
}
