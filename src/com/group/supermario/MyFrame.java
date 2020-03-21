package com.group.supermario;


import javax.swing.*;
import java.awt.*;


public class MyFrame extends JFrame {
    public MyFrame() {
        MyPanel myPanel = new MyPanel();

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.addKeyListener(myPanel);
        this.setLocation((width - 900) / 2, (height - 600) / 2);
        this.setResizable(false);//不能改变窗口大小
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("低配版超级玛丽");
        this.add(myPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
    }
}
