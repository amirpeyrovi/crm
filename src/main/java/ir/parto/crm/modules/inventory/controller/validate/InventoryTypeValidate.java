package ir.parto.crm.modules.inventory.controller.validate;

import ir.parto.crm.modules.inventory.model.entity.InventoryType;
import ir.parto.crm.modules.inventory.model.service.InventoryTypeService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class InventoryTypeValidate implements ValidateInterface<InventoryType> {
    private InventoryTypeService inventoryTypeService;

    @Autowired
    public InventoryTypeValidate(InventoryTypeService inventoryTypeService) {
        this.inventoryTypeService = inventoryTypeService;
    }

    @Override
    public ValidateObject validateAddNewItem(InventoryType inventoryType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryType == null) {
            errorList.add("InventoryType is nul");
        } else {
            if (inventoryType.getName() == null || inventoryType.getName().isEmpty()) {
                errorList.add("Name is required");
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
    public ValidateObject validateUpdateItem(InventoryType inventoryType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryType == null) {
            errorList.add("InventoryType is nul");
        } else {
            if (!this.inventoryTypeService.existsById(inventoryType.getInventoryTypeId())) {
                errorList.add("InventoryType Id not defined");
            }

            if (inventoryType.getName() == null || inventoryType.getName().isEmpty()) {
                errorList.add("Name is required");
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
    public ValidateObject deleteItem(InventoryType inventoryType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryType == null) {
            errorList.add("InventoryType is nul");
        } else {
            if (!this.inventoryTypeService.existsById(inventoryType.getInventoryTypeId())) {
                errorList.add("InventoryType Id not defined");
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
    public ValidateObject findOne(InventoryType inventoryType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryType == null) {
            errorList.add("InventoryType is nul");
        } else {
            if (!this.inventoryTypeService.existsById(inventoryType.getInventoryTypeId())) {
                errorList.add("InventoryType Id not defined");
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
    public ValidateObject findById(InventoryType inventoryType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryType == null) {
            errorList.add("InventoryType is nul");
        } else {
            if (!this.inventoryTypeService.existsById(inventoryType.getInventoryTypeId())) {
                errorList.add("InventoryType Id not defined");
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
