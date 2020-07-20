package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterServerConfigValueValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigValue;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigGroupService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigValueService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerService;
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
@RequestMapping("/v1/dataCenter/dataCenterServerConfigValue")
public class DataCenterServerConfigValueRestController implements RestControllerInterface {
    private DataCenterServerConfigValueService dataCenterServerConfigValueService;
    private DataCenterServerConfigValueValidate dataCenterServerConfigValueValidate;
    private DataCenterServerService dataCenterServerService;
    private DataCenterServerConfigGroupService dataCenterServerConfigGroupService;

    @Autowired
    public DataCenterServerConfigValueRestController(DataCenterServerConfigGroupService dataCenterServerConfigGroupService ,
                                                     DataCenterServerService dataCenterServerService,
                                                     DataCenterServerConfigValueService dataCenterServerConfigValueService,
                                                     DataCenterServerConfigValueValidate dataCenterServerConfigValueValidate) {
        this.dataCenterServerConfigValueService = dataCenterServerConfigValueService;
        this.dataCenterServerConfigValueValidate = dataCenterServerConfigValueValidate;
        this.dataCenterServerService = dataCenterServerService;
        this.dataCenterServerConfigGroupService = dataCenterServerConfigGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServerConfigValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterServerConfigValueValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterServerConfigValue> dataCenterServerConfigValuePage = this.dataCenterServerConfigValueService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenterServerConfigValue", sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterServerConfigValuePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterServerConfigValue dataCenterServerConfigValue) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterServerConfigValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigValue - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServerConfigValue.setConfigId(null);

        ValidateObject validateObject = this.dataCenterServerConfigValueValidate.validateAddNewItem(dataCenterServerConfigValue);
        if (validateObject.getResult().equals("success")) {
            dataCenterServerConfigValue.setDataCenterServer(this.dataCenterServerService.findOne(dataCenterServerConfigValue.
                    getDataCenterServer()));
            dataCenterServerConfigValue.setDataCenterServerConfigGroup(this.dataCenterServerConfigGroupService.findOne
                    (dataCenterServerConfigValue.getDataCenterServerConfigGroup()));
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterServerConfigValueService.addNewItem(dataCenterServerConfigValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterServerConfigValue dataCenterServerConfigValue) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterServerConfigValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigValue - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServerConfigValue.setConfigId(id);

        ValidateObject validateObject = this.dataCenterServerConfigValueValidate.validateUpdateItem(dataCenterServerConfigValue);
        if (validateObject.getResult().equals("success")) {
            try {
                dataCenterServerConfigValue.setDataCenterServer(this.dataCenterServerService.findOne(dataCenterServerConfigValue.
                        getDataCenterServer()));
                dataCenterServerConfigValue.setDataCenterServerConfigGroup(this.dataCenterServerConfigGroupService.findOne
                        (dataCenterServerConfigValue.getDataCenterServerConfigGroup()));
                return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigValueService.
                        updateItem(dataCenterServerConfigValue)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterServerConfigValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigValue - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServerConfigValue dataCenterServerConfigValue = new DataCenterServerConfigValue();
        dataCenterServerConfigValue.setConfigId(id);
        ValidateObject validateObject = this.dataCenterServerConfigValueValidate.deleteItem(dataCenterServerConfigValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigValueService.deleteItem(
                    dataCenterServerConfigValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServerConfigValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServerConfigValue dataCenterServerConfigValue = new DataCenterServerConfigValue();
        dataCenterServerConfigValue.setConfigId(id);
        ValidateObject validateObject = this.dataCenterServerConfigValueValidate.findOne(dataCenterServerConfigValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigValueService.findOne(dataCenterServerConfigValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}

