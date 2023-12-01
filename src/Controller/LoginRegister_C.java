/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AllSongs_M;
import Model.Database_M;
import Model.PlayList_M;
import Model.User_M;
import View.LoginRegister_V;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class LoginRegister_C {
    LoginRegister_V logRegV;
    User_M user;
    LoggedHome_C home;
    Database_M database;
    AllSongs_M allSongs;
    public LoginRegister_C(LoginRegister_V logPanel)
    {
        database=Database_M.getDatabaseConnection();
        allSongs=allSongs.getAllSongs();
        logRegV=logPanel;
        logRegV.setGoToLogin(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                logRegV.readyLoginPanel();
                logRegV.showLoginPanel();
            }
        });
        logRegV.setGoToRegister(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                logRegV.readyRegisterPanel();
                logRegV.showRegisterPanel();
            }
        });
        logRegV.setFieldEmptyCheckLogin(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) { 
            if(logRegV.getUsername().length() == 0 || logRegV.getCurrentPassword().length() == 0)
                logRegV.enabledLogin(false);
            else
            {
                logRegV.enabledLogin(true);
            }
        }
        });
        logRegV.setFieldEmptyCheckRegister(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) { 
            if(logRegV.getUsername().length() == 0 || logRegV.getNewPassword().length() == 0)
                logRegV.enabledRegister(false);
            else if(logRegV.getNewPassword().matches(".*[!@#$%^&*()-+={}\\[\\]|\\\\;:'\"<>?/,].*") || logRegV.getUsername().matches(".*[!@#$%^&*()-+={}\\[\\]|\\\\;:'\"<>?/,].*")){
                logRegV.enabledRegister(false);
                JOptionPane.showMessageDialog(logRegV.getFrame(), "No special characters other than '_' are allowed in username or password");
            }
            else if(logRegV.getNewPassword().equals(logRegV.getNewPassword().toLowerCase()) || logRegV.getUsername().equals(logRegV.getUsername().toLowerCase()))
            {
                logRegV.enabledRegister(false);
                JOptionPane.showMessageDialog(logRegV.getFrame(), "Only small characters, numbers and '_' is allowed in username and password");
               
            }
            else
            {
                logRegV.enabledRegister(true);
            }
        }
        });
        logRegV.setSubmitLogin(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=logRegV.getUsername();
                String password=logRegV.getCurrentPassword();
                System.out.println("USER PASSWORD "+username+ " "+password);
                String reason=database.dbFindLogin(username,password);
                if(!reason.matches("\\d+")){
                   JOptionPane.showMessageDialog(logRegV.getFrame(), reason);
                }
                else
                {
                    database.dbPutAllSongsInDb(allSongs.getSongsData());
                    user=new User_M(username,password);
                    ArrayList<String> namesArray=new ArrayList<>();
                    Object[] playlistIds=database.dbGetPlaylistNameId(Integer.valueOf(reason), namesArray);
                    if(playlistIds!=null){
                        for(int i=0;i<namesArray.size();i++){               //Loading user playlists
                            PlayList_M p=new PlayList_M();
                            System.out.println("NAMSE PLAYLIST = "+namesArray.get(i));
                            p.setPlayListName(namesArray.get(i));
                            ArrayList<String> playlistSongNames=database.dbGetPlayListsSongs((int)playlistIds[i]);
                            allSongs=allSongs.getAllSongs();
                            for(int j=0;j<playlistSongNames.size();j++){
                                System.out.println("\tSONGS IN "+playlistSongNames.get(j));
                                    p.addToPlaylist(allSongs.getSongOrDont(playlistSongNames.get(j)));
                            }
                            user.createPlayList(p);
                        }
                    }
                    user.setHistory(database.dbgetHistory(Integer.valueOf(reason)));  //loading the fetched history
                    namesArray=database.dbgetLikedSongs(Integer.valueOf(reason));
                    if(namesArray!=null){
                        for(int i=0;i<namesArray.size();i++){
                            System.out.println("liked songs "+namesArray.get(i));
                            user.addToLikedSongs(allSongs.getSongOrDont(namesArray.get(i)));
                        }
                    }
                    home=new LoggedHome_C(user);
                }
            }
        
        });
        logRegV.setSubmitRegister(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=logRegV.getUsername();
                String password=logRegV.getNewPassword();
                String reason = database.dbRegister(username, password);
                System.out.println("USER PASSWORD "+username+ " "+password);
                if(!reason.matches("\\d+"))
                {
                    JOptionPane.showMessageDialog(logRegV.getFrame(), reason);
                }
                else
                {
                    
                    database.dbPutAllSongsInDb(allSongs.getSongsData());
                    user=new User_M(username,password);
                   home=new LoggedHome_C(user);
                }
                
            }
        
        });
    }
}
