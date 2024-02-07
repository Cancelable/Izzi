package com.stuypulse.robot.commands.climber;

import com.stuypulse.robot.commands.amper.AmperToHeight;
import com.stuypulse.robot.commands.notealignment.SwerveDriveNoteAlignedDrive;
import com.stuypulse.robot.commands.swerve.SwerveDriveDrive;
import com.stuypulse.robot.commands.swerve.SwerveDriveToPose;
import com.stuypulse.robot.constants.Ports;
import com.stuypulse.robot.constants.Settings;
import com.stuypulse.robot.subsystems.amper.Amper;
import com.stuypulse.robot.subsystems.climber.*;
import com.stuypulse.robot.subsystems.swerve.SwerveDrive;
import com.stuypulse.stuylib.input.Gamepad;
import com.stuypulse.stuylib.input.gamepads.AutoGamepad;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ClimbSetupRoutine extends SequentialCommandGroup {

    public final Gamepad driver = new AutoGamepad(Ports.Gamepad.DRIVER);

    public ClimbSetupRoutine() {
        addCommands(
            new AmperToHeight(Settings.Amper.Lift.MIN_HEIGHT),
            new ClimberToTop(),
            new SwerveDriveDrive(driver),
            new ClimberToBottom()
        );
    }
        

}
