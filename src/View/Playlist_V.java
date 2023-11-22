/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.AllHome_C;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class Playlist_V {
    JFrame frame;
    JTable table;
    JPanel cardPanel;
    CardLayout layout;
    DefaultTableModel tableModel;
    //JTextField name;
    JLabel nameFixed;
    JToggleButton edit;
    JPanel top;
    JPanel disabledPanel;
    JPanel enabledPanel;
    public Playlist_V()
    {
        frame=new JFrame();
        frame.setSize(600,600);
        nameFixed=new JLabel("Random Text");
        nameFixed.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        nameFixed.setEnabled(false);
        top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        top.add(nameFixed);
        ImageIcon originalIcon = new ImageIcon("Icons\\edit_dark.png");         
        edit=new JToggleButton(resizeIcon(originalIcon,50,50),false);
        top.add(edit);
        frame.add(top,BorderLayout.NORTH);
        cardPanel = new JPanel();
        layout=new CardLayout();
        cardPanel.setLayout(layout);
        enabledPanel = new JPanel(new BorderLayout());
        disabledPanel = new JPanel();
        cardPanel.add(disabledPanel, "disabled");
        cardPanel.add(enabledPanel, "Enabled");
        disabledPanel.setLayout(new BorderLayout());
        table=new JTable();
        table.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        table.setRowHeight(40);
        table.setEnabled(false);
        disabledPanel.add(new JScrollPane(table),BorderLayout.CENTER);
        frame.add(cardPanel,BorderLayout.CENTER);
        frame.setVisible(true);
        }
    public JPanel getCardPanel()
    {
        return cardPanel;
    }
     
     public CardLayout getLayout()
     {
         return layout;
     }
     public void setPlaylistName(String n)
     {
         nameFixed.setText(n);
     }
     public void setToggle(ItemListener c)
     {
         edit.addItemListener(c);
     }
     public void setTableActionListener(MouseAdapter ac)
    {
        table.addMouseListener(ac);
    }

    public void editingPanel(String[][] songNames){
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        for(int i=0;i<songNames.length;i++){
            JCheckBox checkBox=new JCheckBox(songNames[i][0]);
            p.add(checkBox);
            checkBox.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        }
        
        enabledPanel.add(new JScrollPane(p),BorderLayout.CENTER);
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
        System.out.println(     tableModel.getRowCount());
        tableModel.fireTableDataChanged();
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
