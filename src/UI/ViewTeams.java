/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Console.Trace;
import Data.DAL;
import Data.MemberTableModel;
import Data.ParseClass;
import Objects.Guild;
import Objects.Player;
import Objects.Staff;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * View Guild and their Members
 * @author Jessica Moolenschot
 */
public class ViewTeams extends javax.swing.JFrame implements ListSelectionListener, WindowListener
{
    DAL database;
    MemberTableModel m;
    TableColumnModel view;
    /**
     * Creates new form ViewTeams
     */
    public ViewTeams()
    {
        
        ImageIcon i = new ImageIcon("./src/img/ff14icon.jpg");
        this.setIconImage(i.getImage());
        initComponents();
        warnGuildList.setVisible(false);
        database = new DAL();
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        ArrayList<Guild> guilds = database.getGuilds();
        for(Guild g : guilds)
            combo.addElement(g);
        

        ddlTeams.setModel(combo);
        m = new MemberTableModel();
        tableTeams.setModel(m);
        tableTeams.updateUI();
        tableTeams.getSelectionModel().addListSelectionListener(this);
        if(!guilds.isEmpty())
        {
            ddlTeams.setEnabled(true);
            viewTeams();
        }
        else
            warnGuildList.setVisible(true);
                /*ResultSet r = database.executeQuery("SELECT * FROM guild");
        try
        {
            tableTeams.setModel(buildTableModel(r));
        }
        catch(Exception e)
        {
            Trace.error("uh oh");
        }
        tableTeams.updateUI();*/
        tableTeams.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ParseClass p = new ParseClass();
        tableTeams.getColumnModel().getColumn(5).setCellRenderer(p);
        resizeTable();
        
    }
    /**
     * Update teamTable based on selected team
     */
    public void viewTeams()
    {
        Guild temp = (Guild)ddlTeams.getSelectedItem();
        m.getMembers(temp.getGuildID());
        tableTeams.updateUI();
        resizeTable();
    }
    /**
     * Resizes the JTable Cells based on their content
     */
    public void resizeTable()
    {
        //https://stackoverflow.com/questions/17627431/auto-resizing-the-jtable-column-widths
        view = tableTeams.getColumnModel();
        for(int column = 0; column < tableTeams.getColumnCount(); column ++)//resizes the cells based on the content and header widths
        {
            int width = 100; //min width
            for(int row = 0; row < tableTeams.getRowCount(); row++)
            {
                TableCellRenderer renderer = tableTeams.getCellRenderer(row,column);
                Component c = tableTeams.prepareRenderer(renderer,row,column);
                width = Math.max(width, c.getPreferredSize().width);//tableTeams.getColumnModel().getColumn(column).getPreferredWidth());
                if(column == 5)
                {
                    int height = tableTeams.getRowHeight(row);
                    height = Math.max(20, c.getPreferredSize().height);
                    tableTeams.setRowHeight(row,height);
                }
            }
            view.getColumn(column).setPreferredWidth(width);
        }
        

    }
    @Override
    /**
     * Fires when user selects a different row, enables the edit/delete button
     */
    public void valueChanged(ListSelectionEvent e)
    {
        //https://stackoverflow.com/questions/375265/jtable-selection-change-event-handling-find-the-source-table-dynamically
        if(!e.getValueIsAdjusting())
        {
            Trace.log("row selected");
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        Banner = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTeams = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        ddlTeams = new javax.swing.JComboBox<>();
        warnGuildList = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Guilds");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        Banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Banner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Banner.jpg"))); // NOI18N
        Banner.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Banner.setDoubleBuffered(true);
        Banner.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        jPanel1.add(Banner, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Yu Gothic Light", 0, 24)); // NOI18N
        jLabel1.setText("View Guilds");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(250, 200));

        tableTeams.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        tableTeams.setMinimumSize(new java.awt.Dimension(0, 478));
        tableTeams.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableTeams.getTableHeader().setReorderingAllowed(false);
        tableTeams.addInputMethodListener(new java.awt.event.InputMethodListener()
        {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt)
            {
                tableTeamsCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt)
            {
            }
        });
        jScrollPane1.setViewportView(tableTeams);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 500;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 15);
        jPanel1.add(btnDelete, gridBagConstraints);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 45, 0, 0);
        jPanel1.add(btnCancel, gridBagConstraints);

        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(btnEdit, gridBagConstraints);

        ddlTeams.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><i>There are no guilds!</i></html>" }));
        ddlTeams.setEnabled(false);
        ddlTeams.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ddlTeamsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 0);
        jPanel1.add(ddlTeams, gridBagConstraints);

        warnGuildList.setFont(new java.awt.Font("Yu Gothic UI", 2, 11)); // NOI18N
        warnGuildList.setForeground(new java.awt.Color(255, 51, 51));
        warnGuildList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warnGuildList.setText("No guilds found!");
        warnGuildList.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(warnGuildList, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Load newly selected team
     * @param evt 
     */
    private void ddlTeamsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ddlTeamsActionPerformed
    {//GEN-HEADEREND:event_ddlTeamsActionPerformed
        viewTeams();
        tableTeams.clearSelection();
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_ddlTeamsActionPerformed
    /**
     * Close window
     * @param evt 
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelActionPerformed
    {//GEN-HEADEREND:event_btnCancelActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tableTeamsCaretPositionChanged(java.awt.event.InputMethodEvent evt)//GEN-FIRST:event_tableTeamsCaretPositionChanged
    {//GEN-HEADEREND:event_tableTeamsCaretPositionChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tableTeamsCaretPositionChanged
    /**
     * Show the EditMember form, pass the current memberID to it
     * @param evt 
     */
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEditActionPerformed
    {//GEN-HEADEREND:event_btnEditActionPerformed
        
        
        int memberID = Integer.parseInt(tableTeams.getModel().getValueAt(tableTeams.getSelectedRow(), 0).toString());//ONCE AGAIN WORKED FIRST TRY????
        EditMember a = new EditMember(memberID);
        a.setLocationRelativeTo(this);
        a.addWindowListener(this);
        this.setEnabled(false);
        a.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed
/**
 * Delete selected Member from the database
 * @param evt 
 */
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDeleteActionPerformed
    {//GEN-HEADEREND:event_btnDeleteActionPerformed
        database.connect();
        Object o = m.getMemberByRow(tableTeams.getSelectedRow());
        int option = JOptionPane.showConfirmDialog(this, "Delete member " +  o.toString(), "Confirm delete", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        
        if(option == JOptionPane.YES_OPTION)
        {
            int memberID = o.getClass() == Player.class ? ((Player)o).getMemberID() : ((Staff)o).getMemberID();
            database.deleteMember(memberID);
            JOptionPane.showMessageDialog(this, "Deleted member successfully","Deleted",JOptionPane.INFORMATION_MESSAGE);
            viewTeams();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Cancelled deletion","Cancelled",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    @Override
        /**
     * Re-enable this window when the child window closes and set the Guild JComboBox to the last value
     */
    public void windowClosed(WindowEvent e)
    {
        this.setEnabled(true);
        this.setVisible(true);
        viewTeams();
    }
    @Override
    public void windowDeactivated(WindowEvent e){}
    @Override
    public void windowActivated(WindowEvent e){}
    @Override
    public void windowDeiconified(WindowEvent e){}
    @Override
    public void windowIconified(WindowEvent e){}
    @Override
    public void windowClosing(WindowEvent e){}
    @Override public void windowOpened(WindowEvent e){}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Banner;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> ddlTeams;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTeams;
    private javax.swing.JLabel warnGuildList;
    // End of variables declaration//GEN-END:variables
}
