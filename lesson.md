# שיעור #3 
בשיעור נלמד זה נלמד להפעיל את הרובוט על ידי שעון בעזרת מכונת מצבים.  
## מכונת מצבים 
הרעיון של מכונת מצבים הוא שיש לנו מצבים שאנחנו רוצים לייצג בקוד ומעברים בין המעברים האלו. מצבים לרוב יבצעו רעיון יחיד (נהיגה ידנית, התחלה שעון, נסיעה עד שהזמן נגמר) ומעברים בין מצבים אך ורק ישנו את המצב. מכונות מצבים שימושיות במיוחד כאשר הקוד רץ בלולאה אינסופית שכל איטרציה שלה צריכה להסתיים בזמן קצר, שזה בדיוק המקרה שלנו.  
למכונת מצבים שאנחנו נכתוב יהיו שלושה מצבים:  
  * *נהיגה ידנית* - הקוד עבור נהיגה שכתבנו בשיעורים קודמים, עוברים ל*התחלת שעון* כאשר הכפתור B לחוץ  
  * *התחלת שעון* - מתחילים טיימר, עוברים למצב הבא   
  * *נסיעה עד שהזמן נגמר* - מפעילים את המנועים במהירות קבועה, עוברים למצב *נהיגה ידנית* כאשר הזמן הטיימר מראה שעברו 5 שניות.
## ייצוג מצבים בקוד - enum
   הדרך הנוחה ביותר לייצג מצבים של מכונת מצבים בקוד היא ידי יצירת סוג משתנה שייצג את המצבים הללו. אנחנו יכולים להכין סוגי משתנים בעזרת `enum`, זה יאפשר לנו לתת לערכים של הסוג משתנה איזה שם שאנחנו רוצים ובדיוק כמה ערכים שאנחנו רוצים. מגדירים `enum`כך:  

     
```java
public enum RobotStates{
   MANUAL_DRIVE,
   AUTO_START_TIMER,
   AUTO_DRIVE_UNTIL_TIME_IS_UP
} 
```  
<!-- בשורה למטה כתוב "ערך" ללפני כל ערך כדי שפסיקים התנהגו טוב ויהיו מימין  לשמאל-->
השם של ה`enum` (ושל הסוג משתנה) בדוגמה הוא `RobotState` ויש לו שלושה ערכים אפשריים: הערך `MANUAL_DRIVE`, הערך `AUTO_START_TIMER` והערך `AUTO_DRIVE_UNTIL_TIME_IS_UP`. שימו לב שהגדרה של `enum`צריכה להכתב מחוץ לפונקציה.   

    
אנחנו יכולים להכריז משתנים מהסוג `RobotState` וגם להציב במשתנים את הערכים שהגדרנו ב`enum`. לדוגמה:  
```
RobotStates state;
state = MANUAL_DRIVE;
if(state==MANUAL_DRIVE){
  //this code will excute bc 'state' is set to MANUAL_DRIVE
}
if(state==AUTO_START_TIMER){
  //this code will not excute bc 'state' is not set to AUTO_START_TIMER
}
```  
<!---
  עכשיו תפתחו את הקוד משיעור קודם ותכתבו בו את ההגדרה של ה`enum` מחוץ לפונקציה `runOpMode` ותכריזו על משתנה בחלק של אתחול הקוד
-->

<details>
<summary dir="rtl">עכשיו תפתחו את הקוד משיעור קודם ותכתבו בו את ההגדרה של ה`enum` מחוץ לפונקציה `runOpMode` ותכריזו על משתנה בחלק של אתחול הקוד</summary>  
    
```java  
public class Drive extends LinearOpMode {
    public enum RobotStates{
        MANUAL_DRIVE,
        AUTO_START_TIMER,
        AUTO_DRIVE_UNTIL_TIME_IS_UP;
    } 
    @Override
    public void runOpMode()  {
        RobotStates robot_state=RobotStates.MANUAL_DRIVE;
        ...
        waitForStart();

```  
</details>  

 ## מדידת זמן בקוד  
  בסיפריה שאנחנו משתמשים יש סוג משתנה, `ElapsedTime`, שמאפשר לנו להכין טיימרים. אתחול של המשתנה מהסוג הזה נעשה כך:  
  
  `ElapsedTime timer=new ElapsedTime();`  
  
  אחרי אתחול שלו אפשר לקרוא לפעולה `()timer.reset` שתאפס את הזמן שהטיימר ספר. אפשר גם לקרוא לפעולה `()timer.seconds` שתגיד כמה שניות עבורו מאז האיפוס. שימו לב שאיפוס של טיימר אחד לא משפיע על טיימר אחר.  
<details>
<summary dir="rtl">הטיימר משתמש בשעון הפינימי של  הרובוט כדי לפעול</summary>  
 
 בכל המחשבים (הרובוט מופעל על ידי מחשב קטן) יש שעון פנימי שסופר מעלה כל עוד המחשב יש חשמל.  שכאשר רוצים להתחיל למדוד זמן בקוד שומרים את הערך של השעון. כאשר רוצים לבדוק כמה זמן עבר פשוט מחסרים בין הערך העדכני של השעון והערך השמור בתחילת המדידה. `ElapsedTime` עובד בדיוק כך אבל נותן לפעולות שם כדי שיהיה קל יותר להבין (וגם המרה נוחה בין ננו-שניות ושניות)
</details>  
  
 ## כתיבת מכונה המצבים  
 אנחנו יודעים לייצג מצבים בקוד, למדוד זמן ולהשוות בין מצבים אז עכשיו אפשר לכתוב את המכונת מצבים.  
 <!---
   בתוך הלולאה תכתבו את הקוד עבור המצב הראשון במכונת מצבים ותשימו אותו בתוך `if` שבודק `state==MANUAL_DRIVE`  
-->

<details>
<summary dir="rtl"> בתוך הלולאה תכתבו את הקוד עבור המצב הראשון במכונת מצבים ותשימו אותו בתוך `if` שבודק `state==MANUAL_DRIVE`    </summary>  
    
```java  
if(state==MANUAL_DRIVE){
   speed_left=-gamepad1.left_stick_y;
   speed_right=gamepad1.right_stick_y;
   
   left_motor.setPower(speed_left);
   right_motor.setPower(speed_right);
   
   if(gamepad1.b){
       robot_state=RobotStates.AUTO_START_TIMER;
   }
}
```  
</details>  

 
 <!---
      תוסיפו `if` שבודק `state==AUTO_START_TIMER` ובתוכו את הקוד עבור המצב השני במכונת מצבים ותזכרו לאתחל את הטיימר  
-->

<details>
<summary dir="rtl">   תוסיפו `if` שבודק `state==AUTO_START_TIMER` ובתוכו את הקוד עבור המצב השני במכונת מצבים ותזכרו לאתחל את הטיימר     </summary>  
    
```java  
if(state==AUTO_START_TIMER){
   timer.reset();
   robot_state=RobotStates.AUTO_DRIVE_UNTIL_TIME_IS_UP;
}
```  
</details>  

  
 <!---
      תוסיפו `if` שבודק `state==AUTO_DRIVE_UNTIL_TIME_IS_UP` ובתוכו את הקוד עבור המצב השלישי במכונת מצבים   
-->

<details>
<summary dir="rtl">    תוסיפו `if` שבודק `state==AUTO_DRIVE_UNTIL_TIME_IS_UP` ובתוכו את הקוד עבור המצב השלישי במכונת מצבים      </summary>  
    
```java  
if(state==AUTO_DRIVE_UNTIL_TIME_IS_UP){
  left_motor.setPower(0.5);
  right_motor.setPower(0.5);
  
  if(timer.seconds()>5) {
     robot_state = RobotStates.MANUAL_DRIVE;
  }
}
```  
</details>  
 
 <details>
<summary dir="rtl">  
  הקוד במלואו צריך להראות כך:       </summary>  
    
```java  
public class TankDrive extends LinearOpMode {
    public enum RobotStates {
        MANUAL_DRIVE,
        AUTO_START_TIMER,
        AUTO_DRIVE_UNTIL_TIME_IS_UP
    }

    @Override
    public void runOpMode() {

        DcMotor left_motor;
        DcMotor right_motor;
        float speed_left;
        float speed_right;
        boolean slow_robot;

        RobotStates robot_state = RobotStates.MANUAL_DRIVE;
        ElapsedTime timer = new ElapsedTime();

        left_motor = hardwareMap.dcMotor.get("1");
        right_motor = hardwareMap.dcMotor.get("2");

        left_motor.setMode(RUN_WITHOUT_ENCODER);
        right_motor.setMode(RUN_WITHOUT_ENCODER);

        left_motor.setDirection(FORWARD);
        right_motor.setDirection(REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            if (state == MANUAL_DRIVE) {
                speed_left = -gamepad1.left_stick_y;
                speed_right = gamepad1.right_stick_y;

                left_motor.setPower(speed_left);
                right_motor.setPower(speed_right);

                if (gamepad1.b) {
                    robot_state = RobotStates.AUTO_START_TIMER;
                }
            }
            if (state == AUTO_START_TIMER) {
                timer.reset();
                robot_state = RobotStates.AUTO_DRIVE_UNTIL_TIME_IS_UP;
            }
            if (state == AUTO_DRIVE_UNTIL_TIME_IS_UP) {
                left_motor.setPower(0.5);
                right_motor.setPower(0.5);

                if (timer.seconds() > 5) {
                    robot_state = RobotStates.MANUAL_DRIVE;
                }
            }
            dashboard.addData("left speed",speed_left);
            dashboard.addData("right speed",speed_right);
            
            dashboard.update();
        }
    }
}
```  
</details>  
 
 תרידו את הקוד לרובוט, תפעילו אותו ותנסו להפעיל את הנסיעה של החמש שניות.  
 
## שימוש בswitch  
‏`switch` דומה ל`if` בכך ששניהם בודקים את הערך של משתנה או ביטוי מסויים. ב`switch` צריך להיות קטע קוד שיתאים לכל ערך אפשרי שיכול להיות במשתנה, לעומת `if` שיש לו קטע קוד רק אם הערך שווה ל`true`. לרוב כאשר בודקים ערך של משתנה שהסוג שלו מוגדר על ידי `enum` משמתמשים ב`switch`.  
 במקרה שלנו אפשר להחליף את שלושת בדיקות ה`if` ב`switch`, הקוד שהיה מקודם כך:  
 ```java
if (state == MANUAL_DRIVE) {
  code 1
}
if (state == AUTO_START_TIMER) {
  code 2
}
if (state == AUTO_DRIVE_UNTIL_TIME_IS_UP) {
  code3
}
```
 יראה כך:  
```java
 switch (robot_state) {
    case MANUAL_DRIVE:
        code 1
        break;

    case AUTO_START_TIMER:
        code 2
        break;

    case AUTO_DRIVE_UNTIL_TIME_IS_UP:
        code 3
        break;
}
```

### הסבר על switch
 כמו ב`if` בסוגריים `()` נמצא המשתנה שאנחנו בודקים ובתוך ההסוגריים המסולסלות `{}` נמצא הקוד של ה`switch`, והוא מחולק על ידי `case` לערכים השונים שמוגדרים ב`RobotStates`. ליד כל `case`יש את השם שתואם לו ואחרי השם נקודותיים `:`. מהנקודותיים עד למילה `;break` נמצא הקוד שמתבצע אם הערך בrobot_state תואם לcase.  
 
 חשוב לכתוב `;break` בסוף כל `case` כדי שהקוד של ה`case` הבא לא בטעות יבצע יחד איתו.  
 <!---
   תשנו את הקוד כך שישתמש ב`switch` במקום `if` 
 -->

<details>
<summary dir="rtl">  תשנו את הקוד כך שישתמש ב`switch` במקום `if`  </summary>  
    
```java  
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

  dashboard.update();
}

```  
</details>  

 ## הצגת enum בdashboard
 לכל משתנה מסוג שהוגדר על ידי `enum`יש שני פעולות שעוזרות לנו בהצגה שלו:
 * ‏`()name.` שנותן לנו את השם של הערך במשתנה. אם robot_state שווה ל`MANUAL_DRIVE` אז `()robot_state.name` יתן "MANUAL_DRIVE" כמילה שאפשר להציג במסך.
 * ‏`()ordinal.` שנותן לנו את מספר הסידורי של הערך במשתנה. אם robot_state שווה ל`MANUAL_DRIVE` אז `()robot_state.name` יתן את המספר `0` כי המצב מוגדר ראשון ב`enum`.

 שימו לב שלרוב בתכנות מספור יתחיל מהמספר אפס ולא מאחד.
     
   בעזרת שני הפעולות האלה אפשר להוסיף הצגה של המצב של רובוט dashboard.  
 ```java
 dashboard.addData("left speed",speed_left);
 dashboard.addData("right speed",speed_right);
 dashboard.addData("RobotState name",robot_state.name()); //add this 
 dashboard.addData("RobotState number",robot_state.ordinal()); // and this
 
 dashboard.update();
```
תוסיפו את ההצגה ותורידו את הקוד לרובוט בישביל בדיקה.  

   
 <details>
<summary dir="rtl">  
  הקוד במלואו צריך להראות כך:       </summary>  
    
```java  
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

```  
</details>  
