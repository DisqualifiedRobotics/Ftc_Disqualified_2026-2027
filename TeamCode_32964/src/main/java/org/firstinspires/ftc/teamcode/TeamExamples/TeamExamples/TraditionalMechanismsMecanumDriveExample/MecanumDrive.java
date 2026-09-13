package org.firstinspires.ftc.teamcode.TeamExamples.TeamExamples.TraditionalMechanismsMecanumDriveExample;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {
    /*
    * Code was used from the RobotTeleopMecanumFieldRelativeDrive example in the examples provided from the FTC SDK
    * Check it out and other cool examples in the file path:
    * FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples/externalhardware
    */


    // Declare Motors
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    DcMotor backLeftDrive;
    DcMotor backRightDrive;

    // This is a Constructor, used for initializing the Mechanism Object
    // It is standard practice to comment and describe the methods you make
    // COMMENT YOUR CODE
    /**
     * Description: Initializes drivetrain and internal gamepad object
     * Pre-Condition: hardwareMap and gamepad must be provided
     * Post-Condition: HardwareMap properly initialized in Motor Objects with gamepad values transferred
     * @param hardwareMap HardwareMap Object
     */
    public MecanumDrive(HardwareMap hardwareMap) {

        // Hardware Map (Match device name with configuration in DriverHub)
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");

        // Change Motor Directions (Default is Forward)
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        /*
        This Mode sets the motors to use encoders that track their position
        This is only needed for tracking the robots position, or having a field relative drive
        We will be using Pedropathing for robot tracking

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        */
    }

    // The standard practice and format for the update method in mechanisms
    public void update(double forward, double right, double rotate) {
        // Calling the drive method in this update block
        // This Gets the motor inputs from the main telop's gamepad
        drive(forward, right, rotate);
    }

    /**
     * Description: Moves the drivetrain robot oriented
     * Pre-Condition: Params must be doubles
     * Post-Condition: The drivetrain moves as commanded relative to the robot
     * @param forward value for moving forward or backward
     * @param right value for strafing left or right
     * @param rotate value for turning left or right
     */
    public void drive(double forward, double right, double rotate) {

        // This calculates the power needed for each wheel based on the amount of forward,
        // strafe right, and rotate
        double frontLeftPower = forward + right + rotate;
        double frontRightPower = forward - right - rotate;
        double backRightPower = forward + right - rotate;
        double backLeftPower = forward - right + rotate;

        double maxPower = 1.0;

        // This is needed to make sure we don't pass > 1.0 to any wheel
        // It allows us to keep all of the motors in proportion to what they should
        // be and not get clipped
        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));

        // We multiply by maxSpeed so that it can be set lower for outreaches
        // When a young child is driving the robot, we may not want to allow full
        // speed.
        frontLeftDrive.setPower(frontLeftPower / maxPower);
        frontRightDrive.setPower(frontRightPower / maxPower);
        backLeftDrive.setPower(backLeftPower / maxPower);
        backRightDrive.setPower(backRightPower / maxPower);
    }
}