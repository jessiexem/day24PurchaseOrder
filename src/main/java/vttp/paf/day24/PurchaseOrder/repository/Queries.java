package vttp.paf.day24.PurchaseOrder.repository;

public interface Queries {

    public static final String SQL_INSERT_PURCHASE_ORDER
            = "insert into kwikmart.purchase_order(order_id,name, order_date) values (?,?,?);";

    public static final String SQL_INSERT_LINE_ITEM
            = "insert into kwikmart.line_item(quantity,order_id,prod_id) values (?,?,?);";

}
