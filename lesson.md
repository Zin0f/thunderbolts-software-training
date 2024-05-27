# שיעור #2 
בשיעור זה נשפר את הנהיגה משיעור קודם ונלמד להציג מידע מהרובוט במחשב שלנו  
## הצגת מידע dashboard  
בשיעור קודמת העפלנו מנועים בעזרת הפעולה `()setPower` ונתנו לפעולה ערכים לפי הגויסטיקים. בעיור הזה נתחיל להציג את הערכו האלו על המחשב, בעזרת מה שנקרא dashboard. תתחילו בהוספת השורה למטה בתחילת `runOpMode`. השורה מגדירה משתנה שנותן גישה לdashboash.
```
Telemetry dashboard=FtcDashboard.getInstance().getTelemetry();
```  
לאחרי מכן בסוף הלולאה תסיפו את שתי השורות האלו שמציגות את הערכים ב`speed_left` ו`speed_right` לdashboard.  
```
while (opModeIsActive()){
    //code ...
    
    dashboard.addData("left speed",speed_left);
    dashboard.addData("right speed",speed_right);
    dashboard.update();
}
```  
תרידו את הקוד לרובוט ותפעילו אותו, אתם יכולים לראות עכשיו בפינה הימנית למטה את הערכים שהמנועים מקבלים. אפשר אפילו לראות את הערכים כגרף.  
<!-- צריך להוסיף תמונה של שתמחיש את השורה למעלה -->  

## כפתורים  
כמו שיכולנו לגשת לערכים של הגויסטיקים בשלט אנחנו יכולים גם לגשת לערכים של הכפתורים בשלט. הערך של כל כפתור יכול להיות "לחוץ" או "לא לחוץ". בקוד הערך מצוייג על ידי סוג משתנה שנקרא `boolean`, הסוג משתנה יכול לשמור את הערך `true` (לחוץ) או `false` (לא לחוץ). לדוגמה בישביל לגשת לכפתור A, נכתוב `gamepad1.a`. 

## תנאים if
נניח שאנחנו רוצים להוסיף לנו אפשרות לנהוג ברובוט לאט יותר כדי לנהוג מדוייק יותר. אנחנו יכול להוסיף בקוד שאם כתפור מסויים לחוץ אז נורד את המהירות פי 2.  
```
    speed_left=gamepad1.left_stick_y;
    
    if(gamepad1.a) { //לחוץ A אם
        speed_left = speed_left / 2.0; // אז מחלקים ב2
    }

    left_motor.setPower(speed_left);

```
<details>
<summary dir="rtl">תוסיפו לקוד את הכפתור שמאט את הנהיגה</summary>  
    
```  
public void runOpMode()  {  
            ...
            while (opModeIsActive()){
            speed_left=-gamepad1.left_stick_y;
            speed_right=gamepad1.right_stick_y;

            //add if below
            if(gamepad1.a){ 
                speed_left=speed_left/2;
                speed_right=speed_right/2;
            }  

            left_motor.setPower(speed_left);
            right_motor.setPower(speed_right);

            dashboard.addData("left speed",speed_left);
            dashboard.addData("right speed",speed_right);
            dashboard.update();
        }

    }
}  
```  
</details>  

תורידו את הקוד לרובוט ותבדקו האם הכפתור פועל. (אפשר להסתכל או על האם הרובוט נוסע לאט יותר או על הdashboard).  
## &#x200f;else המשלים של if
ראינו עכשיו את if שמאפשר לנו לבצע קוד רק אם משהו קורה. else מאפשר לנו לבצע קוד אם המשהו לא קורה. למשל:
    ```
    boolean slowed;
    ...
    while (opModeIsActive()){
        if(gamepad1.a) { //לחוץ A אם
            slowed=true; // אז הקוד הזה מתבצע
        }
        else { //אחרת 
            slowed=false; // הקוד הזה מתבצע
        }
        
        dashboard.addData("robot is being slowed",slowed);
    }
    ```  
 בדוגמה הגדרנו משתנה מסוג `boolean` שישמור האם הרובוט מואט או לא מואט. אם (`if`) הכפתור `A` לחוץ אז מצביעים ב`slowed` שהרובוט מואט כרגע (`true`) אחרת (`else`) מציבים ב`slowed` שהרובוט **לא** מואט כרגע (`false`). ההגדרה של 
