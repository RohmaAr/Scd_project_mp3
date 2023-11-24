/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.AllHome_C;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    JButton Login=new JButton("Log In");
    JFrame frame=new JFrame("Home");
    public NonUserHome_V()
    {
        Container con=frame.getContentPane();
        table=new JTable();
        table.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        table.setRowHeight(40);
        JScrollPane scroll=new JScrollPane(table);
 table.setEnabled(false);
        con.add(scroll,BorderLayout.CENTER);
        frame.setSize(500,600);
       frame.add(Login,BorderLayout.NORTH);
    }
    public void setLoginListener(ActionListener a)
    {
        Login.addActionListener(a);
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
}
