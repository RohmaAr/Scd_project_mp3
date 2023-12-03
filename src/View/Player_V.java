/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.Player_C;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Player_V {
    private JFrame frame;
     Color purple=new Color(192, 188, 219);
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
    private JButton like;
    private JToggleButton loop;
    private JButton lyricsButton;
    private JPanel top=new JPanel();
    private JPanel right=new JPanel();
    private JPanel bottom=new JPanel();
    private JPanel center=new JPanel();
     private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
     public void setlightMode(boolean paused)
     {
         this.frame.setBackground(purple);
         this.like.setBackground(purple);
         this.loop.setBackground(purple);
         this.bottom.setBackground(purple);
         this.center.setBackground(purple);
          this.playBackToolBar.setBackground(purple);
         this.volumeToolBar.setBackground(purple);
         this.lyricsButton.setBackground(purple);
         this.muteButton.setBackground(purple);
         this.nextButton.setBackground(purple);
         this.play_pause_Button.setBackground(purple);
         this.previousButton.setBackground(purple);
         this.progressBar.setBackground(purple);
         this.progressBar.setForeground(Color.darkGray);
         this.right.setBackground(purple);
         this.songLabel.setBackground(purple);
         this.songLabel.setForeground(Color.darkGray);
         this.top.setBackground(purple);
         this.volumeDownButton.setBackground(purple);
         this.volumeUpButton.setBackground(purple);
         ImageIcon originalIcon = new ImageIcon("Icons\\lyrics_dark.png");
        lyricsButton.setIcon(resizeIcon(originalIcon,40,40)); 
        if(paused)        
            originalIcon = new ImageIcon("Icons\\play_dark.png");
        else
            originalIcon = new ImageIcon("Icons\\pause_dark.png");
        play_pause_Button.setIcon(resizeIcon(originalIcon,60,60));         
        originalIcon = new ImageIcon("Icons\\volume_up_dark.png");
        volumeUpButton.setIcon(resizeIcon(originalIcon,40,40));
        originalIcon = new ImageIcon("Icons\\volume_down_dark.png");
        volumeDownButton.setIcon(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\previous_dark.png");
        previousButton.setIcon(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\next_dark.png");
        nextButton.setIcon(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\mute_dark.png");
        muteButton.setIcon(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\loop_dark.png");
        loop.setIcon(resizeIcon(originalIcon,30,30)); 
        
     }
     public void setDarkMode(boolean paused)
     {
         this.frame.setBackground(Color.darkGray);
         this.like.setBackground(Color.darkGray);
         this.loop.setBackground(Color.darkGray);
          this.bottom.setBackground(Color.darkGray);
           this.center.setBackground(Color.darkGray);
          this.playBackToolBar.setBackground(Color.darkGray);
         this.volumeToolBar.setBackground(Color.darkGray);
         this.lyricsButton.setBackground(Color.darkGray);
         this.muteButton.setBackground(Color.darkGray);
         this.nextButton.setBackground(Color.darkGray);
         this.songLabel.setForeground(purple);
         this.play_pause_Button.setBackground(Color.darkGray);
         this.previousButton.setBackground(Color.darkGray);
         this.progressBar.setBackground(Color.darkGray);
         this.progressBar.setForeground(purple);
         this.right.setBackground(Color.darkGray);
         this.songLabel.setBackground(Color.darkGray);
         this.top.setBackground(Color.darkGray);
         this.volumeDownButton.setBackground(Color.darkGray);
         this.volumeUpButton.setBackground(Color.darkGray);
         ImageIcon originalIcon = new ImageIcon("Icons\\lyrics_light.png");
        lyricsButton.setIcon(resizeIcon(originalIcon,40,40));         
        if(paused)        
            originalIcon = new ImageIcon("Icons\\play_light.png");
        else
            originalIcon = new ImageIcon("Icons\\pause_light.png");
        play_pause_Button.setIcon(resizeIcon(originalIcon,60,60));
        originalIcon = new ImageIcon("Icons\\volume_up_light.png");
        volumeUpButton.setIcon(resizeIcon(originalIcon,40,40));
        originalIcon = new ImageIcon("Icons\\volume_down_light.png");
        volumeDownButton.setIcon(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\previous_light.png");
        previousButton.setIcon(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\next_light.png");
        nextButton.setIcon(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\mute_light.png");
        muteButton.setIcon(resizeIcon(originalIcon,40,40)); 
       originalIcon = new ImageIcon("Icons\\loop_light.png");
        loop.setIcon(resizeIcon(originalIcon,30,30)); 
        
     }
     public void setSong(String s)
     {
         songLabel.setText(s);
     }
     public void progressSetMax(int m)
     {
         progressBar.setMaximum(m);
     }
     public void progressReset()
     {
         progressBar.setValue(0);
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
     public void lyricsListener(ActionListener e)
     {
         lyricsButton.addActionListener(e);
     }
     public void progressControll(ChangeListener se){
         progressBar.addChangeListener(se);
 
     }
     public JFrame getFrame(){
         return frame;
     }
     public void setWindowListener(WindowAdapter a){
         frame.addWindowListener(a);
     }
     public void progressAdd()
     {
         int n=progressBar.getValue();
         progressBar.setValue(++n);
         System.out.println("progress="+n+" "+progressBar.getMaximum());
     }
     public  void likedListener(ActionListener a)
     {
         like.addActionListener(a);
     }
     public void loopListener(ChangeListener a)
     {
         loop.addChangeListener(a);
     }
     public void setLikeIcon(String pic)
     {
         ImageIcon originalIcon = new ImageIcon(pic);
        like.setIcon(resizeIcon(originalIcon,30,30));
     }
     public void clickPlayPause(){
        this.play_pause_Button.doClick();
     }
     //#C0BCDB
    public Player_V() {
        
        frame = new JFrame("MP3 Player");
        playBackToolBar = new JToolBar();
        volumeToolBar=new JToolBar(JToolBar.VERTICAL);
        playBackToolBar.setBounds(0, 0, 500, 100);
        ImageIcon originalIcon = new ImageIcon("Icons\\lyrics_dark.png");
        lyricsButton = new JButton(resizeIcon(originalIcon,40,40));         
        originalIcon = new ImageIcon("Icons\\play_dark.png");
        play_pause_Button = new JButton(resizeIcon(originalIcon,60,60));         
        originalIcon = new ImageIcon("Icons\\volume_up_dark.png");
        volumeUpButton = new JButton(resizeIcon(originalIcon,40,40));
        originalIcon = new ImageIcon("Icons\\volume_down_dark.png");
        volumeDownButton = new JButton(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\previous_dark.png");
        previousButton = new JButton(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\next_dark.png");
        nextButton = new JButton(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\mute_dark.png");
        muteButton = new JButton(resizeIcon(originalIcon,40,40)); 
        originalIcon = new ImageIcon("Icons\\like.png");
        like = new JButton(resizeIcon(originalIcon,30,30)); 
        originalIcon = new ImageIcon("Icons\\loop_dark.png");
        loop = new JToggleButton(resizeIcon(originalIcon,30,30),false); 
        //muteButton.setBackground(new Color(192, 188, 219));
        //muteButton.setBackground(new Color(0,0,0));
        progressBar = new JProgressBar();
        songLabel = new JLabel();
        songLabel.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 15));
        top.add(songLabel);
        //playBackToolBar.setBackground(new Color(0,0,0));
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));
        playBackToolBar.add(loop);
        playBackToolBar.addSeparator(new Dimension(40,0));
        playBackToolBar.add(previousButton);
        playBackToolBar.addSeparator(new Dimension(30,0));
        playBackToolBar.add(play_pause_Button);
        //play_pause_Button.setBackground(new Color(0,0,0));
        playBackToolBar.addSeparator(new Dimension(30,0));
        playBackToolBar.add(nextButton);
        playBackToolBar.addSeparator(new Dimension(40,0));
        playBackToolBar.add(like);
        bottom.add(progressBar);
        playBackToolBar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        volumeToolBar.add(lyricsButton);
        volumeToolBar.add(volumeUpButton);
        volumeToolBar.add(volumeDownButton);
        volumeToolBar.add(muteButton);
        bottom.add(playBackToolBar);
        right.add(volumeToolBar);
        frame.add(right,BorderLayout.EAST);
        frame.add(top,BorderLayout.NORTH);
        frame.add(bottom,BorderLayout.SOUTH);
        frame.add(center,BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

}
