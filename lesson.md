# שיעור #1 
בשיעור זה נפעיל בעל שני מנועים שיסע בצורת טנק
לפני שנתחיל לכתוב קוד אנחנו צריכים להבין קצת לגבי השפה java.  
## משתנים  
בשפה יש לנו משתנים שיכולים ליכולים להגדיר. לכל  משתנה יש שם וסוג מידע.  

`float speed;`  
`DcMotor drive_motor;`  
  
כאן הגדרנו שני משתנים. אחד בעל השם `speed` והסוג `float` שמייצג מספר עם נקודה עשורנית. והשני בעל השם `drive_motor` והסוג `DcMotor` שמייצג מנוע בקוד ומאפשר לנו להשתמש במנוע הפיזי.  

אחרי שהגדרנו משתנה אנחנו יכולים להציב בו ערך על ידי הסימן `=`. משמאל לסימן צריך להיות המשתנה שאנחנו רוצים להציב בו ומימין הערך שאחנו רוצים להציב. אחרי כך נוכל גם להשתמש בערך שהצבנו

`speed=0.5;`  
`speed=speed+1.0;`  

בדוגמה הזו  הצבנו במשתנה `speed` את הערך חצי (0.5). אחרי כך  הצבנו ב`speed` ערך חדש, הערך שב`speed` ועוד אחד (1). אחרי ההצבה השניה, הערך שבמשתנה `speed` כבר לא חצי (0.5) אלא אחד וחצי (1.5).  

הסוג מתשנה `float` נחשב סוג משתנה פשוט משום שהוא רק מכיל ערך מתמטי, הוא חלק מjava. לעומתו סוג המתשנה `DcMotor` שמייצג משהו פיזי מורכב יותר שמאפשר לנו להפעיל את הדבר הפיזי הוא מייצג. `DcMotor` הוא לא חלק מjava, הוא חלק מהסיפריות שאנחנו משתמשים בהן. בגלל ש`DcMotor` ועוד סוגי משתנה שנלמוד עליהם בהמשך מפעילים משהו פיזי, יש לנו כלים מיוחדים שבעזרתם אנחנו מציבים ערכים במשתנים מסוגים כאלו. בדוגמה שלנו יש לנו מפה שיש בו את כל רכיבים שמחוברים לרבוט ואת השמות שלהם ואנחנו יכולים בעזרת המפה לבקש את הערכים של המנועים.  

`left_motor = hardwareMap.dcMotor.get("1");`  

כאן נגשו למפה `hardwareMap` לחלק של המנועים `dcMotor` וביקשנו את הערך של מנוע שהשם שלו הוא "1" `.get("1")`.  
שימו לב לשימוש ב`.`, זו הדרך העיקרים להשתמש במשתנה מורכבים.  
שכדי שהקוד שאנחנו כותבים ירוץ אחנו צריכים שמשהו חיצוני יקרא לו. בשיעור הזה נכתוב את הקוד בתוך פונקציה שנקראת `runOpMode`.  
## פונציה 
כדי שהקוד שלנו ירוץ צריך לקרוא לו ממקום חיצוני. לשם כך אחנו צריכים פונקציה, כרגע אתם יכולים לחשוב על פונציה פשוט כדרך לתת לקטע קוד שם ודרך השם ניתן להריץ אותו.  
```
public void run(){
  float speed;
  speed =0.5;
  speed =speed +1;
}
```  
הקוד בתוך הסוגריים המשולשלות `{}` הוא הקוד שירות שכהפונציה תקרא.
## כתיבת קוד  
