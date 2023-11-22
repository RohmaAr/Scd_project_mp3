/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 *
 * @author Dell
 */
public class LoggedInHome_V {
    JFrame frame;
    JPanel playListsPanel;
    JToolBar toolBar;
    JScrollPane scroll;
    JButton detectSongs=new JButton("Detect Songs");
    JButton history=new JButton("History");
    JButton liked=new JButton("Liked");
    JButton addPlaylist=new JButton("New list");
    JLabel person=new JLabel("user");
    public LoggedInHome_V()
    {
        
        frame=new JFrame("Home");
        frame.setSize(600,600);
        frame.setLayout(new BorderLayout());
        playListsPanel=new JPanel(new GridLayout(2,0,40,50));
        for(int i=0;i<10;i++){
            ImageIcon originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\playlist_dark.png");
        JButton button = new JButton(resizeIcon(originalIcon,70,70));         
        button.setText("Button");
           playListsPanel.add(button);
        }
        scroll=new JScrollPane(playListsPanel);
        toolBar=new JToolBar(JToolBar.HORIZONTAL);
        detectSongs.setSize(50,50);
        toolBar.add(detectSongs);
         ImageIcon originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\liked.png");
        liked.setIcon(resizeIcon(originalIcon,50,50));
        liked.setSize(50,50);
        toolBar.add(liked);
        originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\history_dark.png");
        history.setIcon(resizeIcon(originalIcon,50,50));
        history.setSize(50,50);
        originalIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\addPlaylist_dark.png");
        addPlaylist.setIcon(resizeIcon(originalIcon,50,50));
        addPlaylist.setSize(50,50);
        toolBar.add(addPlaylist);
        toolBar.add(history);
       // toolBar.addSeparator(new Dimension(20,0));
        person.setHorizontalAlignment(SwingConstants.RIGHT);
        toolBar.add(person);
        person.setSize( 70, 30);
        frame.add(toolBar,BorderLayout.NORTH);
        frame.add(scroll,BorderLayout.CENTER);
        frame.setVisible(true);
       
    }
     private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
