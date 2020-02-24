package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.toedter.calendar.*;
import model.DataBase;
import model.Task;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.*;


public class Work extends JDialog {
    private JTextField name;
    private JLabel labName;
    private JTextArea content;
    private JLabel labContent;
    private JLabel labData;
    private JSpinner time1;
    private JSpinner time2;
    private JLabel labFirstTime;
    private JLabel labSecondTime;
    private JButton save;
    private JButton cancel;
    private JDateChooser  cal;
    private JComboBox<String> color;
    private JLabel labColor;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");

    public Work(DataBase dataBase) {
        super();
        setTitle("Добавить дело");
        setSize(400, 400);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new GridLayout(7,2,5,5));
        name = new JTextField("Новое дело", 100);
        labName = new JLabel("Название");
        content= new JTextArea(10, 100);
        labContent = new JLabel("Содержание");
        labData = new JLabel("Дата");
        time1 = new JSpinner( new SpinnerDateModel());
        time2 = new JSpinner( new SpinnerDateModel());
        labFirstTime = new JLabel("Время начала");
        labSecondTime = new JLabel("Время окончания");
        save = new JButton("Добавить");
        cancel = new JButton("Отменить");
        cal = new JDateChooser(new Date());
        color= new JComboBox();
        labColor = new JLabel("Цвет");
        c.add(labName);
        c.add(name);
        c.add(labContent);
        c.add(content);
        c.add(labData);
        c.add(cal);
        c.add(labFirstTime);
        time1.setEditor(new JSpinner.DateEditor(time1, "HH:mm"));
        time2.setEditor(new JSpinner.DateEditor(time2, "HH:mm"));
        c.add(time1);
        c.add(labSecondTime);
        c.add(time2);
        color.addItem("Красный");
        color.addItem("Жёлтый");
        color.addItem("Зелёный");
        color.addItem("Синий");
        c.add(labColor);
        c.add(color);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0){
                Task task=new Task(getName(), getContent(), getDate(), getTime1(), getTime2(), getColor());
                boolean event =false;
                try{
                    if(dateFormat.parse(task.getDate()).before(new Date())&&(!task.getDate().equals(dateFormat.format(new Date())))){
                        Info information = new Info("Дата из прошлого");
                        information.setVisible(true);
                        event =true;
                    }
                }
                catch (ParseException e){Info ex = new Info("Неверный формат даты");}
                if((task.getHour2()<task.getHour1())||((task.getHour2()==task.getHour1())&&(task.getMinute2()<task.getMinute1()))){
                    Info information = new Info("Неверно введено время");
                    information.setVisible(true);
                    event =true;
                }
                if(!event) {
                    Info information2;
                    information2 = new Info("Дело добавлено! Не забудьте сохранить!");
                    dataBase.add(task);
                    information2.setVisible(true);
                    setVisible(false);
                }
            }
        });

        c.add(save);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
        c.add(cancel);
        setVisible(false);
    }

    public Work(DataBase dataBase, Task task){
        super();
        try {
            setTitle("Изменить дело");
            setSize(400, 400);
            setLocationRelativeTo(null);
            Container c = getContentPane();
            c.setLayout(new GridLayout(7, 2, 5, 5));
            name = new JTextField(task.getName(), 100);
            labName = new JLabel("Название");
            content = new JTextArea(task.getContent(), 10, 100);
            labContent = new JLabel("Содержание");
            labData = new JLabel("Дата");
            SpinnerDateModel sdm = new SpinnerDateModel(task.getTime1D(), null, null, Calendar.MINUTE);
            time1 = new JSpinner(sdm);
            sdm = new SpinnerDateModel(task.getTime2D(), null, null, Calendar.MINUTE);
            time2 = new JSpinner(sdm);
            labFirstTime = new JLabel("Время начала");
            labSecondTime = new JLabel("Время окончания");
            save = new JButton("Изменить");
            cancel = new JButton("Отменить");
            cal = new JDateChooser(task.getDateD());

            labColor = new JLabel("Цвет");
            color= new JComboBox();
            color.addItem(task.getColor());
            switch (task.getColor()) {
                case "Красный": {
                    color.addItem("Жёлтый");
                    color.addItem("Зелёный");
                    color.addItem("Синий");
                }
                break;
                case "Жёлтый": {
                    color.addItem("Красный");
                    color.addItem("Зелёный");
                    color.addItem("Синий");
                }
                break;
                case "Зелёный": {
                    color.addItem("Красный");
                    color.addItem("Жёлтый");
                    color.addItem("Синий");
                }
                break;
                case "Синий": {
                    color.addItem("Красный");
                    color.addItem("Жёлтый");
                    color.addItem("Зелёный");
                }
                break;
            }

            c.add(labName);
            c.add(name);
            c.add(labContent);
            c.add(content);
            c.add(labData);
            c.add(cal);
            c.add(labFirstTime);
            time1.setEditor(new JSpinner.DateEditor(time1, "HH:mm"));
            time2.setEditor(new JSpinner.DateEditor(time2, "HH:mm"));
            c.add(time1);
            c.add(labSecondTime);
            c.add(time2);
            c.add(labColor);
            c.add(color);
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){

                    boolean event =false;
                    try{
                        if(dateFormat.parse(getDate()).before(new Date())&&(!getDate().equals(dateFormat.format(new Date())))){
                            Info information = new Info("Дата из прошлого");
                            information.setVisible(true);
                            event =true;
                        }
                    }
                    catch (ParseException e){Info ex = new Info("Неверный формат даты");}
                    if((getHour2()<getHour1())||((getHour2()==getHour1())&&(getMinute2()<getMinute1()))){
                        Info information = new Info("Неверно введено время");
                        information.setVisible(true);
                        event =true;
                    }
                    if(!event) {
                        Info information2;
                        information2 = new Info("Дело изменено! Не забудьте сохранить!");
                        task.setName(getName());
                        task.setContent(getContent());
                        task.setDate(getDate());
                        task.setTime1(getTime1());
                        task.setTime2(getTime2());
                        task.setColor(getColor());
                        information2.setVisible(true);
                        setVisible(false);
                    }
                }
            });
            c.add(save);
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    setVisible(false);
                }
            });
            c.add(cancel);
            setVisible(false);
        }
        catch (ParseException e){Info ex = new Info("Неверный формат даты");}
    }

    public String getName(){
        return name.getText();
    }
    public String getContent(){
        return content.getText();
    }
    public String getColor(){
        return color.getSelectedItem().toString();
    }
    public String getDate(){
        Calendar c = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
        c.setTime(cal.getDate());
        String str=dateFormat.format(c.getTime());

        return str;
    }
    public String getTime1(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(time1.getValue());
        return date;
    }

    public String getTime2(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(time2.getValue());
        return date;
    }
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