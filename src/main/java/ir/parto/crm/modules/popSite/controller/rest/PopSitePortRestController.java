package ir.parto.crm.modules.popSite.controller.rest;

import ir.parto.crm.modules.popSite.controller.validate.PopSitePortValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSitePort;
import ir.parto.crm.modules.popSite.model.service.PopSitePortService;
import ir.parto.crm.modules.popSite.model.service.PopSitePortService;
import ir.parto.crm.modules.popSite.model.service.PopSiteSwitchService;
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
@RequestMapping("/v1/popsite/popsitePort")
public class PopSitePortRestController  implements RestControllerInterface {
    private PopSitePortService popSitePortService;
    private PopSitePortValidate popSitePortValidate;
    private PopSiteSwitchService popSiteSwitchService;

    @Autowired
    public PopSitePortRestController(PopSiteSwitchService popSiteSwitchService, PopSitePortValidate popSitePortValidate, PopSitePortService popSitePortService) {
        this.popSitePortService = popSitePortService;
        this.popSitePortValidate = popSitePortValidate;
        this.popSiteSwitchService = popSiteSwitchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSitePort")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSitePort - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSitePortValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSitePort> popSitePortPage = this.popSitePortService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSitePort", sortProperty, sortOrder));
            return new ApiResponse("Success", popSitePortPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSitePort popSitePort) {
        if (CheckPermission.getInstance().check("admin_add", "PopSitePort")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSitePort - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSitePort.setPortId(null);

        ValidateObject validateObject = this.popSitePortValidate.validateAddNewItem(popSitePort);
        if (validateObject.getResult().equals("success")) {
            popSitePort.setPopSiteSwitch(this.popSiteSwitchService.findOne(popSitePort.getPopSiteSwitch()));
            return new ApiResponse("Success", Arrays.asList(this.popSitePortService.addNewItem(popSitePort)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSitePort popSitePort) {
        if (CheckPermission.getInstance().check("admin_update", "PopSitePort")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSitePort - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSitePort.setPortId(id);

        ValidateObject validateObject = this.popSitePortValidate.validateUpdateItem(popSitePort);
        if (validateObject.getResult().equals("success")) {
            try {
                popSitePort.setPopSiteSwitch(this.popSiteSwitchService.findOne(popSitePort.getPopSiteSwitch()));
                return new ApiResponse("Success", Arrays.asList(this.popSitePortService.updateItem(popSitePort)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSitePort")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSitePort - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSitePort popSitePort = new PopSitePort();
        popSitePort.setPortId(id);
        ValidateObject validateObject = this.popSitePortValidate.deleteItem(popSitePort);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSitePortService.deleteItem(popSitePort)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSitePort")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSitePort - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSitePort popSitePort = new PopSitePort();
        popSitePort.setPortId(id);
        ValidateObject validateObject = this.popSitePortValidate.findOne(popSitePort);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSitePortService.findOne(popSitePort)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
