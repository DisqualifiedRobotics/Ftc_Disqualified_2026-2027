package org.firstinspires.ftc.teamcode.TeamExamples.TeamExamples.TraditionalMechanismsMecanumDriveExample;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MainTeleop extends OpMode {

    // Declare Mecanum Drive Object
    MecanumDrive drive;

    // The main init for the init phase
    @Override
    public void init() {
        // Initialize Constructor, pass hardwareMap
        drive = new MecanumDrive(hardwareMap);
    }

    // The main loop for the play phase
    @Override
    public void loop() {
        // Continuously update the drive logic with gamepad inputs
        drive.update(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }

}
