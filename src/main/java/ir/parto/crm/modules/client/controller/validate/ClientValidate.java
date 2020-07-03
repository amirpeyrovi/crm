package ir.parto.crm.modules.client.controller.validate;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientValidate implements ValidateInterface<Client> {
    private ClientService clientService;

    @Autowired
    public ClientValidate(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ValidateObject validateAddNewItem(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(client == null || client.getFirstName().isEmpty() || client.getFirstName() == null){
            errorList.add("First Name is required");
        }
        if(client == null || client.getLastName().isEmpty() || client.getLastName() == null){
            errorList.add("Last Name is required");
        }
        if(client == null || client.getBirthDate() == null){
            errorList.add("Birth Date is required");
        }
        if(client == null || client.getMobileNumber().isEmpty() || client.getMobileNumber() == null){
            errorList.add("Mobile Number is required");
        }
        if(client == null || client.getEmailAddress().isEmpty() || client.getEmailAddress() == null){
            errorList.add("Email is required");
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
    public ValidateObject validateUpdateItem(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(client == null || client.getFirstName().isEmpty() || client.getFirstName() == null){
            errorList.add("First Name is required");
        }
        if(client == null || client.getLastName().isEmpty() || client.getLastName() == null){
            errorList.add("Last Name is required");
        }
        if(client == null || client.getBirthDate() == null){
            errorList.add("Birth Date is required");
        }
        if(client == null || client.getMobileNumber().isEmpty() || client.getMobileNumber() == null){
            errorList.add("Mobile Number is required");
        }
        if(client == null || client.getEmailAddress().isEmpty() || client.getEmailAddress() == null){
            errorList.add("Email is required");
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
    public ValidateObject deleteItem(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientService.existsById(client.getClientId())){
            errorList.add("ClientId not defined");
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
    public ValidateObject findOne(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientService.existsById(client.getClientId())){
            errorList.add("ClientId not defined");
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
    public ValidateObject findById(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientService.existsById(client.getClientId())){
            errorList.add("Client Id not defined");
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
