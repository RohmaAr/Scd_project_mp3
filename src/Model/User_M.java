/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Dell
 */
public class User_M {
    String name;
    String password;
    PlayList_M likedSongs=new PlayList_M();;
    ArrayList<String> history;
    ArrayList<PlayList_M> playlists;
    public User_M(String n,String p){
        name=n;
        password=p;
        likedSongs.setPlayListName(name+"'s liked songs");
           
    }
    public HashSet getAllPlaylistNames(){
        if(playlists==null || playlists.isEmpty())
        {
            return null;
        }
        HashSet<String> names=new HashSet<>();
        for(int i=0;i<playlists.size();i++){
        names.add(playlists.get(i).getPlayListName());
        }
        return names;
    }
    public ArrayList<PlayList_M> getAllPlaylists(){
        return playlists;
    }
    public ArrayList<String> getHistory()
    {
        return history;
    }
    public PlayList_M getLikedSongs(){
        return likedSongs;
    }
    public String getPass()
    {
        return password;
    }
    public String getName()
    {
        return name;
    }
    public void addToLikedSongs(Song_M s)
    {
            
        System.out.println(likedSongs.getPlayListName());
        likedSongs.addToPlaylist(s);
    }
    public void removedFromLiked(Song_M s)
    {
        if(likedSongs!=null)
            likedSongs.removeFromPlaylist(s);
    }
    public void createPlayList(PlayList_M p)
    {
        if(playlists==null){
            playlists=new ArrayList<>();
        }
        playlists.add(p);       
    }
    public void deletePlaylist(PlayList_M P)
    {
         if(playlists==null){
             return;
         }
        playlists.remove(P);
    }
    public PlayList_M getPlayListOrNot(String p)
    {
        for(int i=0;i<playlists.size();i++){
         if(playlists.get(i).name.equals(p))
             return playlists.get(i); 
        }
        return null;
    }
    public void addToHistory(String s){
       if(history==null){
           history=new ArrayList<>();
       }
        history.add(s);
    }
    public void setHistory(ArrayList<String> h)
    {
        history=h;
    }
    public boolean likesSong(String songs)
    {
        if(likedSongs!=null)
            return likedSongs.hasSong(songs);
        return false;
    }
}
