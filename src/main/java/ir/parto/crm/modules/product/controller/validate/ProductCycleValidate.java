package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.product.model.service.ProductCycleService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ProductCycleValidate implements ValidateInterface<ProductCycle> {
    private ProductCycleService productCycleService;

    @Autowired
    public ProductCycleValidate(ProductCycleService productCycleService) {
        this.productCycleService = productCycleService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductCycle productCycle) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCycle == null) {
            errorList.add("object is null");
        } else {
            if (productCycle.getTitle() == null || productCycle.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            }

            if ((productCycle.getDays() == null || productCycle.getDays() == 0) && (productCycle.getMonth() == null || productCycle.getMonth() == 0)) {
                errorList.add("Days or Month is required");
            }

            if (productCycle.getPaidType() == null || (productCycle.getPaidType() != 1 && productCycle.getPaidType() != 2)) {
                errorList.add("PaidType is required and must be 1 or 2");
            }

            if (productCycle.getOfficialType() == null || (productCycle.getOfficialType() != 1 && productCycle.getOfficialType() != 2)) {
                errorList.add("OfficialType is required and must be 1 or 2");
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
    public ValidateObject validateUpdateItem(ProductCycle productCycle) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCycle == null) {
            errorList.add("object is null");
        } else {
            if (!this.productCycleService.existsById(productCycle.getProductCycleId())) {
                errorList.add("ProductCycle not found");
            }

            if (productCycle.getTitle() == null || productCycle.getTitle().isEmpty()) {
                errorList.add("Title is required");
            }

            if ((productCycle.getDays() == null || productCycle.getDays() == 0) && (productCycle.getMonth() == null || productCycle.getMonth() == 0)) {
                errorList.add("Days or Month is required");
            }

            if (productCycle.getPaidType() == null || (productCycle.getPaidType() != 1 && productCycle.getPaidType() != 2)) {
                errorList.add("PaidType is required and must be 1 or 2");
            }

            if (productCycle.getOfficialType() == null || (productCycle.getOfficialType() != 1 && productCycle.getOfficialType() != 2)) {
                errorList.add("OfficialType is required and must be 1 or 2");
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
    public ValidateObject deleteItem(ProductCycle productCycle) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCycle == null) {
            errorList.add("object is null");
        } else {
            if (!this.productCycleService.existsById(productCycle.getProductCycleId())) {
                errorList.add("Product Cycle Id not defined");
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
    public ValidateObject findOne(ProductCycle productCycle) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCycle == null) {
            errorList.add("object is null");
        } else {
            if (!this.productCycleService.existsById(productCycle.getProductCycleId())) {
                errorList.add("Product Cycle Id not defined");
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
    public ValidateObject findById(ProductCycle productCycle) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCycle == null) {
            errorList.add("object is null");
        } else {
            if (!this.productCycleService.existsById(productCycle.getProductCycleId())) {
                errorList.add("Product Cycle Id not defined");
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
