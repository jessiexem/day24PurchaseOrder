package vttp.paf.day24.PurchaseOrder.model;

import org.springframework.util.MultiValueMap;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class ConversionUtils {

    public static Optional<PurchaseOrder> convert (MultiValueMap<String,String> form) {
        PurchaseOrder po = new PurchaseOrder();
        po.setOrder_id();
        po.setName(form.getFirst("name"));

        SimpleDateFormat formatter1 =new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate = null;
        try {
            orderDate = formatter1.parse(form.getFirst("date"));
        } catch (ParseException e) {
            System.out.println("Controller: Cannot parse date");
        }
        po.setOrder_date(orderDate);

        int i=1;
        while (i<5) {
            String sku_ = form.getFirst(String.format("sku%d",i));
            if (null == sku_ || 0 == sku_.trim().length()) {
                break;
            }
            LineItem lineItem = new LineItem();
            lineItem.setItem_id(Integer.parseInt(sku_));
            lineItem.setQuantity(Integer.parseInt(form.getFirst(String.format("qty%d",i))));
            lineItem.setOrder_id(po.getOrder_id());
            po.addLineItems(lineItem);
            i++;
        }
        return Optional.of(po);
    }
}
