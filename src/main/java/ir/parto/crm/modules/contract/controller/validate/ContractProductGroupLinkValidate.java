package ir.parto.crm.modules.contract.controller.validate;

import ir.parto.crm.modules.contract.model.entity.ContractProductGroupLink;
import ir.parto.crm.modules.contract.model.service.ContractProductGroupLinkService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractProductGroupLinkValidate implements ValidateInterface<ContractProductGroupLink> {
    private ContractProductGroupLinkService contractProductGroupLinkService;

    @Autowired
    public ContractProductGroupLinkValidate(ContractProductGroupLinkService contractProductGroupLinkService) {
        this.contractProductGroupLinkService = contractProductGroupLinkService;
    }


    @Override
    public ValidateObject validateAddNewItem(ContractProductGroupLink contractProductGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractProductGroupLink == null) {
            errorList.add("object is null");
        } else {
            if (contractProductGroupLink.getContractTemplate() == null || contractProductGroupLink.getContractTemplate()
                    .getContractTemplateId() == 0 ) {
                errorList.add("Template is required");
            }
            if(contractProductGroupLink.getProductGroup() == null || contractProductGroupLink.getProductGroup()
                    .getProductGroupId() == 0 ) {
                errorList.add("Product Group is required");
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
    public ValidateObject validateUpdateItem(ContractProductGroupLink contractProductGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractProductGroupLink == null) {
            errorList.add("object is null");
        } else {
            if(!this.contractProductGroupLinkService.existsById(contractProductGroupLink.getContractProductGroupLinkId())){
                errorList.add("ContractProductGroupLink not defined");
            }else {
                if (contractProductGroupLink.getContractTemplate() == null || (contractProductGroupLink.getContractTemplate().getContractTemplateId() == null
                        || contractProductGroupLink.getContractTemplate()
                        .getContractTemplateId() == 0)) {
                    errorList.add("Template is required");
                }
                if (contractProductGroupLink.getProductGroup() == null || (contractProductGroupLink.getProductGroup()
                        .getProductGroupId() == null || contractProductGroupLink.getProductGroup()
                        .getProductGroupId() == 0)) {
                    errorList.add("Product Group is required");
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
    public ValidateObject deleteItem(ContractProductGroupLink contractProductGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractProductGroupLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractProductGroupLinkService.existsById(contractProductGroupLink.getContractProductGroupLinkId())) {
                errorList.add("ContractProductGroupLink not found");
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
    public ValidateObject findOne(ContractProductGroupLink contractProductGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractProductGroupLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractProductGroupLinkService.existsById(contractProductGroupLink.getContractProductGroupLinkId())) {
                errorList.add("ContractProductGroupLink not found");
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
    public ValidateObject findById(ContractProductGroupLink contractProductGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractProductGroupLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractProductGroupLinkService.existsById(contractProductGroupLink.getContractProductGroupLinkId())) {
                errorList.add("ContractProductGroupLink not found");
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
