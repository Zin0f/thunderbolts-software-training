package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

@TeleOp(name = "tank drive")
public class TankDrive extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor left_motor;
        DcMotor right_motor;
       
        left_motor = hardwareMap.dcMotor.get("1");
        right_motor = hardwareMap.dcMotor.get("2");

        left_motor.setMode(RUN_WITHOUT_ENCODER);
        right_motor.setMode(RUN_WITHOUT_ENCODER);

        left_motor.setDirection(FORWARD);
        right_motor.setDirection(REVERSE);

        waitForStart();//אחרי אתחול נחכה שנלחץ על כפתור ההפעלה
        while (opModeIsActive()) {
            left_motor.setPower(0.5);
            right_motor.setPower(0.5);
        }
        
    }
}
