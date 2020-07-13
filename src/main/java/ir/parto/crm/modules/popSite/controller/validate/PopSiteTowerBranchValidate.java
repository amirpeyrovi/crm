package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSiteTowerBranch;
import ir.parto.crm.modules.popSite.model.service.PopSiteTowerBranchService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSiteTowerBranchValidate implements ValidateInterface<PopSiteTowerBranch> {
    private PopSiteTowerBranchService popSiteTowerBranchService;

    @Autowired
    public PopSiteTowerBranchValidate(PopSiteTowerBranchService popSiteTowerBranchService) {
        this.popSiteTowerBranchService = popSiteTowerBranchService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSiteTowerBranch popSiteTowerBranch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTowerBranch == null || popSiteTowerBranch.getPopSiteTower() == null
                || popSiteTowerBranch.getPopSiteTower().getTowerId() == 0){
            errorList.add("PopSiteTowerBranch not defined");
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
    public ValidateObject validateUpdateItem(PopSiteTowerBranch popSiteTowerBranch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTowerBranch != null && popSiteTowerBranch.getPopSiteTower() != null
                && popSiteTowerBranch.getPopSiteTower().getTowerId() == 0){
            errorList.add("PopSiteTowerBranch not defined");
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
    public ValidateObject deleteItem(PopSiteTowerBranch popSiteTowerBranch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTowerBranch == null || !this.popSiteTowerBranchService.existsById(popSiteTowerBranch.getBranchId())){
            errorList.add("PopSiteTowerBranch not defined");
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
    public ValidateObject findOne(PopSiteTowerBranch popSiteTowerBranch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTowerBranch == null || !this.popSiteTowerBranchService.existsById(popSiteTowerBranch.getBranchId())){
            errorList.add("PopSiteTowerBranch not defined");
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
    public ValidateObject findById(PopSiteTowerBranch popSiteTowerBranch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTowerBranch == null || !this.popSiteTowerBranchService.existsById(popSiteTowerBranch.getBranchId())){
            errorList.add("PopSiteTowerBranch not defined");
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
