package ir.parto.crm.modules.service.controller.validate;

import ir.parto.crm.modules.service.model.entity.ServiceProductParameterValue;
import ir.parto.crm.modules.service.model.service.ServiceProductParameterValueService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ServiceProductParameterValueValidate implements ValidateInterface<ServiceProductParameterValue> {
    private ServiceProductParameterValueService serviceProductParameterValueService;

    @Autowired
    public ServiceProductParameterValueValidate(ServiceProductParameterValueService serviceProductParameterValueService) {
        this.serviceProductParameterValueService = serviceProductParameterValueService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServiceProductParameterValue serviceProductParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductParameterValue == null) {
            errorList.add("object is nul");
        } else {
            if (serviceProductParameterValue.getService() == null || serviceProductParameterValue.getService().getServiceId() == null || serviceProductParameterValue.getService().getServiceId() == 0) {
                errorList.add("Service is required");
            }

            if (serviceProductParameterValue.getProductParameter() == null || serviceProductParameterValue.getProductParameter().getProductParameterId() == 0 || serviceProductParameterValue.getProductParameter().getProductParameterId() == null) {
                errorList.add("Product Parameter is required");
            }
            if (serviceProductParameterValue.getValue() == null) {
                errorList.add("Value is required");
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
    public ValidateObject validateUpdateItem(ServiceProductParameterValue serviceProductParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductParameterValue == null) {
            errorList.add("object is nul");
        } else {
//            if (!this.serviceProductParameterValueService.existsById(serviceProductParameterValue.getServiceProductHistory())) {
            if (!this.serviceProductParameterValueService.existsById(serviceProductParameterValue.getServiceProductParameterValueId())) {
                errorList.add("Service Product Parameter Value not defined");
            }
            if (serviceProductParameterValue.getService() == null || serviceProductParameterValue.getService().getServiceId() == null || serviceProductParameterValue.getService().getServiceId() == 0) {
                errorList.add("Service is required");
            }

            if (serviceProductParameterValue.getProductParameter() == null || serviceProductParameterValue.getProductParameter().getProductParameterId() == 0 || serviceProductParameterValue.getProductParameter().getProductParameterId() == null) {
                errorList.add("Product Parameter is required");
            }

            if (serviceProductParameterValue.getValue() == null) {
                errorList.add("Value is required");
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
    public ValidateObject deleteItem(ServiceProductParameterValue serviceProductParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductParameterValue == null) {
            errorList.add("object is nul");
        } else {
//            if (!this.serviceProductParameterValueService.existsById(serviceProductParameterValue.getServiceProductHistory())) {
            if (!this.serviceProductParameterValueService.existsById(serviceProductParameterValue.getServiceProductParameterValueId())) {
                errorList.add("Service Product Parameter Value not defined");
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
    public ValidateObject findOne(ServiceProductParameterValue serviceProductParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductParameterValue == null) {
            errorList.add("object is nul");
        } else {
//            if (!this.serviceProductParameterValueService.existsById(serviceProductParameterValue.getServiceProductHistory())) {
            if (!this.serviceProductParameterValueService.existsById(serviceProductParameterValue.getServiceProductParameterValueId())) {
                errorList.add("Service Product Parameter Value not defined");
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
    public ValidateObject findById(ServiceProductParameterValue serviceProductParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceProductParameterValue == null) {
            errorList.add("object is nul");
        } else {
//            if (!this.serviceProductParameterValueService.existsById(serviceProductParameterValue.getServiceProductHistory())) {
            if (!this.serviceProductParameterValueService.existsById(serviceProductParameterValue.getServiceProductParameterValueId())) {
                errorList.add("Service Product Parameter Value not defined");
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
