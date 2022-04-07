package vttp.paf.day24.PurchaseOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import vttp.paf.day24.PurchaseOrder.model.ConversionUtils;
import vttp.paf.day24.PurchaseOrder.model.PurchaseOrder;
import vttp.paf.day24.PurchaseOrder.repository.PORepository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.util.Optional;

@Controller
public class POController {

    @Autowired
    private PORepository poRepo;

    @PostMapping("/purchaseorder")
    public ModelAndView createPO(@RequestBody MultiValueMap<String, String> form) {
        System.out.println("in the controller");
        ModelAndView mvc = new ModelAndView();

        Optional<PurchaseOrder> opt = ConversionUtils.convert(form);

        if(opt.isEmpty()) {
            mvc.addObject("errMsg","Your order has an error. Cannot create");
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.setViewName("poerror");
            return mvc;
        }

        PurchaseOrder po = opt.get();
        System.out.println(String.format(">>line items =%d",po.getLineItems().size()));

        try {
            boolean poSuccess = poRepo.insertPurchaseOrder(po);
            boolean liSuccess = poRepo.insertLineItems(po.getLineItems());

            if (!poSuccess || !liSuccess) {
                mvc.addObject("errMsg","Your order has an error. Cannot create");
                mvc.setStatus(HttpStatus.BAD_REQUEST);
                mvc.setViewName("poerror");
                return mvc;
            }
        }
        catch (DataAccessException e) {
            mvc.addObject("errMsg","Cannot create PO. Please check if SKU is valid.");
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.setViewName("poerror");
            return mvc;
        }

        mvc.addObject("orderId",po.getOrder_id());
        mvc.setStatus(HttpStatus.CREATED);
        mvc.setViewName("created");
        return mvc;
    }
}
