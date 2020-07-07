package ir.parto.crm.modules.client.controller.validate;

import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import ir.parto.crm.modules.client.model.service.ClientExternalCodeService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ClientExternalCodeValidate implements ValidateInterface<ClientExternalCode> {
    private ClientExternalCodeService clientExternalCodeService;

    @Autowired
    public ClientExternalCodeValidate(ClientExternalCodeService clientExternalCodeService) {
        this.clientExternalCodeService = clientExternalCodeService;
    }

    @Override
    public ValidateObject validateAddNewItem(ClientExternalCode clientExternalCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientExternalCode == null || clientExternalCode.getClient() == null || clientExternalCode.getClient().getClientId() == 0){
            errorList.add("Client Id is required");
        }
        if(clientExternalCode == null || clientExternalCode.getTitle() == null ){
            errorList.add("Title is required");
        }
        if(clientExternalCode == null || clientExternalCode.getExternalCode() == null ){
            errorList.add("ExternalCode is required");
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
    public ValidateObject validateUpdateItem(ClientExternalCode clientExternalCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientExternalCode == null || clientExternalCode.getClient() == null || clientExternalCode.getClient().getClientId() != 0){
            errorList.add("Client Id is required");
        }
        if(clientExternalCode == null || clientExternalCode.getTitle() == null ){
            errorList.add("Title is required");
        }
        if(clientExternalCode == null || clientExternalCode.getExternalCode() == null ){
            errorList.add("ExternalCode is required");
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
    public ValidateObject deleteItem(ClientExternalCode clientExternalCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientExternalCodeService.existsById(clientExternalCode.getClientExternalCodeId())){
            errorList.add("Client External Code not defined");
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
    public ValidateObject findOne(ClientExternalCode clientExternalCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientExternalCode == null || !this.clientExternalCodeService.existsById(clientExternalCode.getClientExternalCodeId())){
            errorList.add("Client External Code not defined");
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
    public ValidateObject findById(ClientExternalCode clientExternalCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientExternalCodeService.existsById(clientExternalCode.getClientExternalCodeId())){
            errorList.add("Client External Code not defined");
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
