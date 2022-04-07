package vttp.paf.day24.PurchaseOrder.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vttp.paf.day24.PurchaseOrder.model.LineItem;
import vttp.paf.day24.PurchaseOrder.model.PurchaseOrder;

import java.util.List;

@Repository
public class PORepository implements Queries{

    @Autowired
    private JdbcTemplate template;

    public boolean insertPurchaseOrder(PurchaseOrder po) {

        int added = template.update(SQL_INSERT_PURCHASE_ORDER,
                po.getOrder_id(),
                po.getName(),
                po.getOrder_date());

        return added>0;
    }

    public boolean insertLineItems(List<LineItem> list) {
        for (LineItem item : list) {
            if(!insertLineItem(item)) {
                return false;
            }
        }
        return true;
    }

    public boolean insertLineItem(LineItem li) {

        int added = template.update(SQL_INSERT_LINE_ITEM,
                li.getQuantity(),
                li.getOrder_id(),
                li.getItem_id());

        return added>0;
    }
}
