/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Player_M;
import View.Player_V;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayBack_Volume__C {
    Player_M player;
    Player_V playerScreen;
    public PlayBack_Volume__C(Player_V p)
    {
        player=new Player_M();
        playerScreen=p;
        playerScreen.nextListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {player.next();}
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
                playerScreen.pausePlayIconChange("C:\\Users\\Dell\\Documents\\NetBeansProjects\\MP3Player\\Icons\\pause_dark.png");
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
    }
}
