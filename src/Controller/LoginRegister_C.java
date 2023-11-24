/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User_M;
import View.LoginRegister_V;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Dell
 */
public class LoginRegister_C {
    LoginRegister_V logRegV;
    User_M user;
    LoggedHome_C home;
    public LoginRegister_C()
    {
        logRegV=new LoginRegister_V();
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
            else
            {
                logRegV.enabledRegister(true);
            }
        }
        });
        logRegV.setSubmitLogin(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String user=logRegV.getUsername();
                String password=logRegV.getCurrentPassword();
                System.out.println("USER PASSWORD "+user+ " "+password);
            }
        
        });
        logRegV.setSubmitRegister(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String user=logRegV.getUsername();
                String password=logRegV.getNewPassword();
                System.out.println("USER PASSWORD "+user+ " "+password);
                
            }
        
        });
    }
}
