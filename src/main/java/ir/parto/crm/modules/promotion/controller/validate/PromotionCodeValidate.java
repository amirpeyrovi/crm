package ir.parto.crm.modules.promotion.controller.validate;

import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.modules.promotion.model.service.PromotionCodeService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PromotionCodeValidate implements ValidateInterface<PromotionCode> {
    private PromotionCodeService promotionCodeService;
    private ClientService clientService;

    @Autowired
    public PromotionCodeValidate(PromotionCodeService promotionCodeService, ClientService clientService) {
        this.promotionCodeService = promotionCodeService;
        this.clientService = clientService;
    }

    @Override
    public ValidateObject validateAddNewItem(PromotionCode promotionCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCode == null) {
            errorList.add("PromotionCode object is null");
        } else {
            if (promotionCode.getClient() != null && !this.clientService.existsById(promotionCode.getClient().getClientId())) {
                errorList.add("Client Id not defined");
            }

            if (promotionCode.getPromotionCode() == null || promotionCode.getPromotionCode().isEmpty()) {
                errorList.add("PromotionCode is required");
            }

            if (promotionCode.getPromotionName() == null || promotionCode.getPromotionName().isEmpty()) {
                errorList.add("PromotionName is required");
            }

            if (promotionCode.getValue() == null || promotionCode.getValue() == 0) {
                errorList.add("PromotionName is required");
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
    public ValidateObject validateUpdateItem(PromotionCode promotionCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCode == null) {
            errorList.add("PromotionCode object is null");
        } else {
            if (!this.promotionCodeService.existsById(promotionCode.getPromotionCodeId())) {
                errorList.add("PromotionCode Id not defined");
            }

            if (promotionCode.getClient() != null && !this.promotionCodeService.existsById(promotionCode.getClient().getClientId())) {
                errorList.add("Client Id not defined");
            }

            if (promotionCode.getPromotionCode() == null || promotionCode.getPromotionCode().isEmpty()) {
                errorList.add("PromotionCode is required");
            }

            if (promotionCode.getPromotionName() == null || promotionCode.getPromotionName().isEmpty()) {
                errorList.add("PromotionName is required");
            }

            if (promotionCode.getValue() == null || promotionCode.getValue() == 0) {
                errorList.add("PromotionName is required");
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
    public ValidateObject deleteItem(PromotionCode promotionCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCode == null) {
            errorList.add("PromotionCode object is null");
        } else {
            if (!this.promotionCodeService.existsById(promotionCode.getPromotionCodeId())) {
                errorList.add("PromotionCode Id not defined");
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
    public ValidateObject findOne(PromotionCode promotionCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCode == null) {
            errorList.add("PromotionCode object is null");
        } else {
            if (!this.promotionCodeService.existsById(promotionCode.getPromotionCodeId())) {
                errorList.add("PromotionCode Id not defined");
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
    public ValidateObject findById(PromotionCode promotionCode) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCode == null) {
            errorList.add("PromotionCode object is null");
        } else {
            if (!this.promotionCodeService.existsById(promotionCode.getPromotionCodeId())) {
                errorList.add("PromotionCode Id not defined");
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
