package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterSwitchValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitch;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterRackService;
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
@RequestMapping("/v1/dataCenter/dataCenterSwitch")
public class DataCenterSwitchRestController implements RestControllerInterface {
    private DataCenterSwitchValidate dataCenterSwitchValidate;
    private DataCenterSwitchService dataCenterSwitchService ;
    private DataCenterRackService dataCenterRackService ;

    @Autowired
    public DataCenterSwitchRestController(DataCenterRackService dataCenterRackService,DataCenterSwitchValidate dataCenterSwitchValidate, DataCenterSwitchService dataCenterSwitchService) {
        this.dataCenterSwitchValidate = dataCenterSwitchValidate;
        this.dataCenterSwitchService = dataCenterSwitchService;
        this.dataCenterRackService = dataCenterRackService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitch - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterSwitchValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterSwitch> dataCenterSwitchPage = this.dataCenterSwitchService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenterSwitch",
                            sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterSwitchPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterSwitch dataCenterSwitch) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitch - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterSwitch.setSwitchId(null);

        ValidateObject validateObject = this.dataCenterSwitchValidate.validateAddNewItem(dataCenterSwitch);
        if (validateObject.getResult().equals("success")) {
            dataCenterSwitch.setDataCenterRack(this.dataCenterRackService.findOne(dataCenterSwitch.getDataCenterRack()));
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterSwitchService.addNewItem(dataCenterSwitch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterSwitch dataCenterSwitch) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterSwitch")) {
            return new ApiResponse("Error", 101,
                    Arrays.asList("DataCenterSwitch - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterSwitch.setSwitchId(id);

        ValidateObject validateObject = this.dataCenterSwitchValidate.validateUpdateItem(dataCenterSwitch);
        if (validateObject.getResult().equals("success")) {
            try {
                dataCenterSwitch.setDataCenterRack(this.dataCenterRackService.findOne(dataCenterSwitch.getDataCenterRack()));
                return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchService.
                        updateItem(dataCenterSwitch)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitch - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterSwitch dataCenterSwitch = new DataCenterSwitch();
        dataCenterSwitch.setSwitchId(id);
        ValidateObject validateObject = this.dataCenterSwitchValidate.deleteItem(dataCenterSwitch);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchService.deleteItem(dataCenterSwitch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitch - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterSwitch dataCenterSwitch = new DataCenterSwitch();
        dataCenterSwitch.setSwitchId(id);
        ValidateObject validateObject = this.dataCenterSwitchValidate.findOne(dataCenterSwitch);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchService.findOne(dataCenterSwitch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
