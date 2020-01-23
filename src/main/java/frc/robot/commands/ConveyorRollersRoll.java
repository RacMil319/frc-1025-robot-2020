package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorRollersRoll extends CommandBase {
    
    private static final String ImpiLib2020 = null;
    ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
    
    public ConveyorRollersRoll() {
        addRequirements(conveyorSubsystem);
    }
    @Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		XboxController driverController = RobotContainer.driverController;
		conveyorSubsystem.conveyorControl(Math.pow(ImpiLib2020.deadzone(driverController.getTriggerAxis(Hand.kLeft), 0.05), 2));
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
