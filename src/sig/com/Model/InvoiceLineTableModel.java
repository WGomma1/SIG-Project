/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.com.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mo7am
 */
public class InvoiceLineTableModel extends AbstractTableModel{
    
    private ArrayList<InvoiceLine> linesArray;
    private String[] columns ={"Item Name","Unit Price","count","Line Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLine> linesArray) {
        this.linesArray = linesArray;
    }
    

    @Override
    public int getRowCount() {
        if (linesArray == null){
            return 0;
        }
        return linesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line =linesArray.get(rowIndex);
        if (linesArray==null){
            return "";
        }else {
        switch (columnIndex){
            case 0: return line.getItem();
            case 1: return line.getItemPrice();
            case 2: return line.getCount();
            case 3: return line.getLineTotal();
            default : return "";
        }
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
}
