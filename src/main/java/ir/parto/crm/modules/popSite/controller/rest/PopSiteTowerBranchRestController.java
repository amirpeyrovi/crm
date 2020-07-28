package ir.parto.crm.modules.popSite.controller.rest;

import ir.parto.crm.modules.popSite.controller.validate.PopSiteTowerBranchValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSiteTowerBranch;
import ir.parto.crm.modules.popSite.model.service.PopSiteTowerBranchService;
import ir.parto.crm.modules.popSite.model.service.PopSiteTowerService;
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
@RequestMapping("/v1/popsite/popsiteTowerBranch")
public class PopSiteTowerBranchRestController  implements RestControllerInterface {
    private PopSiteTowerService popSiteTowerService;
    private PopSiteTowerBranchService popSiteTowerBranchService;
    private PopSiteTowerBranchValidate popSiteTowerBranchValidate;

    @Autowired
    public PopSiteTowerBranchRestController(PopSiteTowerService popSiteTowerService, PopSiteTowerBranchService popSiteTowerBranchService, PopSiteTowerBranchValidate popSiteTowerBranchValidate) {
        this.popSiteTowerService = popSiteTowerService;
        this.popSiteTowerBranchService = popSiteTowerBranchService;
        this.popSiteTowerBranchValidate = popSiteTowerBranchValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteTowerBranch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTowerBranch - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSiteTowerBranchValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSiteTowerBranch> popSiteTowerBranchPage = this.popSiteTowerBranchService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSiteTowerBranch", sortProperty, sortOrder));
            return new ApiResponse("Success", popSiteTowerBranchPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSiteTowerBranch popSiteTowerBranch) {
        if (CheckPermission.getInstance().check("admin_add", "PopSiteTowerBranch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTowerBranch - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSiteTowerBranch.setBranchId(null);

        ValidateObject validateObject = this.popSiteTowerBranchValidate.validateAddNewItem(popSiteTowerBranch);
        if (validateObject.getResult().equals("success")) {
            popSiteTowerBranch.setPopSiteTower(this.popSiteTowerService.findOne(popSiteTowerBranch.getPopSiteTower()));
            return new ApiResponse("Success", Arrays.asList(this.popSiteTowerBranchService.addNewItem(popSiteTowerBranch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSiteTowerBranch popSiteTowerBranch) {
        if (CheckPermission.getInstance().check("admin_update", "PopSiteTowerBranch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTowerBranch - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSiteTowerBranch.setBranchId(id);

        ValidateObject validateObject = this.popSiteTowerBranchValidate.validateUpdateItem(popSiteTowerBranch);
        if (validateObject.getResult().equals("success")) {
            try {
                popSiteTowerBranch.setPopSiteTower(this.popSiteTowerService.findOne(popSiteTowerBranch.getPopSiteTower()));
                return new ApiResponse("Success", Arrays.asList(this.popSiteTowerBranchService.updateItem(popSiteTowerBranch)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSiteTowerBranch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTowerBranch - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSiteTowerBranch popSiteTowerBranch = new PopSiteTowerBranch();
        popSiteTowerBranch.setBranchId(id);
        ValidateObject validateObject = this.popSiteTowerBranchValidate.deleteItem(popSiteTowerBranch);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteTowerBranchService.deleteItem(popSiteTowerBranch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteTowerBranch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTowerBranch - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSiteTowerBranch popSiteTowerBranch = new PopSiteTowerBranch();
        popSiteTowerBranch.setBranchId(id);
        ValidateObject validateObject = this.popSiteTowerBranchValidate.findOne(popSiteTowerBranch);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteTowerBranchService.findOne(popSiteTowerBranch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
