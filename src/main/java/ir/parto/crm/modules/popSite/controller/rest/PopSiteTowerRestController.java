package ir.parto.crm.modules.popSite.controller.rest;
import ir.parto.crm.modules.popSite.controller.validate.PopSiteTowerValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSiteTower;
import ir.parto.crm.modules.popSite.model.service.PopSiteService;
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
@RequestMapping("/v1/popsite/popsiteVendor")
public class PopSiteTowerRestController  implements RestControllerInterface {
    private PopSiteTowerValidate popSiteTowerValidate;
    private PopSiteTowerService popSiteTowerService;
    private PopSiteService popSiteService;

    @Autowired
    public PopSiteTowerRestController(PopSiteTowerValidate popSiteTowerValidate, PopSiteTowerService popSiteTowerService, PopSiteService popSiteService) {
        this.popSiteTowerValidate = popSiteTowerValidate;
        this.popSiteTowerService = popSiteTowerService;
        this.popSiteService = popSiteService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteTower")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTower - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSiteTowerValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSiteTower> popSiteTowerPage = this.popSiteTowerService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSiteTower", sortProperty, sortOrder));
            return new ApiResponse("Success", popSiteTowerPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSiteTower popSiteTower) {
        if (CheckPermission.getInstance().check("admin_add", "PopSiteTower")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTower - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSiteTower.setTowerId(null);

        ValidateObject validateObject = this.popSiteTowerValidate.validateAddNewItem(popSiteTower);
        if (validateObject.getResult().equals("success")) {
            popSiteTower.setPopSite(this.popSiteService.findOne(popSiteTower.getPopSite()));
            return new ApiResponse("Success", Arrays.asList(this.popSiteTowerService.addNewItem(popSiteTower)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSiteTower popSiteTower) {
        if (CheckPermission.getInstance().check("admin_update", "PopSiteTower")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTower - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSiteTower.setTowerId(id);

        ValidateObject validateObject = this.popSiteTowerValidate.validateUpdateItem(popSiteTower);
        if (validateObject.getResult().equals("success")) {
            try {
                popSiteTower.setPopSite(this.popSiteService.findOne(popSiteTower.getPopSite()));
                return new ApiResponse("Success", Arrays.asList(this.popSiteTowerService.updateItem(popSiteTower)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSiteTower")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTower - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSiteTower popSiteTower = new PopSiteTower();
        popSiteTower.setTowerId(id);
        ValidateObject validateObject = this.popSiteTowerValidate.deleteItem(popSiteTower);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteTowerService.deleteItem(popSiteTower)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteTower")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteTower - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSiteTower popSiteTower = new PopSiteTower();
        popSiteTower.setTowerId(id);
        ValidateObject validateObject = this.popSiteTowerValidate.findOne(popSiteTower);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteTowerService.findOne(popSiteTower)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
