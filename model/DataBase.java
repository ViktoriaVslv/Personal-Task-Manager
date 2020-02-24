package model;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static io.WorkRW.xmlReader;
import static io.WorkRW.xmlWriter;

public class DataBase {
    private static ArrayList<Task> taskList = new  ArrayList<>();

    public DataBase(){}
    public void init() throws IOException, ParserConfigurationException, SAXException{
            getXML();
    }
    public void add(Task val){
        taskList.add(val);
    }
    public  int size(){
        return taskList.size();
    }
    public Task getTask(int num){
        return taskList.get(num) ;
    }
    public void remove(Task task){
        taskList.remove(task);
    }
    public void getXML()throws IOException, ParserConfigurationException, SAXException{
        File fileName = new File("C:/Users/aser/IdeaProjects/Personal Task Manager/out/artifacts/Personal_Task_Manager_jar/Info.xml");
        if(fileName.length() != 0) {
            taskList = xmlReader(fileName);
        }
    }
    public  void createXML()throws IOException, ParserConfigurationException, TransformerException {
        FileWriter fileName = new FileWriter("C:/Users/aser/IdeaProjects/Personal Task Manager/out/artifacts/Personal_Task_Manager_jar/Info.xml", false);
        xmlWriter(taskList, fileName);
    }
}
