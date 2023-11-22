/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class User_M {
    String name;
    String password;
    PlayList_M likedSongs;
    ArrayList<PlayList_M> playlists;
    public User_M(String n,String p){
        name=n;
        password=p;
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
        likedSongs.addToPlaylist(s);
    }
    public void removedFromLiked(Song_M s)
    {
        likedSongs.removeFromPlaylist(s);
    }
    public void createPlayList(PlayList_M p)
    {
        playlists.add(p);       
    }
    public void deletePlaylist(PlayList_M P)
    {
        playlists.remove(P);
    }
    public PlayList_M getPlayList(String p)
    {
        for(int i=0;i<playlists.size();i++){
         if(playlists.get(i).name.equals(p))
             return playlists.get(i); 
        }
        return null;
    }
}
