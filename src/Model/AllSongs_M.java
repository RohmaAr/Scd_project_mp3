/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;

/**
 *
 * @author Dell
 */
public class AllSongs_M extends PlayList_M{
     String rootDir = "D:\\DELL\\Music\\Free YouTube Downloader";
    static private AllSongs_M thisObject=null;
     private AllSongs_M()
    {
        this.setPlayListName("Downloads");
        System.out.println("All Songs construct");
       File dir=new File(rootDir);  
       File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                    if (file.getName().toLowerCase().endsWith(".mp3")) {
                        songs.add(new Song_M(file.getAbsolutePath()));
                }
            }
        }
    }
    public static AllSongs_M getAllSongs()
    {
        if(thisObject==null)
        {
            thisObject=new AllSongs_M();
        }
        return thisObject;
        
    }
}
