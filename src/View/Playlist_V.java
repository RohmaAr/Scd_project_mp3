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
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.util.HashSet;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.event.ChangeEvent;
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
    HashSet<String> selected=new HashSet<>();
    HashSet<String> unselected=new HashSet<>();
    JLabel nameFixed;
    JToggleButton edit;
    JPanel top;
    JPanel disabledPanel;
    JPanel enabledPanel;
    JCheckBox checkBox;
    JButton delete;
    JButton playAll;
    JButton saveChanges=new JButton("Save ");
    public Playlist_V()
    {
        frame=new JFrame();
        frame.setSize(600,600);
        nameFixed=new JLabel();
        nameFixed.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        nameFixed.setEnabled(false);
        top = new JPanel();
        top.add(nameFixed);
        ImageIcon originalIcon = new ImageIcon("Icons\\play_dark.png");         
        playAll=new JButton(resizeIcon(originalIcon,50,50));
        top.add(playAll);
        originalIcon = new ImageIcon("Icons\\edit_dark.png");         
        edit=new JToggleButton(resizeIcon(originalIcon,50,50),false);
        top.add(edit);
        originalIcon = new ImageIcon("Icons\\delete_dark.png");         
        delete=new JButton();
        delete.setIcon(resizeIcon(originalIcon,50,50));
        top.add(delete);
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
    public void setTogglefalse()
    {
        edit.doClick();
    }
    public JPanel getCardPanel()
    {
        return cardPanel;
    }
     public HashSet getDeleted()
     {
         return unselected;
     }
     public HashSet getAdded()
     {
         return selected;
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
     public void setDeleteListener(ActionListener a)
     {
         delete.addActionListener(a);
     }

    public void editingPanel(String[][] songNames,Boolean[] present){
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        for(int i=0;i<songNames.length;i++){
            checkBox=new JCheckBox(songNames[i][0],present[i]);
            checkBox.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e) {
                    saveChanges.setEnabled(true);
                    if(e.getStateChange()==ItemEvent.SELECTED)  
                    {
                        JCheckBox checked=(JCheckBox)e.getSource();
                        selected.add(checked.getText());
                        if(unselected.contains(checked.getText()))
                        {
                            unselected.remove(checked.getText());
                        }
                    }
                    else if(e.getStateChange()==ItemEvent.DESELECTED)  
                    {
                        JCheckBox checked=(JCheckBox)e.getSource();
                        unselected.add(checked.getText());
                        if(selected.contains(checked.getText()))
                        {
                            selected.remove(checked.getText());
                        }
                    }
                }
            
            });
            p.add(checkBox);
            checkBox.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        }
        saveChanges.setEnabled(false);
        JPanel p2=new JPanel();
        p2.add(saveChanges);
        enabledPanel.add(p2,BorderLayout.SOUTH);
        enabledPanel.add(new JScrollPane(p),BorderLayout.CENTER);
    }
    public void saveListener(ActionListener a)
    {
        saveChanges.addActionListener(a);
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
    public void playAllListener(ActionListener a)
    {
        playAll.addActionListener(a);
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
