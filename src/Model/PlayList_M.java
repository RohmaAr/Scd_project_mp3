/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
   public void addToPlaylist(Song_M s)
   {
       songs.add(s);
   }
   public void removeFromPlaylist(){
   
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
   
   public Song_M prevSongInList(Song_M h)
   {
       int i=songs.indexOf(h);
       if(i==0)
       {
           return songs.get(songs.size()-1);
       }
       return songs.get(--i);
   }
}
