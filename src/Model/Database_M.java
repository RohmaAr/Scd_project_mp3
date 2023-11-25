/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Dell
 */
public class Database_M {
    String url = "jdbc:mysql://localhost:3306/MyTunes";
    String user = "root";
    String password = "@pa55word";
    Connection con;
    PreparedStatement stat;
    ResultSet rs; 
    String query;
    static Database_M db;
    static public Database_M getDatabaseConnection()
    {
        if(db==null)
        {
           db=new Database_M(); 
        }
        return db;
    }
    private Database_M(){
        try {
             con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public String dbFindLogin(String user,String pass)
     {
        try {
            int userId=getsUserIdByName(user);
            if(userId==0)
            {
                return "No such username found in system";
            }
            query = "select * from users where username =?";
             stat = con.prepareStatement(query);
            stat.setString(1,user);
             rs=stat.executeQuery();
            while(rs.next()){
                if(rs.getString("password").equals(pass))
                {
                     return Integer.toString(rs.getInt("user_id"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "Password is incorrect";
     }
     public Object[] dbGetPlaylistNameId(int userId,ArrayList<String> namesArray)
     {
         try {
            query = "select * from user_playlists where user_id=?";
             stat = con.prepareStatement(query);
             stat.setInt(1, userId);
            rs=stat.executeQuery();
            if(!rs.isBeforeFirst())
            {
                System.out.println("no playlist matches user id");
                return null;
            }
         ArrayList<Integer> ids=new ArrayList<>();
            while(rs.next()){
                namesArray.add(rs.getString("playlist_name"));
                ids.add(rs.getInt("playlist_id"));
            }
             return ids.toArray();
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
     }
     private ArrayList<String> getsSongNamesById()
     {
        try {
            ArrayList<String> playlist=new ArrayList<>();
            while(rs.next()){
                int songsid=rs.getInt("song_id");
                query = "select title from songs where song_id=?";
                stat = con.prepareStatement(query);
                stat.setInt(1,songsid);
                ResultSet rs2=stat.executeQuery();
                while(rs2.next()){
                    playlist.add(rs2.getString(1));
                }
            }
            
            return playlist;
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
     }
     public ArrayList<String> dbGetPlayListsSongs(int playlistId)
     {
         try {
            query = "select song_id from playlist_songs where playlist_id=?";
             stat = con.prepareStatement(query);
             stat.setInt(1, playlistId);
            rs=stat.executeQuery();
            if(!rs.isBeforeFirst())
            {
                System.out.println("no such song with this playlist id");
                return null;
            }
            return this.getsSongNamesById();
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;

     }
     public String dbRegister(String user,String pass){
        try {
            int userId=getsUserIdByName(user);
            if(userId!=0)
            {
                return "There already exists an account with this username";
            }
            query = "insert into users(username,password) values(?,?)";
            stat = con.prepareStatement(query);
            stat.setString(1, user);
            stat.setString(2, pass);
            int updated=stat.executeUpdate();
            if(updated<1)
            {
                return "Could not create account";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Integer.toString(getsUserIdByName(user));
     }
     public ArrayList<String> dbgetLikedSongs(int userId)
     {
         try {
            query = "select song_id from favorite_songs where user_id=?";
             stat = con.prepareStatement(query);
             stat.setInt(1, userId);
            rs=stat.executeQuery();
            if(!rs.isBeforeFirst())
            {
                System.out.println("no songs liked by this id");
                return null;
            }
            return this.getsSongNamesById();
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;

     }
     public ArrayList<String> dbgetHistory(int userId){
     try {
            query = "select song_id from player_history where user_id=?";
             stat = con.prepareStatement(query);
             stat.setInt(1, userId);
            rs=stat.executeQuery();
            if(!rs.isBeforeFirst())
            {
                System.out.println("no playback history for this user id");
                return null;
            }
            return this.getsSongNamesById();
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;

     }
     private int getsUserIdByName(String usern)
     {
        try {
            query = "select user_id from users where username=?";
            stat = con.prepareStatement(query);
            stat.setString(1, usern);
            rs=stat.executeQuery();
            int userId=0;
            if(!rs.isBeforeFirst())
            {
                System.out.println("No such user id");
                return 0;
            }
            while(rs.next()){
                userId=rs.getInt("user_id");
            }
            return userId;
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;    
     }
     private int getsSongIdByName(String song)
     {
        try {
            query = "select song_id from songs where title=?";
            stat = con.prepareStatement(query);
            stat.setString(1, song);
            rs=stat.executeQuery();
            int songId=0;
            if(!rs.isBeforeFirst())
            {
                System.out.println("No such song id");
                return 0;
            }
            while(rs.next()){
                songId=rs.getInt("song_id");
            }
            return songId;
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
     }
     public void dbPutInHistory(String usern,String song)
     {
         try {
            int userId=getsUserIdByName(usern);
            int songId=getsSongIdByName(song);
            query = "insert into player_history(user_id,song_id) values(?,?)";
            stat = con.prepareStatement(query);
            stat.setInt(1, userId);
            stat.setInt(2, songId);
            int entered= stat.executeUpdate();
            if(entered!=1)
            {
                System.out.println("history entered other than 1");
            }
           } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void dbPutInLiked(String usern,String song)
     {
         try {
            int userId=getsUserIdByName(usern);
            int songId=getsSongIdByName(song);
            query = "insert into favorite_songs(user_id,song_id) values(?,?)";
            stat = con.prepareStatement(query);
            stat.setInt(1, userId);
            stat.setInt(2, songId);
            int entered= stat.executeUpdate();
            if(entered!=1)
            {
                System.out.println("liked entered other than 1");
            }
           } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     private int getsPlaylistIdByName(String playlistName)
     {
         try {
            query = "select playlist_id from user_playlists where playlist_name=?";
            stat = con.prepareStatement(query);
            stat.setString(1, playlistName);
            rs=stat.executeQuery();
            int playlistId=0;
            if(!rs.isBeforeFirst())
            {
                System.out.println("No such playlist id");
                return 0;
            }
            while(rs.next()){
                playlistId=rs.getInt("playlist_id");
            }
            return playlistId;
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
     
     }
     public void dbAddAPlaylist(String usern,String playlistName,HashSet<String> songInfo)
     {
        try {
            int userId=getsUserIdByName(usern);
            query = "insert into user_playlists(user_id,playlist_name) values(?,?)";
            stat = con.prepareStatement(query);
            stat.setInt(1, userId);
            stat.setString(2, playlistName);
            int entered= stat.executeUpdate();
            if(entered!=1)
            {
                System.out.println("playlist info entered other than 1");
            }
            int playlistId=getsPlaylistIdByName(playlistName);
            Iterator<String> it=songInfo.iterator();
            while(it.hasNext()){
                int songId=getsSongIdByName(it.next());
               query = "insert into playlist_songs(playlist_id,song_id) values(?,?)";
                stat = con.prepareStatement(query);
                stat.setInt(1, playlistId);
                stat.setInt(2, songId);
                entered= stat.executeUpdate();
                if(entered!=1)
                {
                    System.out.println("playlist "+playlistName+" "+songId+" "+"info entered other than 1");
                }
             }
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
          
     }
     public void dbaddToPlaylist(String song,String playlistName)
     {
        try {
            int playlistId=getsPlaylistIdByName(playlistName);
            int songId=getsSongIdByName(song);
            query = "insert into playlist_songs(playlist_id,song_id) values(?,?)";
            stat = con.prepareStatement(query);
            stat.setInt(1, playlistId);
            stat.setInt(2, songId);
            int entered= stat.executeUpdate();
            if(entered!=1)
            {
                System.out.println("song added to playlist entered other than 1");
            }
           } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public boolean dbUniquePlaylistName(String playlistName){
         if(getsPlaylistIdByName(playlistName)==0)
            return true;
         else 
            return false; 
     }
     public void dbPutAllSongsInDb(String[][] songInfo)
     {
         for(int i=0;i<songInfo.length;i++)
         {
                int songId=getsSongIdByName(songInfo[i][0]);
                if(songId==0){
                    try {
                        query = "insert into songs(title) values(?)";
                        stat = con.prepareStatement(query);
                        stat.setString(1, songInfo[i][0]);
                        int entered=stat.executeUpdate();
                        if(entered!=1)
                        {
                            System.out.println("Song either not entered or entred more than once");
                            
                            
                        }   } catch (SQLException ex) {
                        Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }
             
         }
     }
     public void dbRemoveSongFromPlaylist(String song,String playlistName)
     {              
         try {
            int playlistId=getsPlaylistIdByName(playlistName);
            int songId=getsSongIdByName(song);
            query = "delete from playlist_songs where playlist_id=? and song_id=?";
            stat = con.prepareStatement(query);
            stat.setInt(1, playlistId);
            stat.setInt(2, songId);
            int deleted= stat.executeUpdate();
            if(deleted!=1)
            {
                System.out.println("more than one song deleted from playlist");
            }
           } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void dbDeletePlaylist(String playlistName){
         try {
            int playlistId=getsPlaylistIdByName(playlistName);
            query = "delete from user_playlists where playlist_id=?";
            stat = con.prepareStatement(query);
            stat.setInt(1, playlistId);
            int deleted= stat.executeUpdate();
            if(deleted!=1)
            {
                System.out.println("more than one playlist deleted ");
            }
           } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void dbDisplayAllTables()
     {
        try {
            query = "select * from users ";
            stat = con.prepareStatement(query);
            rs=stat.executeQuery();
            System.out.println("Users");
            if(!rs.isBeforeFirst())
            {
                System.out.println("No data in user ");
            }
            while(rs.next()){
                System.out.print("user "+rs.getString("username"));
                System.out.print("\tpass "+rs.getString("password"));
                System.out.print("\tuser_id "+rs.getInt("user_id"));
                System.out.println("");
            }
            System.out.println("");
            query = "select * from  songs";
            stat = con.prepareStatement(query);
            rs=stat.executeQuery();
            System.out.println("Songs");
            if(!rs.isBeforeFirst())
            {
                System.out.println("No data in songs ");
            }
            while(rs.next()){
                System.out.print("songid "+rs.getInt("song_id"));
                System.out.print("\ttitle "+rs.getString("title"));
                System.out.println("");
            }
            System.out.println("");
            query = "select * from  favorite_songs";
            stat = con.prepareStatement(query);
            rs=stat.executeQuery();
            System.out.println("favorite_songs");
            if(!rs.isBeforeFirst())
            {
                System.out.println("No data in fav songs ");
            }
            while(rs.next()){
                System.out.print("songid "+rs.getInt("song_id"));
                System.out.print("\tuserid "+rs.getInt("user_id"));
                System.out.println("");
            }
            
            System.out.println("");
            query = "select * from  user_playlists";
            stat = con.prepareStatement(query);
            rs=stat.executeQuery();
            System.out.println("user_playlists");
            if(!rs.isBeforeFirst())
            {
                System.out.println("No data in playlits ");
            }
            while(rs.next()){
                System.out.print("playlistid "+rs.getInt("playlist_id"));
                System.out.print("\tplaylist name "+rs.getString("playlist_name"));
                System.out.print("\tuserid "+rs.getInt("user_id"));
                System.out.println("");
            }
            System.out.println("");
            query = "select * from  playlist_songs";
            stat = con.prepareStatement(query);
            rs=stat.executeQuery();
            System.out.println("playlists_song");
            if(!rs.isBeforeFirst())
            {
                System.out.println("No data in playlits ");
            }
            while(rs.next()){
                System.out.print("playlistid "+rs.getInt("playlist_id"));
                System.out.print("\tsongid "+rs.getInt("song_id"));
                System.out.println("");
            }
            System.out.println("");
            query = "select * from  player_history";
            stat = con.prepareStatement(query);
            rs=stat.executeQuery();
            System.out.println("History");
            if(!rs.isBeforeFirst())
            {
                System.out.println("No data in history ");
            }
            while(rs.next()){
                System.out.print("userid "+rs.getInt("user_id"));
                System.out.print("\tsongid "+rs.getInt("song_id"));
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
            
     }
}
