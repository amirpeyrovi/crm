package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSiteVendor;
import ir.parto.crm.modules.popSite.model.service.PopSiteVendorService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSiteVendorValidate implements ValidateInterface<PopSiteVendor> {
    private PopSiteVendorService popSiteVendorService;

    @Autowired
    public PopSiteVendorValidate(PopSiteVendorService popSiteVendorService) {
        this.popSiteVendorService = popSiteVendorService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSiteVendor popSiteVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteVendor == null || popSiteVendor.getModel() == null || popSiteVendor.getModel().isEmpty()){
            errorList.add("Model is required");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(PopSiteVendor popSiteVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteVendor != null && popSiteVendor.getModel() != null && popSiteVendor.getModel().isEmpty()){
            errorList.add("Model is required");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(PopSiteVendor popSiteVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteVendor == null || !this.popSiteVendorService.existsById(popSiteVendor.getVendorId())){
            errorList.add("PopSiteVendor not defined");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findOne(PopSiteVendor popSiteVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteVendor == null || !this.popSiteVendorService.existsById(popSiteVendor.getVendorId())){
            errorList.add("PopSiteVendor not defined");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findById(PopSiteVendor popSiteVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteVendor == null || !this.popSiteVendorService.existsById(popSiteVendor.getVendorId())){
            errorList.add("PopSiteVendor not defined");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }
}
