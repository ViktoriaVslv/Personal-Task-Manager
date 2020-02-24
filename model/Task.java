package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Task {

    private String name;
    private String content;
    private String date;
    private String time1;
    private String time2;
    private String color;
    private SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");


    public Task(String n, String cont,  String d, String t1, String t2, String col){

        name = n;
        content = cont;
        date = d;
        time1 = t1;
        time2 = t2;
        color=col;
    }
    public Task(){}
    public void setName (String newName){
        name = newName;
    }
    public void setContent (String newContent){
        content = newContent;
    }
    public void setDate (String newDate){
        date = newDate;
    }
    public void setTime1 (String newTime1 ){
        time1  = newTime1 ;
    }
    public void setTime2 (String newTime2){
        time2 = newTime2;
    }
    public void setColor(String newColor){
        color = newColor;
    }

    public String getName(){
        return name;
    }
    public String getContent(){
        return content;
    }
    public String getDate(){ return date; }
    public Date getDateD() throws ParseException {
        return dateFormat.parse(date);
    }
    public Date getTime1D() throws ParseException{
        return dateFormat1.parse(time1);
    }
    public Date getTime2D() throws ParseException{
        return dateFormat1.parse(time2);
    }
    public String getTime1(){ return time1; }
    public String getTime2(){ return time2; }
    public String getColor(){ return color; }
    public int getHour1(){
        int hour = 0;
        Scanner scanner = new Scanner(getTime1());
        scanner.useDelimiter(":");
        if (scanner.hasNextInt()){
            hour = scanner.nextInt();
        }
        return hour;
    }
    public int getHour2(){
        int hour = 0;
        Scanner scanner = new Scanner(getTime2());
        scanner.useDelimiter(":");
        if (scanner.hasNextInt()){
            hour = scanner.nextInt();
        }
        return hour;
    }
    public int getMinute1(){
        int minute = 0;
        Scanner scanner = new Scanner(getTime1());
        scanner.useDelimiter(":");
        if (scanner.hasNextInt()){
            scanner.next ();
        }
        if (scanner.hasNextInt()){
            minute =  scanner.nextInt();
        }
        return minute;
    }
    public int getMinute2(){
        int minute = 0;
        Scanner scanner = new Scanner(getTime2());
        scanner.useDelimiter(":");
        if (scanner.hasNextInt()){
            scanner.next ();
        }
        if (scanner.hasNextInt()){
            minute =  scanner.nextInt();
        }
        return minute;
    }
}
