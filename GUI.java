import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class GUI implements ActionListener {

    private JLabel label;
    private JFrame frame;
    private JButton button;
    private JPanel panel;
    int x = 0, y = 0;

    public GUI() {
        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Hangman");

        panel = new JPanel();
        panel.setBackground(Color.CYAN);

        button = new JButton("Bruh pls");
        button.addActionListener(this);
        label = new JLabel(new ImageIcon("steve.png"));
        label.setBounds(x, y, 300, 300);
        panel.add(button);
        panel.add(label);
        frame.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}
