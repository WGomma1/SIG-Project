/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.com.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *
 * @author Mo7am
 */
public class InvoiceHeaderDailog extends JDialog {
    private JTextField custNameField;
    private JTextField invDatField;
    private JLabel custNamLabel;
    private JLabel invDatLabel;
    private JButton okBtn;
    private JButton canceButton;
    
    public InvoiceHeaderDailog(InvoiceFrame frame){
    custNamLabel= new JLabel("Customer Name :");
    custNameField= new JTextField(20);
    invDatLabel= new JLabel("Invoice Date :");
    invDatField= new JTextField(20);
    okBtn= new JButton("OK");
    canceButton= new JButton("Cancel");
    
    okBtn.setActionCommand("newInvoiceOK");
    canceButton.setActionCommand("newInvoiceCancel");
    
    okBtn.addActionListener(frame.getActionListener());
    canceButton.addActionListener(frame.getActionListener());
    setLayout(new GridLayout(3,2));
    
    
    add(invDatLabel);
    add(invDatField);
    add(custNamLabel);
    add(custNameField);
    add(okBtn);
    add(canceButton);
    
    pack();
    
    
    }

    public JTextField getcustNaField(){
    return custNameField;
    }
    
    public JTextField getInvDatField(){
        return invDatField;
    
    }

}
    
    

    

    
    

