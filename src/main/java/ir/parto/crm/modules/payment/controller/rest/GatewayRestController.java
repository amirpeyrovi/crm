package ir.parto.crm.modules.payment.controller.rest;

import ir.parto.crm.modules.payment.controller.validate.GatewayValidate;
import ir.parto.crm.modules.payment.model.entity.Gateway;
import ir.parto.crm.modules.payment.model.service.GatewayService;
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
@RequestMapping("/v1/payment/gateway")
public class GatewayRestController implements RestControllerInterface {
    private GatewayValidate gatewayValidate;
    private GatewayService gatewayService;
    private PaymentVendorService paymentVendorService;

    @Autowired
    public GatewayRestController(GatewayValidate gatewayValidate, GatewayService gatewayService,
                                 PaymentVendorService paymentVendorService) {
        this.gatewayValidate = gatewayValidate;
        this.gatewayService = gatewayService;
        this.paymentVendorService = paymentVendorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Gateway")) {
            return new ApiResponse("Error", 101, Arrays.asList("Gateway - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.gatewayValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Gateway> gatewayPage = this.gatewayService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Gateway", sortProperty, sortOrder));
            return new ApiResponse("Success", gatewayPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Gateway gateway) {
        if (CheckPermission.getInstance().check("admin_add", "Gateway")) {
            return new ApiResponse("Error", 101, Arrays.asList("Gateway - admin_add - access denied!"))
                    .getFaultResponse();
        }

        gateway.setGatewayId(null);

        ValidateObject validateObject = this.gatewayValidate.validateAddNewItem(gateway);
        if (validateObject.getResult().equals("success")) {
            gateway.setPaymentVendor(this.paymentVendorService.findOne(gateway.getPaymentVendor()));
            return new ApiResponse("Success", Arrays.asList(this.gatewayService.addNewItem(gateway)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Gateway gateway) {
        if (CheckPermission.getInstance().check("admin_update", "Gateway")) {
            return new ApiResponse("Error", 101, Arrays.asList("Gateway - admin_update - access denied!"))
                    .getFaultResponse();
        }

        gateway.setGatewayId(id);

        ValidateObject validateObject = this.gatewayValidate.validateUpdateItem(gateway);
        if (validateObject.getResult().equals("success")) {
            try {
                gateway.setPaymentVendor(this.paymentVendorService.findOne(gateway.getPaymentVendor()));
                return new ApiResponse("Success", Arrays.asList(this.gatewayService.updateItem(gateway)))
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
        if (CheckPermission.getInstance().check("admin_delete", "Gateway")) {
            return new ApiResponse("Error", 101, Arrays.asList("Gateway - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Gateway gateway = new Gateway();
        gateway.setGatewayId(id);
        ValidateObject validateObject = this.gatewayValidate.deleteItem(gateway);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.gatewayService.deleteItem(gateway)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Gateway")) {
            return new ApiResponse("Error", 101, Arrays.asList("Gateway - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Gateway gateway = new Gateway();
        gateway.setGatewayId(id);
        ValidateObject validateObject = this.gatewayValidate.findOne(gateway);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.gatewayService.findOne(gateway)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}

