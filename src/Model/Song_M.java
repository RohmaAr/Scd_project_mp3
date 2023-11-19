/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;

/**
 *
 * @author Dell
 */
public class Song_M{
       private File mp3File;
       private int min;
       private int seconds;
   //String rootDir = "D:\\DELL\\Music\\Free YouTube Downloader";
   public Song_M(String filePath){
       mp3File=new File(filePath);
       duration();
//       File dir=new File(rootDir);  
//       File[] files = dir.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                    if (file.getName().toLowerCase().endsWith(".mp3")) {
//                        songs.add(new File(file.getAbsolutePath()));
//                }
//            }
//        }
   }
   public String getSongPath()
   {
       return mp3File.getAbsolutePath();
   }
   public int minutes(){
       return min;
   }
   public int seconds(){
       return seconds;
   }
   public String songTitle()
   {
       return mp3File.getName();
   }
   private void duration()
   {
    
    Header h = null;
    FileInputStream file = null;
    try {
    file = new FileInputStream(mp3File); 
    Bitstream bitstream = new Bitstream(file);
    h = bitstream.readFrame();
    long tn = 0;
    tn = file.getChannel().size();
    System.out.println( h.total_ms((int) tn)/1000);
    float totalSeconds=h.total_ms((int) tn)/1000;
    min=(int)totalSeconds/60;
    seconds=(int)totalSeconds%60;

    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    }
    catch (BitstreamException ex) {
      ex.printStackTrace();
      }
    catch (IOException ex) {
         ex.printStackTrace();
    }
    
   }
}
