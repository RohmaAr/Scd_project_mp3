/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Songs_M{
       ArrayList<File> songs=new ArrayList<>();
   String rootDir = "D:\\DELL\\Music\\Free YouTube Downloader";
   public Songs_M(){
       File dir=new File(rootDir);  
       File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                   // Check if the file is an MP3 file and add its path to the list
                    if (file.getName().toLowerCase().endsWith(".mp3")) {
                        songs.add(new File(file.getAbsolutePath()));
                }
            }
        }
   }
   private void searchMP3Files() {
       for(int i=0;i<songs.size();i++)
       {
           System.out.println(songs.get(i).getName()+"");
       }
    }
}
