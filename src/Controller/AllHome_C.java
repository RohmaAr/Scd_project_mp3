/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AllSongs_M;
import Model.Player_M;
import Model.Song_M;
import View.NonUserHome_V;
import View.Player_V;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;


/**
 *
 * @author Dell
 */
public class AllHome_C {
    AllSongs_M allSongs;
    NonUserHome_V home;
    Player_M player;
    Player_V playScreen;
    PlayBack_Volume__C pbv;
    public AllHome_C(){
        home=new NonUserHome_V();
        allSongs=AllSongs_M.getAllSongs();
        String[][] songInfo=allSongs.getSongsData();
        home.populateTable(songInfo);
        home.setTableActionListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JTable t=(JTable)e.getSource();
               int clicked=t.rowAtPoint(e.getPoint());
               Song_M songPlay=allSongs.getSongAt(clicked);
                System.out.println("set song");
                player=new Player_M(songPlay);
                playScreen=new Player_V();
                pbv=new PlayBack_Volume__C(null,playScreen,player);
                
         }
        });
        home.setLoginListener(e->{
            new LoginRegister_C();
            player.pause();
        });
    }
}
