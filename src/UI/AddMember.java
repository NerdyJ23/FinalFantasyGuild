/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Console.Trace;
import Data.Classes;
import Data.DAL;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import Data.ServerList;
import Objects.Guild;
import Objects.Player;
import Objects.Staff;
import Validation.ValidateInputs;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
/**
 * Form for adding a new Member (Staff or Player)
 * @author Jessica Moolenschot
 */
public class AddMember extends javax.swing.JFrame implements ItemListener,WindowListener
{
    private final String[][] DATA_CENTER = {
        {"Elemental","Gaia","Mana"}, //japan
        {"Aether","Primal","Crystal"}, //america
        {"Chaos","Light"} //europe
    };
    private boolean isStaff = false;
    DAL database;
    /**
     * Initializes the AddMember form
     */
    public AddMember()
    {
        initComponents();
        ImageIcon i = new ImageIcon("./src/img/ff14icon.jpg");
        this.setIconImage(i.getImage());
        toggleWarnings(false);
        TitledBorder b = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),"Class");
        ClassPanel.setBorder(b);
        b = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),"Player");
        PlayerPanel.setBorder(b);
        
        rdAmerica.setActionCommand("America");
        regionGroup.add(rdAmerica);
        rdEurope.setActionCommand("Europe");
        regionGroup.add(rdEurope);
        rdJapan.setActionCommand("Japan");
        regionGroup.add(rdJapan);
        
        memberGroup.add(rdPlayer);
        memberGroup.add(rdStaff);
        rdPlayer.setSelected(true);
        rdPlayer.addItemListener(this);
        rdStaff.addItemListener(this);
        
        rdJapan.addItemListener(this);
        rdAmerica.addItemListener(this);
        rdEurope.addItemListener(this);
        
        //add listeners for the upgraded classes
        chkPaladin.addItemListener(this);
        chkWarrior.addItemListener(this);
        chkMonk.addItemListener(this);
        chkDragoon.addItemListener(this);
        chkNinja.addItemListener(this);
        chkBard.addItemListener(this);
        chkBlk.addItemListener(this);
        chkScholar.addItemListener(this);
        chkSummoner.addItemListener(this);
        chkWht.addItemListener(this);
        
        database = new DAL();
        //Staff p = new Staff();
        //database.addMember(p); test if database knows diff between staff or player objects
        
        lblStaffRole.setVisible(false);
        ddlStaffRole.setModel(new DefaultComboBoxModel(Staff.Role.values()));
        ddlStaffRole.setVisible(false);
        updateGuilds();
    }
    /**
     * Updates the GuildList JComboBox
     */
    public void updateGuilds()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(Guild g : database.getGuilds())
        {
            model.addElement(g);
        }
        if(model.getSize() != 0)
        {
            ddlGuild.setModel(model);
            ddlGuild.setEnabled(true);
        }
    }
    /**
     * Toggles the Staff/Player unique fields
     * @param visible CharacterInfoVisible
     */
    public void toggleStaff(boolean visible)
    {
        isStaff = !visible;
        txtCharName.setVisible(visible);
        lblCharName.setVisible(visible);
        lblRole.setVisible(visible);
        ddlRole.setVisible(visible);
        ClassPanel.setVisible(visible);
        
        lblStaffRole.setVisible(!visible);
        ddlStaffRole.setVisible(!visible);
    }
    /**
     * Toggle warning text on/off
     * @param visible setWarningsVisible
     */
    public void toggleWarnings(boolean visible)
    {
        warnFirstName.setVisible(visible);
        warnLastName.setVisible(visible);
        warnGuild.setVisible(visible);
        warnCharName.setVisible(visible);
        warnRegion.setVisible(visible);
        warnServer.setVisible(visible);
        warnClass.setVisible(visible);
    }
    /**
     * Validate all inputs on the form
     * @return Valid
     */
    public boolean validateInputs()
    {
        //future addition: datacenter of guild and player should match
        boolean valid = true;
        toggleWarnings(false);
        String output = "";
        if(!ValidateInputs.validateFirstName(txtFirstName.getText()))//if one or less characters for name
        {
            valid = false;
            warnFirstName.setVisible(true);
        }
        if(!ValidateInputs.validateLastName(txtLastName.getText()))//if two or less chars for last name
        {
            valid = false;
            warnLastName.setVisible(true);
        }
        //@TODO: Guild select check
        if(!(ddlGuild.getSelectedItem() instanceof Guild))//if no guilds were loaded, or the default item is selected
        {
            valid = false;
            warnGuild.setVisible(true);
        }
        if(!rdJapan.isSelected() && !rdAmerica.isSelected() && !rdEurope.isSelected())//if no region selected
        {
            valid = false;
            warnRegion.setVisible(true);
        }
        //@TODO: Server check
        if(serverList.isSelectionEmpty())
        {
            valid = false;
            warnServer.setVisible(true);
        }
        
        if(!isStaff)
        {
            if(!ValidateInputs.validateCharacterName(txtCharName.getText()))
            {
                valid = false;
                warnCharName.setVisible(true);
            }
            if(!classSelected())
            {
                valid = false;
                warnClass.setVisible(true);
            }
        }
        return valid;   
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
        memberGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Banner = new javax.swing.JLabel();
        txtCharName = new javax.swing.JTextField();
        lblCharName = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        ddlRole = new javax.swing.JComboBox<>();
        ClassPanel = new javax.swing.JPanel();
        chkGladiator = new javax.swing.JCheckBox();
        chkPaladin = new javax.swing.JCheckBox();
        chkMarauder = new javax.swing.JCheckBox();
        chkWarrior = new javax.swing.JCheckBox();
        chkPugilist = new javax.swing.JCheckBox();
        chkMonk = new javax.swing.JCheckBox();
        chkLancer = new javax.swing.JCheckBox();
        chkDragoon = new javax.swing.JCheckBox();
        chkRogue = new javax.swing.JCheckBox();
        chkNinja = new javax.swing.JCheckBox();
        chkArcher = new javax.swing.JCheckBox();
        chkBard = new javax.swing.JCheckBox();
        chkThaum = new javax.swing.JCheckBox();
        chkBlk = new javax.swing.JCheckBox();
        chkArcanist = new javax.swing.JCheckBox();
        chkScholar = new javax.swing.JCheckBox();
        chkConjurer = new javax.swing.JCheckBox();
        chkWht = new javax.swing.JCheckBox();
        chkBlue = new javax.swing.JCheckBox();
        chkDarkKnight = new javax.swing.JCheckBox();
        chkAstro = new javax.swing.JCheckBox();
        chkMachinist = new javax.swing.JCheckBox();
        chkSamurai = new javax.swing.JCheckBox();
        chkRed = new javax.swing.JCheckBox();
        chkGun = new javax.swing.JCheckBox();
        chkDancer = new javax.swing.JCheckBox();
        chkSummoner = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        btnClearClass = new javax.swing.JButton();
        warnClass = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverList = new javax.swing.JList<>();
        ddlDataCenter = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rdJapan = new javax.swing.JRadioButton();
        rdAmerica = new javax.swing.JRadioButton();
        rdEurope = new javax.swing.JRadioButton();
        PlayerPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ddlGuild = new javax.swing.JComboBox<>();
        btnAddTeam = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        warnFirstName = new javax.swing.JLabel();
        warnLastName = new javax.swing.JLabel();
        warnGuild = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdPlayer = new javax.swing.JRadioButton();
        rdStaff = new javax.swing.JRadioButton();
        warnCharName = new javax.swing.JLabel();
        warnServer = new javax.swing.JLabel();
        warnRegion = new javax.swing.JLabel();
        lblStaffRole = new javax.swing.JLabel();
        ddlStaffRole = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Member");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        Banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Banner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Banner.jpg"))); // NOI18N
        Banner.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Banner.setDoubleBuffered(true);
        Banner.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 23, 0);
        jPanel1.add(Banner, gridBagConstraints);

        txtCharName.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(txtCharName, gridBagConstraints);

        lblCharName.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        lblCharName.setText("Character Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(lblCharName, gridBagConstraints);

        lblRole.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        lblRole.setText("Role:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(lblRole, gridBagConstraints);

        ddlRole.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        ddlRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DPS", "Tank", "Healer" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(ddlRole, gridBagConstraints);

        ClassPanel.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout ClassPanelLayout = new java.awt.GridBagLayout();
        ClassPanelLayout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0};
        ClassPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        ClassPanel.setLayout(ClassPanelLayout);

        chkGladiator.setBackground(ClassPanel.getBackground());
        chkGladiator.setText("Gladiator");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        ClassPanel.add(chkGladiator, gridBagConstraints);

        chkPaladin.setBackground(ClassPanel.getBackground());
        chkPaladin.setText("Paladin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkPaladin, gridBagConstraints);

        chkMarauder.setBackground(ClassPanel.getBackground());
        chkMarauder.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkMarauder.setText("Marauder");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkMarauder, gridBagConstraints);

        chkWarrior.setBackground(ClassPanel.getBackground());
        chkWarrior.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkWarrior.setText("Warrior");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkWarrior, gridBagConstraints);

        chkPugilist.setBackground(ClassPanel.getBackground());
        chkPugilist.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkPugilist.setText("Pugilist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkPugilist, gridBagConstraints);

        chkMonk.setBackground(ClassPanel.getBackground());
        chkMonk.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkMonk.setText("Monk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkMonk, gridBagConstraints);

        chkLancer.setBackground(ClassPanel.getBackground());
        chkLancer.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkLancer.setText("Lancer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkLancer, gridBagConstraints);

        chkDragoon.setBackground(ClassPanel.getBackground());
        chkDragoon.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkDragoon.setText("Dragoon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkDragoon, gridBagConstraints);

        chkRogue.setBackground(ClassPanel.getBackground());
        chkRogue.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkRogue.setText("Rogue");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkRogue, gridBagConstraints);

        chkNinja.setBackground(ClassPanel.getBackground());
        chkNinja.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkNinja.setText("Ninja");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkNinja, gridBagConstraints);

        chkArcher.setBackground(ClassPanel.getBackground());
        chkArcher.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkArcher.setText("Archer");
        chkArcher.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                chkArcherActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkArcher, gridBagConstraints);

        chkBard.setBackground(ClassPanel.getBackground());
        chkBard.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkBard.setText("Bard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkBard, gridBagConstraints);

        chkThaum.setBackground(ClassPanel.getBackground());
        chkThaum.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkThaum.setText("Thaumaturge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkThaum, gridBagConstraints);

        chkBlk.setBackground(ClassPanel.getBackground());
        chkBlk.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkBlk.setText("Black Mage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkBlk, gridBagConstraints);

        chkArcanist.setBackground(ClassPanel.getBackground());
        chkArcanist.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkArcanist.setText("Arcanist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkArcanist, gridBagConstraints);

        chkScholar.setBackground(ClassPanel.getBackground());
        chkScholar.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkScholar.setText("Scholar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkScholar, gridBagConstraints);

        chkConjurer.setBackground(ClassPanel.getBackground());
        chkConjurer.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkConjurer.setText("Conjurer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkConjurer, gridBagConstraints);

        chkWht.setBackground(ClassPanel.getBackground());
        chkWht.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkWht.setText("White Mage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkWht, gridBagConstraints);

        chkBlue.setBackground(ClassPanel.getBackground());
        chkBlue.setText("Blue Mage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkBlue, gridBagConstraints);

        chkDarkKnight.setBackground(ClassPanel.getBackground());
        chkDarkKnight.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkDarkKnight.setText("Dark Knight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkDarkKnight, gridBagConstraints);

        chkAstro.setBackground(ClassPanel.getBackground());
        chkAstro.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkAstro.setText("Astrologian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkAstro, gridBagConstraints);

        chkMachinist.setBackground(ClassPanel.getBackground());
        chkMachinist.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkMachinist.setText("Machinist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkMachinist, gridBagConstraints);

        chkSamurai.setBackground(ClassPanel.getBackground());
        chkSamurai.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkSamurai.setText("Samurai");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkSamurai, gridBagConstraints);

        chkRed.setBackground(ClassPanel.getBackground());
        chkRed.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkRed.setText("Red Mage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkRed, gridBagConstraints);

        chkGun.setBackground(ClassPanel.getBackground());
        chkGun.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkGun.setText("Gunbreaker");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkGun, gridBagConstraints);

        chkDancer.setBackground(ClassPanel.getBackground());
        chkDancer.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkDancer.setText("Dancer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkDancer, gridBagConstraints);

        chkSummoner.setBackground(ClassPanel.getBackground());
        chkSummoner.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        chkSummoner.setText("Summoner");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(chkSummoner, gridBagConstraints);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 35;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        ClassPanel.add(jSeparator1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(jSeparator2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(jSeparator3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(jSeparator4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(jSeparator5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(jSeparator6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(jSeparator7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(jSeparator8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 32;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(jSeparator9, gridBagConstraints);

        btnClearClass.setText("Clear Selection");
        btnClearClass.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnClearClassActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 38;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(btnClearClass, gridBagConstraints);

        warnClass.setFont(warnFirstName.getFont());
        warnClass.setForeground(new java.awt.Color(255, 51, 51));
        warnClass.setText("Select at least one class");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 36;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ClassPanel.add(warnClass, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 27;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 15, 10);
        jPanel1.add(ClassPanel, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel4.setText("Data Center:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        serverList.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        serverList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(serverList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
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
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(ddlDataCenter, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel5.setText("Server:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel1.add(jSeparator10, gridBagConstraints);

        btnAdd.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        btnAdd.setText("Save Member");
        btnAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 15, 5);
        jPanel1.add(btnAdd, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel7.setText("Region:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
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
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 20;
        jPanel1.add(jPanel2, gridBagConstraints);

        PlayerPanel.setBackground(getBackground());
        PlayerPanel.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel2.setText("Last Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        PlayerPanel.add(jLabel2, gridBagConstraints);

        txtFirstName.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 251;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        PlayerPanel.add(txtFirstName, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel6.setText("Guild:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 15, 5);
        PlayerPanel.add(jLabel6, gridBagConstraints);

        ddlGuild.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        ddlGuild.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><i>No guilds! Click + to add one </i></html>" }));
        ddlGuild.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        PlayerPanel.add(ddlGuild, gridBagConstraints);

        btnAddTeam.setText("+");
        btnAddTeam.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAddTeamActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        PlayerPanel.add(btnAddTeam, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jLabel8.setText("First Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        PlayerPanel.add(jLabel8, gridBagConstraints);

        txtLastName.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 251;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        PlayerPanel.add(txtLastName, gridBagConstraints);

        warnFirstName.setFont(new java.awt.Font("Yu Gothic UI", 2, 11)); // NOI18N
        warnFirstName.setForeground(new java.awt.Color(255, 51, 51));
        warnFirstName.setText("First name must not be empty or greater than 20 characters");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        PlayerPanel.add(warnFirstName, gridBagConstraints);

        warnLastName.setFont(warnFirstName.getFont());
        warnLastName.setForeground(warnFirstName.getForeground());
        warnLastName.setText("Last name must not be empty or greater than 30 characters");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        PlayerPanel.add(warnLastName, gridBagConstraints);

        warnGuild.setFont(warnFirstName.getFont());
        warnGuild.setForeground(warnFirstName.getForeground());
        warnGuild.setText("Need a guild!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        PlayerPanel.add(warnGuild, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel1.add(PlayerPanel, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Yu Gothic Light", 0, 24)); // NOI18N
        jLabel9.setText("Add Member");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel1.add(jLabel9, gridBagConstraints);

        rdPlayer.setBackground(getBackground());
        rdPlayer.setText("Player");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(rdPlayer, gridBagConstraints);

        rdStaff.setBackground(getBackground());
        rdStaff.setText("Staff");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(rdStaff, gridBagConstraints);

        warnCharName.setFont(warnFirstName.getFont());
        warnCharName.setForeground(warnFirstName.getForeground());
        warnCharName.setText("Character name must not be empty or greater than 30 characters");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(warnCharName, gridBagConstraints);

        warnServer.setFont(warnFirstName.getFont());
        warnServer.setForeground(warnFirstName.getForeground());
        warnServer.setText("Select a server");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(warnServer, gridBagConstraints);

        warnRegion.setFont(warnFirstName.getFont());
        warnRegion.setForeground(warnFirstName.getForeground());
        warnRegion.setText("Select a region");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(warnRegion, gridBagConstraints);

        lblStaffRole.setFont(jLabel8.getFont());
        lblStaffRole.setText("Staff Role:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(lblStaffRole, gridBagConstraints);

        ddlStaffRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No roles?" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(ddlStaffRole, gridBagConstraints);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 15, 5);
        jPanel1.add(btnCancel, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkArcherActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chkArcherActionPerformed
    {//GEN-HEADEREND:event_chkArcherActionPerformed

    }//GEN-LAST:event_chkArcherActionPerformed
    /**
     * Fires on Region JRadioButton change, updates the serverList based on region
     * @param evt ActionEvent
     */
    private void ddlDataCenterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ddlDataCenterActionPerformed
    {//GEN-HEADEREND:event_ddlDataCenterActionPerformed
        if(rdJapan.isSelected())
            serverList.setListData(ServerList.JAPAN[ddlDataCenter.getSelectedIndex()]);
        else if(rdAmerica.isSelected())
            serverList.setListData(ServerList.AMERICA[ddlDataCenter.getSelectedIndex()]);
        else if(rdEurope.isSelected())
            serverList.setListData(ServerList.EUROPE[ddlDataCenter.getSelectedIndex()]);
            
        //serverList.setListData(DATA_CENTER[ddlDataCenter.getSelectedIndex()]);
    }//GEN-LAST:event_ddlDataCenterActionPerformed
/**
 * Checks form validation, and adds Member to the database if valid
 * @param evt ActionEvent
 */
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddActionPerformed
    {//GEN-HEADEREND:event_btnAddActionPerformed
        if(validateInputs())//all inputs are valid
        {
            Trace.log("All inputs were validated!");
            if(isStaff)
            {
                Staff s = new Staff();
                s.setMemberID(database.getNewID(DAL.Table.MEMBER));
                s.setFirstName(txtFirstName.getText().trim());
                s.setLastName(txtLastName.getText().trim());
                s.setRegion(regionGroup.getSelection().getActionCommand());
                s.setDatacenter(ddlDataCenter.getSelectedItem().toString());
                s.setServer(serverList.getSelectedValue());
                s.setRole((Staff.Role)ddlStaffRole.getSelectedItem());
                s.setJoined(new Date());
                
                s.setGuildID(((Guild) ddlGuild.getSelectedItem()).getGuildID());
                if(database.addMember(s))
                {
                    JOptionPane.showMessageDialog(this, "Added successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(this, "Could not insert member!", "Error", JOptionPane.ERROR_MESSAGE);
                Trace.log(s.toString());
            }
            else
            {
                Player p = new Player();
                p.setMemberID(database.getNewID(DAL.Table.MEMBER));
                p.setFirstName(txtFirstName.getText().trim());
                p.setLastName(txtLastName.getText().trim());
                p.setRegion(regionGroup.getSelection().getActionCommand());
                p.setDatacenter(ddlDataCenter.getSelectedItem().toString());
                p.setServer(serverList.getSelectedValue());
                
                p.setCharacterName(txtCharName.getText().trim());
                p.setCharacterRole(ddlRole.getSelectedItem().toString());
                p.setJoined(new Date());
                p.setGuildID(((Guild) ddlGuild.getSelectedItem()).getGuildID());
                String[] charClass = new String[Classes.values().length];
                Arrays.fill(charClass,"0");//0 every field, no classes selected
                
                for(Component j : ClassPanel.getComponents())
                {
                    if(j instanceof JCheckBox)
                    {
                        if(((JCheckBox) j).isSelected())
                        {
                            charClass[Classes.valueOf(((JCheckBox) j).getText().toUpperCase().replace(" ", "_")).getValue()] = "1";
//                            switch(((JCheckBox) j).getText())
//                            {
//                                case("Gladiator"):
//                                    charClass[Classes.GLADIATOR.getValue()] = "1";
//                                    break;
//                                case("Paladin"):
//                                    charClass[Classes.PALADIN.getValue()] = "1";
//                                    break;
//                                case("Marauder"):
//                                    charClass[Classes.MARAUDER.getValue()] = "1";
//                                    break;
//                                case("Warrior"):
//                                    charClass[Classes.WARRIOR.getValue()] = "1";
//                                    break;
//                                
//                            }
                        }
                    }
                }//end panelLoop
                String out = "";
                for(String a : charClass)//convert array into string
                    out += a;
                Trace.log(out);
                p.setCharacterClass(out);
                if(database.addMember(p))
                {
                    JOptionPane.showMessageDialog(this, "Added successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(this, "Could not insert member!", "Error", JOptionPane.ERROR_MESSAGE);
            }//end player entry
        }//end valid input code
        else
        {
            Trace.error("Not all inputs are valid");
        }
    }//GEN-LAST:event_btnAddActionPerformed
    /**
     * Check if any class is selected by iterating over JCheckBox components in ClassPanel
     * @return ClassSelected
     */
    private boolean classSelected()
    {
        boolean selected = false;
        for(Component j : ClassPanel.getComponents())//iterate over every component in the jpanel
        {
            if(j instanceof JCheckBox)
            {
                if(!selected)//must stay true if one is selected, this way it only operates if false
                    selected = ((JCheckBox) j).isSelected();
                if(((JCheckBox) j).isSelected())
                    Trace.log(((JCheckBox) j).getText() + " selected");
            }
        }
        return selected;
    }
    /**
     * Opens the AddTeam form
     * @param evt ActionEvent
     */
    private void btnAddTeamActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddTeamActionPerformed
    {//GEN-HEADEREND:event_btnAddTeamActionPerformed
        AddTeam a = new AddTeam();
        a.setLocationRelativeTo(this);
        a.addWindowListener(this);
        a.setVisible(true);
    }//GEN-LAST:event_btnAddTeamActionPerformed
/**
 * Clears all class JCheckBox's
 * @param evt ActionEvent
 */
    private void btnClearClassActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnClearClassActionPerformed
    {//GEN-HEADEREND:event_btnClearClassActionPerformed
        for(Component j : ClassPanel.getComponents())
        {
            if(j instanceof JCheckBox)
            {
                ((JCheckBox) j).setSelected(false);
                j.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnClearClassActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelActionPerformed
    {//GEN-HEADEREND:event_btnCancelActionPerformed

        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed
    
    @Override
    /**
     * Hide/show fields depending on whether the user is inserting a Player or Staff Member and update the DataCenter JComboBox with Servers that correspond to the selected Region
     */
    public void itemStateChanged(ItemEvent e)
    {
        int server = serverList.getSelectedIndex(); //preserve the selection, since selecting other stuff in code removes it
        
        if(e.getSource() == rdPlayer)
        {
            toggleStaff(isStaff);
            toggleWarnings(false);
        }
        else if(e.getSource() == rdJapan)
            ddlDataCenter.setModel(new DefaultComboBoxModel(DATA_CENTER[0]));
        else if(e.getSource() == rdAmerica)
            ddlDataCenter.setModel(new DefaultComboBoxModel(DATA_CENTER[1]));
        else if(e.getSource() == rdEurope)
            ddlDataCenter.setModel(new DefaultComboBoxModel(DATA_CENTER[2]));
        
        //if one of the upgraded classes is selected, the basic one must also be selected
        else if(e.getSource() == chkPaladin)
        {
            chkGladiator.setSelected(chkPaladin.isSelected());
            chkGladiator.setEnabled(!chkPaladin.isSelected());//if upgraded is enabled, you cannnot deselect the basic class
        }
        else if(e.getSource() == chkWarrior)
        {
            chkMarauder.setSelected(chkWarrior.isSelected());
            chkMarauder.setEnabled(!chkWarrior.isSelected());
        }
        else if(e.getSource() == chkMonk)
        {
            chkPugilist.setSelected(chkMonk.isSelected());
            chkPugilist.setEnabled(!chkMonk.isSelected());
        }
        else if(e.getSource() == chkDragoon)
        {
            chkLancer.setSelected(chkDragoon.isSelected());
            chkLancer.setEnabled(!chkDragoon.isSelected());
        }
        else if(e.getSource() == chkNinja)
        {
            chkRogue.setSelected(chkNinja.isSelected());
            chkRogue.setEnabled(!chkNinja.isSelected());
        }
        else if(e.getSource() == chkBard)
        {
            chkArcher.setSelected(chkBard.isSelected());
            chkArcher.setEnabled(!chkBard.isSelected());
        }
        else if(e.getSource() == chkBlk)
        {
            chkThaum.setSelected(chkBlk.isSelected());
            chkThaum.setEnabled(!chkBlk.isSelected());
        }
        else if(e.getSource() == chkScholar || e.getSource() == chkSummoner)
        {
            chkArcanist.setSelected(chkScholar.isSelected() || chkSummoner.isSelected());
            chkArcanist.setEnabled(!chkSummoner.isSelected() && !chkScholar.isSelected());
        }
        else if(e.getSource() == chkWht)
        {
            chkConjurer.setSelected(chkWht.isSelected());
            chkConjurer.setEnabled(!chkWht.isSelected());
        }
        try
        {
            regionGroup.getSelection().getActionCommand();
            
            //will only perform this if it doesnt throw an error; eg something is selected
            ddlDataCenter.setEnabled(true);
            ddlDataCenter.setSelectedIndex(0);//fires the action performed event
        }
        catch(NullPointerException ex)
        {
            Trace.error("No region selected");
        }
        serverList.setSelectedIndex(server);
    }
    
    @Override
    /**
     * Re-enable this window when the child window closes and set the Guild JComboBox to the last value
     */
    public void windowClosed(WindowEvent e)
    {
        this.setEnabled(true);
        updateGuilds();
        ddlGuild.setSelectedIndex(ddlGuild.getItemCount()-1);
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
    @Override 
    /**
     * Disable this window when child window is open
     */
    public void windowOpened(WindowEvent e)
    {
        super.setEnabled(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Banner;
    private javax.swing.JPanel ClassPanel;
    private javax.swing.JPanel PlayerPanel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddTeam;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClearClass;
    private javax.swing.JCheckBox chkArcanist;
    private javax.swing.JCheckBox chkArcher;
    private javax.swing.JCheckBox chkAstro;
    private javax.swing.JCheckBox chkBard;
    private javax.swing.JCheckBox chkBlk;
    private javax.swing.JCheckBox chkBlue;
    private javax.swing.JCheckBox chkConjurer;
    private javax.swing.JCheckBox chkDancer;
    private javax.swing.JCheckBox chkDarkKnight;
    private javax.swing.JCheckBox chkDragoon;
    private javax.swing.JCheckBox chkGladiator;
    private javax.swing.JCheckBox chkGun;
    private javax.swing.JCheckBox chkLancer;
    private javax.swing.JCheckBox chkMachinist;
    private javax.swing.JCheckBox chkMarauder;
    private javax.swing.JCheckBox chkMonk;
    private javax.swing.JCheckBox chkNinja;
    private javax.swing.JCheckBox chkPaladin;
    private javax.swing.JCheckBox chkPugilist;
    private javax.swing.JCheckBox chkRed;
    private javax.swing.JCheckBox chkRogue;
    private javax.swing.JCheckBox chkSamurai;
    private javax.swing.JCheckBox chkScholar;
    private javax.swing.JCheckBox chkSummoner;
    private javax.swing.JCheckBox chkThaum;
    private javax.swing.JCheckBox chkWarrior;
    private javax.swing.JCheckBox chkWht;
    private javax.swing.JComboBox<String> ddlDataCenter;
    private javax.swing.JComboBox<String> ddlGuild;
    private javax.swing.JComboBox<String> ddlRole;
    private javax.swing.JComboBox<String> ddlStaffRole;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblCharName;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblStaffRole;
    private javax.swing.ButtonGroup memberGroup;
    private javax.swing.JRadioButton rdAmerica;
    private javax.swing.JRadioButton rdEurope;
    private javax.swing.JRadioButton rdJapan;
    private javax.swing.JRadioButton rdPlayer;
    private javax.swing.JRadioButton rdStaff;
    private javax.swing.ButtonGroup regionGroup;
    private javax.swing.JList<String> serverList;
    private javax.swing.JTextField txtCharName;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JLabel warnCharName;
    private javax.swing.JLabel warnClass;
    private javax.swing.JLabel warnFirstName;
    private javax.swing.JLabel warnGuild;
    private javax.swing.JLabel warnLastName;
    private javax.swing.JLabel warnRegion;
    private javax.swing.JLabel warnServer;
    // End of variables declaration//GEN-END:variables
}
