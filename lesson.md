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

