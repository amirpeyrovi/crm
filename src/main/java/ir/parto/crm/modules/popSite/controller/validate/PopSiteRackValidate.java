package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRack;
import ir.parto.crm.modules.popSite.model.service.PopSiteRackService;
import ir.parto.crm.modules.popSite.model.service.PopSiteService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSiteRackValidate implements ValidateInterface<PopSiteRack> {
    private PopSiteRackService popSiteRackService;
    private PopSiteService popSiteService;

    @Autowired
    public PopSiteRackValidate(PopSiteRackService popSiteRackService, PopSiteService popSiteService) {
        this.popSiteRackService = popSiteRackService;
        this.popSiteService = popSiteService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSiteRack popSiteRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (popSiteRack == null) {
            errorList.add("PopSiteRack is null");
        } else {
            if (popSiteRack.getPopSite() == null || popSiteRack.getPopSite().getPopSiteId() == 0) {
                errorList.add("PopSite not defined");
            }
            if (popSiteRack.getRackNumber() == null || popSiteRack.getRackNumber() == 0) {
                errorList.add("RackNumber is required");
            } else {
                if (!popSiteService.existsById(popSiteRack.getPopSite().getPopSiteId())) {
                    errorList.add("PopSite Not Found!");
                }
            }
            if (popSiteRack.getTotalUnit() == null || popSiteRack.getTotalUnit() == 0) {
                errorList.add("TotalUnit is required");
            }
            if (errorList.size() > 0) {
                validateObject.setResult("error");
            } else {
                validateObject.setResult("success");
            }
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(PopSiteRack popSiteRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (popSiteRack == null) {
            errorList.add("PopSiteRack is null");
        } else {
            if (popSiteRack.getPopSite() != null && popSiteRack.getPopSite().getPopSiteId() == 0
                    && !popSiteService.existsById(popSiteRack.getPopSite().getPopSiteId())) {
                errorList.add("PopSite not defined");
            }
            if (popSiteRack.getRackNumber() != null && popSiteRack.getRackNumber() == 0) {
                errorList.add("RackNumber is required");
            }
            if (popSiteRack.getTotalUnit() != null && popSiteRack.getTotalUnit() == 0) {
                errorList.add("TotalUnit is required");
            }
            if (errorList.size() > 0) {
                validateObject.setResult("error");
            } else {
                validateObject.setResult("success");
            }
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(PopSiteRack popSiteRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (popSiteRack == null || !this.popSiteRackService.existsById(popSiteRack.getRackId())) {
            errorList.add("Rack not defined");
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
    public ValidateObject findOne(PopSiteRack popSiteRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (popSiteRack == null || !this.popSiteRackService.existsById(popSiteRack.getRackId())) {
            errorList.add("Rack not defined");
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
    public ValidateObject findById(PopSiteRack popSiteRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (popSiteRack == null || !this.popSiteRackService.existsById(popSiteRack.getRackId())) {
            errorList.add("Rack not defined");
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
