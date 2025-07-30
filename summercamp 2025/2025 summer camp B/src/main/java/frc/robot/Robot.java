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



  //create motor objects, 馬達物件
  private WPI_VictorSPX Left_Drive_1 = new WPI_VictorSPX(Constants.Left_Drive_1_Index);
  private WPI_VictorSPX Left_Drive_2 = new WPI_VictorSPX(Constants.Left_Drive_2_Index);
  private WPI_VictorSPX Right_Drive_1 = new WPI_VictorSPX(Constants.Right_Drive_1_Index);
  private WPI_VictorSPX Right_Drive_2 = new WPI_VictorSPX(Constants.Right_Drive_2_Index);
  private WPI_VictorSPX Hanger = new WPI_VictorSPX(Constants.Hanger_Index);
  private WPI_VictorSPX Intake = new WPI_VictorSPX(Constants.Intake_Index);


  //create controller objects, 搖桿物件
  private XboxController driveController = new XboxController(Constants.Drive_Controller_Index);
  private XboxController operatorController = new XboxController(Constants.Operator_Controller_Index);

  //create chassis object, 機器人底盤物件
  private DifferentialDrive chassis = new DifferentialDrive(Left_Drive_1,Right_Drive_1);



  public Robot() {

    Left_Drive_2.follow(Left_Drive_1);
    Right_Drive_2.follow(Right_Drive_1);

    //set Invert for all motors, 馬達的正反轉
    Left_Drive_1.setInverted(Constants.Left_Drive_1_Inverted);
    Left_Drive_2.setInverted(Constants.Left_Drive_2_Inverted);
    Right_Drive_1.setInverted(Constants.Right_Drive_1_Inverted);
    Right_Drive_2.setInverted(Constants.Right_Drive_2_Inverted);
    Hanger.setInverted(Constants.Hanger_Inverted);
    Intake.setInverted(Constants.Intake_Inverted);

    //set neutral mode for all motors, brake:煞車  coast: 滑行
    Left_Drive_1.setNeutralMode(NeutralMode.Brake);
    Left_Drive_2.setNeutralMode(NeutralMode.Brake);
    Right_Drive_1.setNeutralMode(NeutralMode.Brake);
    Right_Drive_2.setNeutralMode(NeutralMode.Brake);
    Hanger.setNeutralMode(NeutralMode.Brake);
    Intake.setNeutralMode(NeutralMode.Brake);

    //set deadband for chassis, deadband: 死區
    //是指控制系統中，對應輸出為零的輸入信號範圍
    chassis.setDeadband(Constants.Deadband);

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
    //log to smartDashboard
    SmartDashboard.putNumber("Left 1 Motor voltage", Left_Drive_1.getMotorOutputVoltage());
    SmartDashboard.putNumber("Left 2 Motor voltage", Left_Drive_2.getMotorOutputVoltage());
    SmartDashboard.putNumber("Right 1 Motor voltage", Right_Drive_1.getMotorOutputVoltage());
    SmartDashboard.putNumber("Right 2 Motor voltage", Right_Drive_2.getMotorOutputVoltage());

    //arcade drive to control chassis, 
    chassis.arcadeDrive(-driveController.getLeftY()*Constants.Drive_Speed, -driveController.getRightX()*Constants.Turn_Speed);
    /*          
    *            -1                                 1
     *            ↑                                 ↑
     *       -1 ← + →  1         --->          1  ← + →  -1
     *            ↓                                 ↓
     *            1                                -1
     * 
     *    Controller value                    chassis coordinate
     */

    if(operatorController.getAButton()){
      Intake.set(1);
      // set speed of intake motor, [-1,1]
    } else if(operatorController.getBButton()){
      Intake.set(-1);
    } else{
      Intake.set(0);
    }

    
    
    if(operatorController.getLeftTriggerAxis() > Constants.Deadband){
      Hanger.set(operatorController.getLeftTriggerAxis());
    } else if(operatorController.getRightTriggerAxis() > Constants.Deadband){
      Hanger.set(-operatorController.getRightTriggerAxis());
    } else{
      Hanger.set(0);
    }

    //controller buttons (搖桿按鍵): https://gm0.org/en/latest/docs/software/tutorials/gamepad.html


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
