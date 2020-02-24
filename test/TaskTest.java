package test;

import model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TaskTest {
    private Task task;
   // private SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm");
    //private SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
    @Before
    public void setUp() {
        task = new Task("Дело", "Что-то", "ср, 3 июл. 2019", "10:00", "11:58", "Синий");
    }

    @Test
    public void setName() {
        //Task task = new Task("Дело", "Что-то", "ср, 3 июн. 2019", "10:00", "11:58", "Синий");
        task.setName("Новое");
        String name = task.getName();
        String name2 = "Новое";
        Assert.assertEquals(name, name2);
    }

    @Test
    public void setContent() {
        task.setContent("Поменяли");
        String content = task.getContent();
        String content2 = "Поменяли";
        Assert.assertEquals(content, content2);
    }

    @Test
    public void setDate() {
        task.setDate("ср, 4 июл. 2019");
        String date = task.getDate();
        String date2 = "ср, 4 июл. 2019";
        Assert.assertEquals(date, date2);
    }

    @Test
    public void setTime1() {
        task.setTime1("12:00");
        String time = task.getTime1();
        String time2 = "12:00";
        Assert.assertEquals(time, time2);
    }

    @Test
    public void setTime2() {
        task.setTime2("12:00");
        String time = task.getTime2();
        String time2 = "12:00";
        Assert.assertEquals(time, time2);
    }

    @Test
    public void setColor() {
        task.setColor("Красный");
        String color = task.getColor();
        String color2 = "Красный";
        Assert.assertEquals(color, color2);
    }

    @Test
    public void getName() {
        //Task task = new Task("Дело", "Что-то", "ср, 3 июн. 2019", "10:00", "11:58", "Синий");
        String name = task.getName();
        String name2 = "Дело";
        Assert.assertEquals(name, name2);
    }

    @Test
    public void getContent() {
        String content = task.getContent();
        String content2 = "Что-то";
        Assert.assertEquals(content, content2);
    }

    @Test
    public void getDate() {
        String date = task.getDate();
        String date2 = "ср, 3 июл. 2019";
        Assert.assertEquals(date, date2);
    }

    @Test
    public void getDateD() throws ParseException {
        Date d = task.getDateD();
        Date d2 = new Date(119, 6,3);
        Assert.assertEquals(d, d2);

    }

    @Test
    public void getTime1D()throws ParseException  {
        Date d = task.getTime1D();
        Date d2 = new Date(70, 0,1, 10,00);
        Assert.assertEquals(d, d2);
    }

    @Test
    public void getTime2D()throws ParseException  {
        Date d = task.getTime2D();
        Date d2 = new Date(70, 0,1, 11,58);
        Assert.assertEquals(d, d2);
    }

    @Test
    public void getTime1() {
        String time = task.getTime1();
        String time2 = "10:00";
        Assert.assertEquals(time, time2);
    }

    @Test
    public void getTime2() {
        String time = task.getTime2();
        String time2 = "11:58";
        Assert.assertEquals(time, time2);
    }

    @Test
    public void getColor() {
        String color = task.getColor();
        String color2 = "Синий";
        Assert.assertEquals(color, color2);
    }

    @Test
    public void getHour1() {
        int h = task.getHour1();
        int h2 = 10;
        Assert.assertEquals(h, h2);
    }

    @Test
    public void getHour2() {
        int h = task.getHour2();
        int h2 = 11;
        Assert.assertEquals(h, h2);
    }

    @Test
    public void getMinute1() {
        int m = task.getMinute1();
        int m2 = 0;
        Assert.assertEquals(m, m2);
    }

    @Test
    public void getMinute2() {
        int m = task.getMinute2();
        int m2 = 58;
        Assert.assertEquals(m, m2);
    }
}