package ir.parto.crm.modules.popSite.controller.rest;

import ir.parto.crm.modules.popSite.controller.validate.PopSiteRadioValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSiteRadio;
import ir.parto.crm.modules.popSite.model.service.*;
import ir.parto.crm.modules.popSite.model.service.PopSiteRadioService;
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
@RequestMapping("/v1/popsite/popsiteRadio")
public class PopSiteRadioRestController  implements RestControllerInterface {
    private PopSitePortService popSitePortService;
    private PopSiteTowerBranchService popSiteTowerBranchService;
    private PopSiteVendorService popSiteVendorService;
    private PopSiteRadioService popSiteRadioService;
    private PopSiteRadioValidate popSiteRadioValidate;

    @Autowired
    public PopSiteRadioRestController(PopSitePortService popSitePortService, PopSiteTowerBranchService popSiteTowerBranchService, PopSiteVendorService popSiteVendorService, PopSiteRadioService popSiteRadioService, PopSiteRadioValidate popSiteRadioValidate) {
        this.popSitePortService = popSitePortService;
        this.popSiteTowerBranchService = popSiteTowerBranchService;
        this.popSiteVendorService = popSiteVendorService;
        this.popSiteRadioService = popSiteRadioService;
        this.popSiteRadioValidate = popSiteRadioValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteRadio")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadio - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSiteRadioValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSiteRadio> popSiteRadioPage = this.popSiteRadioService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSiteRadio", sortProperty, sortOrder));
            return new ApiResponse("Success", popSiteRadioPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSiteRadio popSiteRadio) {
        if (CheckPermission.getInstance().check("admin_add", "PopSiteRadio")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadio - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSiteRadio.setRadioId(null);

        ValidateObject validateObject = this.popSiteRadioValidate.validateAddNewItem(popSiteRadio);
        if (validateObject.getResult().equals("success")) {
            popSiteRadio.setPopSiteVendor(this.popSiteVendorService.findOne(popSiteRadio.getPopSiteVendor()));
            popSiteRadio.setPopSiteTowerBranch(this.popSiteTowerBranchService.findOne(popSiteRadio.getPopSiteTowerBranch()));
            popSiteRadio.setPopSitePort(this.popSitePortService.findOne(popSiteRadio.getPopSitePort()));
            return new ApiResponse("Success", Arrays.asList(this.popSiteRadioService.addNewItem(popSiteRadio)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSiteRadio popSiteRadio) {
        if (CheckPermission.getInstance().check("admin_update", "PopSiteRadio")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadio - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSiteRadio.setRadioId(id);

        ValidateObject validateObject = this.popSiteRadioValidate.validateUpdateItem(popSiteRadio);
        if (validateObject.getResult().equals("success")) {
            try {
                popSiteRadio.setPopSiteVendor(this.popSiteVendorService.findOne(popSiteRadio.getPopSiteVendor()));
                popSiteRadio.setPopSiteTowerBranch(this.popSiteTowerBranchService.findOne(popSiteRadio.getPopSiteTowerBranch()));
                popSiteRadio.setPopSitePort(this.popSitePortService.findOne(popSiteRadio.getPopSitePort()));
                return new ApiResponse("Success", Arrays.asList(this.popSiteRadioService.updateItem(popSiteRadio)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSiteRadio")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadio - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSiteRadio popSiteRadio = new PopSiteRadio();
        popSiteRadio.setRadioId(id);
        ValidateObject validateObject = this.popSiteRadioValidate.deleteItem(popSiteRadio);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteRadioService.deleteItem(popSiteRadio)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteRadio")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadio - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSiteRadio popSiteRadio = new PopSiteRadio();
        popSiteRadio.setRadioId(id);
        ValidateObject validateObject = this.popSiteRadioValidate.findOne(popSiteRadio);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteRadioService.findOne(popSiteRadio)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
