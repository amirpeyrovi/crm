package ir.parto.crm.modules.contract.controller.validate;

import ir.parto.crm.modules.contract.model.entity.ContractServiceLink;
import ir.parto.crm.modules.contract.model.service.ContractServiceLinkService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceLinkValidate implements ValidateInterface<ContractServiceLink> {
    private ContractServiceLinkService contractServiceLinkService;

    @Autowired
    public ContractServiceLinkValidate(ContractServiceLinkService contractProductGroupLinkService) {
        this.contractServiceLinkService = contractServiceLinkService;
    }

    @Override
    public ValidateObject validateAddNewItem(ContractServiceLink contractServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractServiceLink == null) {
            errorList.add("object is null");
        } else {
            if (contractServiceLink.getContractTemplate() == null || contractServiceLink.getContractTemplate()
                    .getContractTemplateId() == 0 ) {
                errorList.add("Template is required");
            }
            if(contractServiceLink.getService() == null || contractServiceLink.getService().getServiceId() == 0) {
                errorList.add("Service is required");
            }
            if(contractServiceLink.getServiceAddon() == null || contractServiceLink.getServiceAddon().getServiceId() == 0) {
                errorList.add("Addon is required");
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
    public ValidateObject validateUpdateItem(ContractServiceLink contractServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractServiceLink == null) {
            errorList.add("object is null");
        } else {
            if (contractServiceLink.getContractTemplate() == null || contractServiceLink.getContractTemplate()
                    .getContractTemplateId() == 0 ) {
                errorList.add("Template is required");
            }
            if(contractServiceLink.getService() == null || contractServiceLink.getService().getServiceId() == 0) {
                errorList.add("Service is required");
            }
            if(contractServiceLink.getServiceAddon() == null || contractServiceLink.getServiceAddon().getServiceId() == 0) {
                errorList.add("Addon is required");
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
    public ValidateObject deleteItem(ContractServiceLink contractServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractServiceLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractServiceLinkService.existsById(contractServiceLink.getContractServiceLinkId())) {
                errorList.add("ContractServiceLink not found");
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
    public ValidateObject findOne(ContractServiceLink contractServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractServiceLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractServiceLinkService.existsById(contractServiceLink.getContractServiceLinkId())) {
                errorList.add("ContractServiceLink not found");
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
    public ValidateObject findById(ContractServiceLink contractServiceLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractServiceLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractServiceLinkService.existsById(contractServiceLink.getContractServiceLinkId())) {
                errorList.add("ContractServiceLink not found");
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
