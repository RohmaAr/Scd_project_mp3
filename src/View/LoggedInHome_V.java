/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class LoggedInHome_V {
    JFrame frame; 
    Color purple=new Color(192, 188, 219);
    JPanel playListsPanel;
    JPanel createPlaylist;
    JPanel historyPanel;
    JPanel likedPanel;
    JToolBar toolBar;
    CardLayout layout=new CardLayout();
    JTextField nameNewPlaylist=new JTextField(10);
    JButton create=new JButton("Create Playlist");
    HashSet<String> addHashset;
    JPanel container;
    JButton playlistButton;
    JScrollPane scroll;
    JButton back=new JButton("Back");
    JButton detectSongs=new JButton("Detect Songs");
    JButton history=new JButton("History");
    JButton liked=new JButton("Liked");
    JButton newPlaylist=new JButton("New list");
    JLabel person=new JLabel("user");
    JPanel bottomPanel=new JPanel();
    JButton logOut=new JButton("Log out");
    JButton customizeButton=new JButton();
    ActionListener playlistButtonListener;
    HashSet<String> playlistNames;
    public LoggedInHome_V()
    {
        
        frame=new JFrame("Home");
        frame.setSize(600,600);
        JPanel mainPanel=new JPanel();
        container = new JPanel(layout);
        frame.add(container);
        mainPanel = new JPanel(new BorderLayout());
        playListsPanel=new JPanel(new GridLayout(2,0,40,50));
        scroll=new JScrollPane(playListsPanel);
        toolBar=new JToolBar(JToolBar.HORIZONTAL);
        detectSongs.setSize(50,50);
        toolBar.add(detectSongs);
         ImageIcon originalIcon = new ImageIcon("Icons\\liked.png");
        liked.setIcon(resizeIcon(originalIcon,50,50));
        liked.setSize(50,50);
        toolBar.add(liked);
        originalIcon = new ImageIcon("Icons\\history_dark.png");
        history.setIcon(resizeIcon(originalIcon,50,50));
        history.setSize(50,50);
        originalIcon = new ImageIcon("Icons\\addPlaylist_dark.png");
        newPlaylist.setIcon(resizeIcon(originalIcon,50,50));
        newPlaylist.setSize(50,50);
        toolBar.add(newPlaylist);
        toolBar.add(history);
        toolBar.addSeparator();
        toolBar.addSeparator();
        person.setHorizontalAlignment(SwingConstants.RIGHT);
        toolBar.add(person);
        originalIcon = new ImageIcon("Icons\\dark_dark.png");
        customizeButton.setIcon(resizeIcon(originalIcon,50,50));
        
        bottomPanel.add(customizeButton);
        bottomPanel.add(logOut);
        person.setSize( 70, 30);
        mainPanel.add(toolBar,BorderLayout.NORTH);
        mainPanel.add(scroll,BorderLayout.CENTER);
        mainPanel.add(this.bottomPanel,BorderLayout.SOUTH);
        createPlaylist=new JPanel();
        createPlaylist.setLayout(new BorderLayout());
        historyPanel=new JPanel();
        likedPanel=new JPanel();
        container.add(mainPanel, "Main");
        container.add(createPlaylist, "Create");
        container.add(historyPanel, "History");
        container.add(likedPanel, "Liked");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void addPanelToHome(JPanel panel,String constraint)
    {
        container.add(panel,constraint);
    }
    public void setDetectSongsListener(ActionListener a)
    {
        detectSongs.addActionListener(a);
    }
    public void addLogOutListener(ActionListener a)
    {
        logOut.addActionListener(a);
    }
    public void showAddedPanel(String consta)
    {
        layout.show(container, consta);
    }
    public void setLightMode()
    {
        this.person.setForeground(Color.BLACK);
        this.bottomPanel.setBackground(purple);
        this.historyPanel.setBackground(purple);
        this.create.setBackground(purple);
        this.create.setForeground(Color.BLACK);
        this.createPlaylist.setBackground(purple);
        this.createPlaylist.setForeground(Color.BLACK);
        this.customizeButton.setBackground(purple);
        this.history.setBackground(purple);
        this.history.setForeground(Color.BLACK);
        this.liked.setBackground(purple);
        this.liked.setForeground(Color.BLACK);
        this.logOut.setBackground(purple);
        this.logOut.setForeground(Color.BLACK);
        this.newPlaylist.setBackground(purple);
        this.newPlaylist.setForeground(Color.darkGray);
        this.nameNewPlaylist.setForeground(Color.darkGray);
        this.playListsPanel.setBackground(purple);
        this.scroll.setBackground(purple);     
        this.toolBar.setBackground(purple);
        this.detectSongs.setBackground(purple);
        this.detectSongs.setForeground(Color.BLACK);
        this.back.setBackground(purple);
        this.back.setForeground(Color.BLACK);
        ImageIcon originalIcon = new ImageIcon("Icons\\history_dark.png");
        history.setIcon(resizeIcon(originalIcon,50,50));
        originalIcon = new ImageIcon("Icons\\addPlaylist_dark.png");
        newPlaylist.setIcon(resizeIcon(originalIcon,50,50));
        
    }
    
    public void setDarkMode()
    {
        this.bottomPanel.setBackground(Color.darkGray);
        this.historyPanel.setBackground(Color.darkGray);
        this.create.setBackground(Color.darkGray);
        this.create.setForeground(purple);
        this.createPlaylist.setBackground(Color.darkGray);
        this.createPlaylist.setForeground(purple);
        this.customizeButton.setBackground(Color.darkGray);
        this.history.setBackground(Color.darkGray);
        this.history.setForeground(purple);
        this.liked.setBackground(Color.darkGray);
        this.liked.setForeground(purple);
        this.logOut.setBackground(Color.darkGray);
        this.logOut.setForeground(purple);
        this.newPlaylist.setBackground(Color.darkGray);
        this.newPlaylist.setForeground(purple);
        this.playListsPanel.setBackground(Color.darkGray);
        this.nameNewPlaylist.setForeground(purple);
        this.scroll.setBackground(Color.darkGray);     
        this.toolBar.setBackground(Color.darkGray);
        this.detectSongs.setBackground(Color.darkGray);
        this.detectSongs.setForeground(purple);
        this.back.setBackground(Color.darkGray);
        this.back.setForeground(purple);
        this.person.setForeground(purple);
        ImageIcon originalIcon = new ImageIcon("Icons\\history_light.png");
        history.setIcon(resizeIcon(originalIcon,50,50));
        originalIcon = new ImageIcon("Icons\\addPlaylist_light.png");
        newPlaylist.setIcon(resizeIcon(originalIcon,50,50));
        
    }
    public void readyHistoryPanel(ArrayList<String> historyInfo,boolean mode)
    {
        historyPanel.removeAll();
        JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        historyPanel.setLayout(new BorderLayout());
        p.add(back);
        JLabel l=new JLabel("Playback History");
        l.setFont(new Font(Font.SERIF,  Font.PLAIN, 25));   
        p.add(l);
        if(mode){
            p.setBackground(purple);
                l.setBackground(purple);
                l.setForeground(Color.darkGray);
        }
        else
        { p.setBackground(Color.darkGray);
                l.setBackground(Color.darkGray);
                l.setForeground(purple);
        }
        historyPanel.add(p,BorderLayout.NORTH);
        if(historyInfo!=null){
            JPanel pane=new JPanel();
             if(mode)
                pane.setBackground(purple);
            else
                 pane.setBackground(Color.darkGray);
            pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
            pane.add(Box.createVerticalStrut(20));
            for(int i=0;i<historyInfo.size();i++){
                JLabel label=new JLabel(historyInfo.get(i));
                label.setFont(new Font(Font.SERIF,  Font.PLAIN, 19));
                pane.add(label);
                pane.add(Box.createVerticalStrut(20));
                if(mode){
                    label.setBackground(purple);
                    label.setForeground(Color.darkGray);
                }
                else{
                    label.setForeground(purple);
                    label.setBackground(Color.darkGray);
                }
            }
            historyPanel.add(new JScrollPane(pane));
           
        }
        else
        {
            historyPanel.setLayout(new BorderLayout());
            JLabel label=new JLabel("No Playback history found",SwingConstants.CENTER);
                label.setFont(new Font(Font.SERIF,  Font.PLAIN, 19));
                if(mode)
                    label.setBackground(purple);
   
                historyPanel.add(label,BorderLayout.CENTER);

        }
        
    }
    public void setPlaylistNamesListener(HashSet<String> hash,ActionListener ac)
    {
        this.playlistNames=hash;
        this.playlistButtonListener=ac;
    }
    public void showMain()
    {
        layout.show(container, "Main");
    }
    public void showHistory()
    {
        layout.show(container, "History");
    }
    public void showLiked()
    {
        layout.show(container, "Liked");
    }
    public void showCreate()
    {
        layout.show(container, "Create");
    }
    public JFrame getFrame()
    {
        return frame;
    }
    public void modeIconChange(String pic)
     {
         ImageIcon originalIcon = new ImageIcon(pic);
        customizeButton.setIcon(resizeIcon(originalIcon,50,50));
        
     }
     
    public void createPlaylistListener(ActionListener a)
    {
        newPlaylist.addActionListener(a);
    }
    public void backListener(ActionListener a)
    {
        back.addActionListener(a);
    }
    public String getNewName()
    {
        return nameNewPlaylist.getText();
    }
    public void createListener(ActionListener a)
    {
        create.addActionListener(a);
    }
    public JButton getBackButton()
    {
        return back;
    }
    public HashSet getAdded()
    {
        return addHashset;
    }
    public void readyCreate(String[][] songs,boolean mode)
    {
        createPlaylist.removeAll();
        addHashset=new HashSet<>();
        JPanel p=new JPanel();
        JCheckBox checkBox;
        create.setEnabled(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        for(int i=0;i<songs.length;i++){
            checkBox=new JCheckBox(songs[i][0]);
            if(mode){
                checkBox.setBackground(purple);
                 checkBox.setForeground(Color.darkGray);
            }
            else{
            checkBox.setBackground(Color.darkGray);
                 checkBox.setForeground(purple);
            }
            
            checkBox.addItemListener((ItemEvent e) -> {
                if(e.getStateChange()==ItemEvent.SELECTED)
                {
                    create.setEnabled(true);
                    JCheckBox checked=(JCheckBox)e.getSource();
                    addHashset.add(checked.getText());
                }
                else if(e.getStateChange()==ItemEvent.DESELECTED)
                {
                    JCheckBox checked=(JCheckBox)e.getSource();
                    if(addHashset.contains(checked.getText()))
                    {
                        addHashset.remove(checked.getText());
                    }
                    if(addHashset.isEmpty())
                    create.setEnabled(false);
                }
            });
            p.add(checkBox);
            checkBox.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        }
        JPanel p2=new JPanel();
        JPanel p3=new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.CENTER));
        p2.add(create);
        p3.add(back);
        nameNewPlaylist.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 20));
        p3.add(nameNewPlaylist);
        p3.add(new JPanel());
        if(mode){
             p.setBackground(purple);
              p2.setBackground(purple);
               p3.setBackground(purple);
        }else{
            p.setBackground(Color.darkGray);
            p2.setBackground(Color.darkGray);
            p3.setBackground(Color.darkGray);
            
        }
        createPlaylist.add(p2,BorderLayout.SOUTH);
        createPlaylist.add(p3,BorderLayout.NORTH);
        createPlaylist.add(new JScrollPane(p),BorderLayout.CENTER);
    }
    public void setPlaylistNames(boolean mode)
    {
        playListsPanel.removeAll();
        if(playlistNames!=null){
            Iterator<String> it=playlistNames.iterator();
            while(it.hasNext()){
                if(mode){
                    ImageIcon originalIcon = new ImageIcon("Icons\\playlist_dark.png");
                    JButton playlistButton = new JButton(resizeIcon(originalIcon,70,70));         
                    playlistButton.setText(it.next());
                    playlistButton.setBackground(purple);
                    playlistButton.setForeground(Color.darkGray);
                    playlistButton.addActionListener(this.playlistButtonListener);
                    playListsPanel.add(playlistButton);
                }else
                {
                    ImageIcon originalIcon = new ImageIcon("Icons\\playlist_light.png");
                     JButton playlistButton = new JButton(resizeIcon(originalIcon,70,70));         
                    playlistButton.setText(it.next());
                    playlistButton.setBackground(Color.darkGray);
                    playlistButton.setForeground(purple);
                    playlistButton.addActionListener(this.playlistButtonListener);
                    playListsPanel.add(playlistButton);    
                }
            }
       }
        
    }
    public void setCustomizeListener(ActionListener a)
    {
        this.customizeButton.addActionListener(a);
    }
    public void voluntaryBack()
    {
        back.doClick();
    }
     private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    public void setHistoryListener(ActionListener a)
    {
        history.addActionListener(a);
    }
    public void setLikedListener(ActionListener a)
    {
        liked.addActionListener(a);
    }
    public void setUserName(String n)
    {
        this.person.setText(n);
    }
}
