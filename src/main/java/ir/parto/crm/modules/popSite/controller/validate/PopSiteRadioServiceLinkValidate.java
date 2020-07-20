package ir.parto.crm.modules.popSite.controller.validate;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRadioServiceLink;
import ir.parto.crm.modules.popSite.model.service.PopSiteRadioService;
import ir.parto.crm.modules.popSite.model.service.PopSiteRadioServiceLinkService;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PopSiteRadioServiceLinkValidate implements ValidateInterface<PopSiteRadioServiceLink> {
    private PopSiteRadioServiceLinkService popSiteRadioServiceLinkService;
    private PopSiteRadioService popSiteRadioService;
    private ServiceService serviceService;

    @Autowired
    public PopSiteRadioServiceLinkValidate(PopSiteRadioServiceLinkService popSiteRadioServiceLinkService, PopSiteRadioService popSiteRadioService, ServiceService serviceService) {
        this.popSiteRadioServiceLinkService = popSiteRadioServiceLinkService;
        this.popSiteRadioService = popSiteRadioService;
        this.serviceService = serviceService;
    }

    @Override
    public ValidateObject validateAddNewItem(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (popSiteRadioServiceLink == null) {
            errorList.add("PopSiteRadioServiceLink is null");
        } else {
            if (popSiteRadioServiceLink.getPopSiteRadio() == null || popSiteRadioServiceLink.getPopSiteRadio().getRadioId() == 0) {
                errorList.add("PopSiteRadio not defined");
            } else if (!popSiteRadioService.existsById(popSiteRadioServiceLink.getPopSiteRadio().getRadioId())) {
                errorList.add("PopSiteRadio Not Found!");
            }

            if (popSiteRadioServiceLink.getService() == null ||
                    popSiteRadioServiceLink.getService().getServiceId() == 0) {
                errorList.add("Service not defined");
            } else if (!serviceService.existsById(popSiteRadioServiceLink.getService().getServiceId())) {
                errorList.add("Service Not Found!");
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
    public ValidateObject validateUpdateItem(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(popSiteRadioServiceLink == null){
            errorList.add("PopSiteRadioServiceLink is null");
        }else {
            if (popSiteRadioServiceLink.getPopSiteRadio() != null && popSiteRadioServiceLink.getPopSiteRadio().getRadioId() == 0) {
                errorList.add("PopSiteRadio not defined");
            } else if (popSiteRadioServiceLink.getPopSiteRadio() != null && !popSiteRadioService.existsById(popSiteRadioServiceLink.getPopSiteRadio().getRadioId())) {
                errorList.add("PopSiteRadio Not Found!");
            }

            if (popSiteRadioServiceLink.getService() != null &&
                    popSiteRadioServiceLink.getService().getServiceId() == 0) {
                errorList.add("Service not defined");
            } else if (popSiteRadioServiceLink.getService() != null && !serviceService.existsById(popSiteRadioServiceLink.getService().getServiceId())) {
                errorList.add("Service Not Found!");
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
    public ValidateObject deleteItem(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (popSiteRadioServiceLink == null || !this.popSiteRadioServiceLinkService.existsById(popSiteRadioServiceLink.getRadioServiceLinkId())) {
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
        if (popSiteRadioServiceLink == null || !this.popSiteRadioServiceLinkService.existsById(popSiteRadioServiceLink.getRadioServiceLinkId())) {
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
        if (popSiteRadioServiceLink == null || !this.popSiteRadioServiceLinkService.existsById(popSiteRadioServiceLink.getRadioServiceLinkId())) {
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
