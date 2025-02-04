package com.stuypulse.robot.commands.conveyor;

import com.stuypulse.robot.subsystems.conveyor.Conveyor;

import com.stuypulse.robot.subsystems.intake.Intake;

import edu.wpi.first.wpilibj2.command.Command;

public class ConveyorToShooter extends Command {
    
    private final Conveyor conveyor;
    private final Intake intake;

    public ConveyorToShooter() {
        conveyor = Conveyor.getInstance();
        intake = Intake.getInstance();

        addRequirements(conveyor, intake);
    }

    @Override
    public void execute() {
        conveyor.toShooter();
        intake.acquire();
    }

    @Override
    public void end(boolean interrupted) {
        conveyor.stop();
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return conveyor.isNoteAtShooter();
    }

}
