package view;

import model.DataBase;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoTask extends JDialog {
    private JLabel name;
    private JLabel content;
    private JLabel date;
    private JLabel time1;
    private JLabel time2;
    private JLabel color;
    private JLabel labname;
    private JLabel labcontent;
    private JLabel labdate;
    private JLabel labtime1;
    private JLabel labtime2;
    private JLabel labcolor;
    private JButton change = new JButton("Изменить");
    private JButton remove = new JButton("Удалить");
    private JButton close = new JButton("Закрыть");
    private Task t;

    public InfoTask(Task task, DataBase dataBase){
        super();
        t = task;
        setTitle("Информация о деле");
        setSize(400, 200);
        setLocationRelativeTo(null);
        name = new JLabel("Дело: ");
        content = new JLabel("Содержание: ");
        date = new JLabel("Дата: ");
        time1 = new JLabel("Время начала: ");
        time2 = new JLabel("Время окончания: " );
        color = new JLabel("Цвет: ");
        labname = new JLabel(task.getName());
        labcontent = new JLabel( task.getContent());
        labdate = new JLabel( task.getDate());
        labtime1 = new JLabel(task.getTime1());
        labtime2 = new JLabel(task.getTime2());
        labcolor = new JLabel(task.getColor());
        Container container = getContentPane();
        JPanel p1= new JPanel(new GridLayout(6, 2, 5, 5));
        p1.add(name);
        p1.add(labname);
        p1.add(content);
        p1.add(labcontent);
        p1.add(date);
        p1.add(labdate);
        p1.add(time1);
        p1.add(labtime1);
        p1.add(time2);
        p1.add(labtime2);
        p1.add(color);
        p1.add(labcolor);
        JPanel p2= new JPanel(new GridLayout(1, 3, 5, 5));
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Work work = new Work(dataBase, task);
                setVisible(false);
                work.setVisible(true);
            }
        });
        p2.add(change);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dataBase.remove(task);
                Info info = new Info("Дело удалено! Не забудьте сохранить!");

                info.setVisible(true);
                setVisible(false);
            }
        });
        p2.add(remove);
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
