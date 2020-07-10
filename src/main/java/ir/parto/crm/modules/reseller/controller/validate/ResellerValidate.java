package ir.parto.crm.modules.reseller.controller.validate;

import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.modules.reseller.model.service.ResellerService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ResellerValidate implements ValidateInterface<Reseller> {
    private ResellerService resellerService;
    private ClientService clientService;

    @Autowired
    public ResellerValidate(ResellerService resellerService, ClientService clientService) {
        this.resellerService = resellerService;
        this.clientService = clientService;
    }

    @Override
    public ValidateObject validateAddNewItem(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (reseller.getClient() == null) {
                errorList.add("Client object is nul");
            } else {
                if (!this.resellerService.existsById(reseller.getClient().getClientId())) {
                    errorList.add("Client Id not defined");
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
    public ValidateObject validateUpdateItem(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (reseller.getClient() == null) {
                errorList.add("Client object is nul");
            } else {
                if (!this.resellerService.existsById(reseller.getResellerId())) {
                    errorList.add("Reseller Id not defined");
                }

                if (!this.resellerService.existsById(reseller.getClient().getClientId())) {
                    errorList.add("Client Id not defined");
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
    public ValidateObject deleteItem(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (!this.resellerService.existsById(reseller.getResellerId())) {
                errorList.add("Reseller Id not defined");
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
    public ValidateObject findOne(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (!this.resellerService.existsById(reseller.getResellerId())) {
                errorList.add("Reseller Id not defined");
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
    public ValidateObject findById(Reseller reseller) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (reseller == null) {
            errorList.add("Reseller object is nul");
        } else {
            if (!this.resellerService.existsById(reseller.getResellerId())) {
                errorList.add("Reseller Id not defined");
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
