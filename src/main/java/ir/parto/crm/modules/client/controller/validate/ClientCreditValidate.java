package ir.parto.crm.modules.client.controller.validate;

import ir.parto.crm.modules.client.model.entity.ClientCredit;
import ir.parto.crm.modules.client.model.service.ClientCreditService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ClientCreditValidate implements ValidateInterface<ClientCredit> {
    private ClientCreditService clientCreditService;

    @Autowired
    public ClientCreditValidate(ClientCreditService clientCreditService) {
        this.clientCreditService = clientCreditService;
    }

    @Override
    public ValidateObject validateAddNewItem(ClientCredit clientCredit) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientCredit == null || clientCredit.getClient() == null || clientCredit.getClient().getClientId() == 0){
            errorList.add("Client Id is required");
        }
        //amount check
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
    public ValidateObject validateUpdateItem(ClientCredit clientCredit) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientCredit == null || clientCredit.getClient() == null || clientCredit.getClient().getClientId() == 0){
            errorList.add("Client Id is required");
        }
        //amount check
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
    public ValidateObject deleteItem(ClientCredit clientCredit) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientCreditService.existsById(clientCredit.getClientCreditId())){
            errorList.add("Client Credit Id not defined");
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
    public ValidateObject findOne(ClientCredit clientCredit) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientCreditService.existsById(clientCredit.getClientCreditId())){
            errorList.add("Client Credit Id not defined");
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
    public ValidateObject findById(ClientCredit clientCredit) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientCreditService.existsById(clientCredit.getClientCreditId())){
            errorList.add("Client Credit Id not defined");
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
