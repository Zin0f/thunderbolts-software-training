package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "tank drive")
public class TankDrive extends LinearOpMode {
    @Config
    public static class parameters{
        public static float speed_reduction=1;
    }
    @Override
    public void runOpMode()  {
        DcMotor left_motor;
        DcMotor right_motor;
        float speed_left;
        float speed_right;
        float y,x;
        boolean slow_mode=false;
        boolean previous_a=false;

        left_motor = hardwareMap.dcMotor.get("1");
        right_motor = hardwareMap.dcMotor.get("2");

        left_motor.setMode(RUN_WITHOUT_ENCODER);
        right_motor.setMode(RUN_WITHOUT_ENCODER);

        left_motor.setDirection(FORWARD);
        right_motor.setDirection(REVERSE);

        waitForStart();
        while (opModeIsActive()){

            if(gamepad1.a&&previous_a)
            {
                slow_mode=!slow_mode;
            }

            previous_a=gamepad1.a;

            y=gamepad1.left_stick_y;
            x=gamepad1.left_stick_x;

            if(Math.abs(y)>0.1 || Math.abs(x)>0.1){

                speed_left=y-x;
                speed_right=y+x;

                speed_right=speed_right/parameters.speed_reduction;
                speed_left=speed_left/parameters.speed_reduction;

                left_motor.setPower(speed_left);
                right_motor.setPower(speed_right);
            }
        }

    }
}
