# שיעור #3 
בשיעור נלמד זה נלמד להפעיל את הרובוט על ידי שעון בעזרת רעיון שנקרא מכונת מצבים.  
## מכונת מצבים 
הרעיון של מכונת מצבים הוא שיש לנו מצבים שאנחנו רוצים לייצג בקוד ומעברים בין המעברים האלו. מצבים לרוב יבצעו רעיון יחיד (נהיגה ידנית, התחלה שעון, נסיעה עד שהזמן נגמר) ומעברים בין מצבים אך ורק ישנו את המצב. מכונות מצבים שימושיות במיוחד כאשר הקוד רץ בלולאה אינסופית שכל איטרציה שלה צריכה להסתיים בזמן קצר, שזה בדיוק המקרה שלנו.  
למכונת מצבים שאנחנו נכתוב יהיו שלושה מצבים:  
  * נהיגה ידנית - הקוד שכתבנו בשיעורים קודמים, עוברים ל*התחלת שעון* כאשר הכפתור B לחוץ  
  * התחלת שעון - שומרים את הזמן הנוכחים, עוברים למצב הבא "מִיד"  
  * נסיעה עד שהזמן נגמר - מפעילים את המנועים במהירות קבועה, עוברים למצב *נהיגה ידנית* כאשר הזמן נוכחי גדול בחמש שניות מהזמן ששמרנו.
    
 מצבים בקוד יכוליים להיות מיוצגים על ידי מספרים למשל *נהיגה ידנית* יהיה 1, *התחלת שעון* יהיה 2 ו*נסיעה עד שהזמן נגמר* יהיה 3 ואז המכונת מצבים בקוד תראה בערך כך:
```java
float robot_state=1;
float seconds_at_start_of_auto;
...
while (opModeIsActive()){
  speed_left=-gamepad1.left_stick_y;
  speed_right=gamepad1.right_stick_y;
 
  switch (robot_state) {
    case 1:
       drive code

       if(gamepad1.b){
           robot_state=2;
       }
       break;

    case 2:
       seconds_at_start_of_auto=System.SecondsNow();
       robot_state=3;
       break;

    case 3:
       left_motor.setPower(0.5);
       right_motor.setPower(0.5);

       if(System.SecondsNow()-5.0 > seconds_at_start_of_auto ) {
           robot_state = 1;
       }
       break;
  }
}
```
 אבל נהוג להשתמש בשמות שמייצגים את המספרים שכדי שיהיה ברור יותר למי שקורא מה כל מצב עושה. נותנים להם שמות בעזרת מה שנקרא `enum` והקוד יראה בערך כך:  
 ```java
public enum RobotStates{
   MANUAL_DRIVE,
   AUTO_START_TIMER,
   AUTO_DRIVE_UNTIL_TIME_IS_UP
} 

public void runOpMode()  {
  RobotStates robot_state=MANUAL_DRIVE;
  float seconds_at_start_of_auto;
  ...
  while (opModeIsActive()){
    speed_left=-gamepad1.left_stick_y;
    speed_right=gamepad1.right_stick_y;
   
    switch (robot_state) {
      case MANUAL_DRIVE:
         drive code
  
         if(gamepad1.b){
             robot_state=AUTO_START_TIMER;
         }
         break;
  
      case AUTO_START_TIMER:
         seconds_at_start_of_auto=System.SecondsNow();
         robot_state=AUTO_DRIVE_UNTIL_TIME_IS_UP;
         break;
  
      case AUTO_DRIVE_UNTIL_TIME_IS_UP:
         left_motor.setPower(0.5);
         right_motor.setPower(0.5);
  
         if(System.SecondsNow()-5.0 > seconds_at_start_of_auto ) {
             robot_state = MANUAL_DRIVE;
         }
         break;
    }
  }
}
```
### enum 








<details>
<summary dir="rtl">הגדרת משתנה חדש</summary>  
    
```java  
public void runOpMode()  {  
           
    DcMotor left_motor;
    DcMotor right_motor;
    float speed_left;
    float speed_right;
    boolean slow_robot; // המשתנה שיכיל את הערך מהכפתור 
    ...
    
}
```  
</details>  
