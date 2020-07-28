package ir.parto.crm.modules.payment.controller.rest;

import ir.parto.crm.modules.payment.controller.validate.PaymentVendorValidate;
import ir.parto.crm.modules.payment.model.entity.PaymentVendor;
import ir.parto.crm.modules.payment.model.service.PaymentVendorService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.PaymentAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@PaymentAnnotation
@RequestMapping("/v1/payment/paymentVandor")
public class PaymentVendorRestController  implements RestControllerInterface {
    private PaymentVendorValidate paymentVendorValidate;
    private PaymentVendorService paymentVendorService;

    @Autowired
    public PaymentVendorRestController(PaymentVendorValidate paymentVendorValidate, PaymentVendorService paymentVendorService) {
        this.paymentVendorValidate = paymentVendorValidate;
        this.paymentVendorService = paymentVendorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PaymentVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PaymentVendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.paymentVendorValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PaymentVendor> paymentVendorPage = this.paymentVendorService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PaymentVendor", sortProperty, sortOrder));
            return new ApiResponse("Success", paymentVendorPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PaymentVendor paymentVendor) {
        if (CheckPermission.getInstance().check("admin_add", "PaymentVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PaymentVendor - admin_add - access denied!"))
                    .getFaultResponse();
        }

        paymentVendor.setVendorId(null);

        ValidateObject validateObject = this.paymentVendorValidate.validateAddNewItem(paymentVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.paymentVendorService.addNewItem(paymentVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PaymentVendor paymentVendor) {
        if (CheckPermission.getInstance().check("admin_update", "PaymentVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PaymentVendor - admin_update - access denied!"))
                    .getFaultResponse();
        }

        paymentVendor.setVendorId(id);

        ValidateObject validateObject = this.paymentVendorValidate.validateUpdateItem(paymentVendor);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.paymentVendorService.updateItem(paymentVendor)))
                        .getSuccessResponse();
            } catch (InvocationTargetException e) {
                return new ApiResponse("Error", 103, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            } catch (IllegalAccessException e) {
                return new ApiResponse("Error", 104, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_delete", "PaymentVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PaymentVendor - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PaymentVendor paymentVendor = new PaymentVendor();
        paymentVendor.setVendorId(id);
        ValidateObject validateObject = this.paymentVendorValidate.deleteItem(paymentVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.paymentVendorService.deleteItem(paymentVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PaymentVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PaymentVendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PaymentVendor paymentVendor = new PaymentVendor();
        paymentVendor.setVendorId(id);
        ValidateObject validateObject = this.paymentVendorValidate.findOne(paymentVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.paymentVendorService.findOne(paymentVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}

