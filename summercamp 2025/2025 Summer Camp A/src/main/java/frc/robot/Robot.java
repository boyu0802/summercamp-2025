// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  private WPI_VictorSPX Left_Drive_1 = new WPI_VictorSPX(1);
  private WPI_VictorSPX Left_Drive_2 = new WPI_VictorSPX(2);
  private WPI_VictorSPX Right_Drive_1 = new WPI_VictorSPX(3);
  private WPI_VictorSPX Right_Drive_2 = new WPI_VictorSPX(4);
  private WPI_VictorSPX Pivot = new WPI_VictorSPX(6);
  private WPI_VictorSPX Coral_Output = new WPI_VictorSPX(5);

  private XboxController driveController = new XboxController(Constants.Drive_Controller_Index);

  private DifferentialDrive chassis = new DifferentialDrive(Left_Drive_1,Right_Drive_1);
  private static final Timer timer = new Timer();
  private double switch_seconds_constant = 0.3;

  private void pivotBackRight(){
    double time = timer.get();
    
    double speed = (int)(time / switch_seconds_constant) % 2 == 0? 0.5 : -0.5;

    Pivot.set(speed);
  }

  public Robot() {
    Left_Drive_2.follow(Left_Drive_1);
    Right_Drive_2.follow(Right_Drive_1);

    Left_Drive_1.setInverted(Constants.Left_Drive_1_Inverted);
    Left_Drive_2.setInverted(Constants.Left_Drive_2_Inverted);
    Right_Drive_1.setInverted(Constants.Right_Drive_1_Inverted);
    Right_Drive_2.setInverted(Constants.Right_Drive_2_Inverted);
    Pivot.setInverted(Constants.Pivot_Inverted);
    Coral_Output.setInverted(Constants.Coral_Output_Inverted);

    Left_Drive_1.setNeutralMode(NeutralMode.Brake);
    Left_Drive_2.setNeutralMode(NeutralMode.Brake);
    Right_Drive_1.setNeutralMode(NeutralMode.Brake);
    Right_Drive_2.setNeutralMode(NeutralMode.Brake);
    Pivot.setNeutralMode(NeutralMode.Brake);
    Coral_Output.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    timer.start();
  }

  
  @Override
  public void teleopPeriodic() {
    if(driveController.getLeftTriggerAxis() > 0 && driveController.getLeftY() > Constants.Deadband){
      double trigger = driveController.getLeftTriggerAxis()/2;
      double y = driveController.getLeftY()/2;
      double speed = 0;
      if(y > 0){
        speed = -trigger - y;
      }else if(y < 0){
        speed = trigger - y;
      } 
      
      chassis.arcadeDrive(-speed, -driveController.getRightX()*0.8);
    }else{
      chassis.arcadeDrive(driveController.getLeftY()*0.5 , -driveController.getRightX()*0.8 );
    }

    if(driveController.getRightTriggerAxis() > 0){
      pivotBackRight();
    }

    
    if(driveController.getAButton()){
      Coral_Output.set(1);
      
    } else if(driveController.getBButton()){
      Coral_Output.set(-1);
    } else{
      Coral_Output.set(0);
    }

    if(driveController.getLeftBumperButton()){
      Pivot.set(0.8);
    } else if(driveController.getRightBumperButton()){
      Pivot.set(-0.8);
    } else{
      Pivot.set(0);
    }

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
