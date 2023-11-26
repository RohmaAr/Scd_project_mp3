/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.AllSongs_M;
import Model.Player_M;
import Model.User_M;
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

public class PlayBack_Volume__C {
    Player_M player;
    Player_V playerScreen; 
        int i = 1;
    public PlayBack_Volume__C(User_M user,Player_V p,Player_M pm)
    {
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
       playerScreen.nextListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                playerScreen.setSong(player.next());
                playerScreen.progressReset();
                i=0;
            }
        });
        playerScreen.muteListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {player.volumeControl(0.0); }
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
                Logger.getLogger(PlayBack_Volume__C.class.getName()).log(Level.SEVERE, null, ex);
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
                
                
            }
            
        });
    }
}
