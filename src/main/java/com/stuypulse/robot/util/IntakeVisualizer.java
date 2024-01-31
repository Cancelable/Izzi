package com.stuypulse.robot.util;

import static com.stuypulse.robot.constants.Settings.Intake.*;

import com.stuypulse.stuylib.network.SmartNumber;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Mechanism;

public class IntakeVisualizer {

    private double angle = 0;

    private final double WINDOW_WIDTH = 20;
    private final double WINDOW_HEIGHT = 20;
    private final double WINDOW_X_PADDING = 1;
    private final int LINE_WIDTH = 10;


    private final Mechanism2d intake;

    // ligaments
   
    private MechanismLigament2d upper1;
    private MechanismLigament2d lower1;

    private MechanismLigament2d upper2;
    private MechanismLigament2d lower2;

    private MechanismLigament2d upper3;
    private MechanismLigament2d lower3;
   
   // 1 is upper, 2 is lower
    private MechanismLigament2d middle1;
    private MechanismLigament2d middle2;

    //irSensor
    private MechanismLigament2d intakeIRSensor;
    private MechanismLigament2d shooterIRSensor;
    private MechanismLigament2d ampIRSensor;

    // far Right Ligaments
    private MechanismLigament2d rightLigamentTop;
    private MechanismLigament2d rightLigamentBottom;

    // rolling ligaments
    private MechanismLigament2d rollingLeft;
    private MechanismLigament2d rollingRight;

     // roots
    private MechanismRoot2d root_upper1;
    private MechanismRoot2d root_upper2;
    private MechanismRoot2d root_upper3;
    private MechanismRoot2d root_lower1;
    private MechanismRoot2d root_lower2;
    private MechanismRoot2d root_lower3;
    private MechanismRoot2d root_middle;
    private MechanismRoot2d rootIntakeIR;
    private MechanismRoot2d rootShooterIR;
    private MechanismRoot2d rootAmpIR;
    private MechanismRoot2d rightRootTop;
    private MechanismRoot2d rightRootBottom;



// colors
    private Color8Bit white = new Color8Bit(255,255,255);
    private Color8Bit blue = new Color8Bit(0, 0, 255);
    private Color8Bit red = new Color8Bit(255, 0, 0);
    private Color8Bit green = new Color8Bit();


    private MechanismLigament2d getLigament(String name, double length, double angle, Color8Bit color) {
        return new MechanismLigament2d(name, length, angle, LINE_WIDTH, color);
    }

    public IntakeVisualizer() {
        intake = new Mechanism2d(WINDOW_WIDTH, WINDOW_HEIGHT);
    
        // uppper roots
        root_upper1 = intake.getRoot("One Upper", 0, 0);
        root_upper2 = intake.getRoot("Two Upper", 0, 4);
        root_upper3 = intake.getRoot("Three Upper", 2, 6.2);
    
        // lower roots
        root_lower1 = intake.getRoot("One Lower", 2, 0);
        root_lower2 = intake.getRoot("Two Lower", 2, 3);
        root_lower3 = intake.getRoot("Three Lower", 3.3, 4.5);
    
        // middle roots
        root_middle = intake.getRoot("One", 1, 1);

        rootIntakeIR = intake.getRoot("Intake Sensor", 1.5, 4.6);
        rootShooterIR = intake.getRoot("Shooter Sensor", 7, 7.2); 
        rootAmpIR = intake.getRoot("Amp Sensor", 7, 4.6);

        // right (separation) roots
        rightRootTop = intake.getRoot("Right Root Top", 7, 5.7); // Set values when i know them
        rightRootBottom = intake.getRoot("Right Root Bottom", 7, 5.7); // Set values when I know them
    

        // ligaments
        upper1 = getLigament("Upper Ligament 1", 4, 90, blue);
        upper2 = getLigament("Upper Ligament 2", 3, 45, red);
        upper3 = getLigament("Upper Ligament 3", 7, 20, white); // old length 5
        lower1 = getLigament("Lower Ligament 1", 3, 90, blue);
        lower2 = getLigament("Lower Ligament 2", 2, 45, red);
        lower3 = getLigament("Lower Ligament 3", 5, -10, white); // old length 3



        rollingLeft = getLigament("Left Roller", 1, 0, red);
        rollingRight = getLigament("Right Roller", 1, 0,red);

        intakeIRSensor = getLigament("Intake Sensor", 1, 0, red);
        shooterIRSensor = getLigament("Shooter Sensor", 1, 0, red);
        ampIRSensor = getLigament("Amp Sensor", 1, 0, red);

        rightLigamentTop = getLigament("Right Top Ligament", 3, 15, blue); // angle 30 before
        rightLigamentBottom = getLigament("Right Bottom Ligament", 3, -15, blue); // angle -30 before

        root_upper1.append(upper1); 
        root_lower1.append(lower1);
        root_upper2.append(upper2);
        root_lower2.append(lower2);
        root_upper3.append(upper3);
        root_lower3.append(lower3);
        root_middle.append(middle1);
        root_middle.append(middle2);
        rootIntakeIR.append(intakeIRSensor);
        rootShooterIR.append(shooterIRSensor);
        rootAmpIR.append(ampIRSensor);
        rightRootTop.append(rightLigamentTop);
        rightRootBottom.append(rightLigamentBottom);
        root_upper1.append(rollingLeft);
        root_lower1.append(rollingRight);
       

        SmartDashboard.putData("Intake", intake);
    }

    public void update(boolean intakeIR, boolean shooterIR, boolean ampIR) {
        if (intakeIR) {
            intakeIRSensor.setColor(green);
        }  else {
            intakeIRSensor.setColor(red);
        }

        if (shooterIR) {
            shooterIRSensor.setColor(green);
        } else {
            shooterIRSensor.setColor(red);
        }

        if (ampIR) {
            ampIRSensor.setColor(green);
        } else {
            ampIRSensor.setColor(red);
        }
    }

    public void periodic() {
        rollingLeft.setAngle(angle);
        rollingRight.setAngle(angle);
        angle += 1;
    }

}