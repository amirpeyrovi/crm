package ir.parto.crm.modules.service.controller.validate;

import ir.parto.crm.modules.service.model.entity.ServiceAddon;
import ir.parto.crm.modules.service.model.service.ServiceAddonService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ServiceAddonValidate implements ValidateInterface<ServiceAddon> {
    private ServiceAddonService serviceAddonService;

    @Autowired
    public ServiceAddonValidate(ServiceAddonService serviceAddonService) {
        this.serviceAddonService = serviceAddonService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServiceAddon serviceAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceAddon == null) {
            errorList.add("object is nul");
        } else {
            if (serviceAddon.getServiceId() == null || serviceAddon.getServiceId() == 0) {
                errorList.add("Service is required");
            }

            if (serviceAddon.getProductAddon() == null || serviceAddon.getProductAddon().getProductAddonId() == 0) {
                errorList.add("Product Addon is required");
            }

            if (serviceAddon.getProductCycle() == null || serviceAddon.getProductCycle().getProductCycleId() == 0) {
                errorList.add("Product Cycle is required");
            }

            if (serviceAddon.getPrice() == null || serviceAddon.getPrice() == 0) {
                errorList.add("Price is required");
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
    public ValidateObject validateUpdateItem(ServiceAddon serviceAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceAddon == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serviceAddonService.existsById(serviceAddon.getServiceId())) {
                errorList.add("Service not defined");
            }
            if (serviceAddon.getServiceId() == null || serviceAddon.getServiceId() == 0) {
                errorList.add("Service is required");
            }

            if (serviceAddon.getProductAddon() == null || serviceAddon.getProductAddon().getProductAddonId() == 0) {
                errorList.add("Product Addon is required");
            }

            if (serviceAddon.getProductCycle() == null || serviceAddon.getProductCycle().getProductCycleId() == 0) {
                errorList.add("Product Cycle is required");
            }

            if (serviceAddon.getPrice() == null || serviceAddon.getPrice() == 0) {
                errorList.add("Price is required");
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
    public ValidateObject deleteItem(ServiceAddon serviceAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceAddon == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serviceAddonService.existsById(serviceAddon.getServiceId())) {
                errorList.add("Service Addon Id not defined");
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
    public ValidateObject findOne(ServiceAddon serviceAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceAddon == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serviceAddonService.existsById(serviceAddon.getServiceId())) {
                errorList.add("Service Addon Id not defined");
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
    public ValidateObject findById(ServiceAddon serviceAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serviceAddon == null) {
            errorList.add("object is nul");
        } else {
            if (!this.serviceAddonService.existsById(serviceAddon.getServiceId())) {
                errorList.add("Service Addon Id not defined");
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
