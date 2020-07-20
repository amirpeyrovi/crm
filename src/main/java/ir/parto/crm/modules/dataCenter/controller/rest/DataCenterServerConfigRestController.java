package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterServerConfigValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfig;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigGroupService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.DataCenterAnnotation;
import ir.parto.crm.utils.annotations.ProductAnnotation;
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
@RequestMapping("/v1/dataCenter/dataCenterServerConfig")
public class DataCenterServerConfigRestController implements RestControllerInterface {
    private DataCenterServerConfigService dataCenterServerConfigService;
    private DataCenterServerConfigValidate dataCenterServerConfigValidate;
    private DataCenterServerConfigGroupService dataCenterServerConfigGroupService;

    @Autowired
    public DataCenterServerConfigRestController(DataCenterServerConfigGroupService dataCenterServerConfigGroupService,
                                                DataCenterServerConfigService dataCenterServerConfigService, DataCenterServerConfigValidate dataCenterServerConfigValidate) {
        this.dataCenterServerConfigService = dataCenterServerConfigService;
        this.dataCenterServerConfigValidate = dataCenterServerConfigValidate;
        this.dataCenterServerConfigGroupService = dataCenterServerConfigGroupService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServerConfig")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfig - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterServerConfigValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterServerConfig> dataCenterServerConfigPage = this.dataCenterServerConfigService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenterServerConfig", sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterServerConfigPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterServerConfig dataCenterServerConfig) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterServerConfig")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfig - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServerConfig.setConfigId(null);

        ValidateObject validateObject = this.dataCenterServerConfigValidate.validateAddNewItem(dataCenterServerConfig);
        if (validateObject.getResult().equals("success")) {
            dataCenterServerConfig.setDataCenterServerConfigGroup(this.dataCenterServerConfigGroupService.findOne(dataCenterServerConfig.
                    getDataCenterServerConfigGroup()));
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterServerConfigService.addNewItem(dataCenterServerConfig)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterServerConfig dataCenterServerConfig) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterServerConfig")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfig - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServerConfig.setConfigId(id);

        ValidateObject validateObject = this.dataCenterServerConfigValidate.validateUpdateItem(dataCenterServerConfig);
        if (validateObject.getResult().equals("success")) {
            try {
                dataCenterServerConfig.setDataCenterServerConfigGroup(this.dataCenterServerConfigGroupService.findOne(
                        dataCenterServerConfig.getDataCenterServerConfigGroup()));
                return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigService.
                        updateItem(dataCenterServerConfig)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterServerConfig")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfig - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServerConfig dataCenterServerConfig = new DataCenterServerConfig();
        dataCenterServerConfig.setConfigId(id);
        ValidateObject validateObject = this.dataCenterServerConfigValidate.deleteItem(dataCenterServerConfig);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigService.deleteItem(dataCenterServerConfig)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServerConfig")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfig - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServerConfig dataCenterServerConfig = new DataCenterServerConfig();
        dataCenterServerConfig.setConfigId(id);
        ValidateObject validateObject = this.dataCenterServerConfigValidate.findOne(dataCenterServerConfig);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigService.findOne(dataCenterServerConfig)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
