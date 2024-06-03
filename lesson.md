# שיעור #3 
בשיעור נלמד זה נלמד להפעיל את הרובוט על ידי שעון בעזרת מכונת מצבים.  
## מכונת מצבים 
הרעיון של מכונת מצבים הוא שיש לנו מצבים שאנחנו רוצים לייצג בקוד ומעברים בין המעברים האלו. מצבים לרוב יבצעו רעיון יחיד (נהיגה ידנית, התחלה שעון, נסיעה עד שהזמן נגמר) ומעברים בין מצבים אך ורק ישנו את המצב. מכונות מצבים שימושיות במיוחד כאשר הקוד רץ בלולאה אינסופית שכל איטרציה שלה צריכה להסתיים בזמן קצר, שזה בדיוק המקרה שלנו.  
למכונת מצבים שאנחנו נכתוב יהיו שלושה מצבים:  
  * *נהיגה ידנית* - הקוד שכתבנו בשיעורים קודמים, עוברים ל*התחלת שעון* כאשר הכפתור B לחוץ  
  * *התחלת שעון* - מתחילים טיימר, עוברים למצב הבא "מייד"  
  * *נסיעה עד שהזמן נגמר* - מפעילים את המנועים במהירות קבועה, עוברים למצב *נהיגה ידנית* כאשר הזמןהטיימר מראה שעברו 5 שניות.
    
 מצבים בקוד יכוליים להיות מיוצגים על ידי מספרים למשל *נהיגה ידנית* יהיה 0, *התחלת שעון* יהיה 1 ו*נסיעה עד שהזמן נגמר* יהיה 2 (תזכרו שלרוב בתכנות מספרים מתחילים מ0) ואז המכונת מצבים בקוד תראה בערך כך:
```java
float robot_state=1;
ElapsedTime timer=new ElapsedTime();
...
while (opModeIsActive()){
  speed_left=-gamepad1.left_stick_y;
  speed_right=gamepad1.right_stick_y;
 
  if(robot_state==1) {
    drive code
    if(gamepad1.b) {
      robot_state=2;
    }
  }

  if(robot_state==2) {
    timer.start();
    robot_state=3;
  }

  if(robot_state==3) {
    left_motor.setPower(0.5);
    right_motor.setPower(0.5);
    if(timer.seconds()>5 ) {
      robot_state = 1;
    }
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
  ElapsedTime timer=new ElapsedTime();
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
         timer.start()
         robot_state=AUTO_DRIVE_UNTIL_TIME_IS_UP;
         break;
  
      case AUTO_DRIVE_UNTIL_TIME_IS_UP:
         left_motor.setPower(0.5);
         right_motor.setPower(0.5);
         iftimer.seconds()>5) {
             robot_state = MANUAL_DRIVE;
         }
         break;
    }
  }
}
```
  
### מה זה enum?  
  
זו היא אחת  מהדרכים ליצור סוגי משתנה משלנו. במקרה של `enum` לסוג משתנה יש כמות מוגבלת של ערכים אפשריים, כמו הסוג משתנה `boolean` שראינו בשיעור הקודם שיכול להיות רק `true` או `false`. הערכים האפשריים של הסוג שמורים כמספרים אבל בקוד אנחנו רואים מילים והמילים עוזרות לנו להבין את הקוד.  


 ```java
public enum RobotStates{
   MANUAL_DRIVE,
   AUTO_START_TIMER,
   AUTO_DRIVE_UNTIL_TIME_IS_UP
}
```  

 אז בדוגמה הזאת יצרנו סוג משתנה חדש שנקרא `RobotStates` והדגרנו שיש לו שלושה ערכים אפשריים, הערך `MANUAL_DRIVE`, הערך `AUTO_START_TIMER` והערך `AUTO_DRIVE_UNTIL_TIME_IS_UP`. כל אחד מהערכים מייצג את אחד מהמצבים שהגדרנו בתחילת השיעור עבור המכונת מצבים.  
   
   
 ```java
 RobotStates robot_state=MANUAL_DRIVE;
```
 אחרי כך בקוד הגדרנו משתנה (`robot_state`) בעל הסוג הזה ואתחלנו אותו במצב של נהיגה ידנית `MANUAL_DRIVE` כדי שנוכל לנהוג ברובוט מיד עם הפעלתו ולשנות המצב מאוחר יותר במכונת המצבים.  
    
 ### &#x200f;switch
   
 כאשר משתמשים ב`enum` במכונת מצבים נהוג לבדוק את המצבים  בעזרת `switch` ולא בעזרת `if`.  
 השניים דומים, לשניהם יש ביטוי התוך הסוגריים `()` שלהם, אבל ל`switch` צריך להיות קוד בשביל כל ערך אפשריים של הביטוי לעומת `if` שיש לו קוד שרץ רק עבור הערך `true`.  
 
 ```java  
switch (robot_state) {
      case MANUAL_DRIVE:
         code1
         break;
  
      case AUTO_START_TIMER:
         code2
         break;
  
      case AUTO_DRIVE_UNTIL_TIME_IS_UP:
         code3
         break;
}
```
  
מכונת המצבים עצמה היא ה`switch` שכל איטרציה של הלולאה בודק את הערך של המשתנה `robot_state` ומבצע את הקוד המתאים למצב.  
כאשר הערך ב`robot_state` הוא `MANUAL_DRIVE` הקטע שמסומן כ'code1' יתבצע. אותו דבר לגבי `AUTO_START_TIMER` ו'code2' ובמצב השלישי.  

  ## מדידת זמן בקוד  
  בסיפריה שאנחנו משתמשים יש סוג משתנה `ElapsedTime` שמאפשר לנו להכין טיימרים. אתחול של המשתנה מהסוג הזה נעשה כך:  
  `ElapsedTime timer=new ElapsedTime();`  
  אחרי אתחול שלו אפשר לקרוא לפעולה `()timer.reset` שתאפס את הזמן הטיימר ספר ו`()timer.seconds` תביא לנו כמה שניות עבור מאז האיפוס. שימו לב שאיפוס של טיימר אחד לא משפיע על טיימר אחר.  
<details>
<summary dir="rtl">הטיימר משתמש בשעון הפינימי של  הרובוט כדי לפעול</summary>  
 בכל המחשבים (הרובוט מופעל על ידי מחשב קטן) יש שעון פנימי שסופר מעלה כל עוד המחשב יש חשמל.  שכאשר רוצים להתחיל למדוד זמן בקוד שומרים את הערך של השעון. כאשר רוצים לבדוק כמה זמן עבר פשוט מחסרים בין הערך העדכני של השעון והערך השמור בתחילת המדידה. `ElapsedTime` עובד בדיוק כך אבל נותן לזה שם שקל יותר להבין (וגם המרה נוחה בין ננו-שניות ושניות)
</details>
   




<!--
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
-->
