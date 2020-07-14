package ir.parto.crm.modules.server.controller.validate;

import ir.parto.crm.modules.server.model.entity.Server;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.modules.server.model.service.ServerService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
@Component
public class ServerValidate implements ValidateInterface<Server> {
    private ServerService serverService;
    private ServerGroupService serverGroupService;

    @Autowired
    public ServerValidate(ServerService serverService, ServerGroupService serverGroupService) {
        this.serverService = serverService;
        this.serverGroupService = serverGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(Server server) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (server == null) {
            errorList.add("Server object is nul");
        } else {
            if (server.getServerGroup() == null) {
                errorList.add("ServerGroup object is nul");
            } else {
                if (!this.serverGroupService.existsById(server.getServerGroup().getServerGroupId())) {
                    errorList.add("ServerGroup Id not defined");
                }

                if(server.getTitle() == null || server.getTitle().isEmpty()){
                    errorList.add("Title is required");
                }

                if(server.getAddress() == null || server.getAddress().isEmpty()){
                    errorList.add("Address is required");
                }

                if(server.getUsername() == null || server.getUsername().isEmpty()){
                    errorList.add("Username is required");
                }

                if(server.getPassword() == null || server.getPassword().isEmpty()){
                    errorList.add("Password is required");
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
    public ValidateObject validateUpdateItem(Server server) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (server == null) {
            errorList.add("Server object is nul");
        } else {
            if (server.getServerGroup() == null) {
                errorList.add("ServerGroup object is nul");
            } else {
                if (!this.serverService.existsById(server.getServerId())) {
                    errorList.add("Server Id not defined");
                }

                if (!this.serverGroupService.existsById(server.getServerGroup().getServerGroupId())) {
                    errorList.add("ServerGroup Id not defined");
                }

                if(server.getTitle() == null || server.getTitle().isEmpty()){
                    errorList.add("Title is required");
                }

                if(server.getAddress() == null || server.getAddress().isEmpty()){
                    errorList.add("Address is required");
                }

                if(server.getUsername() == null || server.getUsername().isEmpty()){
                    errorList.add("Username is required");
                }

                if(server.getPassword() == null || server.getPassword().isEmpty()){
                    errorList.add("Password is required");
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
    public ValidateObject deleteItem(Server server) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (server == null) {
            errorList.add("Server object is nul");
        } else {
            if (!this.serverService.existsById(server.getServerId())) {
                errorList.add("Server Id not defined");
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
    public ValidateObject findOne(Server server) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (server == null) {
            errorList.add("Server object is nul");
        } else {
            if (!this.serverService.existsById(server.getServerId())) {
                errorList.add("Server Id not defined");
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
    public ValidateObject findById(Server server) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (server == null) {
            errorList.add("Server object is nul");
        } else {
            if (!this.serverService.existsById(server.getServerId())) {
                errorList.add("Server Id not defined");
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
