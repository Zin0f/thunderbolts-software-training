# שיעור #2 
בשיעור זה נלמד איך להשתמש בכפתורים ולהציג מידע מהרובוט במחשב שלנו  
## כפתורים  
ראינו שכאשר אנחנו ניגשים לג'ויסטיק אנחנו מקבלים ערך מסוג `float`. הסוג משתנה שכפתורים נותנים לנו נקרא `boolean` ויש לו רק שתי ערכים אפשריים, `true` ו`false` והם יכולים לייצג "לחוץ" (`true`) ו"לא לחוץ"(`false`). ניגשים לכפורים באותה צורה שניגשים לג`ויסטיק.  

```java
boolean A_pressed;
A_pressed=gamepad.a;
```
בדוגמה נגשנו לכפתור A.

כדאי להשתמש במשתנה מסוג `boolean` צריך להשתמש במה שנקרא `if`. ל`if` יש שני חלקים:  
  *  תנאי, הוא נמצא בתוך הסוגריים רגילות `()` ואם הערך של המשתנה שמשמש כתנאי הוא `true` אז הקוד בחלק השני יתבצע.  
  * קוד, נמצאים בתוך הסוגריים המסולסלות `{}`  ומתבצע רק אם התנאי נכון (ערכו `true`).  
    
  ```java
  if (codition)
  {
    code that only excutes if the codition is true
  }
  code that will excute regadless of the codition.
```  
  
עכשיו, לדוגמה, אנחנו יכולים להכין כפתור שכאשר נלחץ עליו הרובוט יסע לאט יותר מפי 2. לשם כך אנחנו צריכים להוסיף הגדרת משתנה באתחול הקוד ו`if` בלולאה. נתחיל בהגדרת משתנה מסוג `boolean` בקוד משיעור קודם בחלק של האתחול. אחרי כך בתוך הלולאה ניגש לכפתור ונציב את הערך שלו במשתנה. אחרי ההצבה נוסיף `if` שבודק את המשתנה ובקוד של ה`if` נציב ערכים חדשים במשתנים `speed_right` `speed_left`. הסימן לחילוק הוא `/` והסימון לכפל הוא `*`.  



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

<details> 
<summary dir="rtl">הצבת הערך של הכפתור במשתנה </summary>  
 
```java  
public void runOpMode()  {  
    ...
    waitForStart();
    while (opModeIsActive()){
        speed_left=-gamepad1.left_stick_y;
        speed_right=gamepad1.right_stick_y;
        slow_robot=gamepad1.a; // הצבת הערך של הכפתור במשתנה 

        left_motor.setPower(speed_left);
        right_motor.setPower(speed_right);
    }
}  
```
</details>  

<details> 
<summary dir="rtl">התנאי ושינוי ערכי המהירות</summary>

```java

public void runOpMode()  {  
    ...
    waitForStart();
    while (opModeIsActive()){
        speed_left=-gamepad1.left_stick_y;
        speed_right=gamepad1.right_stick_y;
        slow_robot=gamepad1.a;

        if(slow_robot){// התנאי והקוד של התנאי
            speed_left=speed_left/2;
            speed_right=speed_right/2;
        }

        left_motor.setPower(speed_left);
        right_motor.setPower(speed_right);
    }
}  
```  
</details>  

 תורידו את הקוד לרובוט ותבדקו האם הכפתור מאט את הנסיעה של הרובוט כאשר אתם נוהגים.


## הצגת מידע dashboard  
עד כמו כשרצינו לראות האם הקוד שכתבנו עובד הרצנו אותו על הרובוט וראינו את ההתנהגות הפיזית שלו. זה יכול להיות טוב לבדיקות מסויימות ואבל עובד פחות טוב לבדיקות אחרות. לכן יש לנו כלי נוסף שמאפשר להציג מידע מהרובוט למחשב, הכלי נקרא dashboard ובעצם השתמשתם בו כבר בשביל להפעיל את הרובוט. בשביל להשתמש בdashboard צריך באתחול הקוד להגדיר משתנה שישמש כקישור לdashabord.  
```java
Telemetry dashboard=FtcDashboard.getInstance().getTelemetry();
```  
לאחרי מכן בסוף הלולאה תסיפו את ארבע שורות האלו. השלוש הראשונות מעבירה את הערכים ב`speed_left`, `slow_robot` ו`speed_right` בdashboard. השורה האחרונה מעדכנן את האתר במחשב עם הערכים שעברנו לdashboard. עידכון צריך לקרות פעם אחד בכל לולאה.  
```java
    dashboard.addData("left speed",speed_left);
    dashboard.addData("right speed",speed_right);
    dashboard.addData("slow robot",slow_robot);
 
    dashboard.update();
```
המילים בתוך הגרשיים `"left_speed"` הן המילים שייצגו בdashboard ליד הערך של המשתנה.  


<details> 
<summary dir="rtl">הקוד אחרי הוספת הdashboard צריך להראות כך</summary>

```java
public void runOpMode()  {
    Telemetry dashboard=FtcDashboard.getInstance().getTelemetry();

    DcMotor left_motor;
    DcMotor right_motor;
    float speed_left;
    float speed_right;
    boolean slow_robot;
    
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
        slow_robot=gamepad1.a;
    
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
    }

}  
```
</details>  

  תרידו את הקוד לרובוט ותפעילו אותו. אתם יכולים לראות עכשיו בפינה הימנית למטה את הערכים שהמנועים מקבלים. אפשר אפילו לראות את הערכים כגרף.    
<!-- צריך להוסיף תמונה של שתמחיש את השורה למעלה -->  

## לוגיקה בוליאנית  
בתחילת השיעור ראינו שאפשר להציב במשתנה מסוג `boolean` (משתנה בולאני) ערך ולבדוק אותו אחרי כך אם `if`, אבל אפשר לבצע איתם גם חיבורים לוגיים כמו "וגם", "או" ו"אם לא". החיבורים האלו מחזירים `boolean`  כמו שחיבור מספרים נותן לנו מספר, אפשר להשתמש בהם בהצבה או בתוך תוך התאני של `if`. להלן כמה דוגמאות.  

בדיקה האם אחד משני תנאים נכונים בתוך if בעזרת `||`
```java
 boolean A_Button;
 boolean B_Button;

 if(A || B)
 {
   code // this excutes when either A or B are pressed 
 }
```
 הצבה במשתנה האם שני תנאים נכונים בעזרת `&&`
```java
 boolean A_Button;
 boolean B_Button;
 boolean condition;
 ...
 condition = A_Button && B_Button;
 if(condition)
 {
  code // this exuctes only when both A and B are pressed
 }
``` 
בדיקה האם תנאי לא נכון בעזרת `!`
```java
 boolean A_Button;
 ...
 if(!A_Button){
  code // this only excutes if A is not pressed
 }
```
בדיקה האם תנאי נכון ומקודם היה לא נכון בעזרת `!`ו`&&`   
```java
 boolean A_Button;
 boolean prev_A_Button;
 ...
 prev_A_Button = A_Button;
 A_Button = gamepad1.a;

 if(A_Button && (!prev_A_Button) ){
   code // הקודם מתבצע רק עם הכפתור לחוץ ומקודם הוא היה לחוץ
 }
```  
בעזרת החיבורים הלוגים האלו אנחנו יכולים לשנות את ההתנהגות של הכפתור האטה ככה שלחיצה אחת תעביר את הרובוט למצב איטי ולחיצה נוספת כדי להחזיר לנסיעה רגילה.  
נעשה את זה על ידי הוספת משתנה שישמור את הערך של הכפתור מהלולאה הקודמת ו`if` בדומה לדוגמה האחרונה.  


```java
public void runOpMode()  {
    Telemetry dashboard=FtcDashboard.getInstance().getTelemetry();

    DcMotor left_motor;
    DcMotor right_motor;
    float speed_left;
    float speed_right;
    boolean previous_A_button; //added this

    boolean slow_robot;
    
    ...
    waitForStart();
    while (opModeIsActive()){
        speed_left=-gamepad1.left_stick_y;
        speed_right=gamepad1.right_stick_y;

        slow_robot=gamepad1.a && (!previous_A_button); // added the requirement that the button needs to be previously not pressed (&& !previous_A_button)
        
        if(slow_robot){ //note that this if hasn`t changed
            speed_left=speed_left/2;
            speed_right=speed_right/2;
        }
        
        left_motor.setPower(speed_left);
        right_motor.setPower(speed_right);

        dashboard.addData("left speed",speed_left);
        dashboard.addData("right speed",speed_right);
        dashboard.addData("slow robot",slow_robot);

        dashboard.update();

        previous_A_button=gamepad1.a; // this line at the end so it will correctly be the last value of A when we are in the next loop iteration. 
      
    }

}  
```
