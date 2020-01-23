/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.*;

public class ChasisSubsystem extends SubsystemBase {
  /**
   * Creates a new ChasisSubsystem.
   */
  
  public CANSparkMax leftFront = new CANSparkMax(Constants.leftFrontPort);
  public CANSparkMax rightFront = new CANSparkMax(Constants.rightFrontPort);
  public CANSparkMax leftRear = new CANSparkMax(Constants.leftRearPort);
  public CANSparkMax rightRear = new CANSparkMax(Constants.rightRearPort);
  
  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  public DifferentialDrive drive = new DifferentialDrive(leftFront, rightFront);

  public ChasisSubsystem() {
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);
  }
  // this is manualDrive() method
  public void manualDrive(double move, double turn) {
    // if(move > .5) move = .5;

    drive.arcadeDrive(move, turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new DriveManuallyCommand());
  }
}
