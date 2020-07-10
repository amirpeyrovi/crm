package ir.parto.crm.modules.inventory.controller.validate;

import ir.parto.crm.modules.inventory.model.entity.InventoryItem;
import ir.parto.crm.modules.inventory.model.service.InventoryGroupService;
import ir.parto.crm.modules.inventory.model.service.InventoryItemService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class InventoryItemValidate implements ValidateInterface<InventoryItem> {
    private InventoryItemService inventoryItemService;
    private InventoryGroupService inventoryGroupService;

    @Autowired
    public InventoryItemValidate(InventoryItemService inventoryItemService, InventoryGroupService inventoryGroupService) {
        this.inventoryItemService = inventoryItemService;
        this.inventoryGroupService = inventoryGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(InventoryItem inventoryItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItem == null) {
            errorList.add("InventoryItem object is nul");
        } else {
            if (inventoryItem.getInventoryGroup() == null) {
                errorList.add("InventoryGroup object is nul");
            } else {
                if (!this.inventoryGroupService.existsById(inventoryItem.getInventoryGroup().getInventoryGroupId())) {
                    errorList.add("InventoryGroup Id not defined");
                }

                if (inventoryItem.getName() == null || inventoryItem.getName().isEmpty()) {
                    errorList.add("Name is required");
                }

                if (inventoryItem.getFileName() == null || inventoryItem.getFileName().isEmpty()) {
                    errorList.add("FileName is required");
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
    public ValidateObject validateUpdateItem(InventoryItem inventoryItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItem == null) {
            errorList.add("InventoryItem object is nul");
        } else {
            if (inventoryItem.getInventoryGroup() == null) {
                errorList.add("InventoryGroup object is nul");
            } else {
                if (!this.inventoryItemService.existsById(inventoryItem.getInventoryItemId())) {
                    errorList.add("InventoryItem Id not defined");
                }

                if (!this.inventoryGroupService.existsById(inventoryItem.getInventoryGroup().getInventoryGroupId())) {
                    errorList.add("InventoryGroup Id not defined");
                }

                if (inventoryItem.getName() == null || inventoryItem.getName().isEmpty()) {
                    errorList.add("Name is required");
                }

                if (inventoryItem.getFileName() == null || inventoryItem.getFileName().isEmpty()) {
                    errorList.add("FileName is required");
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
    public ValidateObject deleteItem(InventoryItem inventoryItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItem == null) {
            errorList.add("InventoryItem object is nul");
        } else {
            if (!this.inventoryItemService.existsById(inventoryItem.getInventoryItemId())) {
                errorList.add("InventoryItem Id not defined");
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
    public ValidateObject findOne(InventoryItem inventoryItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItem == null) {
            errorList.add("InventoryItem object is nul");
        } else {
            if (!this.inventoryItemService.existsById(inventoryItem.getInventoryItemId())) {
                errorList.add("InventoryItem Id not defined");
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
    public ValidateObject findById(InventoryItem inventoryItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItem == null) {
            errorList.add("InventoryItem object is nul");
        } else {
            if (!this.inventoryItemService.existsById(inventoryItem.getInventoryItemId())) {
                errorList.add("InventoryItem Id not defined");
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
