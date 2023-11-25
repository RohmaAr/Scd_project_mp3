/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AllSongs_M;
import Model.Database_M;
import Model.PlayList_M;
import Model.Player_M;
import Model.Song_M;
import Model.User_M;
import View.Player_V;
import View.Playlist_V;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author Dell
 */
public class PlayListManage_C {
    PlayList_M playlist;
    Playlist_V playlistDisplay;
    Player_M player;
    Player_V playScreen;
    AllSongs_M allSongs;
    PlayBack_Volume__C pbv;
    User_M user;
    Database_M database;
    public PlayListManage_C(User_M use,PlayList_M pl)
    {
        database=Database_M.getDatabaseConnection();
        user=use;
        allSongs=AllSongs_M.getAllSongs();
        playlist=pl;
        playlistDisplay=new Playlist_V();
        playlistDisplay.setPlaylistName(playlist.getPlayListName());
       playlistDisplay.setToggle(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            CardLayout layout=playlistDisplay.getLayout();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String[][] songInfo=allSongs.getSongsData();
                Boolean[] present=new Boolean[songInfo.length];
                for(int i=0;i<songInfo.length;i++){
                    if(playlist.getSongOrDont(songInfo[i][0])==null)
                    present[i]=false;
                    else
                    present[i]=true;
                }
            playlistDisplay.editingPanel(songInfo,present);
            layout.show(playlistDisplay.getCardPanel(), "Enabled");
         
            } else {
            
            layout.show(playlistDisplay.getCardPanel(), "disabled");
            }
        }
        });
       playlistDisplay.saveListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice=JOptionPane.showConfirmDialog(playlistDisplay.getCardPanel(), "Do you want to save changes?", "Confirm", JOptionPane.YES_NO_OPTION);
                if(choice==JOptionPane.YES_OPTION)
                {
                    HashSet<String> remove=playlistDisplay.getDeleted();
                    HashSet<String> add=playlistDisplay.getAdded();
                    System.out.println("ADDED");
                    for (String songToAdd : add) {
                        System.out.println(songToAdd + " "); 
                        Song_M s=allSongs.getSongOrDont(songToAdd);
                        playlist.addToPlaylist(s);
                        database.dbaddToPlaylist(songToAdd, playlist.getPlayListName());
                    }
                    System.out.println("Deleted");    
                    for (String songToRemove : remove){
                        System.out.println(songToRemove + " "); 
                        Song_M s=allSongs.getSongOrDont(songToRemove);
                        playlist.removeFromPlaylist(s);
                        database.dbRemoveSongFromPlaylist(songToRemove, playlist.getPlayListName());
                    }
                    System.out.println("PRINTING NEW TABLE'S INFO");
                    String[][] son=playlist.getSongsData();
                    playlistDisplay.populateTable(son);
                    for(int i=0;i<son.length;i++){
                        System.out.println(son[i][0]);
                    }
                    playlistDisplay.setTogglefalse();
                }
            }
        });
        String[][] songInfo=playlist.getSongsData();
        playlistDisplay.populateTable(songInfo);
        playlistDisplay.setTableActionListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JTable t=(JTable)e.getSource();
               int clicked=t.rowAtPoint(e.getPoint());
               Song_M songPlay=playlist.getSongAt(clicked);
                System.out.println("set song");
                player=new Player_M(songPlay);
                playScreen=new Player_V();
                pbv=new PlayBack_Volume__C(user,playScreen,player);
                
         }
        });
        playlistDisplay.setDeleteListener(e->{
           int choice=JOptionPane.showConfirmDialog(playlistDisplay.getCardPanel(),playlist.getPlayListName()+" will be permanently deleted. Are you sure?", "Confirm Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
           if(choice==JOptionPane.OK_OPTION)
           {
               user.deletePlaylist(playlist);
               database.dbDeletePlaylist(playlist.getPlayListName());
               new LoggedHome_C(user);
           }
        });
    }
}
