// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 9/5/25
// Obtains year, month, and day from elapsed time in milliseconds. Displays the date.

/*---------------------------------------
|               MyDate                  |
-----------------------------------------
| - year: int                           |
| - month: int                          |
| - day: int                            |
-----------------------------------------
| + MyDate()                            |
| + MyDate(elapsedTime: long)           |
| + MyDate(year: int, month: int, day: int) |
| + getYear(): int                      |
| + getMonth(): int                     |
| + getDay(): int                       |
| + setDate(elapsedTime: long): void    |
-----------------------------------------*/
import java.util.GregorianCalendar;

public class MyDate {

    private int year;
    private int month;
    private int day;

    // no-arg constructor that creates MyDate object for current date
    public MyDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // constructs MyDate object with elapsed time
    public MyDate(long elapsedTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // constructs MyDate object with specified year, month, and day
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // set date using elapsed time
    public void setDate(long elapsedTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH); // starts at 0 when there is no 0 month, so adding +1 to fix the date
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) {
        MyDate date1 = new MyDate();
        MyDate date2 = new MyDate(34355555133101L);

        // display results
        System.out.println("Date 1: " + (date1.getMonth() + 1) + "/" + date1.getDay() + "/" + date1.getYear()); // adding 1 to the month because the calendar function starts at 0
        System.out.println("Date 2: " + (date2.getMonth() + 1) + "/" + date2.getDay() + "/" + date2.getYear());
    }

}
