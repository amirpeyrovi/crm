package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterServerConfigGroupValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigGroup;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerConfigGroupService;
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
@RequestMapping("/v1/dataCenter/dataCenterServerServerConfigGroup")
public class DataCenterServerConfigGroupRestController implements RestControllerInterface {
    private DataCenterServerConfigGroupService dataCenterServerConfigGroupService;
    private DataCenterServerConfigGroupValidate dataCenterServerConfigGroupValidate;

    @Autowired
    public DataCenterServerConfigGroupRestController(DataCenterServerConfigGroupService dataCenterServerConfigGroupService,
                                                          DataCenterServerConfigGroupValidate dataCenterServerConfigGroupValidate) {
        this.dataCenterServerConfigGroupService = dataCenterServerConfigGroupService;
        this.dataCenterServerConfigGroupValidate = dataCenterServerConfigGroupValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServerConfigGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterServerConfigGroupValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterServerConfigGroup> dataCenterServerConfigPage = this.dataCenterServerConfigGroupService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenterServerConfigGroup", sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterServerConfigPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterServerConfigGroup dataCenterServerConfig) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterServerConfigGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigGroup - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServerConfig.setConfigGroupId(null);

        ValidateObject validateObject = this.dataCenterServerConfigGroupValidate.validateAddNewItem(dataCenterServerConfig);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterServerConfigGroupService.addNewItem(dataCenterServerConfig)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterServerConfigGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigGroup - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServerConfigGroup.setConfigGroupId(id);

        ValidateObject validateObject = this.dataCenterServerConfigGroupValidate.validateUpdateItem(dataCenterServerConfigGroup);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigGroupService.
                        updateItem(dataCenterServerConfigGroup)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterServerConfigGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfig - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServerConfigGroup dataCenterServerConfigGroup = new DataCenterServerConfigGroup();
        dataCenterServerConfigGroup.setConfigGroupId(id);
        ValidateObject validateObject = this.dataCenterServerConfigGroupValidate.deleteItem(dataCenterServerConfigGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigGroupService.deleteItem(dataCenterServerConfigGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServerConfigGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerConfigGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServerConfigGroup dataCenterServerConfigGroup = new DataCenterServerConfigGroup();
        dataCenterServerConfigGroup.setConfigGroupId(id);
        ValidateObject validateObject = this.dataCenterServerConfigGroupValidate.findOne(dataCenterServerConfigGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerConfigGroupService.findOne(dataCenterServerConfigGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}

