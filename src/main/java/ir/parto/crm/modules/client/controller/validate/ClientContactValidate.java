package ir.parto.crm.modules.client.controller.validate;

import ir.parto.crm.modules.client.model.entity.ClientContact;
import ir.parto.crm.modules.client.model.service.ClientContactService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ClientContactValidate implements ValidateInterface<ClientContact> {
    private ClientContactService clientContactService;

    @Autowired
    public ClientContactValidate(ClientContactService clientContactService) {
        this.clientContactService = clientContactService;
    }

    @Override
    public ValidateObject validateAddNewItem(ClientContact clientContact) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientContact != null && (clientContact.getClient() == null || clientContact.getClient().getClientId() == 0)){
            errorList.add("Client Id is required");
        }
        if(clientContact != null && (clientContact.getFirstName() == null || clientContact.getFirstName().isEmpty())){
            errorList.add("First Name is required");
        }
        if(clientContact != null && (clientContact.getLastName() == null || clientContact.getLastName().isEmpty())){
            errorList.add("Last Name is required");
        }
        if(clientContact != null && (clientContact.getMobileNumber() == null ||clientContact.getMobileNumber().isEmpty()))
        {
            errorList.add("Mobile Number is required");
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
    public ValidateObject validateUpdateItem(ClientContact clientContact) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientContact != null && (clientContact.getClient() != null && clientContact.getClient().getClientId() == 0)){
            errorList.add("Client Id is required");
        }
        if(clientContact != null && (clientContact.getFirstName() != null && clientContact.getFirstName().isEmpty())){
            errorList.add("First Name is required");
        }
        if(clientContact != null && (clientContact.getLastName() != null && clientContact.getLastName().isEmpty())){
            errorList.add("Last Name is required");
        }
        if(clientContact != null && (clientContact.getMobileNumber() != null && clientContact.getMobileNumber().isEmpty() )){
            errorList.add("Mobile Number is required");
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
    public ValidateObject deleteItem(ClientContact clientContact) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(!this.clientContactService.existsById(clientContact.getClientContactId())){
            errorList.add("Contact Id not defined");
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
    public ValidateObject findOne(ClientContact clientContact) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientContact == null || !this.clientContactService.existsById(clientContact.getClientContactId())){
            errorList.add("Contact Id not defined");
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
    public ValidateObject findById(ClientContact clientContact) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(clientContact == null || this.clientContactService.existsById(clientContact.getClientContactId())){
            errorList.add("Contact Id not defined");
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
