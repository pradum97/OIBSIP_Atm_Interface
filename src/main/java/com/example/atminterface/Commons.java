package com.example.atminterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Commons {
    public Component Frame() {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode("#ADD8E6"));
        //-----------------------------------------------
        return frame;
    }
}