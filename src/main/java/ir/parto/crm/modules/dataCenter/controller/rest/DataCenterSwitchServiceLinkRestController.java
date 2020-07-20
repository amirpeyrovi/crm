package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterSwitchServiceLinkValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchServiceLink;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchServiceLinkService;
import ir.parto.crm.modules.service.model.service.ServiceService;
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
@RequestMapping("/v1/dataCenter/dataCenterSwitchServiceLink")
public class DataCenterSwitchServiceLinkRestController implements RestControllerInterface {
    private DataCenterSwitchServiceLinkValidate dataCenterSwitchServiceLinkValidate;
    private DataCenterSwitchServiceLinkService dataCenterSwitchServiceLinkService;
    private DataCenterSwitchService dataCenterSwitchService;
    private ServiceService serviceService;

    @Autowired
    public DataCenterSwitchServiceLinkRestController(ServiceService serviceService ,DataCenterSwitchServiceLinkValidate dataCenterSwitchServiceLinkValidate, DataCenterSwitchServiceLinkService dataCenterSwitchServiceLinkService) {
        this.dataCenterSwitchServiceLinkValidate = dataCenterSwitchServiceLinkValidate;
        this.dataCenterSwitchServiceLinkService = dataCenterSwitchServiceLinkService;
        this.dataCenterSwitchService = dataCenterSwitchService;
        this.serviceService = serviceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterSwitchServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterSwitchServiceLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterSwitchServiceLink> dataCenterSwitchServiceLinkPage = this.dataCenterSwitchServiceLinkService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "DataCenterSwitchServiceLink", sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterSwitchServiceLinkPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterSwitchServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchServiceLink - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterSwitchServiceLink.setSwitchServiceLinkId(null);

        ValidateObject validateObject = this.dataCenterSwitchServiceLinkValidate.validateAddNewItem(dataCenterSwitchServiceLink);
        if (validateObject.getResult().equals("success")) {
            dataCenterSwitchServiceLink.setDataCenterSwitch(this.dataCenterSwitchService.findOne(dataCenterSwitchServiceLink.getDataCenterSwitch()));
            dataCenterSwitchServiceLink.setService(this.serviceService.
                    findOne(dataCenterSwitchServiceLink.getService()));
            return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchServiceLinkService.addNewItem(dataCenterSwitchServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterSwitchServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchServiceLink - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterSwitchServiceLink.setSwitchServiceLinkId(id);

        ValidateObject validateObject = this.dataCenterSwitchServiceLinkValidate.validateUpdateItem(dataCenterSwitchServiceLink);
        if (validateObject.getResult().equals("success")) {
            try {
                dataCenterSwitchServiceLink.setDataCenterSwitch(this.dataCenterSwitchService.
                        findOne(dataCenterSwitchServiceLink.getDataCenterSwitch()));
                dataCenterSwitchServiceLink.setService(this.serviceService.
                        findOne(dataCenterSwitchServiceLink.getService()));
                return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchServiceLinkService.updateItem(dataCenterSwitchServiceLink)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterSwitchServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchServiceLink - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterSwitchServiceLink dataCenterSwitchServiceLink = new DataCenterSwitchServiceLink();
        dataCenterSwitchServiceLink.setSwitchServiceLinkId(id);
        ValidateObject validateObject = this.dataCenterSwitchServiceLinkValidate.deleteItem(dataCenterSwitchServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchServiceLinkService.deleteItem(dataCenterSwitchServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterSwitchServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterSwitchServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterSwitchServiceLink dataCenterSwitchServiceLink = new DataCenterSwitchServiceLink();
        dataCenterSwitchServiceLink.setSwitchServiceLinkId(id);
        ValidateObject validateObject = this.dataCenterSwitchServiceLinkValidate.findOne(dataCenterSwitchServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterSwitchServiceLinkService.findOne(dataCenterSwitchServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}

