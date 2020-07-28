package ir.parto.crm.modules.popSite.controller.rest;

import ir.parto.crm.modules.popSite.controller.validate.PopSiteSwitchValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSiteSwitch;
import ir.parto.crm.modules.popSite.model.service.PopSiteRackService;
import ir.parto.crm.modules.popSite.model.service.PopSiteSwitchService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.PopSiteAnnotation;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@PopSiteAnnotation
@RequestMapping("/v1/popsite/popsiteSwitch")
public class PopSiteSwitchRestController {
    private PopSiteSwitchService popSiteSwitchService;
    private PopSiteSwitchValidate popSiteSwitchValidate;
    private PopSiteRackService popSiteRackService;

    @Autowired
    public PopSiteSwitchRestController(PopSiteSwitchService popSiteSwitchService, PopSiteSwitchValidate popSiteSwitchValidate, PopSiteRackService popSiteRackService) {
        this.popSiteSwitchService = popSiteSwitchService;
        this.popSiteSwitchValidate = popSiteSwitchValidate;
        this.popSiteRackService = popSiteRackService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteSwitch - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSiteSwitchValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSiteSwitch> popSiteSwitchPage = this.popSiteSwitchService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSiteSwitch", sortProperty, sortOrder));
            return new ApiResponse("Success", popSiteSwitchPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSiteSwitch popSiteSwitch) {
        if (CheckPermission.getInstance().check("admin_add", "PopSiteSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteSwitch - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSiteSwitch.setSwitchId(null);

        ValidateObject validateObject = this.popSiteSwitchValidate.validateAddNewItem(popSiteSwitch);
        if (validateObject.getResult().equals("success")) {
            popSiteSwitch.setPopSiteRack(this.popSiteRackService.findOne(popSiteSwitch.getPopSiteRack()));
            return new ApiResponse("Success", Arrays.asList(this.popSiteSwitchService.addNewItem(popSiteSwitch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSiteSwitch popSiteSwitch) {
        if (CheckPermission.getInstance().check("admin_update", "PopSiteSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteSwitch - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSiteSwitch.setSwitchId(id);

        ValidateObject validateObject = this.popSiteSwitchValidate.validateUpdateItem(popSiteSwitch);
        if (validateObject.getResult().equals("success")) {
            try {
                popSiteSwitch.setPopSiteRack(this.popSiteRackService.findOne(popSiteSwitch.getPopSiteRack()));
                return new ApiResponse("Success", Arrays.asList(this.popSiteSwitchService.updateItem(popSiteSwitch)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSiteSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteSwitch - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSiteSwitch popSiteSwitch = new PopSiteSwitch();
        popSiteSwitch.setSwitchId(id);
        ValidateObject validateObject = this.popSiteSwitchValidate.deleteItem(popSiteSwitch);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteSwitchService.deleteItem(popSiteSwitch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteSwitch")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteSwitch - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSiteSwitch popSiteSwitch = new PopSiteSwitch();
        popSiteSwitch.setSwitchId(id);
        ValidateObject validateObject = this.popSiteSwitchValidate.findOne(popSiteSwitch);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteSwitchService.findOne(popSiteSwitch)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
