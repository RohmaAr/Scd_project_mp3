/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;

/**
 *
 * @author Dell
 */
public class LoginRegister_V extends JPanel{
    
    private Color purple=new Color(192, 188, 219);
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
    JButton back;
    public LoginRegister_V(JButton back)
    {
        loginPanel=new JPanel();
        registerPanel=new JPanel();
        mainPanel=new JPanel();
        layout=new CardLayout();
        pageLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 34));
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
        this.setLayout(new BorderLayout());
        JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.back=back;
        back.setBackground(purple);
        loginPanel.setBackground(purple);
        registerPanel.setBackground(purple);
        mainPanel.setBackground(purple);
        pageLabel.setBackground(purple);
        password.setBackground(purple);
        username.setBackground(purple);
        p.add(back);
        this.add(p,BorderLayout.NORTH);
        this.add(mainPanel,BorderLayout.CENTER);
        this.setSize(400, 500);
        this.setVisible(true);
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
        JPanel labelPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setBackground(purple);
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageLabel.setText("Log In");
        labelPanel.add(pageLabel);
        loginPanel.add(labelPanel);
        JPanel p= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setBackground(purple);
        p.add(username);
        userField.setText("");
        p.add(userField);
        loginPanel.add(Box.createRigidArea(new Dimension(0,30)));
        loginPanel.add(p);
        loginPanel.add(Box.createRigidArea(new Dimension(0,15)));
        p= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(password);
        p.add(passwordLogin);
        p.setBackground(purple);
        passwordLogin.setText("");
        loginPanel.add(p);
        loginPanel.add(Box.createRigidArea(new Dimension(0,15)));
        p= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(submitLogin);
        p.add(gotoRegister);
        p.setBackground(purple);
        loginPanel.add(p);
        submitLogin.setEnabled(false);
    
        
    }
    public JFrame getFrame(){
        return frame;
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
        JPanel labelPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setBackground(purple);
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageLabel.setText("Register");
        labelPanel.add(pageLabel);
        registerPanel.add(labelPanel);
        JPanel p= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(username);
        p.add(userField);
        p.setBackground(purple);
        userField.setText("");
        p.add(userField);
        registerPanel.add(Box.createRigidArea(new Dimension(0,30)));
        registerPanel.add(p);
        registerPanel.add(Box.createRigidArea(new Dimension(0,15)));
        p= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(password);
        p.setBackground(purple);
        p.add(passwordRegister);
        registerPanel.add(p);
        registerPanel.add(Box.createRigidArea(new Dimension(0,15)));
        p= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setBackground(purple);
        p.add(submitRegister);
        submitRegister.setEnabled(false);
        p.add(gotoLogin);
        registerPanel.add(p);
        
    }
}
