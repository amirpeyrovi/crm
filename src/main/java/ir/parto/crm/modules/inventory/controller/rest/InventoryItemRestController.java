package ir.parto.crm.modules.inventory.controller.rest;


import ir.parto.crm.modules.inventory.controller.validate.InventoryItemValidate;
import ir.parto.crm.modules.inventory.model.entity.InventoryGroup;
import ir.parto.crm.modules.inventory.model.entity.InventoryItem;
import ir.parto.crm.modules.inventory.model.service.InventoryGroupService;
import ir.parto.crm.modules.inventory.model.service.InventoryItemService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.InventoryAnnotation;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@InventoryAnnotation
@RequestMapping("/v1/invenory/inventoryGroup")
public class InventoryItemRestController {
    private InventoryItemService inventoryItemService;
    private InventoryItemValidate inventoryItemValidate;
    private InventoryGroupService inventoryGroupService;

    @Autowired
    public InventoryItemRestController(InventoryItemService inventoryItemService, InventoryItemValidate inventoryItemValidate, InventoryGroupService inventoryGroupService) {
        this.inventoryItemService = inventoryItemService;
        this.inventoryItemValidate = inventoryItemValidate;
        this.inventoryGroupService = inventoryGroupService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "InventoryItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItem - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.inventoryItemValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<InventoryItem> inventoryItemPage = this.inventoryItemService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "InventoryGroup", sortProperty, sortOrder));
            return new ApiResponse("Success", inventoryItemPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody InventoryItem inventoryItem) {
        if (CheckPermission.getInstance().check("admin_add", "InventoryItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItem - admin_add - access denied!"))
                    .getFaultResponse();
        }

        inventoryItem.setInventoryItemId(null);

        ValidateObject validateObject = this.inventoryItemValidate.validateAddNewItem(inventoryItem);
        if (validateObject.getResult().equals("success")) {
            inventoryItem.setInventoryGroup(this.inventoryGroupService.findOne(inventoryItem.getInventoryGroup()));
            return new ApiResponse("Success", Arrays.asList(this.inventoryItemService.addNewItem(inventoryItem)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody InventoryItem inventoryItem) {
        if (CheckPermission.getInstance().check("admin_update", "InventoryItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItem - admin_update - access denied!"))
                    .getFaultResponse();
        }

        inventoryItem.setInventoryItemId(id);

        ValidateObject validateObject = this.inventoryItemValidate.validateUpdateItem(inventoryItem);
        if (validateObject.getResult().equals("success")) {
            try {
                inventoryItem.setInventoryGroup(this.inventoryGroupService.findOne(inventoryItem.getInventoryGroup()));
                return new ApiResponse("Success", Arrays.asList(this.inventoryItemService.updateItem(inventoryItem)))
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
        if (CheckPermission.getInstance().check("admin_delete", "InventoryItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItem - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setInventoryItemId(id);
        ValidateObject validateObject = this.inventoryItemValidate.deleteItem(inventoryItem);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryItemService.deleteItem(inventoryItem)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "InventoryItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryItem - admin_show - access denied!"))
                    .getFaultResponse();
        }

        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setInventoryItemId(id);
        ValidateObject validateObject = this.inventoryItemValidate.findOne(inventoryItem);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryItemService.findOne(inventoryItem)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
