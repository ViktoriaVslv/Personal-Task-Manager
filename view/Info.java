package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Info extends JDialog {
    private JLabel infa;
    private JButton ok = new JButton("OK");
    public Info(String str){
        setTitle("Информация");
        setSize(280, 100);
        setLocationRelativeTo(null);
        infa = new JLabel(str);
        Container c = getContentPane();
        c.add(infa, BorderLayout.CENTER);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
        c.add(ok, BorderLayout.SOUTH);
        setVisible(false);
    }
}
