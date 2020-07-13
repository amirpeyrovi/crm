package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRadioServiceLink;
import ir.parto.crm.modules.popSite.model.service.PopSiteRadioServiceLinkService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSiteRadioServiceLinkValidate implements ValidateInterface<PopSiteRadioServiceLink> {
    private PopSiteRadioServiceLinkService popSiteRadioServiceLinkService;

    @Autowired
    public PopSiteRadioServiceLinkValidate(PopSiteRadioServiceLinkService popSiteRadioServiceLinkService) {
        this.popSiteRadioServiceLinkService = popSiteRadioServiceLinkService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadioServiceLink == null || popSiteRadioServiceLink.getPopSiteRadio() == null || popSiteRadioServiceLink.getPopSiteRadio().getRadioId() ==0){
            errorList.add("PopSiteRadio not defined");
        }
        if(popSiteRadioServiceLink == null || popSiteRadioServiceLink.getService() == null ||
                popSiteRadioServiceLink.getService().getServiceId() ==0){
            errorList.add("Service not defined");
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
    public ValidateObject validateUpdateItem(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadioServiceLink != null && popSiteRadioServiceLink.getPopSiteRadio() != null && popSiteRadioServiceLink.getPopSiteRadio().getRadioId() ==0){
            errorList.add("PopSiteRadio not defined");
        }
        if(popSiteRadioServiceLink != null && popSiteRadioServiceLink.getService() != null &&
                popSiteRadioServiceLink.getService().getServiceId() == 0){
            errorList.add("Service not defined");
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
    public ValidateObject deleteItem(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadioServiceLink == null || !this.popSiteRadioServiceLinkService.existsById(popSiteRadioServiceLink.getRadioServiceLinkId())){
            errorList.add("PopSiteRadioServiceLink not defined");
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
    public ValidateObject findOne(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadioServiceLink == null || !this.popSiteRadioServiceLinkService.existsById(popSiteRadioServiceLink.getRadioServiceLinkId())){
            errorList.add("PopSiteRadioServiceLink not defined");
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
    public ValidateObject findById(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadioServiceLink == null || !this.popSiteRadioServiceLinkService.existsById(popSiteRadioServiceLink.getRadioServiceLinkId())){
            errorList.add("PopSiteRadioServiceLink not defined");
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
