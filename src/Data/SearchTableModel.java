/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Console.Trace;
import Objects.Guild;
import Objects.Player;
import Objects.Staff;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *  Model to output Members to a JTable, used in SearchMember
 * @author Jessica Moolenschot
 */
public class SearchTableModel extends AbstractTableModel
{
    private ArrayList<Object> list = new ArrayList<>();
    private String[] columnNames = {"Member ID","Guild","First Name","Last Name","Character Name","Character Role","Character Classes","Region","DataCenter","Server","Date Joined","Player Role"};
    DAL database;
    /**
     * Default constructor, initializes the database
     */
    public SearchTableModel()
    {
        database = new DAL();  
    }
    /**
     * Get filtered members by param
     * @param sql Filter
     */
    public void getMembers(String sql)
    {
        database.connect();
        Trace.log(sql);
        list = database.getMembers(sql);
        if(list.isEmpty())
            JOptionPane.showMessageDialog(null, "No results!","Error",JOptionPane.ERROR_MESSAGE);
        database.close();
        
    }
    @Override
    public int getRowCount(){return list.size();}
    @Override
    public int getColumnCount(){return columnNames.length;}
    
    @Override
    /**
     * Returns custom values depending on which column is requested
     * Also contains some OP code for multiline rendering the classes via JList
     */
    public Object getValueAt(int row, int col)
    {
        database.connect();
        Object o = list.get(row);
        
        switch(col)
        {
            case 0: return o.getClass() == Player.class ? ((Player)o).getMemberID() : ((Staff)o).getMemberID();
            case 1:
            {
                for(Guild g : database.getGuilds())
                {
                    if(o.getClass() == Player.class)
                        if(((Player)o).getGuildID() == g.getGuildID())
                        {
                            database.close();//pluggin that data leak one close at a time
                            return g.getName();
                        }
                        
                    else if(o.getClass() == Staff.class)
                        if(((Staff)o).getGuildID() == g.getGuildID())
                        {
                            database.close();
                            return g.getName();
                            
                        }
                }
            }
            case 2: return o.getClass() == Player.class ? ((Player)o).getFirstName() : ((Staff)o).getFirstName();
            case 3: return o.getClass() == Player.class ? ((Player)o).getLastName() : ((Staff)o).getLastName();
            case 7: return o.getClass() == Player.class ? ((Player)o).getRegion() : ((Staff)o).getRegion();
            case 8: return o.getClass() == Player.class ? ((Player)o).getDatacenter() : ((Staff)o).getDatacenter();
            case 9: return o.getClass() == Player.class ? ((Player)o).getServer() : ((Staff)o).getServer();
            case 10: 
                SimpleDateFormat f = new SimpleDateFormat("dd/MMM/yyyy"); //long text month to prevent america/world date confusion
                return (o.getClass() == Player.class) ? f.format(((Player)o).getJoined()) : f.format(((Staff)o).getJoined()); 
        }
        if(o.getClass() == Player.class)
        {
            switch(col)
            {
                case 4: return ((Player)o).getCharacterName();
                case 5: return ((Player)o).getCharacterRole();
                case 6: 
                    //JList list = new JList();
                    //list.setModel(ParseClass.parseClasses(((Player)o).getCharacterClass()));
                    //String classes = ((Player)o).getCharacterClass();
                    //return classes == null ? "" : ParseClass.parseClasses(classes).toArray(new String[0]);
                    
                    //return ((Player)o);
                    return ((Player)o).getCharacterClass();
            }
        }
        else if(o.getClass() == Staff.class)
        {
            if(col == 11)
                return ((Staff)o).getRole();
        }
        return null;
    }
    @Override
    public String getColumnName(int col){return columnNames[col];}
    
    //gets member from row
    public Object getMemberByRow(int row)
    {
        Object o = list.get(row);
        return o;
    }
}
