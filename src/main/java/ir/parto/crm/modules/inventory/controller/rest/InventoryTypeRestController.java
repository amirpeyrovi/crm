package ir.parto.crm.modules.inventory.controller.rest;


import ir.parto.crm.modules.inventory.controller.validate.InventoryTypeValidate;
import ir.parto.crm.modules.inventory.model.entity.InventoryType;
import ir.parto.crm.modules.inventory.model.service.InventoryTypeService;
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
@RequestMapping("/v1/inventory/inventoryType")
public class InventoryTypeRestController implements RestControllerInterface {
    private InventoryTypeService inventoryTypeService;
    private InventoryTypeValidate inventoryTypeValidate;

    @Autowired
    public InventoryTypeRestController(InventoryTypeService inventoryTypeService, InventoryTypeValidate inventoryTypeValidate) {
        this.inventoryTypeService = inventoryTypeService;
        this.inventoryTypeValidate = inventoryTypeValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "InventoryType")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryType - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.inventoryTypeValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<InventoryType> inventoryTypePage = this.inventoryTypeService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Product", sortProperty, sortOrder));
            return new ApiResponse("Success", inventoryTypePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody InventoryType inventoryType) {
        if (CheckPermission.getInstance().check("admin_add", "InventoryType")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryType - admin_add - access denied!"))
                    .getFaultResponse();
        }

        inventoryType.setInventoryTypeId(null);

        ValidateObject validateObject = this.inventoryTypeValidate.validateAddNewItem(inventoryType);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryTypeService.addNewItem(inventoryType)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody InventoryType inventoryType) {
        if (CheckPermission.getInstance().check("admin_update", "InventoryType")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryType - admin_update - access denied!"))
                    .getFaultResponse();
        }

        inventoryType.setInventoryTypeId(id);

        ValidateObject validateObject = this.inventoryTypeValidate.validateUpdateItem(inventoryType);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.inventoryTypeService.updateItem(inventoryType)))
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
        if (CheckPermission.getInstance().check("admin_delete", "InventoryType")) {
            return new ApiResponse("Error", 101, Arrays.asList("InventoryType - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        InventoryType inventoryType = new InventoryType();
        inventoryType.setInventoryTypeId(id);
        ValidateObject validateObject = this.inventoryTypeValidate.deleteItem(inventoryType);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryTypeService.deleteItem(inventoryType)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Product")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_show - access denied!"))
                    .getFaultResponse();
        }

        InventoryType inventoryType = new InventoryType();
        inventoryType.setInventoryTypeId(id);
        ValidateObject validateObject = this.inventoryTypeValidate.findOne(inventoryType);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.inventoryTypeService.findOne(inventoryType)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
