package ir.parto.crm.modules.ipam.controller.validate;

import ir.parto.crm.modules.ipam.model.entity.IpGroup;
import ir.parto.crm.modules.ipam.model.service.IpGroupService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class IpGroupValidate implements ValidateInterface<IpGroup> {
    private IpGroupService ipGroupService;

    @Autowired
    public IpGroupValidate(IpGroupService ipGroupService) {
        this.ipGroupService = ipGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(IpGroup ipGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroup == null) {
            errorList.add("IpGroup object is nul");
        } else {
            if (ipGroup.getOwnerName() == null || ipGroup.getOwnerName().isEmpty()) {
                errorList.add("OwnerName is required");
            }

            if (ipGroup.getOwner() == null || ipGroup.getOwner() == 0) {
                errorList.add("Owner is required");
            }

            if (ipGroup.getStartIpAddress() == null || ipGroup.getStartIpAddress().isEmpty()) {
                errorList.add("StartIpAddress is required");
            }

            if (ipGroup.getEndIpAddress() == null || ipGroup.getEndIpAddress().isEmpty()) {
                errorList.add("EndIpAddress is required");
            }

            if (ipGroup.getIpNetmask() == null || ipGroup.getIpNetmask() == 0) {
                errorList.add("IpNetmask is required");
            }

            if (ipGroup.getIpVersion() == null || ipGroup.getIpVersion() == 0) {
                errorList.add("IpVersion is required");
            }

            if (ipGroup.getStatus() == null || ipGroup.getStatus() == 0) {
                errorList.add("Status is required");
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
    public ValidateObject validateUpdateItem(IpGroup ipGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroup == null) {
            errorList.add("IpGroup object is nul");
        } else {
            if (!this.ipGroupService.existsById(ipGroup.getIpGroupId())) {
                errorList.add("IpGroup Id not defined");
            }

            if (ipGroup.getOwnerName() == null || ipGroup.getOwnerName().isEmpty()) {
                errorList.add("OwnerName is required");
            }

            if (ipGroup.getOwner() == null || ipGroup.getOwner() == 0) {
                errorList.add("Owner is required");
            }

            if (ipGroup.getStartIpAddress() == null || ipGroup.getStartIpAddress().isEmpty()) {
                errorList.add("StartIpAddress is required");
            }

            if (ipGroup.getEndIpAddress() == null || ipGroup.getEndIpAddress().isEmpty()) {
                errorList.add("EndIpAddress is required");
            }

            if (ipGroup.getIpNetmask() == null || ipGroup.getIpNetmask() == 0) {
                errorList.add("IpNetmask is required");
            }

            if (ipGroup.getIpVersion() == null || ipGroup.getIpVersion() == 0) {
                errorList.add("IpVersion is required");
            }

            if (ipGroup.getStatus() == null || ipGroup.getStatus() == 0) {
                errorList.add("Status is required");
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
    public ValidateObject deleteItem(IpGroup ipGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroup == null) {
            errorList.add("IpGroup object is nul");
        } else {
            if (!this.ipGroupService.existsById(ipGroup.getIpGroupId())) {
                errorList.add("IpGroup Id not defined");
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
    public ValidateObject findOne(IpGroup ipGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroup == null) {
            errorList.add("IpGroup object is nul");
        } else {
            if (!this.ipGroupService.existsById(ipGroup.getIpGroupId())) {
                errorList.add("IpGroup Id not defined");
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
    public ValidateObject findById(IpGroup ipGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroup == null) {
            errorList.add("IpGroup object is nul");
        } else {
            if (!this.ipGroupService.existsById(ipGroup.getIpGroupId())) {
                errorList.add("IpGroup Id not defined");
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
