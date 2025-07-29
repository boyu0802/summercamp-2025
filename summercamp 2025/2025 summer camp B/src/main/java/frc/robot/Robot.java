// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

  private WPI_VictorSPX Left_Drive_1 = new WPI_VictorSPX(3);
  private WPI_VictorSPX Left_Drive_2 = new WPI_VictorSPX(4);
  private WPI_VictorSPX Right_Drive_1 = new WPI_VictorSPX(1);
  private WPI_VictorSPX Right_Drive_2 = new WPI_VictorSPX(2);
  private WPI_VictorSPX Hanger = new WPI_VictorSPX(5);
  private WPI_VictorSPX Intake = new WPI_VictorSPX(6);

  private XboxController driveController = new XboxController(0);

  private DifferentialDrive chassis = new DifferentialDrive(Left_Drive_1,Right_Drive_1);



  public Robot() {
    Left_Drive_2.follow(Left_Drive_1);
    Right_Drive_2.follow(Right_Drive_1);

    Left_Drive_1.setInverted(Constants.Left_Drive_1_Inverted);
    Left_Drive_2.setInverted(Constants.Left_Drive_2_Inverted);
    Right_Drive_1.setInverted(Constants.Right_Drive_1_Inverted);
    Right_Drive_2.setInverted(Constants.Right_Drive_2_Inverted);
    Hanger.setInverted(Constants.Hanger_Inverted);
    Intake.setInverted(Constants.Intake_Inverted);

    Left_Drive_1.setNeutralMode(NeutralMode.Brake);
    Left_Drive_2.setNeutralMode(NeutralMode.Brake);
    Right_Drive_1.setNeutralMode(NeutralMode.Brake);
    Right_Drive_2.setNeutralMode(NeutralMode.Brake);
    Hanger.setNeutralMode(NeutralMode.Brake);
    Intake.setNeutralMode(NeutralMode.Brake);
    chassis.setDeadband(0.05);

  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
  
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("LEft 1 Motor voltage", Left_Drive_1.getMotorOutputVoltage());
    SmartDashboard.putNumber("LEft 2 Motor voltage", Left_Drive_2.getMotorOutputVoltage());
    SmartDashboard.putNumber("rIGHT 1 Motor voltage", Right_Drive_1.getMotorOutputVoltage());
    SmartDashboard.putNumber("Right 2 Motor voltage", Right_Drive_2.getMotorOutputVoltage());

    chassis.arcadeDrive(-driveController.getLeftY(), -driveController.getRightX());
    
    if(driveController.getAButton()){
      Intake.set(1);
    } else if(driveController.getBButton()){
      Intake.set(-1);
    } else{
      Intake.set(0);
    }

    
    
    if(driveController.getLeftTriggerAxis() > 0.0001){
      Hanger.set(driveController.getLeftTriggerAxis());
    } else if(driveController.getRightTriggerAxis() > 0.0002){
      Hanger.set(-driveController.getRightTriggerAxis());
    } else{
      Hanger.set(0);
    }

    /**
     * @param deadband
     * 
     * double deadband = 0.005;
     * if(driveController.getLeftTriggerAxis()>deadband || 
       driveController.getRightTriggerAxis()>deadband){
      Hanger.set(driveController.getLeftTriggerAxis() - driveController.getRightTriggerAxis());
    } else{
      Hanger.set(0);
    }
      */
    

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
