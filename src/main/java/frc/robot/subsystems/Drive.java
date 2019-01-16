/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.IRMode;
import frc.robot.commands.LimitSwitchMode;
import frc.robot.commands.UltrasonicMode;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com.ctre.phoenix.motorcontrol.can.*;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.FRONTLEFTTALON);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.FRONTRIGHTTALON);
  WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.BACKLEFTTALON);
  WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.BACKRIGHTTALON);

  AnalogInput ultrasonic = new AnalogInput(RobotMap.ULTRASONICPORT);
  DigitalInput limitSwitch = new DigitalInput(RobotMap.LIMITSWITCHPORT);
  DigitalInput infrared = new DigitalInput(RobotMap.INFRAREDPORT);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new UltrasonicMode());
  }

  public void ultrasonicMode() {
    boolean usTrigger;
    double usReading = ultrasonic.getVoltage();

    if (usReading < RobotMap.USTHRESHOLD) {
      usTrigger = true; // The robot will stop running when usTrigger is true - when usReading is less than 1.2 units
    }

    else {
      usTrigger = false;
    }

    if (usTrigger) {
      robotStop();
    }

    else {
      robotRun();
    }

    SmartDashboard.putBoolean("Ultrasonic", usTrigger);
    SmartDashboard.putNumber("Ultrasonic Reading", usReading);
      
  }

  public void limitSwitchMode() {
    boolean lsTrigger = limitSwitch.get();

    if (lsTrigger) { // The robot will stop when the limit switch is pressed
      robotStop();
    }

    else {
      robotRun();
    }

    SmartDashboard.putBoolean("Limit Switch", lsTrigger);
  }

  public void infraredMode() {
    boolean irTrigger = infrared.get();

    if (irTrigger) { // The robot will stop when the infrared receives its own rays
      robotStop();
    }
    
    else {
      robotRun();
    }

    SmartDashboard.putBoolean("Infrared", irTrigger);

  }

  // Code for running and stopping tank drive
  public void robotStop() {
    frontLeft.set(RobotMap.TALONSTOP);
    frontRight.set(RobotMap.TALONSTOP);
    backLeft.set(RobotMap.TALONSTOP);
    backRight.set(RobotMap.TALONSTOP);
  }

  public void robotRun() {

    // Sets the right Talons to reverse direction
    frontRight.setInverted(true);
    backRight.setInverted(true);

    frontLeft.set(RobotMap.TALONRUN);
    frontRight.set(RobotMap.TALONRUN);
    backLeft.set(RobotMap.TALONRUN);
    backRight.set(RobotMap.TALONRUN);
  }

  public void toggleMethod(Joystick stick) {

    Button usSwitch = new JoystickButton(stick, RobotMap.USSWITCH);
    Button irSwitch = new JoystickButton(stick, RobotMap.IRSWITCH);
    Button lsSwitch = new JoystickButton(stick, RobotMap.LSSWITCH);

    usSwitch.whenPressed(new UltrasonicMode());
    irSwitch.whenPressed(new IRMode());
    lsSwitch.whenPressed(new LimitSwitchMode());

    usSwitch.close();
    irSwitch.close();
    lsSwitch.close();
  }
}
