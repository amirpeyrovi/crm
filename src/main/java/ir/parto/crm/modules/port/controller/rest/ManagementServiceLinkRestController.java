package ir.parto.crm.modules.port.controller.rest;

import ir.parto.crm.modules.port.controller.validate.ManagementServiceLinkValidate;
import ir.parto.crm.modules.port.controller.validate.ManagementValidate;
import ir.parto.crm.modules.port.model.entity.Management;
import ir.parto.crm.modules.port.model.entity.ManagementServiceLink;
import ir.parto.crm.modules.port.model.service.ManagementService;
import ir.parto.crm.modules.port.model.service.ManagementServiceLinkService;
import ir.parto.crm.modules.service.controller.validate.ServiceValidate;
import ir.parto.crm.modules.service.model.service.ServiceService;
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
@RequestMapping("/v1/port/managementServiceLink")
public class ManagementServiceLinkRestController implements RestControllerInterface {
    private ManagementServiceLinkValidate managementServiceLinkValidate;
    private ManagementValidate managementValidate;
    private ServiceValidate serviceValidate;
    private ManagementServiceLinkService managementServiceLinkService;
    private ManagementService managementService;
    private ServiceService serviceService;

    @Autowired
    public ManagementServiceLinkRestController(ManagementServiceLinkValidate managementServiceLinkValidate, ManagementValidate managementValidate, ServiceValidate serviceValidate, ManagementServiceLinkService managementServiceLinkService, ManagementService managementService, ServiceService serviceService) {
        this.managementServiceLinkValidate = managementServiceLinkValidate;
        this.managementValidate = managementValidate;
        this.serviceValidate = serviceValidate;
        this.managementServiceLinkService = managementServiceLinkService;
        this.managementService = managementService;
        this.serviceService = serviceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ManagementServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ManagementServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.managementServiceLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ManagementServiceLink> productPage = this.managementServiceLinkService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ManagementServiceLink", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ManagementServiceLink managementServiceLink) {
        if (CheckPermission.getInstance().check("admin_add", "ManagementServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ManagementServiceLink - admin_add - access denied!"))
                    .getFaultResponse();
        }

        managementServiceLink.setManagementServiceId(null);

        ValidateObject validateObject = this.managementServiceLinkValidate.validateAddNewItem(managementServiceLink);
        if (validateObject.getResult().equals("success")) {
            managementServiceLink.setService(this.serviceService.findOne(managementServiceLink.getService()));
            managementServiceLink.setManagement(this.managementService.findOne(managementServiceLink.getManagement()));
            return new ApiResponse("Success", Arrays.asList(this.managementServiceLinkService.addNewItem(managementServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ManagementServiceLink managementServiceLink) {
        if (CheckPermission.getInstance().check("admin_update", "ManagementServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ManagementServiceLink - admin_update - access denied!"))
                    .getFaultResponse();
        }

        managementServiceLink.setManagementServiceId(id);

        ValidateObject validateObject = this.managementServiceLinkValidate.validateUpdateItem(managementServiceLink);
        if (validateObject.getResult().equals("success")) {
            try {
                managementServiceLink.setService(this.serviceService.findOne(managementServiceLink.getService()));
                managementServiceLink.setManagement(this.managementService.findOne(managementServiceLink.getManagement()));
                return new ApiResponse("Success", Arrays.asList(this.managementServiceLinkService.updateItem(managementServiceLink)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ManagementServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ManagementServiceLink - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ManagementServiceLink managementServiceLink = new ManagementServiceLink();
        managementServiceLink.setManagementServiceId(id);
        ValidateObject validateObject = this.managementServiceLinkValidate.deleteItem(managementServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.managementServiceLinkService.deleteItem(managementServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ManagementServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ManagementServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ManagementServiceLink managementServiceLink = new ManagementServiceLink();
        managementServiceLink.setManagementServiceId(id);
        ValidateObject validateObject = this.managementServiceLinkValidate.findOne(managementServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.managementServiceLinkService.findOne(managementServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
