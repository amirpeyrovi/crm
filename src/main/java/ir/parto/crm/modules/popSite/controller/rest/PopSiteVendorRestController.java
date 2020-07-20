package ir.parto.crm.modules.popSite.controller.rest;

import ir.parto.crm.modules.popSite.controller.validate.PopSiteVendorValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSiteVendor;
import ir.parto.crm.modules.popSite.model.service.PopSiteVendorService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.PopSiteAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@PopSiteAnnotation
@RequestMapping("/v1/popsite/popsiteVendor")
public class PopSiteVendorRestController implements RestControllerInterface {
    private PopSiteVendorService popSiteVendorService;
    private PopSiteVendorValidate popSiteVendorValidate;

    @Autowired
    public PopSiteVendorRestController(PopSiteVendorService popSiteVendorService, PopSiteVendorValidate popSiteVendorValidate) {
        this.popSiteVendorService = popSiteVendorService;
        this.popSiteVendorValidate = popSiteVendorValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteVendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSiteVendorValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSiteVendor> popSiteVendorPage = this.popSiteVendorService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSiteVendor", sortProperty, sortOrder));
            return new ApiResponse("Success", popSiteVendorPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSiteVendor popSiteVendor) {
        if (CheckPermission.getInstance().check("admin_add", "PopSiteVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteVendor - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSiteVendor.setVendorId(null);

        ValidateObject validateObject = this.popSiteVendorValidate.validateAddNewItem(popSiteVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteVendorService.addNewItem(popSiteVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSiteVendor popSiteVendor) {
        if (CheckPermission.getInstance().check("admin_update", "PopSiteVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteVendor - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSiteVendor.setVendorId(id);

        ValidateObject validateObject = this.popSiteVendorValidate.validateUpdateItem(popSiteVendor);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.popSiteVendorService.updateItem(popSiteVendor)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSiteVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteVendor - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSiteVendor popSiteVendor = new PopSiteVendor();
        popSiteVendor.setVendorId(id);
        ValidateObject validateObject = this.popSiteVendorValidate.deleteItem(popSiteVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteVendorService.deleteItem(popSiteVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteVendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSiteVendor popSiteVendor = new PopSiteVendor();
        popSiteVendor.setVendorId(id);
        ValidateObject validateObject = this.popSiteVendorValidate.findOne(popSiteVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteVendorService.findOne(popSiteVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
