package ir.parto.crm.modules.server.controller.validate;

import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ServerGroupValidate implements ValidateInterface<ServerGroup> {

    private ServerGroupService serverGroupService;
    @Autowired
    public ServerGroupValidate(ServerGroupService serverGroupService) {
        this.serverGroupService = serverGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverGroup == null || serverGroup.getTitle() == null ){
            errorList.add("Title is required");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }
        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverGroup != null && (serverGroup.getTitle() != null && serverGroup.getTitle().isEmpty())){
            errorList.add("Title is required");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }
        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverGroup == null || this.serverGroupService.existsById(serverGroup.getServerGroupId())){
            errorList.add("Server Group Id not defined");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }
        return validateObject;
    }

    @Override
    public ValidateObject findOne(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverGroup == null || !this.serverGroupService.existsById(serverGroup.getServerGroupId())){
            errorList.add("Server Group Id not defined");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }
        return validateObject;
    }

    @Override
    public ValidateObject findById(ServerGroup serverGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverGroup == null || this.serverGroupService.existsById(serverGroup.getServerGroupId())){
            errorList.add("Server Group Id not defined");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
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
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }
        return validateObject;
    }
}
