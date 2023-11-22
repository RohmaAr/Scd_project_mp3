/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Controller.AllHome_C;
import Controller.PlayListManage_C;
import Model.AllSongs_M;
import Model.PlayList_M;
import View.LoggedInHome_V;
import View.NonUserHome_V;
import View.Player_V;
import View.Playlist_V;
import javax.swing.SwingUtilities;
/**
 *
 * @author Dell
 */
public class Main {
    public static void main(String[] ar)
    {
        SwingUtilities.invokeLater(() -> {
            //new Playlist_V();
            new PlayListManage_C(new AllSongs_M(),new Playlist_V());
            //new LoggedInHome_V();
        });
    }
}
