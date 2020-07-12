package ir.parto.crm.modules.service.controller.validate;

import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ValidationAnnotation
public class ServiceValidate implements ValidateInterface<Service> {
    private ServiceService serviceService;

    @Autowired
    public ServiceValidate(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public ValidateObject validateAddNewItem(Service service) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (service == null) {
            errorList.add("Object is null");
        } else {
            if (service.getUsername() != null) {
                errorList.add("Username is required");
            }

            if (service.getPrice() != null) {
                errorList.add("Price is required");
            }

            if (service.getProduct() != null || service.getProduct().getProductId() == null || service.getProduct().getProductId() == 0) {
                errorList.add("Product is required");
            }

            if (service.getClient() != null || service.getClient().getClientId() == null || service.getClient().getClientId() == 0) {
                errorList.add("Client is required");
            }

            if (service.getStatus() != null) {
                errorList.add("Status is required");
            }

            if (service.getTerminationDate() != null) {
                errorList.add("TerminationDate is required");
            } else if (service.getTerminationDate().compareTo(LocalDate.now()) < 0) {
                errorList.add("TerminationDate is not correct");
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
    public ValidateObject validateUpdateItem(Service service) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (service == null || service.getServiceId() == null || service.getServiceId() == 0) {
            errorList.add("Ticket not defined!");
        } else {
            if (service.getTerminationDate() != null) {
                if (service.getTerminationDate().compareTo(LocalDate.now()) < 0) {
                    errorList.add("TerminationDate is not correct");
                }
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
    public ValidateObject deleteItem(Service service) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (service == null || service.getServiceId() == null || service.getServiceId() == 0) {
            errorList.add("Ticket not defined!");
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
    public ValidateObject findOne(Service service) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (service == null || service.getServiceId() == null || service.getServiceId() == 0) {
            errorList.add("Ticket not defined!");
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
    public ValidateObject findById(Service service) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (service == null || service.getServiceId() == null || service.getServiceId() == 0) {
            errorList.add("Ticket not defined!");
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
