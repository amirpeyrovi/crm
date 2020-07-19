package ir.parto.crm.modules.popSite.controller.rest;

import ir.parto.crm.modules.popSite.controller.validate.PopSiteRackValidate;
import ir.parto.crm.modules.popSite.controller.validate.PopSiteRadioValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSiteRack;
import ir.parto.crm.modules.popSite.model.service.PopSiteRackService;
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
@RequestMapping("/v1/popsite/popsiteRack")
public class PopSiteRackRestController implements RestControllerInterface {
    private PopSiteRackService popSiteRackService;
    private PopSiteRackValidate popSiteRackValidate;
    private PopSiteService popSiteService;

    @Autowired
    public PopSiteRackRestController(PopSiteRackService popSiteRackService, PopSiteRackValidate popSiteRackValidate, PopSiteService popSiteService) {
        this.popSiteRackService = popSiteRackService;
        this.popSiteRackValidate = popSiteRackValidate;
        this.popSiteService = popSiteService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRack - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSiteRackValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSiteRack> productPage = this.popSiteRackService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSiteRack", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSiteRack popSiteRack) {
        if (CheckPermission.getInstance().check("admin_add", "PopSiteRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRack - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSiteRack.setRackId(null);

        ValidateObject validateObject = this.popSiteRackValidate.validateAddNewItem(popSiteRack);
        if (validateObject.getResult().equals("success")) {
            popSiteRack.setPopSite(this.popSiteService.findOne(popSiteRack.getPopSite()));
            return new ApiResponse("Success", Arrays.asList(this.popSiteRackService.addNewItem(popSiteRack)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSiteRack popSiteRack) {
        if (CheckPermission.getInstance().check("admin_update", "PopSiteRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRack - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSiteRack.setRackId(id);
        ValidateObject validateObject = this.popSiteRackValidate.validateUpdateItem(popSiteRack);
        if (validateObject.getResult().equals("success")) {
            try {
                popSiteRack.setPopSite(this.popSiteService.findOne(popSiteRack.getPopSite()));
                return new ApiResponse("Success", Arrays.asList(this.popSiteRackService.updateItem(popSiteRack)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSiteRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRack - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSiteRack popSiteRack = new PopSiteRack();
        popSiteRack.setRackId(id);
        ValidateObject validateObject = this.popSiteRackValidate.deleteItem(popSiteRack);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteRackService.deleteItem(popSiteRack)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteRack")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRack - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSiteRack popSiteRack = new PopSiteRack();
        popSiteRack.setRackId(id);
        ValidateObject validateObject = this.popSiteRackValidate.findOne(popSiteRack);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteRackService.findOne(popSiteRack)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
