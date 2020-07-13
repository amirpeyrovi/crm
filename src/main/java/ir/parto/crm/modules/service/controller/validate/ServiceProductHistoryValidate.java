package ir.parto.crm.modules.service.controller.validate;

import ir.parto.crm.modules.service.model.entity.ServiceProductHistory;
import ir.parto.crm.modules.service.model.service.ServiceProductHistoryService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ServiceProductHistoryValidate implements ValidateInterface<ServiceProductHistory> {
    private ServiceProductHistoryService serviceProductHistoryService;

    @Autowired
    public ServiceProductHistoryValidate(ServiceProductHistoryService serviceProductHistoryService) {
        this.serviceProductHistoryService = serviceProductHistoryService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServiceProductHistory serviceProductHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductHistory == null) {
            errorList.add("object is nul");
        } else {
            if (serviceProductHistory.getService() == null || serviceProductHistory.getService().getServiceId() == null || serviceProductHistory.getService().getServiceId() == 0) {
                errorList.add("Service is required");
            }

            if (serviceProductHistory.getProduct() == null || serviceProductHistory.getProduct().getProductId() == 0 || serviceProductHistory.getProduct().getProductId() == null) {
                errorList.add("Product is required");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(ServiceProductHistory serviceProductHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductHistory == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serviceProductHistoryService.existsById(serviceProductHistory.getServiceProductHistoryId())) {
                errorList.add("Service Product History not defined");
            }

            if (serviceProductHistory.getService() == null || serviceProductHistory.getService().getServiceId() == null || serviceProductHistory.getService().getServiceId() == 0) {
                errorList.add("Service is required");
            }

            if (serviceProductHistory.getProduct() == null || serviceProductHistory.getProduct().getProductId() == 0 || serviceProductHistory.getProduct().getProductId() == null) {
                errorList.add("Product is required");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(ServiceProductHistory serviceProductHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductHistory == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serviceProductHistoryService.existsById(serviceProductHistory.getServiceProductHistoryId())) {
                errorList.add("Service Product History not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findOne(ServiceProductHistory serviceProductHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductHistory == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serviceProductHistoryService.existsById(serviceProductHistory.getServiceProductHistoryId())) {
                errorList.add("Service Product History not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findById(ServiceProductHistory serviceProductHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductHistory == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serviceProductHistoryService.existsById(serviceProductHistory.getServiceProductHistoryId())) {
                errorList.add("Service Product History not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }
}
