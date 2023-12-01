/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.AllHome_C;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author Dell
 */
public class NonUserHome_V {
    JTable table;
    DefaultTableModel tableModel;
    JButton LoginRegister=new JButton("Log In/Register");
    JFrame frame=new JFrame("Home");
    JButton back=new JButton("Back");
    JButton playPause=new JButton();
    CardLayout layout;
    JPanel mainPanel=new JPanel();
    JPanel allsongPanel=new JPanel(new BorderLayout());
    public NonUserHome_V()
    {
        
        table=new JTable();
        table.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        table.setRowHeight(40);
        JScrollPane scroll=new JScrollPane(table);
        table.setEnabled(false);
        allsongPanel.add(scroll,BorderLayout.CENTER);
        frame.setSize(500,600);
        JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(LoginRegister);
        ImageIcon originalIcon = new ImageIcon("Icons\\play_dark.png");
        playPause = new JButton(resizeIcon(originalIcon,50,50));         
        p.add(playPause);
        allsongPanel.add(p,BorderLayout.NORTH);
        layout=new CardLayout();
        mainPanel.setLayout(layout);
        mainPanel.add(allsongPanel,"allSongs");
        this.goToAllSongs();
        frame.add(mainPanel); 
    }
    public JButton getBackButton()
     {
         return back;
     }
     public void setAllPlaylistener(ActionListener a)
     {
         this.playPause.addActionListener(a);
     }
    public void setBackListener(ActionListener a)
    {
        back.addActionListener(a);
    }
    public void setLoginRegisterListener(ActionListener a)
    {
        LoginRegister.addActionListener(a);
    }
    public void setTableActionListener(MouseAdapter ac)
    {
        table.addMouseListener(ac);
    }
     class RightAlignmentRenderer extends DefaultTableCellRenderer {
        public RightAlignmentRenderer() {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
    }
    public void goToAllSongs()
    {
        layout.show(mainPanel, "allSongs");
    }
    public void addLoginPanel(LoginRegister_V logReg)
    {
        mainPanel.add(logReg,"LoginRegister");
    }
    public void goToLoginReg()
    {
        layout.show(mainPanel, "LoginRegister");
    }
    public void populateTable(String[][] songInfo)
    {
        String[] col={"Song","Duration"};
        tableModel=new DefaultTableModel(songInfo,col);
        table.setModel(tableModel);
        tableEditing();
    }
    private void tableEditing()
    {
         table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setCellRenderer(new RightAlignmentRenderer());
         frame.setVisible(true);
    
    }
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
   
}
