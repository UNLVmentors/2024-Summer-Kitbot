// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  private DifferentialDrive m_myRobot;

  private CANSparkMax m_leftMotor;
  private CANSparkMax m_leftMotor2;
  private CANSparkMax m_rightMotor;
  private CANSparkMax m_rightMotor2;

  private CANSparkFlex shooterMotor;
  private CANSparkFlex shooterMotor2;

  private XboxController driveStick;

  //public final WPI_VictorSPX m_outake;

  @Override
  public void robotInit() {

    m_leftMotor = new CANSparkMax(12, MotorType.kBrushless);
    m_leftMotor2 = new CANSparkMax(13, MotorType.kBrushless);  
    m_rightMotor = new CANSparkMax(14, MotorType.kBrushless);
    m_rightMotor2 = new CANSparkMax(15, MotorType.kBrushless);

    shooterMotor = new CANSparkFlex(21, MotorType.kBrushless);
    shooterMotor2 = new CANSparkFlex(22, MotorType.kBrushless);

    m_leftMotor.restoreFactoryDefaults();
    m_leftMotor2.restoreFactoryDefaults();

    m_leftMotor.setInverted(false);
    m_leftMotor2.setInverted(false);
    m_rightMotor.setInverted(true);
    m_rightMotor2.setInverted(true);

    m_leftMotor2.follow(m_leftMotor);
    m_rightMotor2.follow(m_rightMotor2);

    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);

    driveStick = new XboxController(0);

    //WPI_VictorSPX outakeMotor = new WPI_VictorSPX(20);
    //outakeMotor.set(1);

    shooterMotor.setOpenLoopRampRate(2);
    shooterMotor2.setOpenLoopRampRate(2);

   

  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    WPI_VictorSPX outakeMotor = new WPI_VictorSPX(20);

    m_myRobot.arcadeDrive(-driveStick.getLeftY()*0.5, -driveStick.getRightX()*0.5);
//outakeMotor.set(1);
    if (driveStick.getAButton()){
      outakeMotor.set(1);
    
    }else if(driveStick.getBButton()){
      outakeMotor.set(-1);
    }else{
      outakeMotor.set(0);
    }

    if(driveStick.getRawButton(5)){
      shooterMotor.set(0.8);
      shooterMotor2.set(0.8);
    }else if (driveStick.getRawButton(6)){
      shooterMotor.set(-0.8);
      shooterMotor2.set(-0.8);
    }else{
      shooterMotor.set(0);
      shooterMotor2.set(0);
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
