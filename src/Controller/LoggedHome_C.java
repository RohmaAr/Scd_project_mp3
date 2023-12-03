/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AllSongs_M;
import Model.Database_M;
import Model.PlayList_M;
import Model.Song_M;
import Model.User_M;
import View.LoggedInHome_V;
import View.Playlist_V;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Dell
 */
public class LoggedHome_C {
    User_M user;
    LoggedInHome_V home;
    AllSongs_M allSongs;
    Database_M database;
    Playlist_V playlistV;
    PlayListManage_C displayPlaylist;
    public LoggedHome_C(User_M u)
    {
        database=Database_M.getDatabaseConnection();
        user=u;
        home=new LoggedInHome_V();
        home.setPlaylistNamesListener(user.getAllPlaylistNames(), new ListenerForPlaylistButtons());
        home.setPlaylistNames();
        home.setUserName(user.getName());
        home.showMain();
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
                else if(!database.dbUniquePlaylistName(namePlaylist))
                {
                  JOptionPane.showMessageDialog(home.getFrame(), "A playlist by this name already exists. Pick another name");
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
                }
                user.createPlayList(newPlaylist);
                database.dbAddAPlaylist(user.getName(), newPlaylist.getPlayListName(), songNames);
                home.voluntaryBack();
                }
           }
        });
        home.addLogOutListener(e->{
           home.getFrame().dispose();
           if(displayPlaylist!=null)
           displayPlaylist.disposePlayerFrame();
           new AllHome_C();
        });
        home.readyCreate(allSongs.getSongsData());
        home.backListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                home.setPlaylistNamesListener(user.getAllPlaylistNames(), new ListenerForPlaylistButtons());
                home.setPlaylistNames();
                home.showMain();
            }
        
        });
        home.createPlaylistListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                home.showCreate();
            }
        });
        home.setHistoryListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                home.readyHistoryPanel(user.getHistory());
                home.showHistory();
            }
        });
        home.setLikedListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                playlistV=new Playlist_V(home.getBackButton());
                home.addPanelToHome(playlistV);
                home.showAddedPanel();
                playlistV.setDisablechanges();
                displayPlaylist=new PlayListManage_C(user,user.getLikedSongs(),playlistV);
        
            }
        });
    }
    class ListenerForPlaylistButtons implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b=(JButton)e.getSource();
            PlayList_M playlist=user.getPlayListOrNot(b.getText());
            playlistV=new Playlist_V(home.getBackButton());
            home.addPanelToHome(playlistV);
            home.showAddedPanel();
            displayPlaylist=new PlayListManage_C(user,playlist,playlistV);
        }
    
    }
}
