package ir.parto.crm.modules.port.controller.rest;

import ir.parto.crm.modules.port.controller.validate.ManagementValidate;
import ir.parto.crm.modules.port.model.entity.Management;
import ir.parto.crm.modules.port.model.service.ManagementService;
import ir.parto.crm.modules.port.model.service.VendorService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.PortManagement;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@PortManagement
@RequestMapping("/v1/port/management")
public class ManagementRestController implements RestControllerInterface {
    private ManagementValidate managementValidate;
    private ManagementService managementService;
    private VendorService vendorService;

    @Autowired
    public ManagementRestController(ManagementValidate managementValidate, ManagementService managementService, VendorService vendorService) {
        this.managementValidate = managementValidate;
        this.managementService = managementService;
        this.vendorService = vendorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Management")) {
            return new ApiResponse("Error", 101, Arrays.asList("Management - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.managementValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Management> productPage = this.managementService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Management", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Management management) {
        if (CheckPermission.getInstance().check("admin_add", "Management")) {
            return new ApiResponse("Error", 101, Arrays.asList("Management - admin_add - access denied!"))
                    .getFaultResponse();
        }

        management.setManagementId(null);

        ValidateObject validateObject = this.managementValidate.validateAddNewItem(management);
        if (validateObject.getResult().equals("success")) {
            management.setVendor(this.vendorService.findOne(management.getVendor()));
            return new ApiResponse("Success", Arrays.asList(this.managementService.addNewItem(management)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Management management) {
        if (CheckPermission.getInstance().check("admin_update", "Management")) {
            return new ApiResponse("Error", 101, Arrays.asList("Management - admin_update - access denied!"))
                    .getFaultResponse();
        }

        management.setManagementId(id);

        ValidateObject validateObject = this.managementValidate.validateUpdateItem(management);
        if (validateObject.getResult().equals("success")) {
            try {
                management.setVendor(this.vendorService.findOne(management.getVendor()));
                return new ApiResponse("Success", Arrays.asList(this.managementService.updateItem(management)))
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
        if (CheckPermission.getInstance().check("admin_delete", "Management")) {
            return new ApiResponse("Error", 101, Arrays.asList("Management - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Management management = new Management();
        management.setManagementId(id);
        ValidateObject validateObject = this.managementValidate.deleteItem(management);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.managementService.deleteItem(management)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Management")) {
            return new ApiResponse("Error", 101, Arrays.asList("Management - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Management management = new Management();
        management.setManagementId(id);
        ValidateObject validateObject = this.managementValidate.findOne(management);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.managementService.findOne(management)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
