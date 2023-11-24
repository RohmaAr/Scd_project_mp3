/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AllSongs_M;
import Model.PlayList_M;
import Model.Song_M;
import Model.User_M;
import View.LoggedInHome_V;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 *
 * @author Dell
 */
public class LoggedHome_C {
    User_M user;
    LoggedInHome_V home;
    AllSongs_M allSongs;
    PlayListManage_C displayPlaylist;
    public LoggedHome_C(User_M u)
    {
        user=u;
        home=new LoggedInHome_V();
        allSongs=AllSongs_M.getAllSongs();
        home.createListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 String namePlaylist=home.getNewName();
                if(namePlaylist.equals("Enter Playlist name"))
                {
                  JOptionPane.showMessageDialog(home.getFrame(), "Please enter a name for the playlist");
                    return;
                  
                }
                HashSet<String> songNames=user.getAllPlaylistNames();
                if(songNames!=null && songNames.contains(namePlaylist))
                {
                    JOptionPane.showMessageDialog(home.getFrame(), "You already have a playlist called "+namePlaylist+". Enter a different name.");
                    return;
                }
                else{
                songNames=home.getAdded();
                PlayList_M newPlaylist=new PlayList_M();
                newPlaylist.setPlayListName(namePlaylist);
               Iterator<String> it=songNames.iterator();
                while(it.hasNext()){
                Song_M s=allSongs.getSongOrDont(it.next());
                newPlaylist.addToPlaylist(s);
                user.createPlayList(newPlaylist);
                }
                songNames.clear();
                songNames.add(namePlaylist);
                home.setPlaylistNames(songNames,new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton b=(JButton)e.getSource();
                        PlayList_M playlist=user.getPlayListOrNot(b.getText());
                        displayPlaylist=new PlayListManage_C(playlist);
                    }
                });
                home.readyCreate(allSongs.getSongsData());
                home.showMain();
                }
           }
        });
        home.setPlaylistNames(user.getAllPlaylistNames(),new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton b=(JButton)e.getSource();
                        PlayList_M playlist=user.getPlayListOrNot(b.getText());
                        displayPlaylist=new PlayListManage_C(playlist);
                    }
                });
        home.readyCreate(allSongs.getSongsData());
        home.backListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                home.showMain();
            }
        
        });
        home.createPlaylistListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                home.showCreate();
            }
        });
    }
}
