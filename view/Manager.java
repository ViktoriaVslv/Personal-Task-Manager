package view;

import model.DataBase;
import model.Task;
import model.TaskSquare;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class Manager extends JFrame {
    private JButton work = new JButton("Добавить дело");
    private JButton save = new JButton("Сохранить");
    private JRadioButton days = new JRadioButton("3 дня");
    private JRadioButton week = new JRadioButton("Неделя");
    private JButton today = new JButton("Сегодня");
    private JButton next = new JButton("Вперед >");
    private JButton back = new JButton("< Назад");
    private JButton nextWeek = new JButton(">>");
    private JButton backWeek = new JButton("<<");
    private JScrollPane scrollTab = new JScrollPane();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
    private Calendar calendar = new GregorianCalendar();
    private ArrayList<String> dates;
    private ArrayList<TaskSquare> clicks;
    private DataBase dataBase=new DataBase();
    private Timer timer;


    public Manager() {
        super("Personal Task Manager");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
        dataBase.init();}
        catch (Exception e){Info ex = new Info("Проблемы с файлом");}
        int size =3;
        if(week.isSelected()) { size=7; }
        calendar.add(Calendar.DAY_OF_MONTH, -size/2);
        datesGet();
        Container container = getContentPane();
        clicks = new ArrayList<>();
        JPanel p1= new JPanel(new GridLayout(1, 5, 10, 10));
        ButtonGroup group = new ButtonGroup();
        group.add(days);
        group.add(week);


        days.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(dates.size() == 7){
                    try {
                        calendar.setTime(dateFormat.parse(dates.get(2)));
                        datesGet();
                        repaint();
                    }
                    catch (ParseException e){Info ex = new Info("Неверный формат даты");}
                }
            }
        });

        week.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(dates.size() == 3){
                    try {
                        calendar.setTime(dateFormat.parse(dates.get(0)));
                        calendar.add(Calendar.DAY_OF_MONTH, -2);
                        datesGet();
                        repaint();
                    }
                    catch (ParseException e){Info ex = new Info("Неверный формат даты");}
                }
            }
        });


        class PanelTab extends JPanel {
            public PanelTab(){
                super();
                // this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setPreferredSize(new Dimension(1300,745));
            }
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawLine(100, 0, 100, 745);
                clicks.clear();
                Calendar c = new GregorianCalendar();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minutes = c.get(Calendar.MINUTE);
                g.setColor(Color.CYAN);
                int y = 30;
                g.fillRect(0, 26+y*hour, 50, 29);
                if(minutes < 30){
                    g.fillRect(51, 26+y*hour, 49, 14);
                }
                else {
                    g.fillRect(51, 41+y*hour, 49, 14);
                }
                g.setColor(Color.BLACK);
                if(days.isSelected()){
                    int x=-148;
                    for(int i=0; i<3; i++){
                        x+=416;
                        try{
                            if(dateFormat.parse(dates.get(i)).before(new Date())){
                                g.setColor( new Color(23, 97, 99, 58));
                                g.fillRect(x-167, 0, 416, 745);
                                g.setColor(Color.BLACK);
                            }
                        }
                        catch (ParseException e){Info ex = new Info("Неверный формат даты");}
                        if(dates.get(i).equals(dateFormat.format(new Date()))){
                            g.setColor(new Color(192, 252, 255));
                            g.fillRect(x-167, 0, 417, 745);
                            g.setColor(Color.CYAN);
                            if(minutes < 30){
                                g.fillRect(x-167, 26+y*hour, 417, 14);
                            }
                            else {
                                g.fillRect(x-167, 41+y*hour, 416, 14);
                            }
                            g.setColor(Color.black);
                        }
                        g.drawString(dates.get(i), x, 20);
                    }
                    g.drawLine(517, 0, 517, 745);
                    g.drawLine(934, 0, 934, 745);
                }
                else {
                    int x=100;
                    int x1=-30;
                    for(int i=0; i<7; i++){
                        x+=179;
                        x1+=179;
                        try{
                            if(dateFormat.parse(dates.get(i)).before(new Date())){
                                g.setColor( new Color(23, 97, 99, 58));
                                g.fillRect(x-178, 0, 179, 745);
                                g.setColor(Color.BLACK);
                            }
                        }
                        catch (ParseException e){Info ex = new Info("Неверный формат даты");}
                        if(dates.get(i).equals(dateFormat.format(new Date()))){
                            g.setColor( new Color(192, 252, 255));
                            g.fillRect(x-178, 0, 179, 745);
                            g.setColor(Color.CYAN);
                            if(minutes < 30){
                                g.fillRect(x-178, 26+y*hour, 179, 14);
                            }
                            else {
                                g.fillRect(x-178, 41+y*hour, 179, 14);
                            }
                            g.setColor(Color.BLACK);
                        }
                        g.drawLine(x, 0, x, 745);
                        g.drawString(dates.get(i), x1, 20);
                    }
                }
                g.drawLine(50, 25, 50, 745);
                g.drawLine(0, 25, 1350, 25);
                y = 25;
                for (int i=0; i<24; i++){
                    y+=30;
                    g.drawLine(0, y, 100, y);
                    g.drawString(TOOL_TIP_TEXT_KEY.valueOf(i), 20, y-10);
                    g.drawLine(50, y-15, 100, y-15);
                    g.setColor( new Color(0, 0, 0, 103));
                    g.drawLine(100, y-15, 1350, y-15);
                    g.drawLine(100, y, 1350, y);
                    g.setColor(Color.BLACK);
                    g.drawString(":00", 70, y-17);
                    g.drawString(":30", 70, y-2);
                }

                for(int j=0; j<dataBase.size(); j++){
                    TaskSquare sq =new TaskSquare(days.isSelected(),dataBase.getTask(j),dates);
                    if((sq.widthGet()>0)&& (sq.heightGet()>0)) {
                        clicks.add(sq);
                        switch (dataBase.getTask(j).getColor()) {
                            case "Красный": {
                                g.setColor(Color.RED);
                            }
                            break;
                            case "Жёлтый": {
                                g.setColor(Color.YELLOW);
                            }
                            break;
                            case "Зелёный": {
                                g.setColor(Color.GREEN);
                            }
                            break;
                            case "Синий": {
                                g.setColor(Color.BLUE);
                            }
                            break;
                        }
                        g.fillRect(sq.xGet(), sq.yGet(), sq.widthGet(), sq.heightGet());
                        g.setColor(Color.BLACK);
                        g.drawString(dataBase.getTask(j).getName(), sq.xGet() + 5, sq.yGet() + 12);

                    }
                }

            }
        }

        PanelTab panel = new PanelTab();
        panel.setPreferredSize(new Dimension(1300,745));


        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                for(int i=0; i<clicks.size(); i++) {
                    if (x >=clicks.get(i).xGet() && x <clicks.get(i).xGet()+clicks.get(i).widthGet() && y >=clicks.get(i).yGet() && y <=clicks.get(i).yGet()+clicks.get(i).heightGet()) {
                        InfoTask info = new InfoTask(clicks.get(i).taskGet(), dataBase);
                        info.setVisible(true);
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        class TimePrinter implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                getTimer();
            }
        }
        ActionListener listener = new TimePrinter();
        timer = new Timer(60000, listener);
        timer.start();
        getContentPane().add(scrollTab);
        scrollTab.setViewportView(panel);
        scrollTab.setPreferredSize(new Dimension(1000,600));

        work.addActionListener(new ActionListener() {
            Work w= new Work(dataBase);
            @Override
            public void actionPerformed(ActionEvent arg0) {
                w.setVisible(true);
            }
        });
        p1.add(work);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    dataBase.createXML();
                    repaint();
                }
                catch (Exception e){Info ex = new Info("Проблемы с файлом");}
            }
        });
        p1.add(save);

        today.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Calendar c = new GregorianCalendar();
                datesGet();
                if (days.isSelected()){
                    c.add(c.DAY_OF_MONTH, -1);
                }
                else{
                    c.add(c.DAY_OF_MONTH, -3);
                }
                for (int i =0; i <dates.size(); i++){
                    dates.set(i, dateFormat.format(c.getTime()));
                    c.add(c.DAY_OF_MONTH, 1);
                }
                repaint();
            }
        });
        p1.add(today);
        p1.add(days);
        days.setSelected(true);
        p1.add(week);


        container.add(p1, BorderLayout.NORTH);
        JPanel p2 = new JPanel(new GridLayout(1, 4, 10, 10));
        backWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    calendar.setTime(dateFormat.parse(dates.get(0)));
                    calendar.add(Calendar.DAY_OF_MONTH, -dates.size());
                    datesGet();
                    repaint();
                }
                catch (ParseException  e){Info ex = new Info("Проблемы с форматом даты");}
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    calendar.setTime(dateFormat.parse(dates.get(0)));
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                    datesGet();
                    repaint();
                }
                catch (ParseException  e){Info ex = new Info("Проблемы с форматом даты");}
            }
        });

        nextWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    calendar.setTime(dateFormat.parse(dates.get(dates.size()-1)));
                    calendar.add(Calendar.DAY_OF_MONTH,1);
                    datesGet();
                    repaint();
                }
                catch (ParseException  e){Info ex = new Info("Проблемы с форматом даты");}
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    calendar.setTime(dateFormat.parse(dates.get(1)));
                    datesGet();
                    repaint();
                }
                catch (ParseException  e){Info ex = new Info("Проблемы с форматом даты");}
            }
        });
        p2.add(backWeek);
        p2.add(back);
        p2.add(next);
        p2.add(nextWeek);
        container.add(p2, BorderLayout.SOUTH);
        container.add(scrollTab, BorderLayout.CENTER);
        setVisible(true);
    }

    public void datesGet() {
        int size =3;
        if(week.isSelected()) {
            size=7;
        }
        dates = new ArrayList<String>(size);
        for (int i = 0; i < size; i++) {
            dates.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
    public  void getTimer(){
        calendar.setTime(new Date());
        for(int j=0; j<dataBase.size(); j++) {
            if (dataBase.getTask(j).getDate().equals(dateFormat.format(new Date()))) {
                if (dataBase.getTask(j).getHour1() == calendar.get(Calendar.HOUR_OF_DAY)) {
                    if (dataBase.getTask(j).getMinute1() == calendar.get(Calendar.MINUTE)) {
                        Reminder re = new Reminder(dataBase.getTask(j), dataBase);
                        re.setVisible(true);
                    }
                }
            }
        }
    }

}