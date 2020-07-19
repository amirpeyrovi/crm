package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterRackValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterRack;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterRackService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.TicketAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@TicketAnnotation
@RequestMapping("/v1/dataCenter/dataCenterRack")
public class DataCenterRackRestController implements RestControllerInterface {
    private DataCenterRackValidate dataCenterRackValidate;
    private DataCenterRackService dataCenterRackService;
    private DataCenterService dataCenterService;

    @Autowired
    public DataCenterRackRestController(DataCenterService dataCenterService,DataCenterRackValidate dataCenterRackValidate, DataCenterRackService dataCenterRackService) {
        this.dataCenterRackValidate = dataCenterRackValidate;
        this.dataCenterRackService = dataCenterRackService;
        this.dataCenterService = dataCenterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterRack - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterRackValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterRack> dataCenterRackPage = this.dataCenterRackService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenterRack", sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterRackPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterRack dataCenterRack) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterRack - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterRack.setRackId(null);

        ValidateObject validateObject = this.dataCenterRackValidate.validateAddNewItem(dataCenterRack);
        if (validateObject.getResult().equals("success")) {
            dataCenterRack.setDataCenter(this.dataCenterService.findOne(dataCenterRack.getDataCenter()));
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterRackService.addNewItem(dataCenterRack)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterRack dataCenterRack) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterRack - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterRack.setRackId(id);

        ValidateObject validateObject = this.dataCenterRackValidate.validateUpdateItem(dataCenterRack);
        if (validateObject.getResult().equals("success")) {
            try {
                dataCenterRack.setDataCenter(this.dataCenterService.findOne(dataCenterRack.getDataCenter()));
                return new ApiResponse("Success", Arrays.asList(this.dataCenterRackService.
                        updateItem(dataCenterRack)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterRack - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterRack dataCenterRack = new DataCenterRack();
        dataCenterRack.setRackId(id);
        ValidateObject validateObject = this.dataCenterRackValidate.deleteItem(dataCenterRack);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterRackService.deleteItem(dataCenterRack)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterRack - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterRack dataCenterRack = new DataCenterRack();
        dataCenterRack.setRackId(id);
        ValidateObject validateObject = this.dataCenterRackValidate.findOne(dataCenterRack);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterRackService.findOne(dataCenterRack)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
