/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partymanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.ListModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andredur
 */
public class admin extends javax.swing.JFrame {
    private Party p = PartyManager.partyDetails;
    private Person person;
    private boolean add = false;
    private boolean groupExists = false;
    private int indexG;
    private DefaultListModel persons = new DefaultListModel();
    private DefaultListModel BlackLists = new DefaultListModel();
    private DefaultListModel BlackListEdit = new DefaultListModel();
    private DefaultListModel EditB = new DefaultListModel();
    private DefaultListModel AllpersGroup = new DefaultListModel();
    private DefaultListModel EditG = new DefaultListModel();
    private DefaultListModel selectG = new DefaultListModel();
    private DefaultListModel Allgroups = new DefaultListModel();
    private DefaultListModel newG = new DefaultListModel();
    private DefaultListModel GroupsPer = new DefaultListModel();
    private DefaultListModel WishlistAd = new DefaultListModel();
    private DefaultListModel WishlistAdDraw = new DefaultListModel();
    private int indexP;
    private Action action = new Action();
    /**
     * Creates new form amdin
     */
    public admin() {
        person = PartyManager.party.getAttendee(PartyManager.partyDetails.getAdmin());
        initComponents();
        
    }
    public void updateParty()
    {
        DefaultTableModel model = (DefaultTableModel) Party_Table.getModel();
        model.setValueAt(p.getDate()    , 0, 1);
        model.setValueAt(p.getHour()    , 1, 1);
        model.setValueAt(p.getPlace()   , 2, 1);
        model.setValueAt(p.getAdmin()   , 3, 1);
        model.setValueAt(p.getMinPrice(), 4, 1);
        model.setValueAt(p.getMaxPrice(), 5, 1);
    }
    public void HidePartyRow(DefaultTableModel model, int x)
    {
       // DefaultTableModel model = (DefaultTableModel) Party_Table.getModel(); 
       model.removeRow(x);
    }
    public void RevealPartyRow(DefaultTableModel model, int x)
    {
        //DefaultTableModel model = (DefaultTableModel) Party_Table.getModel();
        String[] partydet = {"Date","Time","Place","Administrator","Minimum Price","Maximum Price"};
        model.addRow(new Object[]{partydet[x],"",""});
    }
    public void updateAllpersons()
    {
        persons.clear();
        PersonList.setModel(persons);
        ArrayList<String> Allattendees = PartyManager.party.getNames();
        for(String name : Allattendees)
            persons.addElement(name);
    }
    public void updateEditPerson(Person person)
    {
        Edit_PersonName.setText(person.getName());
        EditB.clear();
        Edit_PBlackList.setModel(EditB);
        ArrayList<String> black = person.getBlacklistP();
        for(Iterator<String> it = black.iterator(); it.hasNext(); )
        {
            String name = it.next();
            EditB.addElement(name);
        }
        
    }
    public void updateSelectGroup(Person person)
    {
        selectG.clear();
        group_List.setModel(selectG);
        ArrayList<String> groups = person.getGroups();
        for(String name: groups)
            selectG.addElement(name);
    }
    public void setupBlackList(String name)
    {
        BlackLists.clear();
        BlackListEdit.clear();
        for(int i=0;i<persons.getSize();i++)
        {
            BlackLists.addElement(persons.elementAt(i));
        }
        BlackList_all.setModel(BlackLists);
        if(PartyManager.party.contains(name))
        {
            for(int j=BlackLists.getSize();j>0;j--)
            {
                ArrayList<String> test = PartyManager.party.getAttendee(name).getBlacklistP();
                if(PartyManager.party.getAttendee(name).getBlacklistP().contains(BlackLists.elementAt(j-1).toString()))
                {
                   BlackListEdit.addElement(BlackLists.getElementAt(j-1)); 
                   BlackLists.remove(j-1);
                          
                }
                else if(BlackLists.getElementAt(j-1).toString().equals(name))
                    BlackLists.remove(j-1);
                
            }
            for(int j=0;j<BlackListEdit.getSize();j++)
            {
                if(BlackListEdit.getElementAt(j).toString().equals(name))
                    BlackListEdit.remove(j);
            }
        
        }
        BlackList_P.setModel(BlackListEdit);
        BlackListDialog.setVisible(true);
    }
//    public void setupGroup(String name)
//    {
//        for (Group group : PartyManager.groups)
//        {
//            Allgroups.addElement(group.getName());
//        }
//        Groups_all.setModel(Allgroups);
//        if(PartyManager.party.contains(name))
//        {
//            Groups_P.setModel(selectG);
//            for(int j=0;j<Allgroups.getSize();j++)
//            {
//                if(PartyManager.party.getAttendee(name).getGroups().contains(Allgroups.elementAt(j).toString()))
//                { 
//                   Allgroups.remove(j);
//                }
//            }        
//        
//        }
//    }
    public void editGroup(String name)
    {
        Group per = null;
        for(int i=0;i<persons.getSize();i++)
        {
            AllpersGroup.addElement(persons.elementAt(i));
        }
        AllGroups_l.setModel(AllpersGroup);
        for(Group grp: PartyManager.groups)
        {
            if(grp.getName().equals(name))
                per = grp;
            
        }
        if(per!=null)
        {
            ArrayList<String> names = per.getMembers();
            for(int j=AllpersGroup.getSize();j>0;j--)
            {
                if(names.contains(AllpersGroup.elementAt(j-1).toString()))
                {
                   newG.addElement(AllpersGroup.getElementAt(j-1)); 
                   AllpersGroup.remove(j-1);
                }
                
            }        
        
        }
        else
        {
            newG.clear();
        }
        New_groupL.setModel(newG);
        AddGroupDialog.setVisible(true);
    }
    public void updateAllgroups()
    {
        selectG.clear();
        group_List.setModel(selectG);
        ArrayList<Group> Allgroups = PartyManager.groups;
        for(Group grp : Allgroups)
            selectG.addElement(grp.getName());
    }
    public void updatePartOfGroup(String name)
    {
        GroupsPer.clear();
        for(Group grp : PartyManager.groups)
        {
            if(grp.getMembers().contains(name))
            {
                Edit_PGroups.setModel(GroupsPer);
                GroupsPer.addElement(grp.getName());
            }
        }    
    }
    public void updateWishlist() {
        WishlistAd.clear();
        Wishlist.setModel(WishlistAd);
        ArrayList<String> wish = PartyManager.party.getAttendee(p.getAdmin()).getWhishlist();
        for (String item : wish) {
            WishlistAd.addElement(item);
        }
        
    }
     public void updateWishlistDrawn() {
        WishlistAdDraw.clear();
        WishListDraw.setModel(WishlistAdDraw);
        ArrayList<String> wish = PartyManager.party.getAttendee(PartyManager.party.getAttendee(p.getAdmin()).getChosen()).getWhishlist();
        for (String item : wish) {
            WishlistAdDraw.addElement(item);
        }
        
    }
//    private void FillComboP()
//    {
//        ArrayList<String> names = PartyManager.party.getNames();
//        for(int i =0;i<names.size();i++)
//        {
//            ComboB_Person.addItem(names.get(i));
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog2 = new javax.swing.JDialog();
        Party_det = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Date_Input = new javax.swing.JTextField();
        Time_Input = new javax.swing.JTextField();
        Place_Input = new javax.swing.JTextField();
        Min_Input = new javax.swing.JTextField();
        Max_Input = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        AddPersonDialog = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        Edit_PersonName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Edit_PBlackList = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        Add_PBlackList = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Edit_PGroups = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        Person_save = new javax.swing.JButton();
        BlackListDialog = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        B_Owner = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        BlackList_all = new javax.swing.JList<>();
        Add_BlackListButton = new javax.swing.JButton();
        Remove_BlacklistButton = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        BlackList_P = new javax.swing.JList<>();
        Save_Blacklist = new javax.swing.JButton();
        ExportFileFrame = new javax.swing.JFileChooser();
        ImportFileFrame = new javax.swing.JFileChooser();
        WarningPopUp = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        acceptrem = new javax.swing.JButton();
        denyRem = new javax.swing.JButton();
        RemovedItem = new javax.swing.JLabel();
        AddGroupDialog = new javax.swing.JDialog();
        groupfield = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        AllGroups_l = new javax.swing.JList<>();
        Save_Group1 = new javax.swing.JButton();
        Remove_GroupButton2 = new javax.swing.JButton();
        Add_GroupButton1 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        New_groupL = new javax.swing.JList<>();
        popup = new javax.swing.JDialog();
        popupText = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        WarningPopUp2 = new javax.swing.JDialog();
        jLabel14 = new javax.swing.JLabel();
        acceptremPers = new javax.swing.JButton();
        denyRemPers = new javax.swing.JButton();
        RemovedItemPer = new javax.swing.JLabel();
        ExitDialog = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Party_Table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        Person_delete = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        PersonList = new javax.swing.JList<>();
        Person_edit = new javax.swing.JButton();
        BlacklistPButton = new javax.swing.JButton();
        Person_add = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        group_List = new javax.swing.JList<>();
        editGroups = new javax.swing.JButton();
        removeGroup = new javax.swing.JButton();
        addGroup = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pref1_RButton = new javax.swing.JRadioButton();
        pref2_RButton = new javax.swing.JRadioButton();
        pref3_RButton = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        Wishlist = new javax.swing.JList<>();
        Wishlist_Add = new javax.swing.JButton();
        Wishlist_Delete = new javax.swing.JButton();
        Wishlist_Save = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        NewItem = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        WishListDraw = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        RevealHide = new javax.swing.JToggleButton();
        PersonName = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ExportAD = new javax.swing.JMenuItem();
        ImportAd = new javax.swing.JMenuItem();
        SaveAd = new javax.swing.JMenuItem();

        jDialog2.setMinimumSize(new java.awt.Dimension(591, 418));
        jDialog2.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog2.setLocationRelativeTo(null);
        jDialog2.setType(java.awt.Window.Type.POPUP);

        Party_det.setBorder(javax.swing.BorderFactory.createTitledBorder("Party Details"));
        Party_det.setMinimumSize(new java.awt.Dimension(169, 224));

        jLabel1.setText("Date:");

        jLabel2.setText("Time:");

        jLabel3.setText("Place:");

        jLabel5.setText("Minimum Price:");

        jLabel6.setText("Maximum Price:");

        Min_Input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Min_InputActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Party_detLayout = new javax.swing.GroupLayout(Party_det);
        Party_det.setLayout(Party_detLayout);
        Party_detLayout.setHorizontalGroup(
            Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Party_detLayout.createSequentialGroup()
                .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Party_detLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Date_Input, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Place_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Time_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Party_detLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Party_detLayout.createSequentialGroup()
                                .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Max_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Min_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Party_detLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2)
                                .addGap(2, 2, 2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Party_detLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Date_Input, Max_Input, Min_Input, Place_Input, Time_Input});

        Party_detLayout.setVerticalGroup(
            Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Party_detLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Date_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Time_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Place_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Min_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Party_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Max_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(Party_det, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Party_det, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddPersonDialog.setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        AddPersonDialog.setMinimumSize(new java.awt.Dimension(620, 500));
        AddPersonDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        AddPersonDialog.setLocationRelativeTo(null);
        AddPersonDialog.setSize(new java.awt.Dimension(589, 400));
        AddPersonDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                AddPersonDialogWindowClosing(evt);
            }
        });

        jLabel7.setText("Person:");

        Edit_PersonName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Edit_PersonNameActionPerformed(evt);
            }
        });

        Edit_PBlackList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jScrollPane1.setViewportView(Edit_PBlackList);

        jLabel8.setText("Blacklist:");

        Add_PBlackList.setText("Edit BlackList");
        Add_PBlackList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_PBlackListActionPerformed(evt);
            }
        });

        Edit_PGroups.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jScrollPane2.setViewportView(Edit_PGroups);

        jLabel9.setText("Groups:");

        Person_save.setText("Save");
        Person_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Person_saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddPersonDialogLayout = new javax.swing.GroupLayout(AddPersonDialog.getContentPane());
        AddPersonDialog.getContentPane().setLayout(AddPersonDialogLayout);
        AddPersonDialogLayout.setHorizontalGroup(
            AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPersonDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AddPersonDialogLayout.createSequentialGroup()
                        .addGroup(AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddPersonDialogLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(Add_PBlackList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddPersonDialogLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddPersonDialogLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddPersonDialogLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Person_save, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(AddPersonDialogLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Edit_PersonName, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        AddPersonDialogLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        AddPersonDialogLayout.setVerticalGroup(
            AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPersonDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Edit_PersonName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddPersonDialogLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddPersonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Add_PBlackList)
                            .addComponent(Person_save)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        BlackListDialog.setMinimumSize(new java.awt.Dimension(600, 500));
        BlackListDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        BlackListDialog.setResizable(false);
        BlackListDialog.setLocationRelativeTo(null);
        BlackListDialog.setSize(new java.awt.Dimension(536, 385));
        BlackListDialog.setType(java.awt.Window.Type.POPUP);
        BlackListDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                BlackListDialogWindowClosing(evt);
            }
        });

        jLabel11.setText("Blacklist:");

        jScrollPane4.setViewportView(BlackList_all);

        Add_BlackListButton.setText("<html>add<br/> => </html>");
        Add_BlackListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_BlackListButtonActionPerformed(evt);
            }
        });

        Remove_BlacklistButton.setText("<html>remove<br/>&nbsp&nbsp&nbsp&lt=</html> ");
        Remove_BlacklistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remove_BlacklistButtonActionPerformed(evt);
            }
        });

        jScrollPane7.setViewportView(BlackList_P);

        Save_Blacklist.setText("Save");
        Save_Blacklist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_BlacklistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BlackListDialogLayout = new javax.swing.GroupLayout(BlackListDialog.getContentPane());
        BlackListDialog.getContentPane().setLayout(BlackListDialogLayout);
        BlackListDialogLayout.setHorizontalGroup(
            BlackListDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlackListDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BlackListDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlackListDialogLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(BlackListDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(BlackListDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Add_BlackListButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Remove_BlacklistButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Save_Blacklist))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BlackListDialogLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_Owner, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BlackListDialogLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Add_BlackListButton, Remove_BlacklistButton, Save_Blacklist});

        BlackListDialogLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane4, jScrollPane7});

        BlackListDialogLayout.setVerticalGroup(
            BlackListDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlackListDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BlackListDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B_Owner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(BlackListDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlackListDialogLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(BlackListDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(BlackListDialogLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(Add_BlackListButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Remove_BlacklistButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Save_Blacklist)
                        .addGap(51, 51, 51))))
        );

        BlackListDialogLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Add_BlackListButton, Remove_BlacklistButton});

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serializable","ser");
        ExportFileFrame.setFileFilter(filter);
        ExportFileFrame.setAcceptAllFileFilterUsed(false);
        ExportFileFrame.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        ExportFileFrame.setApproveButtonToolTipText("");

        ImportFileFrame.setFileFilter(filter);
        ImportFileFrame.setAcceptAllFileFilterUsed(false);
        ImportFileFrame.setDialogTitle("");

        WarningPopUp.setMinimumSize(new java.awt.Dimension(692, 431));
        WarningPopUp.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        WarningPopUp.setLocationRelativeTo(null);
        WarningPopUp.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                WarningPopUpWindowClosing(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Are you sure you want to delete");

        acceptrem.setText("yes");
        acceptrem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptremActionPerformed(evt);
            }
        });

        denyRem.setText("no");
        denyRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                denyRemActionPerformed(evt);
            }
        });

        RemovedItem.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        RemovedItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout WarningPopUpLayout = new javax.swing.GroupLayout(WarningPopUp.getContentPane());
        WarningPopUp.getContentPane().setLayout(WarningPopUpLayout);
        WarningPopUpLayout.setHorizontalGroup(
            WarningPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WarningPopUpLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(WarningPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WarningPopUpLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WarningPopUpLayout.createSequentialGroup()
                        .addGroup(WarningPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(RemovedItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(WarningPopUpLayout.createSequentialGroup()
                                .addComponent(acceptrem, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(denyRem, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54))))
        );
        WarningPopUpLayout.setVerticalGroup(
            WarningPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WarningPopUpLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RemovedItem, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(WarningPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(denyRem, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(acceptrem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );

        AddGroupDialog.setMinimumSize(new java.awt.Dimension(614, 427));
        AddGroupDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        AddGroupDialog.setResizable(false);
        AddGroupDialog.setLocationRelativeTo(null);
        AddGroupDialog.setType(java.awt.Window.Type.POPUP);
        AddGroupDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                AddGroupDialogWindowClosing(evt);
            }
        });

        groupfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupfieldActionPerformed(evt);
            }
        });

        jLabel13.setText("Groupname: ");

        jScrollPane9.setViewportView(AllGroups_l);

        Save_Group1.setText("Save");
        Save_Group1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_Group1ActionPerformed(evt);
            }
        });

        Remove_GroupButton2.setText("<html>remove<br/>&nbsp&nbsp&nbsp&lt=</html> ");
        Remove_GroupButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remove_GroupButton2ActionPerformed(evt);
            }
        });

        Add_GroupButton1.setText("<html>add<br/> => </html>");
        Add_GroupButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_GroupButton1ActionPerformed(evt);
            }
        });

        jScrollPane10.setViewportView(New_groupL);

        javax.swing.GroupLayout AddGroupDialogLayout = new javax.swing.GroupLayout(AddGroupDialog.getContentPane());
        AddGroupDialog.getContentPane().setLayout(AddGroupDialogLayout);
        AddGroupDialogLayout.setHorizontalGroup(
            AddGroupDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddGroupDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddGroupDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddGroupDialogLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupfield)
                        .addContainerGap())
                    .addGroup(AddGroupDialogLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(AddGroupDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Add_GroupButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Remove_GroupButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Save_Group1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );

        AddGroupDialogLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane10, jScrollPane9});

        AddGroupDialogLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Add_GroupButton1, Remove_GroupButton2, Save_Group1});

        AddGroupDialogLayout.setVerticalGroup(
            AddGroupDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddGroupDialogLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(AddGroupDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(groupfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddGroupDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddGroupDialogLayout.createSequentialGroup()
                        .addComponent(jScrollPane9)
                        .addContainerGap())
                    .addGroup(AddGroupDialogLayout.createSequentialGroup()
                        .addComponent(Add_GroupButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Remove_GroupButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(Save_Group1)
                        .addGap(51, 51, 51))
                    .addGroup(AddGroupDialogLayout.createSequentialGroup()
                        .addComponent(jScrollPane10)
                        .addContainerGap())))
        );

        popup.setMinimumSize(new java.awt.Dimension(692, 431));
        popup.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        popup.setResizable(false);
        popup.setLocationRelativeTo(null);
        popup.setType(java.awt.Window.Type.POPUP);

        popupText.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        popupText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout popupLayout = new javax.swing.GroupLayout(popup.getContentPane());
        popup.getContentPane().setLayout(popupLayout);
        popupLayout.setHorizontalGroup(
            popupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(popupLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, popupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(popupText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        popupLayout.setVerticalGroup(
            popupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(popupLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(popupText, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        WarningPopUp2.setMinimumSize(new java.awt.Dimension(692, 431));
        WarningPopUp2.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        WarningPopUp2.setLocationRelativeTo(null);
        WarningPopUp2.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                WarningPopUp2WindowClosing(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Are you sure you want to delete");
        jLabel14.setMaximumSize(new java.awt.Dimension(202, 17));
        jLabel14.setMinimumSize(new java.awt.Dimension(202, 17));

        acceptremPers.setText("yes");
        acceptremPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptremPersActionPerformed(evt);
            }
        });

        denyRemPers.setText("no");
        denyRemPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                denyRemPersActionPerformed(evt);
            }
        });

        RemovedItemPer.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        RemovedItemPer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout WarningPopUp2Layout = new javax.swing.GroupLayout(WarningPopUp2.getContentPane());
        WarningPopUp2.getContentPane().setLayout(WarningPopUp2Layout);
        WarningPopUp2Layout.setHorizontalGroup(
            WarningPopUp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WarningPopUp2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(WarningPopUp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WarningPopUp2Layout.createSequentialGroup()
                        .addComponent(RemovedItemPer, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(WarningPopUp2Layout.createSequentialGroup()
                        .addComponent(acceptremPers, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(denyRemPers, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WarningPopUp2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        WarningPopUp2Layout.setVerticalGroup(
            WarningPopUp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WarningPopUp2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemovedItemPer, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(WarningPopUp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(denyRemPers, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptremPers, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        ExitDialog.setMinimumSize(new java.awt.Dimension(852, 469));
        ExitDialog.setLocationRelativeTo(null);
        ExitDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ExitDialogWindowClosing(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Do you want to save your changes");

        jButton3.setText("Yes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("No");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ExitDialogLayout = new javax.swing.GroupLayout(ExitDialog.getContentPane());
        ExitDialog.getContentPane().setLayout(ExitDialogLayout);
        ExitDialogLayout.setHorizontalGroup(
            ExitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExitDialogLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );

        ExitDialogLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton5});

        ExitDialogLayout.setVerticalGroup(
            ExitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitDialogLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addGroup(ExitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jButton4.setText("edit party");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Party Details:"));

        Party_Table.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        Party_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Date", null},
                {"Time", null},
                {"Place", null},
                {"Administrator", null},
                {"Minimum Price", null},
                {"Maximum Price", null}
            },
            new String [] {
                "Party:", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Party_Table.setIntercellSpacing(new java.awt.Dimension(1, 5));
        Party_Table.setRowHeight(30);
        Party_Table.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Party_Table, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Party_Table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        if (Party_Table.getColumnModel().getColumnCount() > 0) {
            Party_Table.getColumnModel().getColumn(0).setResizable(false);
            Party_Table.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(353, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Info", jPanel5);

        Person_delete.setText("delete");
        Person_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Person_deleteActionPerformed(evt);
            }
        });

        PersonList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PersonListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(PersonList);

        Person_edit.setText("edit");
        Person_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Person_editActionPerformed(evt);
            }
        });

        BlacklistPButton.setText("blacklist");
        BlacklistPButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlacklistPButtonActionPerformed(evt);
            }
        });

        Person_add.setText("add");
        Person_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Person_addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Person_delete)
                    .addComponent(Person_edit)
                    .addComponent(BlacklistPButton)
                    .addComponent(Person_add))
                .addContainerGap(225, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BlacklistPButton, Person_add, Person_delete, Person_edit});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(Person_delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Person_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Person_edit)
                .addGap(14, 14, 14)
                .addComponent(BlacklistPButton)
                .addGap(166, 166, 166))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Personen", jPanel4);

        jScrollPane5.setViewportView(group_List);

        editGroups.setText("edit");
        editGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editGroupsActionPerformed(evt);
            }
        });

        removeGroup.setText("remove");
        removeGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeGroupActionPerformed(evt);
            }
        });

        addGroup.setText("add");
        addGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeGroup, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addGroup)
                    .addComponent(editGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addGroup, editGroups, removeGroup});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(addGroup)
                        .addGap(29, 29, 29)
                        .addComponent(removeGroup)
                        .addGap(27, 27, 27)
                        .addComponent(editGroups)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Groups", jPanel7);

        pref1_RButton.setText("2 persons can draw each other");
        pref1_RButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pref1_RButtonActionPerformed(evt);
            }
        });

        pref2_RButton.setText("Ignore blacklists");
        pref2_RButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pref2_RButtonActionPerformed(evt);
            }
        });

        pref3_RButton.setText("No Minimum and maximum price");
        pref3_RButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pref3_RButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pref1_RButton)
                    .addComponent(pref2_RButton)
                    .addComponent(pref3_RButton))
                .addContainerGap(489, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(pref1_RButton)
                .addGap(18, 18, 18)
                .addComponent(pref2_RButton)
                .addGap(18, 18, 18)
                .addComponent(pref3_RButton)
                .addContainerGap(424, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pref1_RButton, pref2_RButton, pref3_RButton});

        jTabbedPane1.addTab("Prefrences", jPanel2);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/balloons.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Finish", jPanel8);

        jScrollPane11.setViewportView(Wishlist);

        Wishlist_Add.setText("Add");
        Wishlist_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Wishlist_AddActionPerformed(evt);
            }
        });

        Wishlist_Delete.setText("Delete");
        Wishlist_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Wishlist_DeleteActionPerformed(evt);
            }
        });

        Wishlist_Save.setText("Save");
        Wishlist_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Wishlist_SaveActionPerformed(evt);
            }
        });

        jLabel15.setText("Item:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewItem, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Wishlist_Add, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Wishlist_Delete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Wishlist_Save, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Wishlist_Add, Wishlist_Delete, Wishlist_Save});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(NewItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wishlist_Add))
                        .addGap(67, 67, 67)
                        .addComponent(Wishlist_Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Wishlist_Save))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(98, Short.MAX_VALUE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Admin Whishlist", jPanel3);

        jLabel17.setText("Wishlist of the drawn person:");

        jScrollPane12.setViewportView(WishListDraw);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 531, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Wishlist Drawn", jPanel10);

        jLabel16.setText("This is the person you drew:");

        RevealHide.setText("Reveal");
        RevealHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RevealHideActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        ExportAD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        ExportAD.setText("export");
        ExportAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportADActionPerformed(evt);
            }
        });
        jMenu1.add(ExportAD);

        ImportAd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        ImportAd.setText("import");
        ImportAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportAdActionPerformed(evt);
            }
        });
        jMenu1.add(ImportAd);

        SaveAd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SaveAd.setText("save");
        SaveAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAdActionPerformed(evt);
            }
        });
        jMenu1.add(SaveAd);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PersonName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RevealHide)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PersonName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RevealHide))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jDialog2.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void Person_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Person_editActionPerformed
        if(PersonList.getSelectedValue() != null)
        {
            String name = PersonList.getSelectedValue();
            Person per = PartyManager.party.getAttendee(name);
            updatePartOfGroup(name);
            updateEditPerson(per);
            AddPersonDialog.setVisible(true);
        }
        
    }//GEN-LAST:event_Person_editActionPerformed

    private void Person_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Person_addActionPerformed
        EditB.clear();
        add = true;
        System.out.println("add turned on"); //checkprint
        AddPersonDialog.setVisible(true);
    }//GEN-LAST:event_Person_addActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jDialog2.setVisible(false);
        p.setDate(Date_Input.getText());
        p.setHour(Time_Input.getText());
        p.setPlace(Place_Input.getText());
        p.setMaxPrice(Integer.parseInt(Max_Input.getText()));
        p.setMinPrice(Integer.parseInt(Min_Input.getText()));
        updateParty();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Min_InputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Min_InputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Min_InputActionPerformed

    private void pref2_RButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pref2_RButtonActionPerformed
        if(pref2_RButton.isSelected())
        {
            PartyManager.partyDetails.setBlacklistEnabled(false);
        }
        else
        {
            PartyManager.partyDetails.setBlacklistEnabled(true);
        }
    }//GEN-LAST:event_pref2_RButtonActionPerformed

    private void pref3_RButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pref3_RButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) Party_Table.getModel();
        if(pref3_RButton.isSelected())
        {
            HidePartyRow(model,5);
            HidePartyRow(model,4);
        }
        else
        {
            RevealPartyRow(model,4);
            RevealPartyRow(model,5);
            updateParty();
        }
    }//GEN-LAST:event_pref3_RButtonActionPerformed

    private void pref1_RButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pref1_RButtonActionPerformed
        if(pref1_RButton.isSelected())
        {
            PartyManager.partyDetails.setEachOther(true);
        }
        else
        {
            PartyManager.partyDetails.setEachOther(false);
        }
    }//GEN-LAST:event_pref1_RButtonActionPerformed

    private void Person_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Person_saveActionPerformed
    if(!Edit_PersonName.getText().equals(""))
    {
        Person member;
        //int index;
        //PersonList.setModel(persons);
        String name = Edit_PersonName.getText();
        member = new Person(name,false);
        ArrayList<String> memberbl = new ArrayList<>();
        for(int i=0;i<EditB.getSize();i++)
            memberbl.add(EditB.getElementAt(i).toString());
        AddPersonDialog.setVisible(false);
        if(add == true)
        {
            persons.addElement(name);
            member.setBlacklistP(memberbl);
            PartyManager.party.addPartyMember(member);
            add = false;
            System.out.println("add turned off"); //checkprint
        }
        else
        {
            indexP = PersonList.getSelectedIndex();
            persons.setElementAt(name, indexP);
            PartyManager.party.getAttendee(name).setBlacklistP(memberbl);
        }
    }
    else
    {
        popupText.setText("A person needs a name");
        popup.setVisible(true);
    }
        Edit_PersonName.setText("");
        EditB.removeAllElements();
    }//GEN-LAST:event_Person_saveActionPerformed

    private void Edit_PersonNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Edit_PersonNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Edit_PersonNameActionPerformed

    private void Person_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Person_deleteActionPerformed
       if(PersonList.getSelectedValue() != null)
       {
           if(!PartyManager.partyDetails.getAdmin().equalsIgnoreCase(PersonList.getSelectedValue()))
           {
            RemovedItemPer.setText(PersonList.getSelectedValue());
            WarningPopUp2.setVisible(true);
           }
           else
           {
               popupText.setText("You cannot delete the Admin");
               popup.setVisible(true);
           }
        }
        
       
    }//GEN-LAST:event_Person_deleteActionPerformed

    private void Add_PBlackListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_PBlackListActionPerformed
       B_Owner.setText(Edit_PersonName.getText());
       setupBlackList(Edit_PersonName.getText());
    }//GEN-LAST:event_Add_PBlackListActionPerformed

    private void Add_BlackListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_BlackListButtonActionPerformed
       if(BlackList_all.getSelectedValue() != null && (BlackLists.getSize() != 1))
       {
            BlackList_P.setModel(BlackListEdit);
            int adding = BlackList_all.getSelectedIndex();
            String name = BlackList_all.getSelectedValue();
            BlackListEdit.addElement(name);
            BlackLists.removeElementAt(adding);
       } 
       else
        {
            popupText.setText("You can't blacklist everyone");
            popup.setVisible(true);
                
        }
    }//GEN-LAST:event_Add_BlackListButtonActionPerformed

    private void BlacklistPButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlacklistPButtonActionPerformed
        if(PersonList.getSelectedValue() != null)
        {
        B_Owner.setText(PersonList.getSelectedValue());
        setupBlackList(PersonList.getSelectedValue());
        }
    }//GEN-LAST:event_BlacklistPButtonActionPerformed

    private void Remove_BlacklistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remove_BlacklistButtonActionPerformed
        if(BlackList_P.getSelectedValue() != null)
        {
            int removing = BlackList_P.getSelectedIndex();
            String name = BlackList_P.getSelectedValue();
            BlackLists.addElement(name);
            BlackListEdit.removeElementAt(removing);
        }
    }//GEN-LAST:event_Remove_BlacklistButtonActionPerformed

    private void Save_BlacklistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_BlacklistActionPerformed
        EditB.removeAllElements();
        for(int k=0;k<BlackListEdit.size();k++)
        {
                EditB.addElement(BlackListEdit.elementAt(k));
        }
        Edit_PBlackList.setModel(EditB);
        BlackListDialog.setVisible(false);
        for(int i=0;i<BlackListEdit.size();i++)
        {
            if(!AddPersonDialog.isVisible())
            {
                String name = PersonList.getSelectedValue();
                Person per = PartyManager.party.getAttendee(name);
                ArrayList<String> black = per.getBlacklistP();
                for(Iterator<String> it = black.iterator(); it.hasNext();)
                {
                    name = it.next();
                    if(!BlackListEdit.contains(name))
                        it.remove(); 
                }
                for(int j=0; j<BlackListEdit.getSize();j++)
                {
                    if(!black.contains(BlackListEdit.getElementAt(j).toString()))
                        black.add(BlackListEdit.getElementAt(j).toString());
                }
                PartyManager.party.getAttendee(name).setBlacklistP(black);
            }
        }
        if(BlackListEdit.size() == 0 && !AddPersonDialog.isVisible() )
        {
            String name = PersonList.getSelectedValue();
                Person per = PartyManager.party.getAttendee(name);
                ArrayList<String> black = per.getBlacklistP();
                for(Iterator<String> it = black.iterator(); it.hasNext();)
                {
                    name = it.next();
                    if(!BlackListEdit.contains(name))
                        it.remove(); 
                }
        }
        //DefaultListModel clear = (DefaultListModel) BlackList_all.getModel();
        //clear.removeAllElements();
        BlackLists.removeAllElements();
        BlackListEdit.removeAllElements();
    }//GEN-LAST:event_Save_BlacklistActionPerformed

    private void AddPersonDialogWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_AddPersonDialogWindowClosing
        add = false;
        Edit_PersonName.setText("");
        EditB.removeAllElements();
        System.out.println("add turned off"); //checkprint
    }//GEN-LAST:event_AddPersonDialogWindowClosing

    private void BlackListDialogWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_BlackListDialogWindowClosing
        BlackLists.removeAllElements();
        BlackListEdit.removeAllElements();
    }//GEN-LAST:event_BlackListDialogWindowClosing

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        PartyManager.groups.forEach((grp) -> {
            grp.groupToBlacklistP();
        });
        List temp = new List();
        temp = action.divide(PartyManager.party);
        if(temp != null)
        {
            PartyManager.party = temp;
            popupText.setText("Matching succesfull");
            popup.setVisible(true);
            action.exportPassword();
        }
        else
        {
            popupText.setText("Unable to match");
            popup.setVisible(true);
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void ExportADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportADActionPerformed
        int result = ExportFileFrame.showSaveDialog(this);
        if(result == JFileChooser.APPROVE_OPTION)
        {
             File file = ExportFileFrame.getSelectedFile();
             String path = file.getAbsolutePath();
        if(path.contains("."))
        {
        String[] check = path.split("\\.");
        String test = check[0];
        path = test;
        }
        
         path = path.concat(".ser");
        action.ExportFile(path,PartyManager.party.getAttendees());
        }
        ImportFileFrame.setSelectedFile(new File(""));
    }//GEN-LAST:event_ExportADActionPerformed

    private void SaveAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAdActionPerformed
        action.saveFile(PartyManager.party.getAttendees());
        
    }//GEN-LAST:event_SaveAdActionPerformed

    private void ImportAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportAdActionPerformed
        int result = ImportFileFrame.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File file = ImportFileFrame.getSelectedFile();
            action.ImportFile(file);
            updateAllpersons();
        }
        ImportFileFrame.setSelectedFile(new File(""));
        
    }//GEN-LAST:event_ImportAdActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int pane = jTabbedPane1.getSelectedIndex();
        switch(pane)
        {
            case 0: updateParty();
                    updateAllpersons();
                    break;
            case 1: updateAllpersons();
                    break;
            case 2: updateAllgroups();
                    updateAllpersons();
                    break;
            case 5: updateWishlist(); break;
            
            case 6: if(PartyManager.party.getAttendee(PartyManager.partyDetails.getAdmin()).hasChosen())
                        updateWishlistDrawn(); 
                    break;
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void editGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editGroupsActionPerformed
        if(group_List.getSelectedValue() != null)
        {
        groupfield.setText(group_List.getSelectedValue());
//        for(Group grp: PartyManager.groups)
//        {
//            if(grp.getName().equals(group_List.getSelectedValue()))
//            {
//                groupExists = true;
//            }
//        }
        groupExists = true;
        indexG = group_List.getSelectedIndex();
        editGroup(group_List.getSelectedValue());
        
        
        }
    }//GEN-LAST:event_editGroupsActionPerformed

    private void addGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGroupActionPerformed
        //updateAllpersons();
        groupfield.setText("");
        editGroup("");
    }//GEN-LAST:event_addGroupActionPerformed

    private void removeGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGroupActionPerformed
        if(group_List.getSelectedValue() != null)
        {
            RemovedItem.setText(group_List.getSelectedValue());
            WarningPopUp.setVisible(true);
        }
    }//GEN-LAST:event_removeGroupActionPerformed

    private void acceptremActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptremActionPerformed
        int remove = group_List.getSelectedIndex();
        Iterator<Group> it = PartyManager.groups.iterator();
        while(it.hasNext()) 
        {
            if(it.next().getName().equals(group_List.getSelectedValue()))
                it.remove();
                
        }
        selectG.remove(remove);       
        WarningPopUp.setVisible(false);
    }//GEN-LAST:event_acceptremActionPerformed

    private void Save_Group1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_Group1ActionPerformed
        if(!groupfield.getText().equals(""))
        {  
        Group group = new Group(groupfield.getText());
        for(int i =0;i<newG.size();i++)
        {
            group.addMember(newG.get(i).toString());
        }   
        if(groupExists == true)
        {  
            for(int i =0;i<PartyManager.groups.size();i++)
            {                
                if(PartyManager.groups.get(i).getName().equals((selectG.getElementAt(indexG).toString())))
                {
                    PartyManager.groups.get(i).setName(groupfield.getText());
                    for(int j = 0;j<group.getMembers().size();j++)
                    {
                        String name = group.getMembers().get(j);
                        if(!PartyManager.groups.get(i).getMembers().contains(name))
                                PartyManager.groups.get(i).addMember(name);
                    }
                    for(int j=PartyManager.groups.get(i).getMembers().size();j>0;j--)
                    {
                        String name2 = PartyManager.groups.get(i).getMembers().get(j-1);
                        if(!group.getMembers().contains(name2))
                            PartyManager.groups.get(i).removeMember(name2);
                    }
//                   for(String name : group.getMembers())
//                   {
//                       if(!grp.getMembers().contains(name))
//                           grp.addMember(name);
//                   }
                }
            }
            selectG.setElementAt(groupfield.getText(),indexG);
            
        }
        else
        {
            for(Group grp : PartyManager.groups)
            {
                if(grp.getName().equalsIgnoreCase(groupfield.getText()))
                   groupExists = true;
            }
                if(!groupExists)
                {
                    PartyManager.groups.add(group);
                    selectG.addElement(groupfield.getText());
                }
                else
                {
                    popupText.setText("You can't add two groups with the same name");
                    popup.setVisible(true);
                }
        }
        
        groupExists = false;
        newG.removeAllElements();
        AllpersGroup.removeAllElements();
        updateAllpersons();
        AddGroupDialog.setVisible(false);
        }
    }//GEN-LAST:event_Save_Group1ActionPerformed

    private void Remove_GroupButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remove_GroupButton2ActionPerformed
        if(New_groupL.getSelectedValue() != null && (newG.getSize() != 1))
        {
            int removing = New_groupL.getSelectedIndex();
            String name = New_groupL.getSelectedValue();
            AllpersGroup.addElement(name);      
            newG.removeElementAt(removing);
        }
        else
        {
            popupText.setText("You can't have an empty group");
            popup.setVisible(true);
                
        }
    }//GEN-LAST:event_Remove_GroupButton2ActionPerformed

    private void Add_GroupButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_GroupButton1ActionPerformed
        if(AllGroups_l.getSelectedValue() != null && (AllpersGroup.getSize() != 1))
       {
            //New_groupL.setModel(newG);
            int adding = AllGroups_l.getSelectedIndex();
            String name = AllGroups_l.getSelectedValue();
            newG.addElement(name);
            AllpersGroup.removeElementAt(adding);
            
       } 
        else
         {
            popupText.setText("You can't have everybody in one group");
            popup.setVisible(true);
                
        }   
    }//GEN-LAST:event_Add_GroupButton1ActionPerformed

    private void denyRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_denyRemActionPerformed
        WarningPopUp.setVisible(false);
    }//GEN-LAST:event_denyRemActionPerformed

    private void groupfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupfieldActionPerformed
        
    }//GEN-LAST:event_groupfieldActionPerformed

    private void WarningPopUpWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_WarningPopUpWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_WarningPopUpWindowClosing

    private void AddGroupDialogWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_AddGroupDialogWindowClosing
        groupExists = false;
        newG.removeAllElements();
        AllpersGroup.removeAllElements();
        updateAllpersons();
    }//GEN-LAST:event_AddGroupDialogWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        popup.setVisible(false);
        popupText.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void acceptremPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptremPersActionPerformed
        String name = PersonList.getSelectedValue();
        indexP = PersonList.getSelectedIndex();
        persons.remove(indexP);
        PartyManager.party.deletepartyMember(name);
        Set<String> k = PartyManager.party.getAttendees().keySet();
        Iterator<String> it = k.iterator();
        while(it.hasNext())
        {
            Person p = PartyManager.party.getAttendee(it.next());
            if(p.getBlacklistP().contains(name))
                p.getBlacklistP().remove(name);
        }
        for(int i = 0;i<PartyManager.groups.size();i++)
        {
            if(PartyManager.groups.get(i).containsMember(name))
                PartyManager.groups.get(i).removeMember(name);
        }
        WarningPopUp2.setVisible(false);
    }//GEN-LAST:event_acceptremPersActionPerformed

    private void denyRemPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_denyRemPersActionPerformed
        WarningPopUp2.setVisible(false);
    }//GEN-LAST:event_denyRemPersActionPerformed

    private void WarningPopUp2WindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_WarningPopUp2WindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_WarningPopUp2WindowClosing

    private void Wishlist_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Wishlist_AddActionPerformed
        if(NewItem.getText() != null || NewItem.getText() != "") {
            Wishlist.setModel(WishlistAd);
            String item = NewItem.getText();
            person.AddWhishlistItem(item);
            WishlistAd.addElement(item);
        }
        NewItem.setText("");
    }//GEN-LAST:event_Wishlist_AddActionPerformed

    private void Wishlist_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Wishlist_DeleteActionPerformed
        if(Wishlist.getSelectedValue() != null)
        {
            //Wishlist.setModel(WishListEdit);
            int removing = Wishlist.getSelectedIndex();
            String name = Wishlist.getSelectedValue();
            WishlistAd.removeElementAt(removing);
            person.RemoveWhishlistItem(name);
        }
    }//GEN-LAST:event_Wishlist_DeleteActionPerformed

    private void Wishlist_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Wishlist_SaveActionPerformed
        ArrayList<String> temp = PartyManager.party.getAttendee(PartyManager.partyDetails.getAdmin()).getWhishlist();
        for(int i = 0;i<WishlistAd.size();i++)
        {
            boolean check = true;
            for(String x : temp)
            {
                if(x.equalsIgnoreCase(WishlistAd.getElementAt(i).toString()));
                    check = false;
            }
            if(check == true)
                PartyManager.party.getAttendee(p.getAdmin()).AddWhishlistItem(WishlistAd.getElementAt(i).toString());
        }
        Iterator<String> it = temp.iterator();
        while(it.hasNext())
        {
            String item = it.next();
            if(!WishlistAd.contains(item))
                it.remove();
        }           
        NewItem.setText("");
        action.saveFile(PartyManager.party.getAttendees());
    }//GEN-LAST:event_Wishlist_SaveActionPerformed

    private void RevealHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RevealHideActionPerformed
        if (RevealHide.isSelected()) {
            PersonName.setText(PartyManager.party.getAttendee(p.getAdmin()).getChosen());
            PersonName.setVisible(true);
            RevealHide.setText("Hide");
        }
        else {
            PersonName.setVisible(false);
            RevealHide.setText("Reveal");
        }
    }//GEN-LAST:event_RevealHideActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ExitDialog.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        action.saveFile(PartyManager.party.getAttendees());
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ExitDialogWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ExitDialogWindowClosing
        this.setVisible(true);
    }//GEN-LAST:event_ExitDialogWindowClosing

    private void PersonListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PersonListMouseClicked
        indexP = PersonList.getSelectedIndex();
    }//GEN-LAST:event_PersonListMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AddGroupDialog;
    private javax.swing.JDialog AddPersonDialog;
    private javax.swing.JButton Add_BlackListButton;
    private javax.swing.JButton Add_GroupButton1;
    private javax.swing.JButton Add_PBlackList;
    private javax.swing.JList<String> AllGroups_l;
    private javax.swing.JLabel B_Owner;
    private javax.swing.JDialog BlackListDialog;
    private javax.swing.JList<String> BlackList_P;
    private javax.swing.JList<String> BlackList_all;
    private javax.swing.JButton BlacklistPButton;
    private javax.swing.JTextField Date_Input;
    private javax.swing.JList<String> Edit_PBlackList;
    private javax.swing.JList<String> Edit_PGroups;
    private javax.swing.JTextField Edit_PersonName;
    private javax.swing.JDialog ExitDialog;
    private javax.swing.JMenuItem ExportAD;
    private javax.swing.JFileChooser ExportFileFrame;
    private javax.swing.JMenuItem ImportAd;
    private javax.swing.JFileChooser ImportFileFrame;
    private javax.swing.JTextField Max_Input;
    private javax.swing.JTextField Min_Input;
    private javax.swing.JTextField NewItem;
    private javax.swing.JList<String> New_groupL;
    private javax.swing.JTable Party_Table;
    private javax.swing.JPanel Party_det;
    private javax.swing.JList<String> PersonList;
    private javax.swing.JLabel PersonName;
    private javax.swing.JButton Person_add;
    private javax.swing.JButton Person_delete;
    private javax.swing.JButton Person_edit;
    private javax.swing.JButton Person_save;
    private javax.swing.JTextField Place_Input;
    private javax.swing.JButton Remove_BlacklistButton;
    private javax.swing.JButton Remove_GroupButton2;
    private javax.swing.JLabel RemovedItem;
    private javax.swing.JLabel RemovedItemPer;
    private javax.swing.JToggleButton RevealHide;
    private javax.swing.JMenuItem SaveAd;
    private javax.swing.JButton Save_Blacklist;
    private javax.swing.JButton Save_Group1;
    private javax.swing.JTextField Time_Input;
    private javax.swing.JDialog WarningPopUp;
    private javax.swing.JDialog WarningPopUp2;
    private javax.swing.JList<String> WishListDraw;
    private javax.swing.JList<String> Wishlist;
    private javax.swing.JButton Wishlist_Add;
    private javax.swing.JButton Wishlist_Delete;
    private javax.swing.JButton Wishlist_Save;
    private javax.swing.JButton acceptrem;
    private javax.swing.JButton acceptremPers;
    private javax.swing.JButton addGroup;
    private javax.swing.JButton denyRem;
    private javax.swing.JButton denyRemPers;
    private javax.swing.JButton editGroups;
    private javax.swing.JList<String> group_List;
    private javax.swing.JTextField groupfield;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JDialog popup;
    private javax.swing.JLabel popupText;
    private javax.swing.JRadioButton pref1_RButton;
    private javax.swing.JRadioButton pref2_RButton;
    private javax.swing.JRadioButton pref3_RButton;
    private javax.swing.JButton removeGroup;
    // End of variables declaration//GEN-END:variables
}
