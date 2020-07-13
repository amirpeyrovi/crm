package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSite;
import ir.parto.crm.modules.popSite.model.service.PopSiteService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSiteValidate implements ValidateInterface<PopSite> {
    private PopSiteService popSiteService;

    @Autowired
    public PopSiteValidate(PopSiteService popSiteService) {
        this.popSiteService = popSiteService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSite popSite) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSite == null || popSite.getTitle() == null || popSite.getTitle().isEmpty()){
            errorList.add("Title is required");
        }
        if(popSite == null || popSite.getLatitude() == null || popSite.getLatitude().isEmpty()){
            errorList.add("Latitude is required");
        }
        if(popSite == null || popSite.getLongitude() == null || popSite.getLongitude().isEmpty()){
            errorList.add("Longitude is required");
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
    public ValidateObject validateUpdateItem(PopSite popSite) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSite != null && popSite.getTitle() != null && popSite.getTitle().isEmpty()){
            errorList.add("Title is required");
        }
        if(popSite != null && popSite.getLatitude() != null && popSite.getLatitude().isEmpty()){
            errorList.add("Latitude is required");
        }
        if(popSite != null && popSite.getLongitude() != null && popSite.getLongitude().isEmpty()){
            errorList.add("Longitude is required");
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
    public ValidateObject deleteItem(PopSite popSite) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSite == null || !this.popSiteService.existsById(popSite.getPopSiteId())){
            errorList.add("PopSite not defined");
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
    public ValidateObject findOne(PopSite popSite) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSite == null || !this.popSiteService.existsById(popSite.getPopSiteId())){
            errorList.add("PopSite not defined");
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
    public ValidateObject findById(PopSite popSite) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSite == null || !this.popSiteService.existsById(popSite.getPopSiteId())){
            errorList.add("PopSite not defined");
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
