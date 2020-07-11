package ir.parto.crm.modules.server.controller.validate;

import ir.parto.crm.modules.server.model.entity.ServerParameter;
import ir.parto.crm.modules.server.model.service.ServerParameterService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
@Component
public class ServerParameterValidate implements ValidateInterface<ServerParameter> {
    private ServerParameterService serverParameterService;

    @Autowired
    public ServerParameterValidate(ServerParameterService serverParameterService) {
        this.serverParameterService = serverParameterService;
    }

    @Override
    public ValidateObject validateAddNewItem(ServerParameter serverParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverParameter == null || serverParameter.getServerGroup() == null || serverParameter.getServerGroup().getServerGroupId() == 0 ){
            errorList.add("Server Group is required");
        }
        if(serverParameter == null || serverParameter.getTitle() == null || serverParameter.getTitle().isEmpty() ){
            errorList.add("Title is required");
        }
        if(serverParameter == null || serverParameter.getType() == null || serverParameter.getType().isEmpty() ){
            errorList.add("Type is required");
        }
        if(serverParameter == null || serverParameter.getOptions() == null || serverParameter.getOptions().isEmpty() ){
            errorList.add("Option is required");
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
    public ValidateObject validateUpdateItem(ServerParameter serverParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverParameter != null && serverParameter.getServerGroup() != null && serverParameter.getServerGroup().getServerGroupId() == 0 ){
            errorList.add("Server Group is required");
        }
        if(serverParameter != null && serverParameter.getTitle() == null && serverParameter.getTitle().isEmpty() ){
            errorList.add("Title is required");
        }
        if(serverParameter != null && serverParameter.getType() == null && serverParameter.getType().isEmpty() ){
            errorList.add("Type is required");
        }
        if(serverParameter != null && serverParameter.getOptions() == null && serverParameter.getOptions().isEmpty() ){
            errorList.add("Option is required");
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
    public ValidateObject deleteItem(ServerParameter serverParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverParameter == null || !this.serverParameterService.existsById(serverParameter.getServerParameterId())){
            errorList.add("Server Parameter Id not defined");
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
    public ValidateObject findOne(ServerParameter serverParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverParameter == null || !this.serverParameterService.existsById(serverParameter.getServerParameterId())){
            errorList.add("Server Parameter Id not defined");
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
    public ValidateObject findById(ServerParameter serverParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(serverParameter == null || !this.serverParameterService.existsById(serverParameter.getServerParameterId())){
            errorList.add("Server Parameter Id not defined");
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
