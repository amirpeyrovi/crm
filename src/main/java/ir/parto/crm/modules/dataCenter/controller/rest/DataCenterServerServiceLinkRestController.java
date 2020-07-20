package ir.parto.crm.modules.dataCenter.controller.rest;


import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterServerServiceLinkValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerServiceLink;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerServiceLinkService;
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
@RequestMapping("/v1/dataCenter/dataCenterServerServiceLink")
public class DataCenterServerServiceLinkRestController implements RestControllerInterface {
    private DataCenterServerService dataCenterServerService;
    private ServiceService serviceService;
    private DataCenterServerServiceLinkService dataCenterServerServiceLinkService;
    private DataCenterServerServiceLinkValidate dataCenterServerServiceLinkValidate;

    @Autowired
    public DataCenterServerServiceLinkRestController(ServiceService serviceService ,DataCenterServerService dataCenterServerService ,
                                                     DataCenterServerServiceLinkService dataCenterServerServiceLinkService,
                                                     DataCenterServerServiceLinkValidate dataCenterServerServiceLinkValidate) {
        this.dataCenterServerServiceLinkService = dataCenterServerServiceLinkService;
        this.dataCenterServerServiceLinkValidate = dataCenterServerServiceLinkValidate;
        this.dataCenterServerService = dataCenterServerService;
        this.serviceService = serviceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServerServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterServerServiceLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterServerServiceLink> dataCenterServerServiceLinkPage = this.dataCenterServerServiceLinkService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenterServerServiceLink", sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterServerServiceLinkPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterServerServiceLink dataCenterServerServiceLink) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterServerServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerServiceLink - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServerServiceLink.setServerServiceLinkId(null);

        ValidateObject validateObject = this.dataCenterServerServiceLinkValidate.validateAddNewItem(dataCenterServerServiceLink);
        if (validateObject.getResult().equals("success")) {
            dataCenterServerServiceLink.setDataCenterServer(this.dataCenterServerService.findOne(dataCenterServerServiceLink.
                    getDataCenterServer()));
            dataCenterServerServiceLink.setService(this.serviceService.findOne(dataCenterServerServiceLink.getService()));
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterServerServiceLinkService.addNewItem(dataCenterServerServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterServerServiceLink dataCenterServerServiceLink) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterServerServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerServiceLink - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServerServiceLink.setServerServiceLinkId(id);

        ValidateObject validateObject = this.dataCenterServerServiceLinkValidate.validateUpdateItem(dataCenterServerServiceLink);
        if (validateObject.getResult().equals("success")) {
            try {
                dataCenterServerServiceLink.setDataCenterServer(this.dataCenterServerService.findOne(dataCenterServerServiceLink.
                        getDataCenterServer()));
                dataCenterServerServiceLink.setService(this.serviceService.findOne(dataCenterServerServiceLink.getService()));
                return new ApiResponse("Success", Arrays.asList(this.dataCenterServerServiceLinkService.
                        updateItem(dataCenterServerServiceLink)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterServerServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerServiceLink - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServerServiceLink dataCenterServerServiceLink = new DataCenterServerServiceLink();
        dataCenterServerServiceLink.setServerServiceLinkId(id);
        ValidateObject validateObject = this.dataCenterServerServiceLinkValidate.deleteItem(dataCenterServerServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerServiceLinkService.deleteItem(dataCenterServerServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServerServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServerServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServerServiceLink dataCenterServerServiceLink = new DataCenterServerServiceLink();
        dataCenterServerServiceLink.setServerServiceLinkId(id);
        ValidateObject validateObject = this.dataCenterServerServiceLinkValidate.findOne(dataCenterServerServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerServiceLinkService.findOne(dataCenterServerServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
