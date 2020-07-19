package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterServerValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServer;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterRackService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterVendorService;
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
@RequestMapping("/v1/dataCenter/dataCenterServer")
public class DataCenterServerRestController implements RestControllerInterface {
    private DataCenterRackService dataCenterRackService;
    private DataCenterVendorService dataCenterVendorService;
    private DataCenterServerService dataCenterServerService;
    private DataCenterServerValidate dataCenterServerValidate;

    @Autowired
    public DataCenterServerRestController(DataCenterRackService dataCenterRackService, DataCenterVendorService dataCenterVendorService,
                                          DataCenterServerService dataCenterServerService, DataCenterServerValidate dataCenterServerValidate) {
        this.dataCenterRackService = dataCenterRackService;
        this.dataCenterVendorService = dataCenterVendorService;
        this.dataCenterServerService = dataCenterServerService;
        this.dataCenterServerValidate = dataCenterServerValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServer")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServer - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterServerValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterServer> dataCenterServerPage = this.dataCenterServerService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenterServer",
                            sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterServerPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterServer dataCenterServer) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterServer")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServer - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServer.setServerId(null);

        ValidateObject validateObject = this.dataCenterServerValidate.validateAddNewItem(dataCenterServer);
        if (validateObject.getResult().equals("success")) {
            dataCenterServer.setDataCenterRack(this.dataCenterRackService.findById(dataCenterServer.getDataCenterRack().getRackId()));
            dataCenterServer.setDataCenterVendor(this.dataCenterVendorService.findById(dataCenterServer.getDataCenterVendor().getVendorId()));
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterServerService.addNewItem(dataCenterServer)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterServer dataCenterServer) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterServer")) {
            return new ApiResponse("Error", 101,
                    Arrays.asList("DataCenterServer - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterServer.setServerId(id);

        ValidateObject validateObject = this.dataCenterServerValidate.validateUpdateItem(dataCenterServer);
        if (validateObject.getResult().equals("success")) {
            try {
                dataCenterServer.setDataCenterRack(this.dataCenterRackService.findById(dataCenterServer.getDataCenterRack().getRackId()));
                dataCenterServer.setDataCenterVendor(this.dataCenterVendorService.findById(dataCenterServer.getDataCenterVendor().getVendorId()));
                return new ApiResponse("Success", Arrays.asList(this.dataCenterServerService.
                        updateItem(dataCenterServer)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterServer")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServer - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServer dataCenterServer = new DataCenterServer();
        dataCenterServer.setServerId(id);
        ValidateObject validateObject = this.dataCenterServerValidate.deleteItem(dataCenterServer);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerService.deleteItem(dataCenterServer)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterServer")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterServer - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterServer dataCenterServer = new DataCenterServer();
        dataCenterServer.setServerId(id);
        ValidateObject validateObject = this.dataCenterServerValidate.findOne(dataCenterServer);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterServerService.findOne(dataCenterServer)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }



}
