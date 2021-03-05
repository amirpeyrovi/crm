package ir.parto.crm.modules.contract.controller.validate;

import ir.parto.crm.modules.contract.model.entity.ContractTemplate;
import ir.parto.crm.modules.contract.model.service.ContractTemplateService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractTemplateValidate implements ValidateInterface<ContractTemplate> {
    private ContractTemplateService contractTemplateService;

    @Autowired
    public ContractTemplateValidate(ContractTemplateService contractTemplateService) {
        this.contractTemplateService = contractTemplateService;
    }

    @Override
    public ValidateObject validateAddNewItem(ContractTemplate contractTemplate) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractTemplate == null) {
            errorList.add("object is null");
        } else {
            if (contractTemplate.getTitle() == null || contractTemplate.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            }
            if(contractTemplate.getContract() == null || contractTemplate.getContract().trim().isEmpty()) {
                errorList.add("Contract Text is required");
            }
            if(contractTemplate.getContractGroup() == null || contractTemplate.getContractGroup().getContractGroupId() == 0) {
                errorList.add("Group is required");
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
    public ValidateObject validateUpdateItem(ContractTemplate contractTemplate) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractTemplate == null) {
            errorList.add("object is null");
        } else {
            if (contractTemplate.getTitle() != null && contractTemplate.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            }
            if(contractTemplate.getContract() != null && contractTemplate.getContract().trim().isEmpty()) {
                errorList.add("Contract Text is required");
            }
            if(contractTemplate.getContractGroup() == null ||
                    contractTemplate.getContractGroup().getContractGroupId() == 0) {
                errorList.add("Group is required");
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
    public ValidateObject deleteItem(ContractTemplate contractTemplate) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractTemplate == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractTemplateService.existsById(contractTemplate.getContractTemplateId())) {
                errorList.add("ContractTemplate not found");
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
    public ValidateObject findOne(ContractTemplate contractTemplate) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractTemplate == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractTemplateService.existsById(contractTemplate.getContractTemplateId())) {
                errorList.add("ContractTemplate not found");
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
    public ValidateObject findById(ContractTemplate contractTemplate) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractTemplate == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractTemplateService.existsById(contractTemplate.getContractTemplateId())) {
                errorList.add("ContractTemplate not found");
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
