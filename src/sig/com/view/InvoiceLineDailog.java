/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.com.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JDialog;

/**
 *
 * @author Mo7am
 */
public class InvoiceLineDailog extends JDialog {
    private JTextField itemNameField;
    private JTextField itemCounField;
    private JTextField itemPriceField;
    private JLabel itemNameLabel;
    private JLabel itemCountLabel;
    private JLabel itemPriceLabel;
    private JButton oKButton;
    private JButton cancelButton;
    
    
    public InvoiceLineDailog (InvoiceFrame frame){
    itemNameField= new JTextField(20);
    itemNameLabel= new JLabel("Item Name");
    
    itemCounField= new JTextField(20);
    itemCountLabel = new JLabel("Item Count");
    
    itemPriceField = new JTextField(20);
    itemPriceLabel= new JLabel("Item Price");
    
    oKButton= new JButton("OK");
    cancelButton=new JButton("Cancel");
    
    oKButton.setActionCommand("newLineOK");
    cancelButton.setActionCommand("newLineCancel");
    
    
    oKButton.addActionListener(frame.getActionListener());
    cancelButton.addActionListener(frame.getActionListener());
    setLayout(new GridLayout(4,2));
    
    add(itemNameLabel);
    add(itemNameField);
     add(itemCountLabel);
        add(itemCounField);
        add(itemPriceLabel);
        add(itemPriceField);
        add(oKButton);
        add(cancelButton);
        
        pack();
        
        
                   
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCounField() {
        return itemCounField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
}
