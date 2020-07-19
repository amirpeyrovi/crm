package ir.parto.crm.modules.popSite.controller.rest;

import ir.parto.crm.modules.popSite.controller.validate.PopSiteValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSite;
import ir.parto.crm.modules.popSite.model.service.PopSiteService;
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
@RequestMapping("/v1/popsite/popsite")
public class PopSiteRestController implements RestControllerInterface {
    private PopSiteService popSiteService;
    private PopSiteValidate popSiteValidate;

    @Autowired
    public PopSiteRestController(PopSiteService popSiteService, PopSiteValidate popSiteValidate) {
        this.popSiteService = popSiteService;
        this.popSiteValidate = popSiteValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSite")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSite - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSiteValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSite> popSitePage = this.popSiteService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSite", sortProperty, sortOrder));
            return new ApiResponse("Success", popSitePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSite popSite) {
        if (CheckPermission.getInstance().check("admin_add", "PopSite")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSite - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSite.setPopSiteId(null);

        ValidateObject validateObject = this.popSiteValidate.validateAddNewItem(popSite);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteService.addNewItem(popSite)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSite popSite) {
        if (CheckPermission.getInstance().check("admin_update", "PopSite")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSite - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSite.setPopSiteId(id);

        ValidateObject validateObject = this.popSiteValidate.validateUpdateItem(popSite);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.popSiteService.updateItem(popSite)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSite")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSite - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSite popSite = new PopSite();
        popSite.setPopSiteId(id);
        ValidateObject validateObject = this.popSiteValidate.deleteItem(popSite);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteService.deleteItem(popSite)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSite")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSite - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSite popSite = new PopSite();
        popSite.setPopSiteId(id);
        ValidateObject validateObject = this.popSiteValidate.findOne(popSite);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteService.findOne(popSite)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
