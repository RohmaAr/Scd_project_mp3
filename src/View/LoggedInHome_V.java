/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Iterator;
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

/**
 *
 * @author Dell
 */
public class LoggedInHome_V {
    JFrame frame;
    JPanel playListsPanel;
    JPanel createPlaylist;
    JToolBar toolBar;
    CardLayout layout=new CardLayout();
    JTextField nameNewPlaylist=new JTextField("Enter Playlist name");
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
        person.setHorizontalAlignment(SwingConstants.RIGHT);
        toolBar.add(person);
        person.setSize( 70, 30);
        mainPanel.add(toolBar,BorderLayout.NORTH);
        mainPanel.add(scroll,BorderLayout.CENTER);
        createPlaylist=new JPanel();
        createPlaylist.setLayout(new BorderLayout());
        container.add(mainPanel, "Main");
        container.add(createPlaylist, "Create");
        showMain();
        frame.setVisible(true);
        
    }
    public void showMain()
    {
        layout.show(container, "Main");
    }
    
    public void showCreate()
    {
        layout.show(container, "Create");
    }
    public JFrame getFrame()
    {
        return frame;
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
    public HashSet getAdded()
    {
        return addHashset;
    }
    public void readyCreate(String[][] songs)
    {
        createPlaylist.removeAll();
        addHashset=new HashSet<>();
        JPanel p=new JPanel();
        JCheckBox checkBox;
        create.setEnabled(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        for(int i=0;i<songs.length;i++){
            checkBox=new JCheckBox(songs[i][0]);
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
        p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
        p2.add(create);
        p3.add(back);
        p3.add(nameNewPlaylist);
        createPlaylist.add(p2,BorderLayout.SOUTH);
        createPlaylist.add(p3,BorderLayout.NORTH);
        createPlaylist.add(new JScrollPane(p),BorderLayout.CENTER);
    }
    public void setPlaylistNames(HashSet<String> playlistNames,ActionListener a)
    {
        if(playlistNames!=null){
        Iterator<String> it=playlistNames.iterator();
        while(it.hasNext()){
            ImageIcon originalIcon = new ImageIcon("Icons\\playlist_dark.png");
        JButton playlistButton = new JButton(resizeIcon(originalIcon,70,70));         
        playlistButton.setText(it.next());
        playlistButton.addActionListener(a);
           playListsPanel.add(playlistButton);
        }
        }
        
    }
     private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
