package ir.parto.crm.modules.port.controller.validate;

import ir.parto.crm.modules.port.model.entity.Management;
import ir.parto.crm.modules.port.model.service.ManagementService;
import ir.parto.crm.modules.port.model.service.VendorService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ManagementValidate implements ValidateInterface<Management> {
    private ManagementService managementService;
    private VendorService vendorService;

    @Autowired
    public ManagementValidate(ManagementService managementService, VendorService vendorService) {
        this.managementService = managementService;
        this.vendorService = vendorService;
    }

    @Override
    public ValidateObject validateAddNewItem(Management management) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(Management management) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (management == null) {
            errorList.add("Management object is nul");
        } else {
            if (management.getVendor() == null) {
                errorList.add("Vendor object is nul");
            } else {
                if (!this.managementService.existsById(management.getManagementId())) {
                    errorList.add("Management Id not defined");
                }

                if (management.getTitle() == null || management.getTitle().isEmpty()) {
                    errorList.add("Title is required");
                }

                if (management.getUrl() == null || management.getUrl().isEmpty()) {
                    errorList.add("Url is required");
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
    public ValidateObject deleteItem(Management management) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (management == null) {
            errorList.add("Management object is nul");
        } else {
            if (!this.managementService.existsById(management.getManagementId())) {
                errorList.add("Management Id not defined");
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
    public ValidateObject findOne(Management management) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (management == null) {
            errorList.add("Management object is nul");
        } else {
            if (!this.managementService.existsById(management.getManagementId())) {
                errorList.add("Management Id not defined");
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
    public ValidateObject findById(Management management) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (management == null) {
            errorList.add("Management object is nul");
        } else {
            if (!this.managementService.existsById(management.getManagementId())) {
                errorList.add("Management Id not defined");
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
