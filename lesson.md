# שיעור #2 
בשיעור זה נלמד איך להשתמש בכפתורים ולהציג מידע מהרובוט במחשב שלנו  
## כפתורים  
ראינו שכאשר אנחנו ניגשים לגויסטיק אנחנו מקבלים ערך מסוג `float`. הסוג משתנה שכפתורים נותנים לנו נקרא `boolean` ויש לו רק שתי ערכים אפשריים, `true` ו`false` והם יכולים לייצג "לחוץ" (`true`) ו"לא לחוץ"(`false`). ניגשים לכפורים באותה צורה שניגשים לגויסטיק.  

```java
boolean A_pressed;
A_pressed=gamepad.a;
```  

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
  
עכשיו, לדוגמה, אנחנו יכולים להכין כפתור שכאשר נלחץ עליו הרובוט יסע לאט יותר מפי 2. לשם כך אנחנו צריכים להוסיף הגדרת משתנה באתחול הקוד ו`if` בלולאה. נתחיל בהגדרת מתשנה מסוג `boolean` בקוד משיעור קודם בחלק של האתחול. אחרי כך בתוך הלולאה ניגש לכפתור ונציב את הערך שלו במתשנה. אחרי ההצבה נוסיף `if` שבודק את המתשנה ובקוד של ה`if` נציב ערכים חדשים במשתנים `speed_right` `speed_left`. הסימן לחילוק הוא `/` והסימון לכפל הוא `*`.  



<details>
<summary dir="rtl">הגדרת מתשנה חדש</summary>  
    
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
<summary dir="rtl">הצבת הערך של הכפתור במתשנה </summary>  
 
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


## הצגת מידע dashboard  
בשיעור קודמת העפלנו מנועים בעזרת הפעולה `()setPower` ונתנו לפעולה ערכים לפי הגויסטיקים. בעיור הזה נתחיל להציג את הערכו האלו על המחשב, בעזרת מה שנקרא dashboard. תתחילו בהוספת השורה למטה בתחילת `runOpMode`. השורה מגדירה משתנה שנותן גישה לdashboash.
```java
Telemetry dashboard=FtcDashboard.getInstance().getTelemetry();
```  
לאחרי מכן בסוף הלולאה תסיפו את שתי השורות האלו שמציגות את הערכים ב`speed_left` ו`speed_right` לdashboard.  
```java
while (opModeIsActive()){
    //code ...
    
    dashboard.addData("left speed",speed_left);
    dashboard.addData("right speed",speed_right);
    dashboard.update();
}
```  
תרידו את הקוד לרובוט ותפעילו אותו, אתם יכולים לראות עכשיו בפינה הימנית למטה את הערכים שהמנועים מקבלים. אפשר אפילו לראות את הערכים כגרף.  
<!-- צריך להוסיף תמונה של שתמחיש את השורה למעלה -->  
