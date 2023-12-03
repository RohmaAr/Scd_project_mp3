/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author Dell
 */
import Controller.AudioRecorder_C;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.LineUnavailableException;

public class AudioRecorder_V extends JPanel {
    private JButton startButton,backButton, stopButton, playButton;
    private JLabel statusLabel;
    private boolean mode;
    private Color purple=new Color(192, 188, 219);
    private AudioRecorder_C controller;

    public AudioRecorder_V(JButton b,boolean mode) {
        try {
            controller = new AudioRecorder_C(this);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        backButton=b;
        this.mode=mode;
        setLayout(new FlowLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10)); // 4 rows, 1 column, with gaps
        
        startButton = new JButton("Start Recording");
        stopButton = new JButton("Stop Recording");
        playButton = new JButton("Play Recorded Audio");
        statusLabel = new JLabel("Status: Idle");

        startButton.addActionListener(new StartButtonListener());
        stopButton.addActionListener(new StopButtonListener());
        playButton.addActionListener(new PlayButtonListener());

        // Adding empty borders for space
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        stopButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        playButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(backButton);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(playButton);
        buttonPanel.add(statusLabel);
        if(mode)
        {
            this.playButton.setBackground(purple);
            this.playButton.setForeground(Color.darkGray);
            buttonPanel.setBackground(purple);
            this.backButton.setBackground(purple);
            backButton.setForeground(Color.darkGray);
            this.startButton.setBackground(purple);
            startButton.setForeground(Color.darkGray);
            this.stopButton.setBackground(purple);
            this.stopButton.setForeground(Color.darkGray);
            this.statusLabel.setBackground(purple);
            this.statusLabel.setForeground(Color.darkGray);
            
            this.setBackground(purple);
        }
        else{
            this.statusLabel.setBackground(Color.darkGray);
            this.statusLabel.setForeground(purple);
            
            this.playButton.setBackground(Color.darkGray);
            this.playButton.setForeground(purple);
            buttonPanel.setBackground(Color.darkGray);
            this.backButton.setBackground(Color.darkGray);
            backButton.setForeground(purple);
            this.startButton.setBackground(Color.darkGray);
            startButton.setForeground(purple);
            this.stopButton.setBackground(Color.darkGray);
            this.stopButton.setForeground(purple);
            this.setBackground(Color.darkGray);
        
        }
        add(buttonPanel);

    }
    public void setResult(String text)
    {
        
        JLabel label=new JLabel(text);
        JPanel answer=new JPanel(new FlowLayout());
        if(mode)
        {
            label.setBackground(purple);
            label.setForeground(Color.darkGray);
            answer.setBackground(purple);
        }
        else
        {
            label.setBackground(Color.darkGray);
            answer.setBackground(Color.darkGray);
            label.setForeground(purple);
        }
        answer.add(label);
        this.add(answer);
    }
    public void setStatusLabelText(String text) {
        statusLabel.setText(text);
    }

    class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.startRecording();
        }
    }

    class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.stopRecording();
        }
    }

    class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.playRecordedAudio();
        }
    }

}
