package ir.parto.crm.modules.inventory.controller.rest;


import ir.parto.crm.modules.inventory.controller.validate.InventoryItemServiceLinkValidate;
import ir.parto.crm.modules.inventory.model.entity.InventoryItemServiceLink;
import ir.parto.crm.modules.inventory.model.service.InventoryItemService;
import ir.parto.crm.modules.inventory.model.service.InventoryItemServiceLinkService;
import ir.parto.crm.modules.server.model.service.ServerService;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.InventoryAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@InventoryAnnotation
@RequestMapping("/v1/invenory/inventoryItemService")
public class InventoryItemServiceLinkRestController implements RestControllerInterface {
    private InventoryItemService inventoryItemService;
    private ServiceService serviceService;
    private InventoryItemServiceLinkService inventoryItemServiceLinkService;
    private InventoryItemServiceLinkValidate inventoryItemServiceLinkValidate;

    @Autowired
    public InventoryItemServiceLinkRestController(InventoryItemService inventoryItemService, ServiceService serviceService, InventoryItemServiceLinkService inventoryItemServiceLinkService, InventoryItemServiceLinkValidate inventoryItemServiceLinkValidate) {
        this.inventoryItemService = inventoryItemService;
        this.serviceService = serviceService;
        this.inventoryItemServiceLinkService = inventoryItemServiceLinkService;
        this.inventoryItemServiceLinkValidate = inventoryItemServiceLinkValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "InventoryItemService")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItemService - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.inventoryItemServiceLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<InventoryItemServiceLink> inventoryItemServiceLinkPage = this.inventoryItemServiceLinkService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "InventoryItemServiceLink", sortProperty, sortOrder));
            return new ApiResponse("Success", inventoryItemServiceLinkPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody InventoryItemServiceLink inventoryItemServiceLink) {
        if (CheckPermission.getInstance().check("admin_add", "InventoryItemService")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItemService - admin_add - access denied!"))
                    .getFaultResponse();
        }

        inventoryItemServiceLink.setInventoryServiceLinkId(null);

        ValidateObject validateObject = this.inventoryItemServiceLinkValidate.validateAddNewItem(inventoryItemServiceLink);
        if (validateObject.getResult().equals("success")) {
            inventoryItemServiceLink.setInventoryItem(this.inventoryItemService.findOne(inventoryItemServiceLink.getInventoryItem()));
            inventoryItemServiceLink.setService(this.serviceService.findOne(inventoryItemServiceLink.getService()));
            return new ApiResponse("Success", Arrays.asList(this.inventoryItemServiceLinkService.addNewItem(inventoryItemServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody InventoryItemServiceLink inventoryItemServiceLink) {
        if (CheckPermission.getInstance().check("admin_update", "InventoryItemService")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItemService - admin_update - access denied!"))
                    .getFaultResponse();
        }

        inventoryItemServiceLink.setInventoryServiceLinkId(id);

        ValidateObject validateObject = this.inventoryItemServiceLinkValidate.validateUpdateItem(inventoryItemServiceLink);
        if (validateObject.getResult().equals("success")) {
            try {
                inventoryItemServiceLink.setInventoryItem(this.inventoryItemService.findOne(inventoryItemServiceLink.getInventoryItem()));
                inventoryItemServiceLink.setService(this.serviceService.findOne(inventoryItemServiceLink.getService()));
                return new ApiResponse("Success", Arrays.asList(this.inventoryItemServiceLinkService.updateItem(inventoryItemServiceLink)))
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
        if (CheckPermission.getInstance().check("admin_delete", "InventoryItemService")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItemService - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        InventoryItemServiceLink inventoryItemServiceLink = new InventoryItemServiceLink();
        inventoryItemServiceLink.setInventoryServiceLinkId(id);
        ValidateObject validateObject = this.inventoryItemServiceLinkValidate.deleteItem(inventoryItemServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryItemServiceLinkService.deleteItem(inventoryItemServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "InventoryItemService")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItemService - admin_show - access denied!"))
                    .getFaultResponse();
        }

        InventoryItemServiceLink inventoryItemServiceLink = new InventoryItemServiceLink();
        inventoryItemServiceLink.setInventoryServiceLinkId(id);
        ValidateObject validateObject = this.inventoryItemServiceLinkValidate.findOne(inventoryItemServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryItemServiceLinkService.findOne(inventoryItemServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
