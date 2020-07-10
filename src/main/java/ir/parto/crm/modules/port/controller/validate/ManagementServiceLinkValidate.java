package ir.parto.crm.modules.port.controller.validate;

import ir.parto.crm.modules.port.model.entity.ManagementServiceLink;
import ir.parto.crm.modules.port.model.service.ManagementService;
import ir.parto.crm.modules.port.model.service.ManagementServiceLinkService;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ManagementServiceLinkValidate implements ValidateInterface<ManagementServiceLink> {
    private ManagementServiceLinkService managementServiceLinkService;
    private ManagementService managementService;
    private ServiceService serviceService;

    @Autowired
    public ManagementServiceLinkValidate(ManagementServiceLinkService managementServiceLinkService, ManagementService managementService, ServiceService serviceService) {
        this.managementServiceLinkService = managementServiceLinkService;
        this.managementService = managementService;
        this.serviceService = serviceService;
    }

    @Override
    public ValidateObject validateAddNewItem(ManagementServiceLink managementServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (managementServiceLink == null) {
            errorList.add("ManagementServiceLink object is nul");
        } else {
            if (managementServiceLink.getManagement() == null) {
                errorList.add("Management object is nul");
            } else {
                if (managementServiceLink.getService() == null) {
                    errorList.add("Service object is nul");
                } else {
                    if (!this.managementService.existsById(managementServiceLink.getManagement().getManagementId())) {
                        errorList.add("Management Id not defined");
                    }

                    if (!this.serviceService.existsById(managementServiceLink.getService().getServiceId())) {
                        errorList.add("Service Id not defined");
                    }
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
    public ValidateObject validateUpdateItem(ManagementServiceLink managementServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (managementServiceLink == null) {
            errorList.add("ManagementServiceLink object is nul");
        } else {
            if (managementServiceLink.getManagement() == null) {
                errorList.add("Management object is nul");
            } else {
                if (managementServiceLink.getService() == null) {
                    errorList.add("Service object is nul");
                } else {
                    if (!this.managementServiceLinkService.existsById(managementServiceLink.getManagementServiceId())) {
                        errorList.add("ManagementServiceLink Id not defined");
                    }

                    if (!this.managementService.existsById(managementServiceLink.getManagement().getManagementId())) {
                        errorList.add("Management Id not defined");
                    }

                    if (!this.serviceService.existsById(managementServiceLink.getService().getServiceId())) {
                        errorList.add("Service Id not defined");
                    }
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
    public ValidateObject deleteItem(ManagementServiceLink managementServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (managementServiceLink == null) {
            errorList.add("ManagementServiceLink object is nul");
        } else {
            if (!this.managementServiceLinkService.existsById(managementServiceLink.getManagementServiceId())) {
                errorList.add("ManagementServiceLink Id not defined");
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
    public ValidateObject findOne(ManagementServiceLink managementServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (managementServiceLink == null) {
            errorList.add("ManagementServiceLink object is nul");
        } else {
            if (!this.managementServiceLinkService.existsById(managementServiceLink.getManagementServiceId())) {
                errorList.add("ManagementServiceLink Id not defined");
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
    public ValidateObject findById(ManagementServiceLink managementServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (managementServiceLink == null) {
            errorList.add("ManagementServiceLink object is nul");
        } else {
            if (!this.managementServiceLinkService.existsById(managementServiceLink.getManagementServiceId())) {
                errorList.add("ManagementServiceLink Id not defined");
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
