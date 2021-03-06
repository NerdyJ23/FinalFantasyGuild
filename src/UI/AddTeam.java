/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.DAL;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import Data.ServerList;
import Objects.Guild;
import Validation.ValidateInputs;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *  Form for adding a new Guild to the database
 * @author jessi
 */
public class AddTeam extends javax.swing.JFrame implements ItemListener
{
    private DAL database;
    private final String[][] DATA_CENTER = {
        {"Elemental","Gaia","Mana"}, //japan
        {"Aether","Primal","Crystal"}, //america
        {"Chaos","Light"} //europe
    };
    /**
     * Initializes the AddTeam form
     */
    public AddTeam()
    {
        ImageIcon i = new ImageIcon("./src/img/ff14icon.jpg");
        this.setIconImage(i.getImage());
        database = new DAL();
        initComponents();
        regionGroup.add(rdAmerica);
        regionGroup.add(rdEurope);
        regionGroup.add(rdJapan);
        
        rdJapan.addItemListener(this);
        rdAmerica.addItemListener(this);
        rdEurope.addItemListener(this);
        
        toggleWarnings(false);
    }
    /**
     * Toggle warning text on/off
     * @param visible setWarningsVisible
     */
    public void toggleWarnings(boolean visible)
    {
        warnName.setVisible(visible);
        warnRegion.setVisible(visible);
        warnServer.setVisible(visible);
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

        regionGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Banner = new javax.swing.JLabel();
        txtGuildName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverList = new javax.swing.JList<>();
        ddlDataCenter = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rdJapan = new javax.swing.JRadioButton();
        rdAmerica = new javax.swing.JRadioButton();
        rdEurope = new javax.swing.JRadioButton();
        chkPVP = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        warnName = new javax.swing.JLabel();
        warnRegion = new javax.swing.JLabel();
        warnServer = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Guild");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frame22"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 15, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        Banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Banner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/grand-company-banner.jpg"))); // NOI18N
        Banner.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Banner.setDoubleBuffered(true);
        Banner.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 23, 15);
        jPanel1.add(Banner, gridBagConstraints);

        txtGuildName.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel1.add(txtGuildName, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel1.setText("Guild Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel4.setText("Data Center:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        serverList.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        serverList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(serverList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        ddlDataCenter.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        ddlDataCenter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><i>Select Region</i></html>" }));
        ddlDataCenter.setEnabled(false);
        ddlDataCenter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ddlDataCenterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel1.add(ddlDataCenter, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel5.setText("Server:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 30);
        jPanel1.add(jSeparator10, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel7.setText("Region:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jPanel2.setBackground(getBackground());
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 2, 0));

        rdJapan.setBackground(getBackground());
        rdJapan.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdJapan.setText("Japan");
        jPanel2.add(rdJapan);

        rdAmerica.setBackground(getBackground());
        rdAmerica.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdAmerica.setText("America");
        jPanel2.add(rdAmerica);

        rdEurope.setBackground(getBackground());
        rdEurope.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        rdEurope.setText("Europe");
        jPanel2.add(rdEurope);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel1.add(jPanel2, gridBagConstraints);

        chkPVP.setBackground(getBackground());
        chkPVP.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkPVP.setText("PvP?");
        chkPVP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 15);
        jPanel1.add(chkPVP, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Yu Gothic Light", 0, 24)); // NOI18N
        jLabel2.setText("New Guild");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel2, gridBagConstraints);

        warnName.setFont(new java.awt.Font("Yu Gothic UI", 2, 11)); // NOI18N
        warnName.setForeground(new java.awt.Color(255, 51, 51));
        warnName.setText("Guild name must not be empty or greater than 30 characters ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(warnName, gridBagConstraints);

        warnRegion.setFont(warnName.getFont());
        warnRegion.setForeground(warnName.getForeground());
        warnRegion.setText("Select a region");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(warnRegion, gridBagConstraints);

        warnServer.setFont(warnName.getFont());
        warnServer.setForeground(warnName.getForeground());
        warnServer.setText("Select a server");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(warnServer, gridBagConstraints);

        jPanel3.setBackground(getBackground());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnAdd.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        btnAdd.setText("Save Guild");
        btnAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 15, 5);
        jPanel3.add(btnAdd, gridBagConstraints);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 15, 5);
        jPanel3.add(btnCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Validate all inputs on the form
     * @return Valid
     */
    public boolean validateInputs()
    {
        boolean valid = true;
        if(!ValidateInputs.validateTeamName(txtGuildName.getText()))
        {
            valid = false;
            warnName.setVisible(true);
        }
        if(!rdJapan.isSelected() && !rdAmerica.isSelected() && !rdEurope.isSelected())
        {
            valid = false;
            warnRegion.setVisible(true);
        }
        if(serverList.isSelectionEmpty())
        {
            valid = false;
            warnServer.setVisible(true);
        }
        return valid;
    }
    /**
     * Fires on Region JRadioButton change, updates the serverList based on region
     * @param evt ActionEvent
     */
    private void ddlDataCenterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ddlDataCenterActionPerformed
    {//GEN-HEADEREND:event_ddlDataCenterActionPerformed
        // TODO add your handling code here:
        if(rdJapan.isSelected())
            serverList.setListData(ServerList.JAPAN[ddlDataCenter.getSelectedIndex()]);
        else if(rdAmerica.isSelected())
            serverList.setListData(ServerList.AMERICA[ddlDataCenter.getSelectedIndex()]);
        else if(rdEurope.isSelected())
            serverList.setListData(ServerList.EUROPE[ddlDataCenter.getSelectedIndex()]);
            
        //serverList.setListData(DATA_CENTER[ddlDataCenter.getSelectedIndex()]);
    }//GEN-LAST:event_ddlDataCenterActionPerformed
    /**
     * Checks form validation, and adds Guild to the database if valid
     * @param evt ActionEvent
     */
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddActionPerformed
    {//GEN-HEADEREND:event_btnAddActionPerformed
        // TODO add your handling code here:
        if(validateInputs())
        {
            String selectedRegion = "";
            if(rdAmerica.isSelected())
                selectedRegion = rdAmerica.getText();
            else if(rdEurope.isSelected())
                selectedRegion = rdEurope.getText();
            else if(rdJapan.isSelected())
                selectedRegion = rdJapan.getText();

            //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            //System.out.println(formatter.format(date));
            
            Guild g = new Guild(database.getNewID(DAL.Table.GUILD),txtGuildName.getText(),selectedRegion,ddlDataCenter.getSelectedItem().toString(),serverList.getSelectedValue(),chkPVP.isSelected(),date);
            //String sql = String.format("INSERT INTO guild (guildID,name,pvp,region,datacenter,server,founded) VALUES (%s,'%s',%s,'%s','%s','%s','%s')",);

            //Trace.log(sql);

            if(database.addGuild(g)) //if successfully inserted, close window
            {
                JOptionPane.showMessageDialog(this, "Guild added successfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Please insert info into all fields!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed
/**
 * Exits form on cancel
 * @param evt ActionEvent
 */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelActionPerformed
    {//GEN-HEADEREND:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed
    
    @Override
    /**
     * Update the DataCenter JComboBox with Servers that correspond to the selected Region
     */
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource() == rdJapan)
            ddlDataCenter.setModel(new DefaultComboBoxModel(DATA_CENTER[0]));
        else if(e.getSource() == rdAmerica)
            ddlDataCenter.setModel(new DefaultComboBoxModel(DATA_CENTER[1]));
        else if(e.getSource() == rdEurope)
            ddlDataCenter.setModel(new DefaultComboBoxModel(DATA_CENTER[2]));
        
        ddlDataCenter.setEnabled(true);
        ddlDataCenter.setSelectedIndex(0);//fires the action performed event
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Banner;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JCheckBox chkPVP;
    private javax.swing.JComboBox<String> ddlDataCenter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JRadioButton rdAmerica;
    private javax.swing.JRadioButton rdEurope;
    private javax.swing.JRadioButton rdJapan;
    private javax.swing.ButtonGroup regionGroup;
    private javax.swing.JList<String> serverList;
    private javax.swing.JTextField txtGuildName;
    private javax.swing.JLabel warnName;
    private javax.swing.JLabel warnRegion;
    private javax.swing.JLabel warnServer;
    // End of variables declaration//GEN-END:variables
}
