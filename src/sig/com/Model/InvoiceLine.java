
package sig.com.Model;

public class InvoiceLine {
    
    private String item;
    private double itemPrice;
    private int count;
    private InvoiceHeader header;
   

    public InvoiceLine(String item, double price, int count, InvoiceHeader header) {
        this.item = item;
        this.itemPrice = price;
        this.count = count;
        this.header = header;
    }

    public InvoiceLine() {
    }

    
    
    
    public String getItem() {
        return item;
    }

    public void setItem(String Item) {
        this.item = Item;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double ItemPrice) {
        this.itemPrice = ItemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int Count) {
        this.count = Count;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }
    
    public double getLineTotal(){
        return itemPrice * count;
    }

    @Override
    public String toString() {
        return header.getNum() + "," + item + "," + itemPrice + "," + count;
    }

   
  
    
  
    
    
}
