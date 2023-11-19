/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Player_M;
import Model.Song_M;
import View.Player_V;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayBack_Volume__C {
    Player_M player;
    Player_V playerScreen;
    public PlayBack_Volume__C(Player_V p)
    {
        player=new Player_M(new Song_M("C:\\Users\\Dell\\Downloads\\x2mate.com - SEVENTEEN (세븐틴) '손오공' Official MV (320 kbps).mp3"));
        playerScreen=p;
        playerScreen.nextListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {player.next();}
        });
        playerScreen.muteListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {player.volumeControl(0.0); }
        });
        int dura=player.getDuration();
        playerScreen.progressSetMax(dura);
        playerScreen.playListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(player.isPaused()){
                player.play();
                playerScreen.pausePlayIconChange("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\pause_dark.png");
                    SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
    @Override
    protected Void doInBackground() throws Exception {
        for (int i = 1; i <= dura && !player.isPaused(); i++) {
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
                playerScreen.pausePlayIconChange("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\play_dark.png");
                
                }
            }
            
        });
        playerScreen.previousListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { player.previous(); }
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
                System.out.println("Song cOmplete");
                bar.setValue(0);
            }
            };
        
        });
        
    }
}
