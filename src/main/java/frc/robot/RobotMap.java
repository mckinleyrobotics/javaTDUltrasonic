/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // Ports for the Talon speed controllers
  public static int FRONTLEFTTALON = 21;
  public static int FRONTRIGHTTALON = 23;
  public static int BACKLEFTTALON = 22;
  public static int BACKRIGHTTALON = 24;

  // Ports for the sensors
  public static int ULTRASONICPORT = 0;
  public static int INFRAREDPORT = 1;
  public static int LIMITSWITCHPORT = 0;

  // Threshold when the ultrasonic sensor will be considered triggered
  public static double USTHRESHOLD = 0.075;

  // Percentage outputs for Talons
  public static double TALONSTOP = 0;
  public static double TALONRUN = 0.3;

  // Button ports on Joystick
  public static int USSWITCH = 4;
  public static int IRSWITCH = 3;
  public static int LSSWITCH = 5;
}
