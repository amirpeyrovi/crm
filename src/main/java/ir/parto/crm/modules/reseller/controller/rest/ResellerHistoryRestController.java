package ir.parto.crm.modules.reseller.controller.rest;

import ir.parto.crm.modules.order.model.service.OrderItemService;
import ir.parto.crm.modules.reseller.controller.validate.ResellerHistoryValidate;
import ir.parto.crm.modules.reseller.controller.validate.ResellerValidate;
import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.modules.reseller.model.entity.ResellerHistory;
import ir.parto.crm.modules.reseller.model.service.ResellerHistoryService;
import ir.parto.crm.modules.reseller.model.service.ResellerService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ResellerAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@ResellerAnnotation
@RequestMapping(value = "/v1/reseller/resellerHistory")
public class ResellerHistoryRestController implements RestControllerInterface {
    private ResellerHistoryValidate resellerHistoryValidate;
    private ResellerValidate resellerValidate;
    private ResellerHistoryService resellerHistoryService;
    private OrderItemService orderItemService;
    private ResellerService resellerService;

    @Autowired
    public ResellerHistoryRestController(ResellerHistoryValidate resellerHistoryValidate, ResellerValidate resellerValidate, ResellerHistoryService resellerHistoryService, OrderItemService orderItemService, ResellerService resellerService) {
        this.resellerHistoryValidate = resellerHistoryValidate;
        this.resellerValidate = resellerValidate;
        this.resellerHistoryService = resellerHistoryService;
        this.orderItemService = orderItemService;
        this.resellerService = resellerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ResellerHistory")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerHistory - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.resellerHistoryValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ResellerHistory> resellerHistoryPage = this.resellerHistoryService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ResellerHistory", sortProperty, sortOrder));
            return new ApiResponse("Success", resellerHistoryPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/reseller/{id}", method = RequestMethod.GET)
    public Object findAllByReseller(@PathVariable("id") Long id,
                                    @RequestParam(required = false, defaultValue = "0") String page,
                                    @RequestParam(required = false, defaultValue = "default") String sortProperty,
                                    @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ResellerHistory")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerHistory - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.resellerHistoryValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Reseller reseller = new Reseller();
            reseller.setResellerId(id);
            ValidateObject validateObjectReseller = this.resellerValidate.findOne(reseller);
            if (validateObjectReseller.getResult().equals("success")) {
                Reseller resellerExist = this.resellerService.findOne(reseller);
                Page<ResellerHistory> resellerHistoryPage = this.resellerHistoryService.findAllItemByReseller(resellerExist, PageableRequest.getInstance().createPageRequest(page, "ResellerHistory", sortProperty, sortOrder));
                return new ApiResponse("Success", resellerHistoryPage)
                        .getSuccessResponse();
            } else {
                return new ApiResponse("Error", 102, validateObjectReseller.getMessages())
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ResellerHistory resellerHistory) {
        if (CheckPermission.getInstance().check("admin_add", "ResellerHistory")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerHistory - admin_add - access denied!"))
                    .getFaultResponse();
        }

        resellerHistory.setResellerHistoryId(null);

        ValidateObject validateObject = this.resellerHistoryValidate.validateAddNewItem(resellerHistory);
        if (validateObject.getResult().equals("success")) {
            resellerHistory.setReseller(this.resellerService.findOne(resellerHistory.getReseller()));
            resellerHistory.setOrderItem(this.orderItemService.findOne(resellerHistory.getOrderItem()));
            return new ApiResponse("Success", Arrays.asList(this.resellerHistoryService.addNewItem(resellerHistory)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ResellerHistory resellerHistory) {
        if (CheckPermission.getInstance().check("admin_update", "ResellerHistory")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerHistory - admin_update - access denied!"))
                    .getFaultResponse();
        }

        resellerHistory.setResellerHistoryId(id);

        ValidateObject validateObject = this.resellerHistoryValidate.validateUpdateItem(resellerHistory);
        if (validateObject.getResult().equals("success")) {
            try {
                resellerHistory.setReseller(this.resellerService.findOne(resellerHistory.getReseller()));
                resellerHistory.setOrderItem(this.orderItemService.findOne(resellerHistory.getOrderItem()));
                return new ApiResponse("Success", Arrays.asList(this.resellerHistoryService.updateItem(resellerHistory)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ResellerHistory")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerHistory - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ResellerHistory resellerHistory = new ResellerHistory();
        resellerHistory.setResellerHistoryId(id);
        ValidateObject validateObject = this.resellerHistoryValidate.deleteItem(resellerHistory);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.resellerHistoryService.deleteItem(resellerHistory)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Product")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ResellerHistory resellerHistory = new ResellerHistory();
        resellerHistory.setResellerHistoryId(id);
        ValidateObject validateObject = this.resellerHistoryValidate.findOne(resellerHistory);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.resellerHistoryService.findOne(resellerHistory)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
