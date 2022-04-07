package vttp.paf.day24.PurchaseOrder.model;


public class LineItem {
    private int item_id;
    private int quantity;
    private String order_id;
    private int prod_id;


    public LineItem(int quantity, String order_id, int prod_id) {
        this.quantity = quantity;
        this.order_id = order_id;
        this.prod_id = prod_id;
    }

    public LineItem() {

    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }


}
