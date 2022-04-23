/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.com.Model;
 
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import sig.com.view.InvoiceFrame;
 
public class InvoiceHeaderTableModel extends AbstractTableModel {
    private ArrayList<InvoiceHeader> invoicesArray;
    private String[] columns ={"Invoice Num","Invoice Date","Customer Name","Invoice Total"};
    
    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }
    
    

    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv = invoicesArray.get(rowIndex);
        switch (columnIndex) {
            case 0:return inv.getNum();
            case 1:return InvoiceFrame.dateFormat.format(inv.getInvoiceDate());
            case 2:return inv.getCustomer();
            case 3:return inv.getInvoiceTotal();
        }
        return"";
    }
    
    @Override
    public String getColumnName(int column){
        return columns[column];
    }
}
