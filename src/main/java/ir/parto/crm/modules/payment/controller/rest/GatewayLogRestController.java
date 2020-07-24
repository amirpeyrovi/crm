package ir.parto.crm.modules.payment.controller.rest;

import ir.parto.crm.modules.payment.controller.validate.GatewayLogValidate;
import ir.parto.crm.modules.payment.model.entity.GatewayLog;
import ir.parto.crm.modules.payment.model.service.GatewayLogService;
import ir.parto.crm.modules.payment.model.service.GatewayService;
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
@RequestMapping("/v1/payment/gatewayLog")
public class GatewayLogRestController implements RestControllerInterface {
    private GatewayLogValidate gatewayLogValidate;
    private GatewayLogService gatewayLogService;
    private GatewayService gatewayService;

    @Autowired
    public GatewayLogRestController(GatewayLogValidate gatewayLogValidate, GatewayLogService gatewayLogService
                               , GatewayService gatewayService ) {
        this.gatewayLogValidate = gatewayLogValidate;
        this.gatewayLogService = gatewayLogService;
        this.gatewayService = gatewayService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "GatewayLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("GatewayLog - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.gatewayLogValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<GatewayLog> gatewayPage = this.gatewayLogService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "GatewayLog", sortProperty, sortOrder));
            return new ApiResponse("Success", gatewayPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody GatewayLog gatewayLog) {
        if (CheckPermission.getInstance().check("admin_add", "GatewayLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("GatewayLog - admin_add - access denied!"))
                    .getFaultResponse();
        }

        gatewayLog.setGatewayLogId(null);

        ValidateObject validateObject = this.gatewayLogValidate.validateAddNewItem(gatewayLog);
        if (validateObject.getResult().equals("success")) {
            gatewayLog.setGateway(this.gatewayService.findOne(gatewayLog.getGateway()));
            return new ApiResponse("Success", Arrays.asList(this.gatewayLogService.addNewItem(gatewayLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody GatewayLog gatewayLog) {
        if (CheckPermission.getInstance().check("admin_update", "GatewayLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("GatewayLog - admin_update - access denied!"))
                    .getFaultResponse();
        }

        gatewayLog.setGatewayLogId(id);

        ValidateObject validateObject = this.gatewayLogValidate.validateUpdateItem(gatewayLog);
        if (validateObject.getResult().equals("success")) {
            try {
                gatewayLog.setGateway(this.gatewayService.findOne(gatewayLog.getGateway()));
                return new ApiResponse("Success", Arrays.asList(this.gatewayLogService.updateItem(gatewayLog)))
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
        if (CheckPermission.getInstance().check("admin_delete", "GatewayLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("GatewayLog - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        GatewayLog gatewayLog = new GatewayLog();
        gatewayLog.setGatewayLogId(id);
        ValidateObject validateObject = this.gatewayLogValidate.deleteItem(gatewayLog);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.gatewayLogService.deleteItem(gatewayLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "GatewayLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("GatewayLog - admin_show - access denied!"))
                    .getFaultResponse();
        }

        GatewayLog gatewayLog = new GatewayLog();
        gatewayLog.setGatewayLogId(id);
        ValidateObject validateObject = this.gatewayLogValidate.findOne(gatewayLog);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.gatewayLogService.findOne(gatewayLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}

