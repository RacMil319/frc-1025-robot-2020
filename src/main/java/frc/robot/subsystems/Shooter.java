/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

  
public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private CANSparkMax shooterMotor_A;
  private CANSparkMax shooterMotor_B;
  private CANPIDController pidController;
  private CANEncoder shooterEncoder;
  private double kp;
  private double ki;
  private double kd;
  private double kz;
  private double kFF;
  private double kmaxOutput;
  private double kminOutput;
  private double maxRPM;


  public Shooter() {
    shooterMotor_A = new CANSparkMax(Constants.shooterMotor_A, MotorType.kBrushless);
    shooterMotor_B = new CANSparkMax(Constants.shooterMotor_B, MotorType.kBrushless);

    

    pidController = shooterMotor_A.getPIDController();
    
    
    shooterEncoder = shooterMotor_A.getEncoder();
    
    shooterMotor_B.follow(shooterMotor_A);
    shooterMotor_B.setInverted(true);

    kp = 2.2;
    ki = .005;
    kd = .0;
    kz = 0;
    kFF = 0;
    kmaxOutput = 1;
    kminOutput = -1.;
    maxRPM = 2000;

    SmartDashboard.putNumber("P Gain", kp);
    SmartDashboard.putNumber("I Gain", kp);
    SmartDashboard.putNumber("D Gain", kd);
    SmartDashboard.putNumber("Z Gain", kz);
    SmartDashboard.putNumber("FF Gain", kFF);
    SmartDashboard.putNumber("Max Output", kmaxOutput);
    SmartDashboard.putNumber("Min Output", kminOutput);
    SmartDashboard.putNumber("Set Rotations", 0);

    
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    shooterPID();
  }
  public void shooterPID(){
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double z = SmartDashboard.getNumber("Z Gain", 0);
    double ff = SmartDashboard.getNumber("FF Gain", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min", 0);
    double rotations = SmartDashboard.getNumber("Set Rotations", 0);

    if((p != kp)) { 
    pidController.setP(p); 
    kp = p;
   }

    if((i != ki)) { 
      pidController.setI(i);
       ki = i; 
    }

    if((d != kd)) { 
      pidController.setD(d); 
      kd = d;
     }

    if((z != kz)) {
       pidController.setIZone(z); 
       kz = z;
       }

    if((ff != kFF)) {
       pidController.setFF(ff);
        kFF = ff; 
      }

    if((max != kmaxOutput) || (min != kminOutput)) { 

      pidController.setOutputRange(min, max); 

      kminOutput = min; 
      kmaxOutput = max; 
  }
  
    


}
public void shoot(){
  double setPoint = maxRPM;

  pidController.setReference(setPoint, ControlType.kVelocity);

  shooterMotor_A.pidWrite(setPoint);

  SmartDashboard.putNumber("SetPoint", setPoint);

  SmartDashboard.putNumber("ProcessVariable", shooterEncoder.getVelocity());

  
}
}