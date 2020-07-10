package ir.parto.crm.modules.port.controller.validate;

import ir.parto.crm.modules.port.model.entity.Vendor;
import ir.parto.crm.modules.port.model.service.VendorService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class VendorValidate implements ValidateInterface<Vendor> {
    private VendorService vendorService;

    @Autowired
    public VendorValidate(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Override
    public ValidateObject validateAddNewItem(Vendor vendor) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(Vendor vendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (vendor == null) {
            errorList.add("Vendor object is nul");
        } else {
            if (!this.vendorService.existsById(vendor.getVendorId())) {
                errorList.add("Vendor Id not defined");
            }

            if (vendor.getTitle() == null || vendor.getTitle().isEmpty()) {
                errorList.add("Title is required");
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
    public ValidateObject deleteItem(Vendor vendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (vendor == null) {
            errorList.add("Vendor object is nul");
        } else {
            if (!this.vendorService.existsById(vendor.getVendorId())) {
                errorList.add("Vendor Id not defined");
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
    public ValidateObject findOne(Vendor vendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (vendor == null) {
            errorList.add("Vendor object is nul");
        } else {
            if (!this.vendorService.existsById(vendor.getVendorId())) {
                errorList.add("Vendor Id not defined");
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
    public ValidateObject findById(Vendor vendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (vendor == null) {
            errorList.add("Vendor object is nul");
        } else {
            if (!this.vendorService.existsById(vendor.getVendorId())) {
                errorList.add("Vendor Id not defined");
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
