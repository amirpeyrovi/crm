package ir.parto.crm.modules.inventory.controller.validate;

import ir.parto.crm.modules.inventory.model.entity.InventoryItemServiceLink;
import ir.parto.crm.modules.inventory.model.service.InventoryItemService;
import ir.parto.crm.modules.inventory.model.service.InventoryItemServiceLinkService;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class InventoryItemServiceLinkValidate implements ValidateInterface<InventoryItemServiceLink> {
    private InventoryItemServiceLinkService inventoryItemServiceLinkService;
    private InventoryItemService inventoryItemService;
    private ServiceService serviceService;

    @Autowired
    public InventoryItemServiceLinkValidate(InventoryItemServiceLinkService inventoryItemServiceLinkService, InventoryItemService inventoryItemService, ServiceService serviceService) {
        this.inventoryItemServiceLinkService = inventoryItemServiceLinkService;
        this.inventoryItemService = inventoryItemService;
        this.serviceService = serviceService;
    }

    @Override
    public ValidateObject validateAddNewItem(InventoryItemServiceLink inventoryItemServiceLink) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(InventoryItemServiceLink inventoryItemServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItemServiceLink == null) {
            errorList.add("InventoryItemServiceLink object is nul");
        } else {
            if (inventoryItemServiceLink.getInventoryItem() == null) {
                errorList.add("InventoryItem object is nul");
            } else {
                if (inventoryItemServiceLink.getService() == null) {
                    errorList.add("Service object is nul");
                } else {
                    if (!this.inventoryItemServiceLinkService.existsById(inventoryItemServiceLink.getInventoryServiceLinkId())) {
                        errorList.add("InventoryItemServiceLink Id not defined");
                    }

                    if (!this.inventoryItemService.existsById(inventoryItemServiceLink.getInventoryItem().getInventoryItemId())) {
                        errorList.add("InventoryItem Id not defined");
                    }

                    if (!this.serviceService.existsById(inventoryItemServiceLink.getService().getServiceId())) {
                        errorList.add("Service Id not defined");
                    }
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
    public ValidateObject deleteItem(InventoryItemServiceLink inventoryItemServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItemServiceLink == null) {
            errorList.add("InventoryItemServiceLink object is nul");
        } else {
            if (!this.inventoryItemServiceLinkService.existsById(inventoryItemServiceLink.getInventoryServiceLinkId())) {
                errorList.add("InventoryItemServiceLink Id not defined");
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
    public ValidateObject findOne(InventoryItemServiceLink inventoryItemServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItemServiceLink == null) {
            errorList.add("InventoryItemServiceLink object is nul");
        } else {
            if (!this.inventoryItemServiceLinkService.existsById(inventoryItemServiceLink.getInventoryServiceLinkId())) {
                errorList.add("InventoryItemServiceLink Id not defined");
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
    public ValidateObject findById(InventoryItemServiceLink inventoryItemServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (inventoryItemServiceLink == null) {
            errorList.add("InventoryItemServiceLink object is nul");
        } else {
            if (!this.inventoryItemServiceLinkService.existsById(inventoryItemServiceLink.getInventoryServiceLinkId())) {
                errorList.add("InventoryItemServiceLink Id not defined");
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
