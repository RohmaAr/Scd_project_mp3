/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Controller.AllHome_C;
import Controller.LoggedHome_C;
import Controller.LoginRegister_C;
import Controller.PlayListManage_C;
import Model.AllSongs_M;
import Model.Database_M;
import Model.PlayList_M;
import Model.Song_M;
import Model.User_M;
import View.LoggedInHome_V;
import View.LoginRegister_V;
import View.NonUserHome_V;
import View.Player_V;
import View.Playlist_V;
import javax.swing.SwingUtilities;
/**
 *
 * @author Dellauthor
 */
public class Main {
    public static void main(String[] ar)
    {
        //SwingUtilities.invokeLater(() -> {
            //new Playlist_V();
//            PlayList_M p=new PlayList_M();
//            Song_M s=AllSongs_M.getAllSongs().getSongAt(5);
//            p.addToPlaylist(s);
//            s=AllSongs_M.getAllSongs().getSongAt(3);
//            p.addToPlaylist(s);
//            s=AllSongs_M.getAllSongs().getSongAt(7);
//            p.addToPlaylist(s);
//            
//           new PlayListManage_C(p);
            //new Player_V();
            //new LoggedHome_C(new User_M("yoooo","HOOOOOO"));
        new AllHome_C();
        //Database_M d=Database_M.getDatabaseConnection();
        //d.dbDisplayAllTables();
        //});
        
    }
}
