package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "tank drive")
public class Drive extends LinearOpMode {
    public enum RobotStates{
        MANUAL_DRIVE,
        AUTO_START_TIMER,
        AUTO_DRIVE_UNTIL_TIME_IS_UP;
    } 
    @Override
    public void runOpMode()  {
        RobotStates robot_state=RobotStates.MANUAL_DRIVE;
        Telemetry dashboard=FtcDashboard.getInstance().getTelemetry();

        DcMotor left_motor;
        DcMotor right_motor;
        float speed_left=0;
        float speed_right=0;
        ElapsedTime timer=new ElapsedTime();

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

                    left_motor.setPower(speed_left);
                    right_motor.setPower(speed_right);

                    if(gamepad1.b){
                        robot_state=RobotStates.AUTO_START_TIMER;
                    }
                    break;

                case AUTO_START_TIMER:
                    timer.reset();
                    robot_state=RobotStates.AUTO_DRIVE_UNTIL_TIME_IS_UP;
                    break;

                case AUTO_DRIVE_UNTIL_TIME_IS_UP:
                    left_motor.setPower(0.5);
                    right_motor.setPower(0.5);

                    if(timer.seconds()>5) {
                        robot_state = RobotStates.MANUAL_DRIVE;
                    }
                    break;
            }
           
            dashboard.addData("left speed",speed_left);
            dashboard.addData("right speed",speed_right);
            dashboard.addData("RobotState name",robot_state.name());
            dashboard.addData("RobotState number",robot_state.ordinal());

            dashboard.update();
          
        }
    }
}
