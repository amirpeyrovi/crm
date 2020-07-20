package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSiteTower;
import ir.parto.crm.modules.popSite.model.service.PopSiteService;
import ir.parto.crm.modules.popSite.model.service.PopSiteTowerService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSiteTowerValidate implements ValidateInterface<PopSiteTower> {
    private PopSiteTowerService popSiteTowerService;
    private PopSiteService popSiteService;

    @Autowired
    public PopSiteTowerValidate(PopSiteTowerService popSiteTowerService, PopSiteService popSiteService) {
        this.popSiteTowerService = popSiteTowerService;
        this.popSiteService = popSiteService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSiteTower popSiteTower) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTower == null){
            errorList.add("PopSiteTower is null");
        }else
        {
        }
        if( popSiteTower.getPopSite() == null || popSiteTower.getPopSite().getPopSiteId() == 0){
            errorList.add("PopSite not defined");
        }else if (!popSiteService.existsById(popSiteTower.getPopSite().getPopSiteId())) {
            errorList.add("PopSite Not Found!");
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
    public ValidateObject validateUpdateItem(PopSiteTower popSiteTower) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTower != null && popSiteTower.getPopSite() != null && popSiteTower.getPopSite().getPopSiteId() == 0){
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
    public ValidateObject deleteItem(PopSiteTower popSiteTower) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTower == null || !this.popSiteTowerService.existsById(popSiteTower.getTowerId())){
            errorList.add("PopSiteTower not defined");
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
    public ValidateObject findOne(PopSiteTower popSiteTower) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTower == null || !this.popSiteTowerService.existsById(popSiteTower.getTowerId())){
            errorList.add("PopSiteTower not defined");
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
    public ValidateObject findById(PopSiteTower popSiteTower) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteTower == null || !this.popSiteTowerService.existsById(popSiteTower.getTowerId())){
           errorList.add("PopSiteTower not defined");
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
