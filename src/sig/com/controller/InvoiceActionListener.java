/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.com.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import sig.com.Model.InvoiceHeader;
import java.nio.file.Path;
import java.util.List;
import sig.com.Model.InvoiceHeaderTableModel;
import sig.com.Model.InvoiceLine;
import sig.com.Model.InvoiceLineTableModel;
import sig.com.view.InvoiceHeaderDailog;
import sig.com.view.InvoiceLineDailog;
import sig.com.view.InvoiceFrame;
/**
 *
 * @author Mo7am
 */
public class InvoiceActionListener implements ActionListener{

    private InvoiceFrame frame;
    private InvoiceHeaderDailog headerDailog;
    private InvoiceLineDailog lineDailog;
    
    
    public InvoiceActionListener(InvoiceFrame frame){
        this.frame = frame;
    }

    public InvoiceActionListener() {
        
    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Save Files":
                saveFiles();
            break;
            case "Load Files":
                loadFiles();
            break;
            case "New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break; 
            case "Create Line":
                creatNewLine();
                break;
            case "Delete Line":
                deleteLine();
                break;
            case "newInvoiceOK":
                newInvoiceOK();
                break;
            case "newInvoiceCancel":
                newInvoiceCancel();
                break;     
            case "newLineOK":
                newLineOK();
                break;
            case "newLineCancel":
                newLineCancel();
                break;
        }
    }

    private void saveFiles() {
        ArrayList<InvoiceHeader> invoicesArray = frame.getInvoicesArray();
        JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File firstFile = fileChooser.getSelectedFile();
                FileWriter firstFileWriter = new FileWriter(firstFile);
                String headers = "";
                String lines = "";
                for (InvoiceHeader invoiceHeader : invoicesArray) {
                    headers += invoiceHeader.toString();
                    headers += "\n";
                    for (InvoiceLine line : invoiceHeader.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fileChooser.showSaveDialog(frame);
                File secondFile = fileChooser.getSelectedFile();
                FileWriter secondFileWriter = new FileWriter(secondFile);
                firstFileWriter.write(headers);
                secondFileWriter.write(lines);
                firstFileWriter.close();
                secondFileWriter.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "unable to save", "Error", JOptionPane.ERROR_MESSAGE);
        }       
                
                
    }

    private void loadFiles(){
        JFileChooser fileChooser= new JFileChooser();
        try{
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
        File headerFile = fileChooser.getSelectedFile();
        Path headerPath = Paths.get(headerFile.getAbsolutePath());
        List<String> headerLines = Files.readAllLines(headerPath);
        ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
             for (String headerLine : headerLines){
                String[]arr= headerLine.split(",");
                String str1=arr[0];
                String str2=arr[1];
                String str3=arr[2];
                int code =Integer.parseInt(str1);
                Date invoiceDate =InvoiceFrame.dateFormat.parse(str2);
                InvoiceHeader header = new InvoiceHeader(code, str3, invoiceDate);
                invoiceHeaders.add(header);
        }
             frame.setInvoicesArray(invoiceHeaders);
             
            result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION){
        File lineFile = fileChooser.getSelectedFile();
        Path linePath = Paths.get(lineFile.getAbsolutePath());
        List<String> lineLines = Files.readAllLines(linePath);
        for (String lineLine : lineLines){
                String[]arr= lineLine.split(",");
                String str1=arr[0];
                String str2=arr[1];
                String str3=arr[2];
                String str4=arr[3];
                int invCode =Integer.parseInt(str1);
                double price = Double.parseDouble(str3);
                int count = Integer.parseInt(str4);
                InvoiceHeader inv =frame.getInvObject(invCode);
                InvoiceLine line =new InvoiceLine(str2, price, count, inv);
                inv.getLines().add(line);
        }
                System.out.println("Reaing Files \n.....................................................................");
                System.out.println("Headers ....................................");
                int n = 1;
                for (String headerLine : headerLines){
                    System.out.println("Header Number" + n + ":" + headerLine);
                    n++;
                }
                System.out.println("Lines ....................................");
                int x = 1;
                for (String lineLine : lineLines){
                    System.out.println("Line Number" + x + ":" + lineLine);
                    x++;
                }
                System.out.println("..................................................................... \n Done");
        }
            InvoiceHeaderTableModel headerTableModel = new InvoiceHeaderTableModel(invoiceHeaders);
            frame.setHeaderTableModel(headerTableModel);
            frame.getInvHTBL().setModel(headerTableModel);
            
        }
        } catch (IOException | ParseException ex){
        JOptionPane.showMessageDialog(frame, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private void createNewInvoice() {
        headerDailog = new InvoiceHeaderDailog(frame);
        headerDailog.setVisible(true);
        
    }

    private void deleteInvoice() {
        int selectedInvoice = frame.getInvHTBL().getSelectedRow();
        if (selectedInvoice != -1) {
            frame.getInvoicesArray().remove(selectedInvoice);
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInvLTBL().setModel(new InvoiceLineTableModel(null));
            frame.setLinesArray(null);
            frame.getInvNumber().setText("");
            frame.getInvName().setText("");
            frame.getInvDate().setText("");
            frame.getInvTotal().setText("");
        }
    }

    private void creatNewLine() {
       lineDailog = new InvoiceLineDailog(frame);
       lineDailog.setVisible(true);
       
    }

    private void deleteLine() {
        int selectedLine = frame.getInvLTBL().getSelectedRow();
        int selectedInvoice = frame.getInvHTBL().getSelectedRow();
        if (selectedLine != -1) {
            frame.getLinesArray().remove(selectedLine);
            InvoiceLineTableModel lineTableModel = (InvoiceLineTableModel) frame.getInvLTBL().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getInvTotal().setText("" + frame.getInvoicesArray().get(selectedInvoice).getInvoiceTotal());
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInvHTBL().setRowSelectionInterval(selectedInvoice, selectedInvoice);
        }
    }
    private void newInvoiceOK() {
        headerDailog.setVisible(false);
        String str1 = headerDailog.getcustNaField().getText();
        String str2 = headerDailog.getInvDatField().getText();
        Date date = new Date();
        try {
            date = InvoiceFrame.dateFormat.parse(str2);
        } catch (ParseException parseException){
            JOptionPane.showMessageDialog(frame, "Cannot convert to date", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }
        
        int num = 0;
        for (InvoiceHeader invoiceHeader: frame.getInvoicesArray()){
            if (num < invoiceHeader.getNum()){
                num = invoiceHeader.getNum();
            }        
        }
        num++;
        InvoiceHeader invoiceHeader = new InvoiceHeader(num, str1, date);
        frame.getInvoicesArray().add(invoiceHeader);
        frame.getHeaderTableModel().fireTableDataChanged();
        headerDailog.dispose();
        headerDailog = null;
    }

    private void newInvoiceCancel() {
       headerDailog.setVisible(false);
       headerDailog.dispose();
       headerDailog = null;
    }

    private void newLineOK() {
        lineDailog.setVisible(false);
        String str1 = lineDailog.getItemNameField().getText();
        String str2 = lineDailog.getItemCounField().getText();
        String str3 = lineDailog.getItemPriceField().getText();
        int count = 0;
        double price = 0;
        try {
            count = Integer.parseInt(str2);
        } catch (NumberFormatException exception){JOptionPane.showMessageDialog(frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }  
        try {
            price = Double.parseDouble(str3);
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
            
        int selectedInvoice = frame.getInvHTBL().getSelectedRow();
        if (selectedInvoice != -1){
            InvoiceHeader invoiceHeader = frame.getInvoicesArray().get(selectedInvoice);
            InvoiceLine invoiceLine = new InvoiceLine(str1, price, count, invoiceHeader);
            frame.getLinesArray().add(invoiceLine);
            InvoiceLineTableModel invoiceLineTableModel = (InvoiceLineTableModel) frame.getInvLTBL().getModel();
            invoiceLineTableModel.fireTableDataChanged();
            frame.getHeaderTableModel().fireTableDataChanged();
        }
            frame.getInvHTBL().setRowSelectionInterval(selectedInvoice, selectedInvoice);  
    }   

    private void newLineCancel() {
       lineDailog.setVisible(false);
       lineDailog.dispose();
       lineDailog = null;
    }


}
