package ir.parto.crm.modules.port.controller.rest;

import ir.parto.crm.modules.port.controller.validate.VendorValidate;
import ir.parto.crm.modules.port.model.entity.Vendor;
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
@RequestMapping("/v1/port/vendor")
public class VendorRestController implements RestControllerInterface {
    private VendorValidate vendorValidate;
    private VendorService vendorService;

    @Autowired
    public VendorRestController(VendorValidate vendorValidate, VendorService vendorService) {
        this.vendorValidate = vendorValidate;
        this.vendorService = vendorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Vendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("Vendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.vendorValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Vendor> productPage = this.vendorService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Vendor", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Vendor vendor) {
        if (CheckPermission.getInstance().check("admin_add", "Vendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("Vendor - admin_add - access denied!"))
                    .getFaultResponse();
        }

        vendor.setVendorId(null);

        ValidateObject validateObject = this.vendorValidate.validateAddNewItem(vendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.vendorService.addNewItem(vendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Vendor vendor) {
        if (CheckPermission.getInstance().check("admin_update", "Vendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("Vendor - admin_update - access denied!"))
                    .getFaultResponse();
        }

        vendor.setVendorId(id);

        ValidateObject validateObject = this.vendorValidate.validateUpdateItem(vendor);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.vendorService.updateItem(vendor)))
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

        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        ValidateObject validateObject = this.vendorValidate.deleteItem(vendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.vendorService.deleteItem(vendor)))
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

        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        ValidateObject validateObject = this.vendorValidate.findOne(vendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.vendorService.findOne(vendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
