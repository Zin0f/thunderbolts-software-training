package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;


@TeleOp(name = "tank drive")
public class TankDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //קוד אתחול צריך להיות כאן
        waitForStart();//אחרי אתחול נחכה שנלחץ על כפתור ההפעלה
        while (opModeIsActive()) {
            
        }

    }
}
