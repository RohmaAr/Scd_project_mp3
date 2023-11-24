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
    public Database_M(){
        try {
             con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public boolean dbFindLogin(String user,String pass,String reason)
     {
        try {
            query = "select * from users where username=?";
             stat = con.prepareStatement(query);
            rs=stat.executeQuery();
              if(!rs.isBeforeFirst())
            {
                reason="Username not found";
                System.out.println("Bru its empty");
                con.close();
                return false;
            }
            while(rs.next()){
                if(rs.getString("password").equals(pass))
                {
                    con.close();
                    reason=Integer.toString(rs.getInt("user_id"));
                    return true;
                }
            }
                  con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
        reason="Password is incorrect";
        return false;
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
                System.out.println("Bru its empty");
                con.close();
                return null;
            }
         ArrayList<Integer> ids=new ArrayList<>();
         namesArray=new ArrayList<>();
            rs.getFetchSize();
            while(rs.next()){
                namesArray.add(rs.getString("playlist_name"));
                ids.add(rs.getInt("playlist_id"));
            }
                  con.close();
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
            
            con.close();
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
                System.out.println("Bru its empty");
                con.close();
                return null;
            }
            return this.getsSongNamesById();
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;

     }
     public boolean dbRegister(String user,String pass){
        try {
            query = "insert into users(username,password) values(?,?)";
            stat = con.prepareStatement(query);
            stat.setString(1, user);
            stat.setString(2, pass);
            int updated=stat.executeUpdate();
               con.close();
             
            if(updated<1)
            {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
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
                System.out.println("Bru its empty");
                con.close();
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
                System.out.println("Bru its empty");
                con.close();
                return null;
            }
            return this.getsSongNamesById();
        } catch (SQLException ex) {
            Logger.getLogger(Database_M.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;

     }
}
