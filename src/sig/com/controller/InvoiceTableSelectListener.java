/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.com.controller;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sig.com.Model.InvoiceHeader;
import sig.com.Model.InvoiceLine;
import sig.com.Model.InvoiceLineTableModel;
import sig.com.view.InvoiceFrame;

/**
 *
 * @author Mo7am
 */
public class InvoiceTableSelectListener implements ListSelectionListener{
    private InvoiceFrame frame;

    public InvoiceTableSelectListener(InvoiceFrame frame) {
        this.frame = frame;
    }
    

    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvIndex = frame.getInvHTBL().getSelectedRow();
        if (selectedInvIndex != -1){
        InvoiceHeader selectedInv =frame.getInvoicesArray().get(selectedInvIndex);
        ArrayList<InvoiceLine> lines = selectedInv.getLines();
         InvoiceLineTableModel lineTableModel= new InvoiceLineTableModel(lines);
         frame.setLinesArray(lines);
         frame.getInvLTBL().setModel(lineTableModel);
         frame.getInvName().setText(selectedInv.getCustomer());
         frame.getInvNumber().setText(""+selectedInv.getNum());
         frame.getInvTotal().setText("" + selectedInv.getInvoiceTotal());
         frame.getInvDate().setText(InvoiceFrame.dateFormat.format(selectedInv.getInvoiceDate()));
    }
    }
}