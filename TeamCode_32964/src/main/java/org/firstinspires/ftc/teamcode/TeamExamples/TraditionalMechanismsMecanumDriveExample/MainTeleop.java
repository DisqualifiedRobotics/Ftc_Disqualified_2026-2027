package org.firstinspires.ftc.teamcode.TeamExamples.TraditionalMechanismsMecanumDriveExample;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Mecanum Drive Example")
public class MainTeleop extends OpMode {

    // Declare Mecanum Drive Object
    MecanumDrive drive;

    // The main init for the init phase in teleop
    @Override
    public void init() {
        // Initialize Constructor, pass hardwareMap
        drive = new MecanumDrive(hardwareMap);
    }

    // The main loop for the play phase in teleop
    @Override
    public void loop() {
        // Continuously update the drive logic with gamepad inputs
        drive.update(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }

}
