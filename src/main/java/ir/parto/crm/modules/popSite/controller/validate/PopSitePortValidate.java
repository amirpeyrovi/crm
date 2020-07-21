package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSitePort;
import ir.parto.crm.modules.popSite.model.service.PopSitePortService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSitePortValidate implements ValidateInterface<PopSitePort> {
    private PopSitePortService popSitePortService;

    @Autowired
    public PopSitePortValidate(PopSitePortService popSitePortService) {
        this.popSitePortService = popSitePortService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSitePort popSitePort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSitePort == null ){
            errorList.add("PopSitePort is required");
        }else{
            if(popSitePort.getPopSiteSwitch() == null || popSitePort.getPopSiteSwitch().getSwitchId() == 0){
                errorList.add("Switch is required");
            }
            if(popSitePort.getStatus() == null || popSitePort.getStatus() == 0){
                errorList.add("Status is required");
            }
            if(popSitePort.getNumber() == null || popSitePort.getNumber() == 0){
                errorList.add("PortNumber is required");
            }
            if(popSitePort.getUsername() == null || popSitePort.getUsername().isEmpty()){
                errorList.add("UserName is required");
            }
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
    public ValidateObject validateUpdateItem(PopSitePort popSitePort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSitePort != null && popSitePort.getPopSiteSwitch() != null && popSitePort.getPopSiteSwitch().getSwitchId() == 0){
            errorList.add("Switch is required");
        }
        if(popSitePort != null && popSitePort.getStatus() != null && popSitePort.getStatus() == 0){
            errorList.add("Status is required");
        }
        if(popSitePort != null && popSitePort.getNumber() != null && popSitePort.getNumber() == 0){
            errorList.add("PortNumber is required");
        }
        if(popSitePort != null && popSitePort.getUsername() != null && popSitePort.getUsername().isEmpty()){
            errorList.add("UserName is required");
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
    public ValidateObject deleteItem(PopSitePort popSitePort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSitePort == null || !this.popSitePortService.existsById(popSitePort.getPortId())){
            errorList.add("PopSitePort not defined");
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
    public ValidateObject findOne(PopSitePort popSitePort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSitePort == null || !this.popSitePortService.existsById(popSitePort.getPortId())){
            errorList.add("PopSitePort not defined");
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
    public ValidateObject findById(PopSitePort popSitePort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSitePort == null || !this.popSitePortService.existsById(popSitePort.getPortId())){
            errorList.add("PopSitePort not defined");
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
