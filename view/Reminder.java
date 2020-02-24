package view;

import model.DataBase;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Reminder extends JDialog {
    private JLabel info;
    private JLabel name;
    private JLabel info2;
    private JButton close;
    private JButton see;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
    private Calendar calendar = new GregorianCalendar();



    public Reminder(Task task, DataBase dataBase){
        super();
        setTitle("Напоминание");
        setSize(400, 200);
        setLocationRelativeTo(null);
        info = new JLabel("Дело: ");
        info2 = new JLabel("наступило");
        name = new JLabel(task.getName());
        close = new JButton("Закрыть");
        see = new JButton("Смотреть");
        Container container = getContentPane();
        JPanel p1= new JPanel(new GridLayout(1, 3, 5, 5));
        p1.add(info);
        p1.add(name);
        p1.add(info2);
        JPanel p2= new JPanel(new GridLayout(1, 2, 5, 5));
        see.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                InfoTask infoTask = new InfoTask(task,dataBase);
                infoTask.setVisible(true);
                setVisible(false);
            }
        });
        p2.add(see);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
        p2.add(close);
        container.add(p1, BorderLayout.CENTER);
        container.add(p2, BorderLayout.SOUTH);

        setVisible(false);
    }
}
