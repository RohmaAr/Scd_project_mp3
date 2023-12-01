/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Dell
 */
import jaco.mp3.player.MP3Player;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class Player_M {
    MP3Player player;
    boolean paused=true;
    boolean loop=false;
    PlayList_M list;
    Song_M song;
    public Player_M(Song_M s)
    {
        System.out.println("Song wala Construct for playerM"+s.songTitle());
        song=s;
        player=new MP3Player(new File(song.getSongPath()));
        list=null;
        System.out.println("PLAYER IS  : "+player.toString());
    }
    public String getCurrentSong()
    {
        return song.songTitle();
    }
    public Player_M(PlayList_M l)
    {
        list=l;
        song=list.getSongAt(0);
        player=new MP3Player(new File(song.getSongPath()));
    }
    public int getDuration()
    {
        int d=song.minutes()*60;
        d+=song.seconds();
        return d;
    }
    public void setLoop(boolean b)
    {
        loop=b;
    }
    public void play(){
        paused=false;
        player.play();
    }
    public boolean isPaused()
    {
        return paused;
    }
    public void pause(){
        paused=true;
    player.pause();
    }
    
    public String previous(){
        if(list!=null)
        {
            player.pause();
            song=list.prevSongInList(song);
            player=new MP3Player(new File(song.getSongPath()));
            player.play();
            return song.songTitle();
        }
        return null;
    }
    public String next(){
        if(list!=null)
        {
            player.pause();
            song=list.nextSongInList(song);
            player=new MP3Player(new File(song.getSongPath()));
            player.play();
            return song.songTitle();
        }
        return null;
    }
    public String implicitNext()
    {
           player.pause();
         
        if(loop==true)
        {
            player=new MP3Player(new File(song.getSongPath()));
            player.play();
            return song.songTitle();
        }
        else
        {
            if(list!=null){
                song=list.nextSongInList(song);
                player=new MP3Player(new File(song.getSongPath()));
                player.play();
                return song.songTitle();
            }
            else 
                return null;
        }
        
    }
//    public String forwardToSong()
//    {
//    }
    public void volumeDownControl(Double valueToPlusMinus){
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                boolean opened = true;
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    if(!opened){
                        line.open();
                    }
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((float)currentVolume-(double)volumeToCut);
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }
    
    public void volumeUpControl(Double valueToPlusMinus){
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                boolean opened = true;
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    if(!opened){
                        line.open();
                    }
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((float)currentVolume+(double)volumeToCut);
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }
    public void volumeControl(Double valueToPlusMinus){    //for za mute
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for(Mixer.Info mixerInfo : mixers){
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            for(Line.Info lineInfo : lineInfos){
                Line line = null;
                boolean opened = true;
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    if(!opened){
                        line.open();
                    }
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((double)volumeToCut);
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }

}
