package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSiteSwitch;
import ir.parto.crm.modules.popSite.model.service.PopSiteSwitchService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSiteSwitchValidate implements ValidateInterface<PopSiteSwitch> {
    private PopSiteSwitchService popSiteSwitchService;
    @Autowired
    public PopSiteSwitchValidate(PopSiteSwitchService popSiteSwitchService) {
        this.popSiteSwitchService = popSiteSwitchService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSiteSwitch popSiteSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteSwitch == null || popSiteSwitch.getPopSiteRack() == null || popSiteSwitch.getPopSiteRack().getRackId() == 0){
            errorList.add("Rack not defined");
        }
        if(popSiteSwitch == null || popSiteSwitch.getPort_count() == null ||
                popSiteSwitch.getPort_count() == 0){
            errorList.add("Port Count is required");
        }
        if(popSiteSwitch == null || popSiteSwitch.getStartUnit() == null || popSiteSwitch.getStartUnit() == 0){
            errorList.add("StartUnit is required");
        }
        if(popSiteSwitch == null || popSiteSwitch.getEndUnit() == null || popSiteSwitch.getEndUnit() == 0){
            errorList.add("EndUnit is required");
        }
        if(popSiteSwitch == null || popSiteSwitch.getTotalUnit() == null || popSiteSwitch.getTotalUnit() == 0){
            errorList.add("TotalUnit is required");
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
    public ValidateObject validateUpdateItem(PopSiteSwitch popSiteSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteSwitch != null && popSiteSwitch.getPopSiteRack() != null
                && popSiteSwitch.getPopSiteRack().getRackId() == 0){
            errorList.add("Rack not defined");
        }
        if(popSiteSwitch != null && popSiteSwitch.getPort_count() != null &&
                popSiteSwitch.getPort_count() == 0){
            errorList.add("Port Count is required");
        }
        if(popSiteSwitch != null && popSiteSwitch.getStartUnit() != null && popSiteSwitch.getStartUnit() == 0){
            errorList.add("StartUnit is required");
        }
        if(popSiteSwitch !=  null && popSiteSwitch.getEndUnit() != null && popSiteSwitch.getEndUnit() == 0){
            errorList.add("EndUnit is required");
        }
        if(popSiteSwitch != null && popSiteSwitch.getTotalUnit() != null && popSiteSwitch.getTotalUnit() == 0){
            errorList.add("TotalUnit is required");
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
    public ValidateObject deleteItem(PopSiteSwitch popSiteSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteSwitch == null || !this.popSiteSwitchService.existsById(popSiteSwitch.getSwitchId())){
            errorList.add("PopSiteSwitch not defined");
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
    public ValidateObject findOne(PopSiteSwitch popSiteSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteSwitch == null || !this.popSiteSwitchService.existsById(popSiteSwitch.getSwitchId())){
            errorList.add("PopSiteSwitch not defined");
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
    public ValidateObject findById(PopSiteSwitch popSiteSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteSwitch == null || !this.popSiteSwitchService.existsById(popSiteSwitch.getSwitchId())){
            errorList.add("PopSiteSwitch not defined");
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
