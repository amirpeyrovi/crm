package ir.parto.crm.modules.inventory.controller.rest;

import ir.parto.crm.modules.inventory.controller.validate.InventoryGroupValidate;
import ir.parto.crm.modules.inventory.model.entity.InventoryGroup;
import ir.parto.crm.modules.inventory.model.service.InventoryGroupService;
import ir.parto.crm.modules.inventory.model.service.InventoryTypeService;
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
public class InventoryGroupRestController {
    private InventoryGroupService inventoryGroupService;
    private InventoryGroupValidate inventoryGroupValidate;
    private InventoryTypeService inventoryTypeService;

    @Autowired
    public InventoryGroupRestController(InventoryGroupService inventoryGroupService, InventoryGroupValidate inventoryGroupValidate, InventoryTypeService inventoryTypeService) {
        this.inventoryGroupService = inventoryGroupService;
        this.inventoryGroupValidate = inventoryGroupValidate;
        this.inventoryTypeService = inventoryTypeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "InventoryGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.inventoryGroupValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<InventoryGroup> inventoryGroupPage = this.inventoryGroupService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "InventoryGroup", sortProperty, sortOrder));
            return new ApiResponse("Success", inventoryGroupPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody InventoryGroup inventoryGroup) {
        if (CheckPermission.getInstance().check("admin_add", "InventoryGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryGroup - admin_add - access denied!"))
                    .getFaultResponse();
        }

        inventoryGroup.setInventoryGroupId(null);

        ValidateObject validateObject = this.inventoryGroupValidate.validateAddNewItem(inventoryGroup);
        if (validateObject.getResult().equals("success")) {
            inventoryGroup.setInventoryType(this.inventoryTypeService.findOne(inventoryGroup.getInventoryType()));
            return new ApiResponse("Success", Arrays.asList(this.inventoryGroupService.addNewItem(inventoryGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody InventoryGroup inventoryGroup) {
        if (CheckPermission.getInstance().check("admin_update", "InventoryGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryGroup - admin_update - access denied!"))
                    .getFaultResponse();
        }

        inventoryGroup.setInventoryGroupId(id);

        ValidateObject validateObject = this.inventoryGroupValidate.validateUpdateItem(inventoryGroup);
        if (validateObject.getResult().equals("success")) {
            try {
                inventoryGroup.setInventoryType(this.inventoryTypeService.findOne(inventoryGroup.getInventoryType()));
                return new ApiResponse("Success", Arrays.asList(this.inventoryGroupService.updateItem(inventoryGroup)))
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
        if (CheckPermission.getInstance().check("admin_delete", "InventoryGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryGroup - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        InventoryGroup inventoryGroup = new InventoryGroup();
        inventoryGroup.setInventoryGroupId(id);
        ValidateObject validateObject = this.inventoryGroupValidate.deleteItem(inventoryGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryGroupService.deleteItem(inventoryGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "InventoryGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        InventoryGroup inventoryGroup = new InventoryGroup();
        inventoryGroup.setInventoryGroupId(id);
        ValidateObject validateObject = this.inventoryGroupValidate.findOne(inventoryGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryGroupService.findOne(inventoryGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
