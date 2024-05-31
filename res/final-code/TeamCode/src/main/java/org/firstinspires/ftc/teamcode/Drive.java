package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "tank drive")
public class Drive extends LinearOpMode {
    
    @Override
    public void runOpMode()  {
        Telemetry dashboard=FtcDashboard.getInstance().getTelemetry();

        DcMotor left_motor;
        DcMotor right_motor;
        float speed_left=0;
        float speed_right=0;
        boolean slow_robot=false;
        boolean previous_A_button=false;

        left_motor = hardwareMap.dcMotor.get("1");
        right_motor = hardwareMap.dcMotor.get("2");

        left_motor.setMode(RUN_WITHOUT_ENCODER);
        right_motor.setMode(RUN_WITHOUT_ENCODER);

        left_motor.setDirection(FORWARD);
        right_motor.setDirection(REVERSE);

        waitForStart();
       while (opModeIsActive()){
            speed_left=-gamepad1.left_stick_y;
            speed_right=gamepad1.right_stick_y;
    
            slow_robot=gamepad1.a && (!previos_A_button); 
            
            if(slow_robot){ 
                speed_left=speed_left/2;
                speed_right=speed_right/2;
            }
            
            left_motor.setPower(speed_left);
            right_motor.setPower(speed_right);
    
            dashboard.addData("left speed",speed_left);
            dashboard.addData("right speed",speed_right);
            dashboard.addData("slow robot",slow_robot);
    
            dashboard.update();
    
            previos_A_button=gamepad1.a;
          
        }
    }
}
