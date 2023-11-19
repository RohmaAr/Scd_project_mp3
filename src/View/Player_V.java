/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.PlayBack_Volume__C;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Player_V {
    private JFrame frame;
    private JToolBar playBackToolBar;
    private JToolBar volumeToolBar;
    private JButton play_pause_Button;
    private JButton volumeUpButton;
    private JButton volumeDownButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton muteButton;
    private JProgressBar progressBar;
    private JLabel songLabel;
     private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
     public void setSong(String s)
     {
         songLabel.setText("Now Playing : "+s);
     }
     public void progressSetMax(int m)
     {
         progressBar.setMaximum(m);
     }
     public String getSong()
     {
         return songLabel.getText();
     }
     public void playListener(ActionListener e)
     {
         play_pause_Button.addActionListener(e);
     }
     public void muteListener(ActionListener e)
     {
         muteButton.addActionListener(e);
     }
     public void volumeUpListener(ActionListener e)
     {
         volumeUpButton.addActionListener(e);
     }
     public void volumeDownListener(ActionListener e)
     {
         volumeDownButton.addActionListener(e);
     }
     public void nextListener(ActionListener e)
     {
         nextButton.addActionListener(e);
     }
     public void previousListener(ActionListener e)
     {
         previousButton.addActionListener(e);
     }
     public void pausePlayIconChange(String pic)
     {
         ImageIcon originalIcon = new ImageIcon(pic);
        play_pause_Button.setIcon(resizeIcon(originalIcon,60,60));
        
     }
     public void progressControll(ChangeListener se){
         progressBar.addChangeListener(se);
 
     }
     public void progressAdd()
     {
         int n=progressBar.getValue();
         System.out.println(n+"");
         progressBar.setValue(++n);
     }
     //#C0BCDB
    public Player_V() {
        
        frame = new JFrame("MP3 Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playBackToolBar = new JToolBar();
        volumeToolBar=new JToolBar(JToolBar.VERTICAL);
        playBackToolBar.setBounds(0, 0, 500, 100);
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\play_dark.png");
        play_pause_Button = new JButton(resizeIcon(originalIcon,60,60));         
        originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\volume_up_dark.png");
        volumeUpButton = new JButton(resizeIcon(originalIcon,20,20));
        originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\volume_down_dark.png");
        volumeDownButton = new JButton(resizeIcon(originalIcon,20,20)); 
        originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\previous_dark.png");
        previousButton = new JButton(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\next_dark.png");
        nextButton = new JButton(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\mute_dark.png");
        muteButton = new JButton(resizeIcon(originalIcon,20,20)); 
        //muteButton.setBackground(new Color(192, 188, 219));
        //muteButton.setBackground(new Color(0,0,0));
        progressBar = new JProgressBar();
        songLabel = new JLabel("Now Playing: ");
        new PlayBack_Volume__C(this);
        JPanel top=new JPanel();
        JPanel right=new JPanel();
        top.add(songLabel);
        JPanel bottom=new JPanel();
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));
        playBackToolBar.add(previousButton);
        playBackToolBar.addSeparator(new Dimension(40,0));
        playBackToolBar.add(play_pause_Button);
        playBackToolBar.addSeparator(new Dimension(40,0));
        playBackToolBar.add(nextButton);
        bottom.add(progressBar);
        playBackToolBar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        volumeToolBar.add(volumeUpButton);
        volumeToolBar.add(volumeDownButton);
        volumeToolBar.add(muteButton);
        bottom.add(playBackToolBar);
        right.add(volumeToolBar);
        frame.add(right,BorderLayout.EAST);
        frame.add(top,BorderLayout.NORTH);
        frame.add(bottom,BorderLayout.SOUTH);
        frame.setResizable(false);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

}
