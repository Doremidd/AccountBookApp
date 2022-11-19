package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sketch extends JFrame implements ActionListener {
    private JButton button;

    public Sketch() {
        super("hhh");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);
        button = new JButton("Add");
        button.addActionListener(this);
        add(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(1);
    }

    public static void main(String[] args) {
        new Sketch();
    }
}
