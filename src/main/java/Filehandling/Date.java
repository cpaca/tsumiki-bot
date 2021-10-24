package Filehandling;

import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

public class Date {
    // [DATE] in this case refers to [DAY/MONTH/YEAR]
    // This means HOURS/MINUTES/SECONDS/ETC. ARE IGNORED
    // AKA, HUMAN-READABLE DATE!

    private int day;
    private int month;
    private int year;

    public Date(){
        this(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
    }

    public Date(Calendar c){
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
    }

    public Date(String s){
        String[] dmy = s.split("/");
        if(dmy.length < 3){
            day = 1;
            month = 1;
            year = 1970;
            return;
        }
        day = Integer.parseInt(dmy[0]);
        month = Integer.parseInt(dmy[1]);
        year = Integer.parseInt(dmy[2]);
    }

    public boolean isToday(){
        return this.sameDay(new Date());
    }

    public boolean sameDay(Date other){
        return sameMonth(other) && other.day == this.day;
    }
    public boolean sameMonth(Date other){
        return sameYear(other) && other.month == this.month;
    }
    public boolean sameYear(Date other){
        return other.year == this.year;
    }
    public boolean isDefault(){
        return sameDay(new Date("default"));
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
}
