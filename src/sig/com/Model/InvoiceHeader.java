/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.com.Model;

import java.util.Date;
import java.util.ArrayList;
import sig.com.view.InvoiceFrame;

public class InvoiceHeader {
private  int num;
private String customer;
private Date invoiceDate;
private ArrayList<InvoiceLine> lines;


    public InvoiceHeader() {
    }

    public InvoiceHeader(int num, String customer, Date invoiceDate) {
        this.num = num;
        this.customer = customer;
        this.invoiceDate = invoiceDate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    
    public ArrayList<InvoiceLine> getLines() {
        if(lines == null){
         lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    
    }
    public double getInvoiceTotal() {
        double total = 0.0;
        
        for (int i = 0; i < getLines().size(); i++) {
            total += getLines().get(i).getLineTotal();
        }
        
        return total;
        
    }

    @Override
    public String toString() {
        return num + "," + InvoiceFrame.dateFormat.format(invoiceDate) + "," + customer;
    }
}
