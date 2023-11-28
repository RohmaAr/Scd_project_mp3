/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class PlayList_M {
   LinkedList<Song_M> songs;
   String name;
   public PlayList_M()
   {
       songs=new LinkedList<>();
   }
   public void setPlayListName(String n)
   {
       name=n;
   }
   public boolean hasSong(String song)
   {
       for(Song_M s:songs)
       {
           if(s.songTitle().equals(song))
               return true;
       }
       return false;
   }
   public String getPlayListName()
   {
       return name;
   }
   public void addToPlaylist(Song_M s)
   {
       if(!songs.contains(s))
           songs.add(s);
   }
   public void removeFromPlaylist(Song_M s){
       if(songs.contains(s))
          songs.remove(s);
   }
   public Song_M getSongAt(int index){
       if(index>=0 && index<songs.size())
           return songs.get(index);
       else 
           return null;
   }
   public Song_M nextSongInList(Song_M h)
   {
       int i=songs.indexOf(h);
       if(i==songs.size()-1)
       {
           return songs.get(0);
       }
       return songs.get(++i);
   }
   public Song_M getSongOrDont(String s)
   {
       Iterator<Song_M> it=songs.iterator();
       while(it.hasNext())
       {
           Song_M song=it.next();
           if(song.songTitle().equals(s))
              return song;
       }
       return null;
   }
   public Song_M prevSongInList(Song_M h)
   {
       int i=songs.indexOf(h);
       if(i==0)
       {
           return songs.get(songs.size()-1);
       }
       return songs.get(--i);
   }
   public String[][] getSongsData()
   {
       String[][] data=new String[songs.size()][2];
       for(int i=0;i<songs.size();i++){
           data[i][0]=songs.get(i).songTitle();
           String t=songs.get(i).minutes()+":"+songs.get(i).seconds();
           data[i][1]=t;
       }
       return data;
   }
}
