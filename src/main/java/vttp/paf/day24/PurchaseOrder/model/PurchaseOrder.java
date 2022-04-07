package vttp.paf.day24.PurchaseOrder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PurchaseOrder {

    private String order_id;
    private String name;
    private Date order_date;
    private List<LineItem> lineItems = new ArrayList<>();

    public PurchaseOrder() {
        this.order_id = generateId(8);
    }

    public PurchaseOrder(String name, Date order_date, List<LineItem> lineItems) {
        this.name = name;
        this.order_date = order_date;
        this.lineItems = lineItems;
        this.order_id = generateId(8);
    }

    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length()<numChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.substring(0,numChars);
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id() {
        this.order_id = generateId(8);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }

    public void addLineItems(LineItem lineItem) {this.lineItems.add(lineItem); }
}
