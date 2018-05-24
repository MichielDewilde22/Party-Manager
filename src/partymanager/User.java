/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partymanager;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 * Defines the User GUI
 * @author Andreas Durt, Michiel Dewilde
 */
public class User extends javax.swing.JFrame {
    private Party p = PartyManager.partyDetails;
    private List party = PartyManager.party;
    private String name;
    private Person person;
    private Action a = new Action();
    private ArrayList<String> wish = new ArrayList<>();
    private DefaultListModel WishLists = new DefaultListModel();
    private DefaultListModel WishListEdit = new DefaultListModel();
    private DefaultListModel WishListDrawn = new DefaultListModel();
    /**
     * Creates a new form of User with the details of the given person
     * @param name name of the person who is accesing the gui
     */
    public User(String name) {
        person = party.getAttendee(name);
        initComponents();
        if (person.getPinChanged()==false) {
            ChangePin.setVisible(true); //Users must change their pincode if not already changed!
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ChangePin = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ChangeButton = new javax.swing.JButton();
        NewPin = new javax.swing.JPasswordField();
        ExitDialog = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Party_Table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Wishlist = new javax.swing.JList<>();
        Wishlist_Add = new javax.swing.JButton();
        Wishlist_Delete = new javax.swing.JButton();
        Wishlist_Save = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        NewItem = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        WishListDraw = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        PersonName = new javax.swing.JLabel();
        RevealHide = new javax.swing.JToggleButton();

        ChangePin.setTitle("Change pincode");
        ChangePin.setMinimumSize(new java.awt.Dimension(350, 250));
        ChangePin.setLocationRelativeTo(null);
        ChangePin.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        ChangePin.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ChangePinWindowClosing(evt);
            }
        });

        jLabel3.setText("Please change your pincode!");

        jLabel5.setText("New pin:");

        ChangeButton.setText("Change");
        ChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ChangePinLayout = new javax.swing.GroupLayout(ChangePin.getContentPane());
        ChangePin.getContentPane().setLayout(ChangePinLayout);
        ChangePinLayout.setHorizontalGroup(
            ChangePinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChangePinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChangePinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ChangePinLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ChangePinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ChangeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NewPin)))
                    .addComponent(jLabel3))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        ChangePinLayout.setVerticalGroup(
            ChangePinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChangePinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ChangePinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(NewPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ChangeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        ExitDialog.setMinimumSize(new java.awt.Dimension(852, 469));
        ExitDialog.setLocationRelativeTo(null);
        ExitDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ExitDialogWindowClosing(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Do you want to save your changes");

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
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExitDialogLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );

        ExitDialogLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton5});

        ExitDialogLayout.setVerticalGroup(
            ExitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitDialogLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addGroup(ExitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(587, 319));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Party Details:"));
        jPanel3.setMinimumSize(new java.awt.Dimension(500, 250));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 250));

        Party_Table.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        Party_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {" Date", null},
                {" Time", null},
                {" Place", null},
                {" Administrator", null},
                {" Minimum Price", null},
                {" Maximum Price", null}
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
        Party_Table.setRowHeight(30);
        Party_Table.setRowMargin(5);
        Party_Table.setRowSelectionAllowed(false);
        Party_Table.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Party_Table, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Party_Table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Info", jPanel1);

        jScrollPane2.setViewportView(Wishlist);

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

        jLabel4.setText("Item:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewItem, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Wishlist_Add)
                    .addComponent(Wishlist_Save)
                    .addComponent(Wishlist_Delete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Wishlist_Add, Wishlist_Delete, Wishlist_Save});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Wishlist_Delete)
                .addGap(75, 75, 75)
                .addComponent(Wishlist_Save)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(NewItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Wishlist_Add))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Whishlist", jPanel2);

        jLabel2.setText("Wishlist of the drawn person:");

        jScrollPane1.setViewportView(WishListDraw);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 253, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Drawn Person", jPanel4);

        jLabel1.setText("This is the person you drew:");

        RevealHide.setText("Reveal");
        RevealHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RevealHideActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PersonName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RevealHide)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PersonName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RevealHide))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Updates the defaultlistmodel of the partydetails list
     */
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
    /**
     * updates the defaultlistmodel of the whishlist of the person list
     */
    public void updateWishlist() {
        WishLists.clear();
        WishListEdit.clear();
        Wishlist.setModel(WishListEdit);
        wish = person.getWhishlist();
        for (String item : wish) {
            WishListEdit.addElement(item);
        }
        
    }
    /**
     * updates the defaultlistmodel of the whishlist of the person for who you need to buy a present list
     */
    public void updateWishlistDrawn() {
        if (person.getChosen()!=null) {
            WishListDrawn.clear();
            WishListDraw.setModel(WishListDrawn);
            wish = PartyManager.party.getAttendee(person.getChosen()).getWhishlist();
            for (String item : wish) {
                WishListDrawn.addElement(item);
            }
            
        }
    }
    
    private void Wishlist_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Wishlist_AddActionPerformed
        if(NewItem.getText() != null || NewItem.getText() != "") {
            Wishlist.setModel(WishListEdit);
            String item = NewItem.getText();
            person.AddWhishlistItem(item);
            WishListEdit.addElement(item);
        }
        NewItem.setText("");
    }//GEN-LAST:event_Wishlist_AddActionPerformed

    private void Wishlist_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Wishlist_DeleteActionPerformed
        if(Wishlist.getSelectedValue() != null)
        {
            int removing = Wishlist.getSelectedIndex();
            String name = Wishlist.getSelectedValue();
            WishListEdit.removeElementAt(removing);
            person.RemoveWhishlistItem(name);
        }
    }//GEN-LAST:event_Wishlist_DeleteActionPerformed

    private void Wishlist_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Wishlist_SaveActionPerformed
       ArrayList<String> temp = person.getWhishlist();
        for(int i = 0;i<WishListEdit.size();i++)
       {
           Boolean check = true;
           for(String x: temp)
           {
               if(x.equalsIgnoreCase(WishListEdit.getElementAt(i).toString()));
                    check = false;
           }
           if(check == true)
            person.AddWhishlistItem(WishListEdit.getElementAt(i).toString());
       }
        Iterator<String> it = temp.iterator();
        while(it.hasNext())
        {
            String item = it.next();
            if(!WishListEdit.contains(item))
                it.remove();
        }
       NewItem.setText("");
       a.saveFile(PartyManager.party.getAttendees());
    }//GEN-LAST:event_Wishlist_SaveActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int pane = jTabbedPane1.getSelectedIndex();
        switch(pane)
        {
            case 0: updateParty(); break;
            case 1: updateWishlist(); break;
            case 2: updateWishlistDrawn(); break;
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void RevealHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RevealHideActionPerformed
        if (RevealHide.isSelected()) {
            PersonName.setText(person.getChosen());
            PersonName.setVisible(true);
            RevealHide.setText("Hide");
        }
        else {
            PersonName.setVisible(false);
            RevealHide.setText("Reveal");
        }
    }//GEN-LAST:event_RevealHideActionPerformed

    private void ChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeButtonActionPerformed
        String pin = NewPin.getText();
        person.changePin(pin);
        ChangePin.setVisible(false);
    }//GEN-LAST:event_ChangeButtonActionPerformed

    private void ChangePinWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ChangePinWindowClosing
        System.exit(0);
    }//GEN-LAST:event_ChangePinWindowClosing

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ExitDialog.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        a.saveFile(PartyManager.party.getAttendees());
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void ExitDialogWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ExitDialogWindowClosing
        this.setVisible(true);
    }//GEN-LAST:event_ExitDialogWindowClosing

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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User("test").setVisible(true);
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChangeButton;
    private javax.swing.JDialog ChangePin;
    private javax.swing.JDialog ExitDialog;
    private javax.swing.JTextField NewItem;
    private javax.swing.JPasswordField NewPin;
    private javax.swing.JTable Party_Table;
    private javax.swing.JLabel PersonName;
    private javax.swing.JToggleButton RevealHide;
    private javax.swing.JList<String> WishListDraw;
    private javax.swing.JList<String> Wishlist;
    private javax.swing.JButton Wishlist_Add;
    private javax.swing.JButton Wishlist_Delete;
    private javax.swing.JButton Wishlist_Save;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
