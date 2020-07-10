package ir.parto.crm.modules.ipam.controller.validate;

import ir.parto.crm.modules.ipam.model.entity.IpAddress;
import ir.parto.crm.modules.ipam.model.service.IpAddressService;
import ir.parto.crm.modules.ipam.model.service.IpGroupService;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class IpAddressValidate implements ValidateInterface<IpAddress> {
    private IpAddressService ipAddressService;
    private IpGroupService ipGroupService;
    private ServiceService serviceService;

    @Autowired
    public IpAddressValidate(IpAddressService ipAddressService, IpGroupService ipGroupService, ServiceService serviceService) {
        this.ipAddressService = ipAddressService;
        this.ipGroupService = ipGroupService;
        this.serviceService = serviceService;
    }

    @Override
    public ValidateObject validateAddNewItem(IpAddress ipAddress) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipAddress == null) {
            errorList.add("IpAddress object is nul");
        } else {
            if (ipAddress.getIpGroup() == null) {
                errorList.add("IpGroup object is nul");
            } else {
                if (!this.ipGroupService.existsById(ipAddress.getIpGroup().getIpGroupId())) {
                    errorList.add("IpGroup Id not defined");
                }

                if (ipAddress.getService() != null && !this.serviceService.existsById(ipAddress.getService().getServiceId())) {
                    errorList.add("Service Id not defined");
                }

                if (ipAddress.getIpAddress() == null || ipAddress.getIpAddress().isEmpty()) {
                    errorList.add("IpAddress is required");
                }

                if (ipAddress.getStartIpAddress() == null || ipAddress.getStartIpAddress().isEmpty()) {
                    errorList.add("StartIpAddress is required");
                }

                if (ipAddress.getEndIpAddress() == null || ipAddress.getEndIpAddress().isEmpty()) {
                    errorList.add("EndIpAddress is required");
                }

                if (ipAddress.getIpNetmask() == null || ipAddress.getIpNetmask() == 0) {
                    errorList.add("IpNetmask is required");
                }

                if (ipAddress.getIpVersion() == null || ipAddress.getIpVersion() == 0) {
                    errorList.add("IpVersion is required");
                }

                if (ipAddress.getStatus() == null || ipAddress.getStatus() == 0) {
                    errorList.add("Status is required");
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
    public ValidateObject validateUpdateItem(IpAddress ipAddress) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipAddress == null) {
            errorList.add("IpAddress object is nul");
        } else {
            if (ipAddress.getIpGroup() == null) {
                errorList.add("IpGroup object is nul");
            } else {
                if (!this.ipAddressService.existsById(ipAddress.getIpAddressId())) {
                    errorList.add("IpAddress Id not defined");
                }

                if (!this.ipGroupService.existsById(ipAddress.getIpGroup().getIpGroupId())) {
                    errorList.add("IpGroup Id not defined");
                }

                if (ipAddress.getService() != null && !this.serviceService.existsById(ipAddress.getService().getServiceId())) {
                    errorList.add("Service Id not defined");
                }

                if (ipAddress.getIpAddress() == null || ipAddress.getIpAddress().isEmpty()) {
                    errorList.add("IpAddress is required");
                }

                if (ipAddress.getStartIpAddress() == null || ipAddress.getStartIpAddress().isEmpty()) {
                    errorList.add("StartIpAddress is required");
                }

                if (ipAddress.getEndIpAddress() == null || ipAddress.getEndIpAddress().isEmpty()) {
                    errorList.add("EndIpAddress is required");
                }

                if (ipAddress.getIpNetmask() == null || ipAddress.getIpNetmask() == 0) {
                    errorList.add("IpNetmask is required");
                }

                if (ipAddress.getIpVersion() == null || ipAddress.getIpVersion() == 0) {
                    errorList.add("IpVersion is required");
                }

                if (ipAddress.getStatus() == null || ipAddress.getStatus() == 0) {
                    errorList.add("Status is required");
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
    public ValidateObject deleteItem(IpAddress ipAddress) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipAddress == null) {
            errorList.add("IpAddress object is nul");
        } else {
            if (!this.ipAddressService.existsById(ipAddress.getIpAddressId())) {
                errorList.add("IpAddress Id not defined");
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
    public ValidateObject findOne(IpAddress ipAddress) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipAddress == null) {
            errorList.add("IpAddress object is nul");
        } else {
            if (!this.ipAddressService.existsById(ipAddress.getIpAddressId())) {
                errorList.add("IpAddress Id not defined");
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
    public ValidateObject findById(IpAddress ipAddress) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipAddress == null) {
            errorList.add("IpAddress object is nul");
        } else {
            if (!this.ipAddressService.existsById(ipAddress.getIpAddressId())) {
                errorList.add("IpAddress Id not defined");
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
