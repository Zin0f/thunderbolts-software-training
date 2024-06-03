package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "tank drive")
public class TankDrive extends LinearOpMode {
    public enum RobotStates{
        MANUAL_DRIVE,
        AUTO_START_TIMER,
        AUTO_DRIVE_UNTIL_TIME_IS_UP
    } 
    @Override
    public void runOpMode()  {

        DcMotor left_motor;
        DcMotor right_motor;
        float speed_left;
        float speed_right;
        boolean slow_robot;
        RobotStates robot_state=RobotStates.MANUAL_DRIVE;
        ElapsedTime timer = new ElapsedTime();
        
        left_motor = hardwareMap.dcMotor.get("1");
        right_motor = hardwareMap.dcMotor.get("2");

        left_motor.setMode(RUN_WITHOUT_ENCODER);
        right_motor.setMode(RUN_WITHOUT_ENCODER);

        left_motor.setDirection(FORWARD);
        right_motor.setDirection(REVERSE);

        waitForStart();
        while (opModeIsActive()){

            switch (robot_state) {
            case MANUAL_DRIVE:
                speed_left=-gamepad1.left_stick_y;
                speed_right=gamepad1.right_stick_y;
                slow_robot=gamepad1.a;

                if(slow_robot){
                    speed_left=speed_left/2;
                    speed_right=speed_right/2;
                }
        
                left_motor.setPower(speed_left);
                right_motor.setPower(speed_right);

                if(gamepad1.b){
                    robot_state=AUTO_START_TIMER;
                }
                break;
            
            case AUTO_START_TIMER:
                timer.start();
                robot_state=AUTO_DRIVE_UNTIL_TIME_IS_UP;
                break;
        
            case AUTO_DRIVE_UNTIL_TIME_IS_UP:
                left_motor.setPower(0.5);
                right_motor.setPower(0.5);
                if(timer.seconds()>5) {
                    robot_state = MANUAL_DRIVE;
                }
                break;
            }
        }
    }
}
