/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.PlayList_M;
import Model.Player_M;
import Model.Song_M;
import View.Player_V;
import View.Playlist_V;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Dell
 */
public class PlayListManage_C {
    PlayList_M playlist;
    Playlist_V playlistDisplay;
    Player_M player;
    Player_V playScreen;
    PlayBack_Volume__C pbv;
    public PlayListManage_C(PlayList_M pl,Playlist_V v)
    {
        playlist=pl;
        playlistDisplay=v;
       playlistDisplay.setToggle(new ItemListener() {
    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout layout=playlistDisplay.getLayout();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String[][] songInfo=playlist.getSongsData();
            playlistDisplay.editingPanel(songInfo);
            layout.show(playlistDisplay.getCardPanel(), "Enabled");
             playlistDisplay.populateTable(songInfo);
       
        } else {
            
            layout.show(playlistDisplay.getCardPanel(), "disabled");
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
                pbv=new PlayBack_Volume__C(playScreen,player);
                
         }
        });
    }
}
