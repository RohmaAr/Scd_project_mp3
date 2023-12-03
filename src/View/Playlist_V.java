/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.AllHome_C;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
public class Playlist_V extends JPanel {
   // JFrame frame;
    JTable table;
    JPanel cardPanel;
    CardLayout layout;
    Color purple=new Color(192, 188, 219);
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
    JButton back;
    JButton saveChanges=new JButton("Save ");
    public Playlist_V(JButton b)
    {
        //frame=new JFrame();
        //frame.setSize(600,600);
        this.setLayout(new BorderLayout());
        nameFixed=new JLabel();
        nameFixed.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 17));
        nameFixed.setEnabled(false);
        top = new JPanel();
        back=b;
        top.add(back);
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
       
        this.add(top,BorderLayout.NORTH);
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
        this.add(cardPanel,BorderLayout.CENTER);
    }
    public void setLightMode()
    {
        this.back.setBackground(purple);
        this.back.setForeground(Color.darkGray);
        this.cardPanel.setBackground(purple);
       this.delete.setBackground(purple);
        this.disabledPanel.setBackground(purple);
        this.edit.setBackground(purple);
        this.nameFixed.setBackground(purple);
        this.nameFixed.setForeground(Color.darkGray);
        this.playAll.setBackground(purple);
        this.saveChanges.setBackground(purple);
        this.saveChanges.setForeground(Color.darkGray);
        this.table.setBackground(purple);
        this.table.setForeground(Color.darkGray);
        this.top.setBackground(purple);
         ImageIcon originalIcon = new ImageIcon("Icons\\delete_dark.png");         
        delete.setIcon(resizeIcon(originalIcon,50,50));
         originalIcon = new ImageIcon("Icons\\edit_dark.png");         
        edit.setIcon(resizeIcon(originalIcon,50,50));
         originalIcon = new ImageIcon("Icons\\play_dark.png");         
        playAll.setIcon(resizeIcon(originalIcon,50,50));
    }
     public void setDarkMode()
    {
        this.back.setBackground(Color.darkGray);
        this.back.setForeground(this.purple);
        this.cardPanel.setBackground(Color.darkGray);
        this.delete.setBackground(Color.darkGray);
        this.disabledPanel.setBackground(Color.darkGray);
        this.edit.setBackground(Color.darkGray);
        this.nameFixed.setBackground(Color.darkGray);
        this.nameFixed.setForeground(purple);
        this.playAll.setBackground(Color.darkGray);
        this.saveChanges.setBackground(Color.darkGray);
        this.saveChanges.setForeground(purple);
        this.table.setBackground(Color.darkGray);
        this.table.setForeground(purple);
     this.top.setBackground(Color.darkGray);
        ImageIcon originalIcon = new ImageIcon("Icons\\delete_light.png");         
        delete.setIcon(resizeIcon(originalIcon,50,50));
         originalIcon = new ImageIcon("Icons\\edit_light.png");         
        edit.setIcon(resizeIcon(originalIcon,50,50));
         originalIcon = new ImageIcon("Icons\\play_light.png");         
        playAll.setIcon(resizeIcon(originalIcon,50,50));
    }
    public void setTogglefalse()
    {
        edit.doClick();
    }
    public void setDisablechanges()
    {
        edit.setEnabled(false);
        delete.setEnabled(false);
        
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
    @Override
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

    public void editingPanel(String[][] songNames,Boolean[] present,boolean mode){
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        if(mode)
            p.setBackground(purple);
        else
            p.setBackground(Color.darkGray);
        for(int i=0;i<songNames.length;i++){
            checkBox=new JCheckBox(songNames[i][0],present[i]);
             if(mode){
                checkBox.setBackground(purple);
                 checkBox.setForeground(Color.darkGray);
            }
            else{
            checkBox.setBackground(Color.darkGray);
                 checkBox.setForeground(purple);
            }
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
        if(mode)
            p2.setBackground(purple);
        else
            p2.setBackground(Color.darkGray);
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
    public void populateTable(String[][] songInfo,boolean mode)
    {
        String[] col={"Song","Duration"};
        tableModel=new DefaultTableModel(songInfo,col);
        table.setModel(tableModel);
        System.out.println(     tableModel.getRowCount());
        tableModel.fireTableDataChanged();
        if(mode){
        table.setBackground(purple);
        table.setForeground(Color.darkGray);
        }
        else
        {
             table.setForeground(purple);
             table.setBackground(Color.darkGray);
        }
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
        // frame.setVisible(true);
   
    }
     private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
