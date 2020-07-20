package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterSwitchPortValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchPort;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchPortService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.DataCenterAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@DataCenterAnnotation
@RequestMapping("/v1/dataCenter/dataCenterSwitchPort")
public class DataCenterSwitchPortRestController  implements RestControllerInterface {
    private DataCenterSwitchPortValidate dataCenterSwitchPortValidate;
    private DataCenterSwitchPortService dataCenterSwitchPortService;
    private DataCenterSwitchService dataCenterSwitchService;

    @Autowired
    public DataCenterSwitchPortRestController(DataCenterSwitchService dataCenterSwitchService ,DataCenterSwitchPortValidate dataCenterSwitchPortValidate, DataCenterSwitchPortService dataCenterSwitchPortService) {
        this.dataCenterSwitchPortValidate = dataCenterSwitchPortValidate;
        this.dataCenterSwitchPortService = dataCenterSwitchPortService;
        this.dataCenterSwitchService = dataCenterSwitchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterSwitchPort")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchPort - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterSwitchPortValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterSwitchPort> dataCenterSwitchPortPage = this.dataCenterSwitchPortService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "DataCenterSwitchPort", sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterSwitchPortPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterSwitchPort dataCenterSwitchPort) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterSwitchPort")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchPort - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterSwitchPort.setPortId(null);

        ValidateObject validateObject = this.dataCenterSwitchPortValidate.validateAddNewItem(dataCenterSwitchPort);
        if (validateObject.getResult().equals("success")) {
            dataCenterSwitchPort.setDataCenterSwitch(this.dataCenterSwitchService.findOne(dataCenterSwitchPort.getDataCenterSwitch()));
            return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchPortService.addNewItem(dataCenterSwitchPort)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterSwitchPort dataCenterSwitchPort) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterSwitchPort")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchPort - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterSwitchPort.setPortId(id);

        ValidateObject validateObject = this.dataCenterSwitchPortValidate.validateUpdateItem(dataCenterSwitchPort);
        if (validateObject.getResult().equals("success")) {
            try {
                dataCenterSwitchPort.setDataCenterSwitch(this.dataCenterSwitchService.findOne(dataCenterSwitchPort.getDataCenterSwitch()));
                return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchPortService.updateItem(dataCenterSwitchPort)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterSwitchPort")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchPort - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterSwitchPort dataCenterSwitchPort = new DataCenterSwitchPort();
        dataCenterSwitchPort.setPortId(id);
        ValidateObject validateObject = this.dataCenterSwitchPortValidate.deleteItem(dataCenterSwitchPort);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchPortService.deleteItem(dataCenterSwitchPort)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterSwitchPort")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchPort - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterSwitchPort dataCenterSwitchPort = new DataCenterSwitchPort();
        dataCenterSwitchPort.setPortId(id);
        ValidateObject validateObject = this.dataCenterSwitchPortValidate.findOne(dataCenterSwitchPort);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchPortService.findOne(dataCenterSwitchPort)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
