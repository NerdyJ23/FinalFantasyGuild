/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;//https://stackoverflow.com/questions/22955621/error-type-list-does-not-take-parameters/22955642
import java.util.Arrays;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * Deals with the playerClass information and renders it
 * @author Jessica Moolenschot
 */
//https://stackoverflow.com/questions/9955595/how-to-display-multiple-lines-in-a-jtable-cell/32793088#32793088
public class ParseClass extends JList<String> implements TableCellRenderer
{
   @Override
   /**
    * Renders a multiline JList from the playerClass binary string
    */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof String) {
            
            List<String> list = parseClasses((String)value);
            String[] arr = list.toArray(new String[0]);
            //Trace.log(list.toString());
            setListData(arr);
            //for(int a = 0; a < getModel().getSize(); a++)
                //Trace.log(this.getModel().getElementAt(a));
            //this.add(new JLabel("test"));
        }
        if(value == null)
        {
            String[] data = {""};
            setListData(data);
        }

        if (isSelected) {
            setBackground(UIManager.getColor("Table.selectionBackground"));
        } else {
            setBackground(UIManager.getColor("Table.background"));
        }
        this.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        //this.setMinimumSize(new Dimension(15,30));
        return this;
    }
    /**
     * Returns a List with the names of equipped playerClasses
     * @param all playerClass binary String
     * @return equipClasses
     */
    public static List<String> parseClasses(String all)
    {
        //DefaultListModel m = new DefaultListModel();
        //https://stackoverflow.com/questions/5235401/split-string-into-array-of-character-strings
        List<String> list = Arrays.asList(all.split("(?!^)"));
        ArrayList<String> classList = new ArrayList<>();
        for(int c = 0; c < list.size(); c++)
        {
            if((list.get(c)).equals("1"))//if has the class
            {
                //https://stackoverflow.com/questions/6692664/how-to-get-enum-value-from-index-in-java
                classList.add(Classes.values()[c].name());
            }
        }
        //m.addElement(list);
        return classList;
    }
}
