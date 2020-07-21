package ir.parto.crm.modules.popSite.controller.rest;
import ir.parto.crm.modules.popSite.controller.validate.PopSiteRadioServiceLinkValidate;
import ir.parto.crm.modules.popSite.model.entity.PopSiteRadioServiceLink;
import ir.parto.crm.modules.popSite.model.service.PopSiteRadioService;
import ir.parto.crm.modules.popSite.model.service.PopSiteRadioServiceLinkService;
import ir.parto.crm.modules.service.model.service.ServiceService;
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
@RequestMapping("/v1/popsite/popsiteRadioServiceLink")
public class PopSiteRadioServiceLinkRestController implements RestControllerInterface {
    private PopSiteRadioServiceLinkService popSiteRadioServiceLinkService;
    private ServiceService serviceService;
    private PopSiteRadioService popSiteRadioService;
    private PopSiteRadioServiceLinkValidate popSiteRadioServiceLinkValidate;

    @Autowired
    public PopSiteRadioServiceLinkRestController(PopSiteRadioServiceLinkService popSiteRadioServiceLinkService, ServiceService serviceService, PopSiteRadioService popSiteRadioService, PopSiteRadioServiceLinkValidate popSiteRadioServiceLinkValidate) {
        this.popSiteRadioServiceLinkService = popSiteRadioServiceLinkService;
        this.serviceService = serviceService;
        this.popSiteRadioService = popSiteRadioService;
        this.popSiteRadioServiceLinkValidate = popSiteRadioServiceLinkValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteRadioServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadioServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.popSiteRadioServiceLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PopSiteRadioServiceLink> productPage = this.popSiteRadioServiceLinkService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PopSiteRadioServiceLink", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PopSiteRadioServiceLink popSiteRadioServiceLink) {
        if (CheckPermission.getInstance().check("admin_add", "PopSiteRadioServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadioServiceLink - admin_add - access denied!"))
                    .getFaultResponse();
        }

        popSiteRadioServiceLink.setRadioServiceLinkId(null);

        ValidateObject validateObject = this.popSiteRadioServiceLinkValidate.validateAddNewItem(popSiteRadioServiceLink);
        if (validateObject.getResult().equals("success")) {
            popSiteRadioServiceLink.setPopSiteRadio(this.popSiteRadioService.findOne(popSiteRadioServiceLink.getPopSiteRadio()));
            popSiteRadioServiceLink.setService(this.serviceService.findOne(popSiteRadioServiceLink.getService()));
            return new ApiResponse("Success", Arrays.asList(this.popSiteRadioServiceLinkService.addNewItem(popSiteRadioServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody PopSiteRadioServiceLink popSiteRadioServiceLink) {
        if (CheckPermission.getInstance().check("admin_update", "PopSiteRadioServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadioServiceLink - admin_update - access denied!"))
                    .getFaultResponse();
        }

        popSiteRadioServiceLink.setRadioServiceLinkId(id);
        ValidateObject validateObject = this.popSiteRadioServiceLinkValidate.validateUpdateItem(popSiteRadioServiceLink);
        if (validateObject.getResult().equals("success")) {
            try {
                popSiteRadioServiceLink.setPopSiteRadio(this.popSiteRadioService.findOne(popSiteRadioServiceLink.getPopSiteRadio()));
                popSiteRadioServiceLink.setService(this.serviceService.findOne(popSiteRadioServiceLink.getService()));
                return new ApiResponse("Success", Arrays.asList(this.popSiteRadioServiceLinkService.updateItem(popSiteRadioServiceLink)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PopSiteRadioServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadioServiceLink - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PopSiteRadioServiceLink popSiteRadioServiceLink = new PopSiteRadioServiceLink();
        popSiteRadioServiceLink.setRadioServiceLinkId(id);
        ValidateObject validateObject = this.popSiteRadioServiceLinkValidate.deleteItem(popSiteRadioServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteRadioServiceLinkService.deleteItem(popSiteRadioServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "PopSiteRadioServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("PopSiteRadioServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PopSiteRadioServiceLink popSiteRadioServiceLink = new PopSiteRadioServiceLink();
        popSiteRadioServiceLink.setRadioServiceLinkId(id);
        ValidateObject validateObject = this.popSiteRadioServiceLinkValidate.findOne(popSiteRadioServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.popSiteRadioServiceLinkService.findOne(popSiteRadioServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
