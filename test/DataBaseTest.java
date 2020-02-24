package test;

import model.DataBase;
import model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static io.WorkRW.xmlReader;
import static io.WorkRW.xmlWriter;
import static org.junit.Assert.*;

public class DataBaseTest {
    private Task task1;
    private Task task2;
    private Task task3;
    private DataBase dateBase1;
    private ArrayList<Task> taskList;
    @Before
    public void setUp() {
        dateBase1 = new DataBase();
        task1 = new Task("Новое дело", "", "чт, 04 июл. 2019", "14:26", "15:26", "Жёлтый");
        task2 = new Task("Дело2", "Что-то2", "ср, 03 июл. 2019", "11:00", "12:58", "Красный");
        task3 = new Task("Дело3", "Что-то3", "ср, 04 июл. 2019", "12:00", "13:58", "Зелёный");
        taskList = new  ArrayList<>();
        dateBase1.add(task1);
        dateBase1.add(task2);
        dateBase1.add(task3);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

    }

    @Test
    public void add() {
        dateBase1.add(task1);
        int s = dateBase1.size();
        int s2 = 4;
        Assert.assertEquals(s, s2);
    }

    @Test
    public void size() {
        int s = dateBase1.size();
        int s2 = 3;
        Assert.assertEquals(s, s2);
    }

    @Test
    public void getTask() {
        Task task = dateBase1.getTask(0);
        Task t2 = new Task("Новое дело", "", "чт, 04 июл. 2019", "14:26", "15:26", "Жёлтый");
        String name = task.getName();
        String name2 = t2.getName();
        Assert.assertEquals(name, name2);
        String content = task.getContent();
        String content2 = t2.getContent();
        Assert.assertEquals(content, content2);
        String date = task.getDate();
        String date2 = t2.getDate();
        Assert.assertEquals(date, date2);
        String time = task.getTime1();
        String time2 = t2.getTime1();
        Assert.assertEquals(time, time2);
        String ti = task.getTime2();
        String ti2 = t2.getTime2();
        Assert.assertEquals(ti, ti2);
        String color = task.getColor();
        String color2 = t2.getColor();
        Assert.assertEquals(color, color2);
    }

    @Test
    public void remove() {
        dateBase1.remove(task1);
        int s = dateBase1.size();
        int s2 = 2;
        Assert.assertEquals(s, s2);
    }

    @Test
    public void getXML() throws IOException, ParserConfigurationException, SAXException {

        File fileName2 = new File("InfoTest.xml");
        ArrayList<Task> taskList2 = new ArrayList<>();
        taskList2 = xmlReader(fileName2);
        Assert.assertEquals(taskList.size(), taskList2.size());
        for(int i =0; i< taskList.size(); i++){
            Task task = taskList.get(i);
            Task t2 = taskList2.get(i);
            String name = task.getName();
            String name2 = t2.getName();
            Assert.assertEquals(name, name2);
            String content = task.getContent();
            String content2 = t2.getContent();
            Assert.assertEquals(content, content2);
            String date = task.getDate();
            String date2 = t2.getDate();
            Assert.assertEquals(date, date2);
            String time = task.getTime1();
            String time2 = t2.getTime1();
            Assert.assertEquals(time, time2);
            String ti = task.getTime2();
            String ti2 = t2.getTime2();
            Assert.assertEquals(ti, ti2);
            String color = task.getColor();
            String color2 = t2.getColor();
            Assert.assertEquals(color, color2);
        }
    }

    @Test
    public void createXML()throws IOException, ParserConfigurationException, TransformerException, SAXException  {
        FileWriter fileName = new FileWriter("Info.xml", false);
        xmlWriter(taskList, fileName);
        File fileName1 = new File("Info.xml");
        taskList = xmlReader(fileName1);
        File fileName2 = new File("InfoTest.xml");
        ArrayList<Task> taskList2 = new ArrayList<>();
        taskList2 = xmlReader(fileName2);
        Assert.assertEquals(taskList.size(), taskList2.size());
        for(int i =0; i< taskList.size(); i++){
            Task task = taskList.get(i);
            Task t2 = taskList2.get(i);
            String name = task.getName();
            String name2 = t2.getName();
            Assert.assertEquals(name, name2);
            String content = task.getContent();
            String content2 = t2.getContent();
            Assert.assertEquals(content, content2);
            String date = task.getDate();
            String date2 = t2.getDate();
            Assert.assertEquals(date, date2);
            String time = task.getTime1();
            String time2 = t2.getTime1();
            Assert.assertEquals(time, time2);
            String ti = task.getTime2();
            String ti2 = t2.getTime2();
            Assert.assertEquals(ti, ti2);
            String color = task.getColor();
            String color2 = t2.getColor();
            Assert.assertEquals(color, color2);
        }
    }
}