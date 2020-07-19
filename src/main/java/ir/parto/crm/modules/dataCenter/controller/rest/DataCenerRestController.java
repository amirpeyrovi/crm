package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenter;
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
@RequestMapping("/v1/dataCenter/dataCenter")
public class DataCenerRestController implements RestControllerInterface {

    private DataCenterValidate dataCenterValidate;
    private DataCenterService dataCenterService;

    @Autowired
    public DataCenerRestController(DataCenterValidate dataCenterValidate, DataCenterService dataCenterService) {
        this.dataCenterValidate = dataCenterValidate;
        this.dataCenterService = dataCenterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenter")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenter> dataCenterPage = this.dataCenterService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenter",
                            sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenter dataCenter) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenter")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenter - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenter.setDataCenterId(null);

        ValidateObject validateObject = this.dataCenterValidate.validateAddNewItem(dataCenter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterService.addNewItem(dataCenter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenter dataCenter) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenter")) {
            return new ApiResponse("Error", 101,
                    Arrays.asList("DataCenter - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenter.setDataCenterId(id);

        ValidateObject validateObject = this.dataCenterValidate.validateUpdateItem(dataCenter);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.dataCenterService.
                        updateItem(dataCenter)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenter")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenter - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenter dataCenter = new DataCenter();
        dataCenter.setDataCenterId(id);
        ValidateObject validateObject = this.dataCenterValidate.deleteItem(dataCenter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterService.deleteItem(dataCenter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenter")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenter dataCenter = new DataCenter();
        dataCenter.setDataCenterId(id);
        ValidateObject validateObject = this.dataCenterValidate.findOne(dataCenter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterService.findOne(dataCenter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


}
