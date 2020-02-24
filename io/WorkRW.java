package io;

import model.Task;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import view.Work;

import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkRW  {

    public static ArrayList<Task> xmlReader( File fileName) throws IOException, ParserConfigurationException, SAXException {
        ArrayList<Task> tasks = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(fileName);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("task");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                Element element = (Element) node;
                Task task = new Task();

                NodeList nodeCh = element.getChildNodes();
                for(int j=0; j<nodeCh.getLength(); j++){
                    if(nodeCh.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element elementCh = (Element) nodeCh.item(j);
                        switch (elementCh.getNodeName()){
                            case "work":{
                                task.setName(elementCh.getTextContent());
                            }break;
                            case "content":{
                                task.setContent(elementCh.getTextContent());
                            }break;
                            case "date":{
                                task.setDate(elementCh.getAttribute("date"));
                            }break;
                            case "firstTime":{
                                task.setTime1(elementCh.getAttribute("time"));
                            }break;
                            case "secondTime":{
                                task.setTime2(elementCh.getAttribute("time2"));
                            }break;
                            case "color":{
                                task.setColor(elementCh.getAttribute("color"));
                            }break;

                        }
                    }
                }
                tasks.add(task);
            }
        }
        return(tasks);
    }
    public static void xmlWriter(List<Task> tasks,  FileWriter fileName)throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element taskManager = doc.createElement("taskManager");
        doc.appendChild(taskManager);

        for (Task task : tasks) {
            Element t = doc.createElement("task");
            Element work = doc.createElement("work");
            Text tWork = doc.createTextNode(task.getName());
            Element date = doc.createElement("date");
            date.setAttribute("date", task.getDate());
            Element firstTime = doc.createElement("firstTime");
            Element secondTime = doc.createElement("secondTime");
            Element color = doc.createElement("color");
            Element content = doc.createElement("content");
            Text tContent = doc.createTextNode(task.getContent());
            firstTime.setAttribute("time", task.getTime1());
            secondTime.setAttribute("time2", task.getTime2());
            color.setAttribute("color", task.getColor());
            taskManager.appendChild(t);
            t.appendChild(work);
            work.appendChild(tWork);
            t.appendChild(content);
            content.appendChild(tContent);
            t.appendChild(date);
            t.appendChild(firstTime);
            t.appendChild(secondTime);
            t.appendChild(color);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(fileName));
    }
}