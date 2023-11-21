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
    JFrame frame=new JFrame("Home");
    public NonUserHome_V()
    {
        Container con=frame.getContentPane();
        new AllHome_C(this);
        table.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        table.setRowHeight(40);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setCellRenderer(new RightAlignmentRenderer());
 JScrollPane scroll=new JScrollPane(table);
 table.setEnabled(false);
        con.add(scroll,BorderLayout.CENTER);
        frame.setSize(500,600);
       frame.add(new JPanel(),BorderLayout.NORTH);
        frame.setVisible(true);
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
        table=new JTable();
        table.setModel(tableModel);
    }
}