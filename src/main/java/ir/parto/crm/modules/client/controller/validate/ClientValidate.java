package ir.parto.crm.modules.client.controller.validate;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
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
        if (client == null) {
            errorList.add("Client information is required");
        } else {

            if (client.getFirstName() == null || client.getFirstName().trim().isEmpty()) {
                errorList.add("First Name is required");
            }
            if (client.getLastName() == null || client.getLastName().trim().isEmpty()) {
                errorList.add("Last Name is required");
            }
            if (client.getBirthDate() == null) {
                errorList.add("Birth Date is required");
            } else if (client.getBirthDate().isAfter(LocalDate.now())) {
                errorList.add("Birth Date is not correct");
            }

            if (client.getMobileNumber() == null || client.getMobileNumber().trim().isEmpty()) {
                errorList.add("Mobile Number is required");
            }
            if (client.getEmailAddress() == null || client.getEmailAddress().trim().isEmpty()) {
                errorList.add("Email is required");
            } else if (client.getEmailAddress() != null) {
                Client exist = this.clientService.findByEmail(client.getEmailAddress());
                if (exist != null) {
                    errorList.add("Email must be unique");
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
    public ValidateObject validateUpdateItem(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (client == null || client.getClientId() == null || client.getClientId() == 0) {
            errorList.add("Client is required");
        }else if(!this.clientService.existsById(client.getClientId())){
            errorList.add("Client is not defined");
        } else {
            if (client.getFirstName() != null && client.getFirstName().isEmpty()) {
                errorList.add("First Name is required");
            }
            if (client.getLastName() != null && client.getLastName().isEmpty()) {
                errorList.add("Last Name is required");
            }
            if (client.getBirthDate() != null && client.getBirthDate().toString().isEmpty()) {
                errorList.add("Birth Date is required");
            }
            if (client.getMobileNumber() != null && client.getMobileNumber().isEmpty()) {
                errorList.add("Mobile Number is required");
            }
            if (client.getEmailAddress() != null && client.getEmailAddress().isEmpty()) {
                errorList.add("Email is required");
            } else {
                Client exist = this.clientService.findByEmail(client.getEmailAddress());
                if (exist != null && !exist.getClientId().equals(client.getClientId())) {
                    errorList.add("Email must be unique");
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
    public ValidateObject deleteItem(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (!this.clientService.existsById(client.getClientId())) {
            errorList.add("Client Id not defined");
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
    public ValidateObject findOne(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (client == null || client.getClientId() == null
        || client.getClientId() != null || !this.clientService.existsByIdNotDeleted(client.getClientId())) {
            errorList.add("Client Id not defined");
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
    public ValidateObject findById(Client client) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (client == null || client.getClientId() == null || !this.clientService.existsById(client.getClientId())) {
            errorList.add("Client Id not defined");
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
