package test;

import model.Task;
import model.TaskSquare;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskSquareTest {
    private TaskSquare sq;
    private Task task;
    private ArrayList<String> dates;
    @Before
    public void setUp() {
        task = new Task("Дело", "Что-то", "ср, 3 июл. 2019", "10:00", "11:58", "Синий");
        dates  = new  ArrayList<>();
        dates.add("вт, 2 июл. 2019");
        dates.add("ср, 3 июл. 2019");
        dates.add("чт, 4 июл. 2019");
        sq =new TaskSquare(true, task, dates);
    }

    @Test
    public void height() {
        int height = sq.height();
        int height2 = 60;
        Assert.assertEquals(height, height2);
    }

    @Test
    public void xGet() {
        int x = sq.xGet();
        int x2 = 518;
        Assert.assertEquals(x, x2);
    }

    @Test
    public void yGet() {
        int y = sq.yGet();
        int y2 = 326;
        Assert.assertEquals(y, y2);
    }

    @Test
    public void widthGet() {
        int width = sq.widthGet();
        int width2 = 416;
        Assert.assertEquals(width, width2);
    }

    @Test
    public void heightGet() {
        int height = sq.heightGet();
        int height2 = 59;
        Assert.assertEquals(height, height2);
    }

    @Test
    public void taskGet() {
        Task t = sq.taskGet();
        Task t2 = new Task("Дело", "Что-то", "ср, 3 июл. 2019", "10:00", "11:58", "Синий");
        String name = t.getName();
        String name2 = t2.getName();
        Assert.assertEquals(name, name2);
        String content = t.getContent();
        String content2 = t2.getContent();
        Assert.assertEquals(content, content2);
        String date = t.getDate();
        String date2 = t2.getDate();
        Assert.assertEquals(date, date2);
        String time = t.getTime1();
        String time2 = t2.getTime1();
        Assert.assertEquals(time, time2);
        String ti = t.getTime2();
        String ti2 = t2.getTime2();
        Assert.assertEquals(ti, ti2);
        String color = t.getColor();
        String color2 = t2.getColor();
        Assert.assertEquals(color, color2);
    }
}
