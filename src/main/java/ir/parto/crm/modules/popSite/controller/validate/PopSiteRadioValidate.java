package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRadio;
import ir.parto.crm.modules.popSite.model.service.PopSiteRadioService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PopSiteRadioValidate implements ValidateInterface<PopSiteRadio> {
    private PopSiteRadioService popSiteRadioService;

    @Autowired
    public PopSiteRadioValidate(PopSiteRadioService popSiteRadioService) {
        this.popSiteRadioService = popSiteRadioService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSiteRadio popSiteRadio) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadio == null || popSiteRadio.getPopSitePort() == null || popSiteRadio.getPopSitePort().getPortId() == 0){
            errorList.add("PopSitePort not defined");
        }
        if(popSiteRadio == null || popSiteRadio.getPopSiteTowerBranch() == null || popSiteRadio.getPopSiteTowerBranch().getBranchId() == 0){
            errorList.add("PopSiteTowerBranch not defined");
        }
        if(popSiteRadio == null || popSiteRadio.getPopSiteVendor() == null || popSiteRadio.getPopSiteVendor().getVendorId() == 0){
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
    public ValidateObject validateUpdateItem(PopSiteRadio popSiteRadio) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadio != null && popSiteRadio.getPopSitePort() != null && popSiteRadio.getPopSitePort().getPortId() == 0){
            errorList.add("PopSitePort not defined");
        }
        if(popSiteRadio != null && popSiteRadio.getPopSiteTowerBranch() != null && popSiteRadio.getPopSiteTowerBranch().getBranchId() == 0){
            errorList.add("PopSiteTowerBranch not defined");
        }
        if(popSiteRadio != null && popSiteRadio.getPopSiteVendor() != null && popSiteRadio.getPopSiteVendor().getVendorId() == 0){
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
    public ValidateObject deleteItem(PopSiteRadio popSiteRadio) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadio != null || !this.popSiteRadioService.existsById(popSiteRadio.getRadioId())){
            errorList.add("PopSiteRadio not defined");
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
    public ValidateObject findOne(PopSiteRadio popSiteRadio) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadio != null || !this.popSiteRadioService.existsById(popSiteRadio.getRadioId())){
            errorList.add("PopSiteRadio not defined");
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
    public ValidateObject findById(PopSiteRadio popSiteRadio) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadio != null || !this.popSiteRadioService.existsById(popSiteRadio.getRadioId())){
            errorList.add("PopSiteRadio not defined");
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
