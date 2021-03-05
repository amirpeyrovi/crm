package ir.parto.crm.modules.contract.controller.validate;

import ir.parto.crm.modules.contract.model.entity.ContractGroup;
import ir.parto.crm.modules.contract.model.service.ContractGroupService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ContractGroupValidate implements ValidateInterface<ContractGroup> {
    private ContractGroupService contractGroupService;

    @Autowired
    public ContractGroupValidate(ContractGroupService contractGroupService) {
        this.contractGroupService = contractGroupService;
    }


    @Override
    public ValidateObject validateAddNewItem(ContractGroup contractGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractGroup == null) {
            errorList.add("object is null");
        } else {
            if (contractGroup.getTitle() == null || contractGroup.getTitle().trim().isEmpty() ) {
                errorList.add("Title is required");
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
    public ValidateObject validateUpdateItem(ContractGroup contractGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractGroup == null) {
            errorList.add("object is null");
        } else {
            if(!this.contractGroupService.existsById(contractGroup.getContractGroupId())){
                errorList.add("ContarctGroup not define");
            }else {
                if (contractGroup.getTitle() != null && contractGroup.getTitle().trim().isEmpty()) {
                    errorList.add("Title is required");
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
    public ValidateObject deleteItem(ContractGroup contractGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractGroup == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractGroupService.existsById(contractGroup.getContractGroupId())) {
                errorList.add("ContractGroup not found");
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
    public ValidateObject findOne(ContractGroup contractGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractGroup == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractGroupService.existsById(contractGroup.getContractGroupId())) {
                errorList.add("ContractGroup not found");
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
    public ValidateObject findById(ContractGroup contractGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (contractGroup == null) {
            errorList.add("object is null");
        } else {
            if (!this.contractGroupService.existsById(contractGroup.getContractGroupId())) {
                errorList.add("ContractGroup not found");
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
