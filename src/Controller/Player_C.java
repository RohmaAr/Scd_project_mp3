/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.AllSongs_M;
import Model.Database_M;
import Model.Player_M;
import Model.Song_M;
import Model.User_M;
//import View.FrontEnd;
import View.Player_V;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import javax.swing.JToggleButton;

public class Player_C {
    Player_M player;
    Player_V playerScreen; 
  //  FrontEnd frontEnd;//for lyrics front end by fatima
        int i = 1;
    public Player_C(User_M user,Player_V p,Player_M pm)
    {
        Database_M db=Database_M.getDatabaseConnection();
        if(pm==null){
            System.out.println("pm is null");
        AllSongs_M allSongs=AllSongs_M.getAllSongs();
        player=new Player_M(allSongs);
        }
        else
        {
            player=pm;
        }
        playerScreen=p;
         playerScreen.setSong(player.getCurrentSong());
         if(user.likesSong(player.getCurrentSong()))
             playerScreen.setLikeIcon("Icons\\liked.png");
         else
              playerScreen.setLikeIcon("Icons\\like.png");
            
       playerScreen.nextListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                playerScreen.setSong(player.next());
                playerScreen.progressReset();
                user.addToHistory(playerScreen.getSong());
                db.dbPutInHistory(user.getName(), player.getCurrentSong());
                i=0;
            }
        });
        playerScreen.muteListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {player.volumeControl(0.0); }
        });
        playerScreen.loopListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                JToggleButton b=(JToggleButton)e.getSource();
                if(b.isSelected())
                    player.setLoop(true);
                else 
                    player.setLoop(false);
            }
        });
        playerScreen.likedListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.likesSong(player.getCurrentSong()))
                {
                    AllSongs_M songs=AllSongs_M.getAllSongs();
                    Song_M s=songs.getSongOrDont(player.getCurrentSong());
                    user.removedFromLiked(s);
                    playerScreen.setLikeIcon("Icons\\like.png");
                    Database_M database=Database_M.getDatabaseConnection();
                    database.dbRemoveFromLiked(user.getName(), s.songTitle());
                }
                else{
                       AllSongs_M songs=AllSongs_M.getAllSongs();
                    Song_M s=songs.getSongOrDont(player.getCurrentSong());
                    user.addToLikedSongs(s);
                    playerScreen.setLikeIcon("Icons\\liked.png");                    
                    Database_M database=Database_M.getDatabaseConnection();
                    database.dbPutInLiked(user.getName(), s.songTitle());
                }
            }
        });
        playerScreen.playListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(player.isPaused()){
                player.play();
                playerScreen.pausePlayIconChange("Icons\\pause_dark.png");
                    SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
    @Override
    protected Void doInBackground() throws Exception {
        int dura=player.getDuration();
        playerScreen.progressSetMax(dura);
        
        for (; i <= dura && !player.isPaused(); i++) {
            try {
                Thread.sleep(1000);
                publish();
                } catch (InterruptedException ex) {
                Logger.getLogger(Player_C.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;
            }

            @Override
            protected void process(List<Void> chunks) {
            playerScreen.progressAdd();
            }

                };
                sw.execute();
            
                }else
                {
                    player.pause();
                playerScreen.pausePlayIconChange("Icons\\play_dark.png");
                
                }
            }
            
        });
        playerScreen.previousListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                playerScreen.setSong(player.previous());
                user.addToHistory(playerScreen.getSong());
                db.dbPutInHistory(user.getName(), player.getCurrentSong());
                playerScreen.progressReset();
            i=0;
            }
        });
        playerScreen.volumeDownListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { player.volumeDownControl(0.1); }
        });
        playerScreen.volumeUpListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { player.volumeUpControl(0.1);}
        });
        playerScreen.progressControll(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
            JProgressBar bar=(JProgressBar)e.getSource();
            if(bar.getPercentComplete()==1)
            {
                System.out.println("Song complete");
                bar.setValue(0);
                playerScreen.setSong(player.implicitNext());
                user.addToHistory(playerScreen.getSong());
            db.dbPutInHistory(user.getName(), player.getCurrentSong());
            }
            };
        
        });
        playerScreen.lyricsListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String song=player.getCurrentSong();
                Scanner scan=new Scanner(song);
                scan.useDelimiter("-|\\.mp3");
                String artist=scan.next();
                String track=scan.next();
                System.out.println(artist +" "+track);
//                frontEnd=new FrontEnd();
//                frontEnd.setArtistAndTrack(artist, track);//for lyrics front end by fatima
            }
            
        });
        
    }
}
