/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class LoginRegister_V {
    JFrame frame=new JFrame("Register an Account");
    JPanel loginPanel;
    JPanel registerPanel;
    JPanel mainPanel;
    JLabel password=new JLabel("Password");
    JLabel username=new JLabel("Username");
    JLabel pageLabel=new JLabel();
    JTextField userField=new JTextField(16);
    JTextField passwordRegister=new JTextField(16);
    JPasswordField passwordLogin=new JPasswordField(16);
    JButton gotoLogin=new JButton("Already have an account");
    JButton submitLogin=new JButton("Submit");
    JButton submitRegister=new JButton("Submit");
    JButton gotoRegister=new JButton("Create an account");
    CardLayout layout;
    public LoginRegister_V()
    {
        loginPanel=new JPanel();
        registerPanel=new JPanel();
        mainPanel=new JPanel();
        layout=new CardLayout();
        pageLabel.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 34));
        password.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        username.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        passwordLogin.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        passwordRegister.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        userField.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        mainPanel.setLayout(layout);
        mainPanel.add(loginPanel,"login");
        mainPanel.add(registerPanel,"register");
        loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.Y_AXIS));
        registerPanel.setLayout(new BoxLayout(registerPanel,BoxLayout.Y_AXIS));
        readyLoginPanel();
        showLoginPanel();
        frame.add(mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
    public void showLoginPanel()
    {
        layout.show(mainPanel, "login");
    }
    public String getUsername()
    {
        return userField.getText();
    }
    public String getCurrentPassword()
    {
         char[] p=passwordLogin.getPassword();
       String pas=new String(p);
       return pas;
    }
    public void setFieldEmptyCheckLogin(KeyAdapter k)
    {
        passwordLogin.addKeyListener(k);
    }
    public void setFieldEmptyCheckRegister(KeyAdapter k)
    {
        passwordRegister.addKeyListener(k);
    }
    public void enabledLogin(boolean b)
    {
        submitLogin.setEnabled(b);
    }
    public void enabledRegister(boolean b)
    {
        submitRegister.setEnabled(b);
    }
    public void showRegisterPanel()
    {
        layout.show(mainPanel, "register");
    }
    public void readyLoginPanel(){
        loginPanel.removeAll();
        loginPanel.add(Box.createRigidArea(new Dimension(0,30)));
        loginPanel.add(pageLabel);
        pageLabel.setText("Log In");
        JPanel p= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(username);
        userField.setText("");
        p.add(userField);
        loginPanel.add(Box.createRigidArea(new Dimension(0,30)));
        loginPanel.add(p);
        loginPanel.add(Box.createRigidArea(new Dimension(0,15)));
        p= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(password);
        p.add(passwordLogin);
        passwordLogin.setText("");
        loginPanel.add(p);
        loginPanel.add(Box.createRigidArea(new Dimension(0,15)));
        p= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(submitLogin);
        p.add(gotoRegister);
        loginPanel.add(p);
        submitLogin.setEnabled(false);
    
        
    }
    public String getNewPassword()
    {
        return passwordRegister.getText(); 
    }
    public void setGoToLogin(ActionListener a)
    {
        gotoLogin.addActionListener(a);
    }
    public void setGoToRegister(ActionListener a)
    {
        gotoRegister.addActionListener(a);
    }
    public void setSubmitLogin(ActionListener a)
    {
        submitLogin.addActionListener(a);
    }
    public void setSubmitRegister(ActionListener a)
    {
        submitRegister.addActionListener(a);
    }
    public void readyRegisterPanel(){
        
        registerPanel.removeAll();
        registerPanel.add(Box.createRigidArea(new Dimension(0,30)));
        registerPanel.add(pageLabel);
        pageLabel.setText("Register");
        JPanel p= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(username);
        p.add(userField);
        userField.setText("");
        p.add(userField);
        registerPanel.add(Box.createRigidArea(new Dimension(0,30)));
        registerPanel.add(p);
        registerPanel.add(Box.createRigidArea(new Dimension(0,15)));
        p= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(password);
        p.add(passwordRegister);
        registerPanel.add(p);
        registerPanel.add(Box.createRigidArea(new Dimension(0,15)));
        p= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(submitRegister);
        submitRegister.setEnabled(false);
        p.add(gotoLogin);
        registerPanel.add(p);
        
    }
}
