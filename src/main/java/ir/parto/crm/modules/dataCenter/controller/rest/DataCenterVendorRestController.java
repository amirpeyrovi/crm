package ir.parto.crm.modules.dataCenter.controller.rest;

import ir.parto.crm.modules.dataCenter.controller.validate.DataCenterVendorValidate;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterVendor;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterVendorService;
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
@RequestMapping("/v1/dataCenter/dataCenterVendor")
public class DataCenterVendorRestController implements RestControllerInterface {
    private DataCenterVendorValidate dataCenterVendorValidate;
    private DataCenterVendorService dataCenterVendorService;

    @Autowired
    public DataCenterVendorRestController(DataCenterVendorValidate dataCenterVendorValidate, DataCenterVendorService dataCenterVendorService) {
        this.dataCenterVendorValidate = dataCenterVendorValidate;
        this.dataCenterVendorService = dataCenterVendorService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterVendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.dataCenterVendorValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<DataCenterVendor> dataCenterVendorPage = this.dataCenterVendorService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "DataCenterVendor",
                            sortProperty, sortOrder));
            return new ApiResponse("Success", dataCenterVendorPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody DataCenterVendor dataCenterVendor) {
        if (CheckPermission.getInstance().check("admin_add", "DataCenterVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterVendor - admin_add - access denied!"))
                    .getFaultResponse();
        }

        dataCenterVendor.setVendorId(null);

        ValidateObject validateObject = this.dataCenterVendorValidate.validateAddNewItem(dataCenterVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.dataCenterVendorService.addNewItem(dataCenterVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody DataCenterVendor dataCenterVendor) {
        if (CheckPermission.getInstance().check("admin_update", "DataCenterVendor")) {
            return new ApiResponse("Error", 101,
                    Arrays.asList("DataCenterVendor - admin_update - access denied!"))
                    .getFaultResponse();
        }

        dataCenterVendor.setVendorId(id);

        ValidateObject validateObject = this.dataCenterVendorValidate.validateUpdateItem(dataCenterVendor);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.dataCenterVendorService.
                        updateItem(dataCenterVendor)))
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
        if (CheckPermission.getInstance().check("admin_delete", "DataCenterVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterVendor - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        DataCenterVendor dataCenterVendor = new DataCenterVendor();
        dataCenterVendor.setVendorId(id);
        ValidateObject validateObject = this.dataCenterVendorValidate.deleteItem(dataCenterVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterVendorService.deleteItem(dataCenterVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "DataCenterVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("DataCenterVendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        DataCenterVendor dataCenterVendor = new DataCenterVendor();
        dataCenterVendor.setVendorId(id);
        ValidateObject validateObject = this.dataCenterVendorValidate.findOne(dataCenterVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.dataCenterVendorService.findOne(dataCenterVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


}

