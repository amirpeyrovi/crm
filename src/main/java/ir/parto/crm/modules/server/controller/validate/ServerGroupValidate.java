package ir.parto.crm.modules.server.controller.validate;

import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.modules.server.model.service.ServerVendorService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ServerGroupValidate implements ValidateInterface<ServerGroup> {
    private ServerGroupService serverGroupService;
    private ServerVendorService serverVendorService;

    @Autowired
    public ServerGroupValidate(ServerGroupService serverGroupService, ServerVendorService serverVendorService) {
        this.serverGroupService = serverGroupService;
        this.serverVendorService = serverVendorService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverGroup == null) {
            errorList.add("ServerGroup object is null");
        } else {
            if (serverGroup.getServerVendor() == null) {
                errorList.add("ServerVendor object is null");
            } else {
                if (!this.serverVendorService.existsById(serverGroup.getServerVendor().getServerVendorId())) {
                    errorList.add("ServerVendor Id not defined");
                }

                if (serverGroup.getTitle() == null || serverGroup.getTitle().isEmpty() || serverGroup.getTitle().trim().isEmpty()) {
                    errorList.add("Title is required");
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
    public ValidateObject validateUpdateItem(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverGroup == null) {
            errorList.add("ServerGroup object is null");
        } else {
            ServerGroup serverGroupExist = this.serverGroupService.findById(serverGroup.getServerGroupId());
            if (serverGroupExist.getServerVendor() != null && serverGroup.getServerVendor() != null && (serverGroup.getServerVendor().getServerVendorId() == null ||
                    serverGroup.getServerVendor().getServerVendorId() == 0)) {
                errorList.add("ServerVendor object is null");
            } else {
                if (!this.serverGroupService.existsById(serverGroup.getServerGroupId())) {
                    errorList.add("ServerGroup Id not defined");
                }

                if (serverGroup.getServerVendor() != null && !this.serverVendorService.existsById(serverGroup.getServerVendor().getServerVendorId())) {
                    errorList.add("ServerVendor Id not defined");
                }

                if (serverGroup.getTitle() != null && (serverGroup.getTitle().isEmpty() || serverGroup.getTitle().trim().isEmpty())) {
                    errorList.add("Title is required");
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
    public ValidateObject deleteItem(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverGroup == null) {
            errorList.add("ServerGroup object is null");
        } else {
            if (!this.serverGroupService.existsById(serverGroup.getServerGroupId())) {
                errorList.add("ServerGroup Id not defined");
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
    public ValidateObject findOne(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverGroup == null) {
            errorList.add("ServerGroup object is null");
        } else {
            if (!this.serverGroupService.existsById(serverGroup.getServerGroupId())) {
                errorList.add("ServerGroup Id not defined");
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
    public ValidateObject findById(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (serverGroup == null) {
            errorList.add("ServerGroup object is null");
        } else {
            if (!this.serverGroupService.existsById(serverGroup.getServerGroupId())) {
                errorList.add("ServerGroup Id not defined");
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
