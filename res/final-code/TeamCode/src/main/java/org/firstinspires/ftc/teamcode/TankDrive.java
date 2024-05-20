package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "tank drive")
public class TankDrive extends LinearOpMode {
    @Override
    public void runOpMode()  {
        DcMotor left_motor;
        DcMotor right_motor;
        float speed_left;
        float speed_right;

        left_motor = hardwareMap.dcMotor.get("1");
        right_motor = hardwareMap.dcMotor.get("2");

        left_motor.setMode(RUN_WITHOUT_ENCODER);
        right_motor.setMode(RUN_WITHOUT_ENCODER);

        left_motor.setDirection(FORWARD);
        right_motor.setDirection(REVERSE);

        waitForStart();
        while (opModeIsActive()){
            speed_left=gamepad1.left_stick_y;
            speed_right=-gamepad1.right_stick_y;

            left_motor.setPower(speed_left);
            right_motor.setPower(speed_right);
        }

    }
}
