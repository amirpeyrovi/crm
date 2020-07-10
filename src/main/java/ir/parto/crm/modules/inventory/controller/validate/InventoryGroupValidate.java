package ir.parto.crm.modules.inventory.controller.validate;

import ir.parto.crm.modules.inventory.model.entity.InventoryGroup;
import ir.parto.crm.modules.inventory.model.service.InventoryGroupService;
import ir.parto.crm.modules.inventory.model.service.InventoryTypeService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class InventoryGroupValidate implements ValidateInterface<InventoryGroup> {
    private InventoryGroupService inventoryGroupService;
    private InventoryTypeService inventoryTypeService;

    @Autowired
    public InventoryGroupValidate(InventoryGroupService inventoryGroupService, InventoryTypeService inventoryTypeService) {
        this.inventoryGroupService = inventoryGroupService;
        this.inventoryTypeService = inventoryTypeService;
    }

    @Override
    public ValidateObject validateAddNewItem(InventoryGroup inventoryGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryGroup == null) {
            errorList.add("InventoryGroup object is nul");
        } else {
            if (inventoryGroup.getInventoryType() == null) {
                errorList.add("InventoryType object is nul");
            } else {
                if (!this.inventoryTypeService.existsById(inventoryGroup.getInventoryType().getInventoryTypeId())) {
                    errorList.add("InventoryType Id not defined");
                }

                if (inventoryGroup.getName() == null || inventoryGroup.getName().isEmpty()) {
                    errorList.add("Name is required");
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(InventoryGroup inventoryGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryGroup == null) {
            errorList.add("InventoryGroup object is nul");
        } else {
            if (inventoryGroup.getInventoryType() == null) {
                errorList.add("InventoryType object is nul");
            } else {
                if (!this.inventoryGroupService.existsById(inventoryGroup.getInventoryGroupId())) {
                    errorList.add("InventoryGroup Id not defined");
                }

                if (!this.inventoryTypeService.existsById(inventoryGroup.getInventoryType().getInventoryTypeId())) {
                    errorList.add("InventoryType Id not defined");
                }

                if (inventoryGroup.getName() == null || inventoryGroup.getName().isEmpty()) {
                    errorList.add("Name is required");
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(InventoryGroup inventoryGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryGroup == null) {
            errorList.add("InventoryGroup object is nul");
        } else {
            if (!this.inventoryGroupService.existsById(inventoryGroup.getInventoryGroupId())) {
                errorList.add("InventoryGroup Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findOne(InventoryGroup inventoryGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryGroup == null) {
            errorList.add("InventoryGroup object is nul");
        } else {
            if (!this.inventoryGroupService.existsById(inventoryGroup.getInventoryGroupId())) {
                errorList.add("InventoryGroup Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findById(InventoryGroup inventoryGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryGroup == null) {
            errorList.add("InventoryGroup object is nul");
        } else {
            if (!this.inventoryGroupService.existsById(inventoryGroup.getInventoryGroupId())) {
                errorList.add("InventoryGroup Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }
}
