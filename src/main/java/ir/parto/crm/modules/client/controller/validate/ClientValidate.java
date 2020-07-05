package ir.parto.crm.modules.client.controller.validate;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
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

        if(client == null || client.getFirstName() == null || client.getFirstName().isEmpty() ){
            errorList.add("First Name is required");
        }
        if(client == null || client.getLastName() == null || client.getLastName().isEmpty() ){
            errorList.add("Last Name is required");
        }
        if(client == null || client.getBirthDate() == null){
            errorList.add("Birth Date is required");
        }
        if(client == null || client.getMobileNumber() == null || client.getMobileNumber().isEmpty() ){
            errorList.add("Mobile Number is required");
        }
        if(client == null  || client.getEmailAddress() == null || client.getEmailAddress().isEmpty()){
            errorList.add("Email is required");
        }
        if(client != null && client.getEmailAddress() != null){
            Client exist = this.clientService.findByEmail(client.getEmailAddress());
            if(exist != null){
                errorList.add("Email must be unique");
            }
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

        if(client != null && client.getFirstName() != null && client.getFirstName().isEmpty() ){
            errorList.add("First Name is required");
        }
        if(client != null && client.getLastName() != null && client.getLastName().isEmpty() ){
            errorList.add("Last Name is required");
        }
        if(client != null && client.getBirthDate() != null && client.getBirthDate().equals(null)){
            errorList.add("Birth Date is required");
        }
        if(client != null && client.getMobileNumber() != null && client.getMobileNumber().isEmpty() ){
            errorList.add("Mobile Number is required");
        }
        if(client != null && client.getEmailAddress() != null && client.getEmailAddress().isEmpty() ){
            errorList.add("Email is required");
        }
        if(client != null){
            Client exist = this.clientService.findByEmail(client.getEmailAddress());
            if(exist != null && exist.getClientId() != client.getClientId()){
                errorList.add("Email must be unique");
            }
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
    public ValidateObject findOne(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(client == null || !this.clientService.existsByIdNotDeleted(client.getClientId())){
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
    public ValidateObject findById(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(client == null || this.clientService.existsById(client.getClientId()) == false){
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
